package com.resume.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.resume.model.UserLanguage;
import com.resume.model.UserRepository;

/**
 * Converts user repositories data type to language specific data type.
 */
public class LanguageConverter implements Converter<List<UserRepository>, List<UserLanguage>> {

    private final static String UNKNOWN_LANGUAGE = "Others";

    /**
     * Convert user repository to user language.
     */
    @Override
    public List<UserLanguage> convert(final List<UserRepository> userRepositories) {
        final Map<String, Integer> languageCountMap = new HashMap<String, Integer>();
        populateLanguages(languageCountMap, userRepositories);
        return getUserLanguages(languageCountMap, userRepositories.size());
    }

    private void populateLanguages(Map<String, Integer> languageCountMap, final List<UserRepository> userRepositories) {
        for (UserRepository userRepository : userRepositories) {
            String language = userRepository.getLanguage() == null ? UNKNOWN_LANGUAGE : userRepository.getLanguage();
            int languageCount = languageCountMap.getOrDefault(language, 0) + 1;
            languageCountMap.put(language, languageCount);
        }
    }

    private List<UserLanguage> getUserLanguages(Map<String, Integer> languageCountMap, double totalRepositories) {
        final List<UserLanguage> userLanguages = new ArrayList<UserLanguage>(languageCountMap.size());
        for (Entry<String, Integer> entry : languageCountMap.entrySet()) {
            userLanguages.add(new UserLanguage(entry.getKey(), entry.getValue() / totalRepositories, entry.getValue()));
        }
        Collections.sort(userLanguages);
        return userLanguages;
    }
}
