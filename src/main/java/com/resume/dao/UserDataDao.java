package com.resume.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;
import com.resume.model.User;

public class UserDataDao implements UserDao {

    private final RestTemplate restTemplate;
    private static final String GITHUB_USER_URL = System.getProperty("resume.github.userdata.url");
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public UserDataDao(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = "usercache", key = "#userName")
    // TODO We will need to refresh cache after every say 15 mins to incorporate any changes if any?
    // @EnableScheduling!
    public User getUser(final String userName) {
        final User user = restTemplate.getForEntity(GITHUB_USER_URL, User.class, userName).getBody();
        logger.info("user data request for user {} completed successfully.", userName);
        return user;
    }
}
