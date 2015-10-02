package com.resume.dao;

import com.resume.model.User;

/**
 * Fetch user related data.
 */
public interface UserDao {

    /**
     * Get all user related data on github based on user name supplied.
     * 
     * @param userName
     * @return
     */
    public User getUser(String userName);
}
