package com.resume.dao;

import java.util.List;
import com.resume.model.UserRepository;

/**
 * Dao to fetch user repositories.
 */
public interface RepositoryDao {

    /**
     * Get all the repositories associated with the user supplied as input.
     * 
     * @param userName
     * @return
     */
    public List<UserRepository> getRepositories(String userName);
}
