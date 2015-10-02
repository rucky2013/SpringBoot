package com.resume.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRepository implements Serializable {

    private static final long serialVersionUID = -5521395083168480849L;
    @JsonProperty("default_branch")
    private String defaultBranch;
    @JsonProperty("open_issues")
    private String openIssues;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String forks;
    @JsonProperty("updated_at")
    private String updatedAt;
    private String description;
    private String name;
    @JsonProperty("created_at")
    private String createdAt;
    private String language;
    @JsonProperty("open_issues_count")
    private String openIssuesCount;
    @JsonProperty("forks_count")
    private String forksCount;
    private String watchers;

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public String getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(String openIssues) {
        this.openIssues = openIssues;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(String openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public String getForksCount() {
        return forksCount;
    }

    public void setForksCount(String forksCount) {
        this.forksCount = forksCount;
    }

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }

    @Override
    public String toString() {
        return "Repository [defaultBranch=" + defaultBranch + ", openIssues=" + openIssues + ", htmlUrl=" + htmlUrl + ", forks=" + forks + ", updatedAt=" + updatedAt + ", description=" + description + ", name=" + name + ", createdAt=" + createdAt + ", language=" + language + ", openIssuesCount="
                        + openIssuesCount + ", forksCount=" + forksCount + ", watchers=" + watchers + "]";
    }
}
