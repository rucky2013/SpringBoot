package com.resume.factory;

import com.resume.model.User;

public class UserStatusFactory {

    private static final double COEF_REPOS = 2;
    private static final double COEF_GISTS = 0.25;
    private static final double COEF_FOLLOWERS = 0.5;
    private static final double COEF_FOLLOWING = 0.25;
    private static final double FIRST_STEP = 0;
    private static final double SECOND_STEP = 5;
    private static final double THIRD_STEP = 20;
    private static final double FOURTH_STEP = 50;
    private static final double FIFTH_STEP = 150;

    /**
     * Based on user details, identify what type of user it is and how active one is on github.
     * 
     * @param user
     * @return
     */
    public static String getUserStatus(final User user) {
        double statusScore = user.getPublicRepoCount() * COEF_REPOS + user.getPublicGist() * COEF_GISTS + user.getFollowers() * COEF_FOLLOWERS + user.getFollowing() * COEF_FOLLOWING;
        if (statusScore > FIFTH_STEP) {
            return "Passionate GitHub user";
        } else if (statusScore > FOURTH_STEP) {
            return "Enthusiastic GitHub user";
        } else if (statusScore > THIRD_STEP) {
            return "Advanced GitHub user";
        } else if (statusScore > SECOND_STEP) {
            return "Regular GitHub user";
        } else if (statusScore > FIRST_STEP) {
            return "Newbie GitHub user";
        }
        return "Inactive GitHub user";
    }
}
