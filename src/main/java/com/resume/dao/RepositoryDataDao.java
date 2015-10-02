package com.resume.dao;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.resume.model.UserRepository;

public class RepositoryDataDao implements RepositoryDao {

    private final RestTemplate restTemplate;
    private final static int MAX_RECORDS_PER_PAGE = 1000;
    private final static String GITHUB_REPOSITORY_URL = System.getProperty("resume.github.repository.url");
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public RepositoryDataDao(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = "repocache", key = "#userName")
    public List<UserRepository> getRepositories(final String userName) {
        int pageNumber = 1;
        final List<UserRepository> repositories = new ArrayList<UserRepository>();
        List<UserRepository> repositoryList = null;
        do {
            ResponseEntity<List<UserRepository>> rateResponse = restTemplate.exchange(GITHUB_REPOSITORY_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserRepository>>() {}, userName, pageNumber++, MAX_RECORDS_PER_PAGE);
            repositoryList = rateResponse.getBody();
            repositories.addAll(repositoryList);
        } while (repositoryList.size() != 0 && repositoryList.size() == MAX_RECORDS_PER_PAGE);
        logger.info("repository request for user {} got {} records.", userName, repositories.size());
        return repositories;
    }
}
