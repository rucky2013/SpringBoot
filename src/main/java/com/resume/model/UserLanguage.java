package com.resume.model;

public class UserLanguage implements Comparable<UserLanguage> {

    private String language;
    private double percent;
    private int count;

    public UserLanguage() {}

    public UserLanguage(String language, double percent, int count) {
        this.language = language;
        this.percent = percent;
        this.count = count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserLanguage [language=" + language + ", percent=" + percent + ", count=" + count + "]";
    }

    @Override
    public int compareTo(UserLanguage thatUserLanguage) {
        return Integer.compare(thatUserLanguage.getCount(), this.count);
    }
}
