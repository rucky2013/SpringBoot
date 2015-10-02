package com.resume.model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Serializable {

    private static final long serialVersionUID = -8585056291912101887L;
    private int following;
    private int followers;
    @JsonProperty("updated_at")
    private Date updatedAt;
    private String location;
    @JsonProperty("public_repos")
    private int publicRepoCount;
    private String company;
    @JsonProperty("created_at")
    private Date createdAt;
    private String login;
    @JsonProperty("blog")
    private String blogUrl;
    @JsonProperty("repos_url")
    private String reposUrl;
    @JsonProperty("public_gists")
    private int publicGist;
    private String userType;

    public User() {}

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPublicRepoCount() {
        return publicRepoCount;
    }

    public void setPublicRepoCount(int publicRepoCount) {
        this.publicRepoCount = publicRepoCount;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public int getPublicGist() {
        return publicGist;
    }

    public void setPublicGist(int publicGist) {
        this.publicGist = publicGist;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User [following=" + following + ", followers=" + followers + ", updatedAt=" + updatedAt + ", location=" + location + ", publicRepoCount=" + publicRepoCount + ", company=" + company + ", createdAt=" + createdAt + ", login=" + login + ", blogUrl=" + blogUrl + ", reposUrl=" + reposUrl
                        + ", publicGist=" + publicGist + ", userType=" + userType + "]";
    }
}
