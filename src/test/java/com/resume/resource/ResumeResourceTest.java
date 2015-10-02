package com.resume.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import com.resume.application.ResumeConfig;
import com.resume.model.User;
import com.resume.model.UserLanguage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ResumeConfig.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
@DirtiesContext
public class ResumeResourceTest {

    private final RestTemplate restTemplate = new TestRestTemplate();
    @Autowired
    private CacheManager cacheManager;

    private void assertCacheData() {
        final Cache cache = cacheManager.getCache("usercache");
        Assert.assertNotNull(cache);
        Assert.assertNotNull(cache.get("DracoBlue"));
        User user = (User) cache.get("DracoBlue").get();
        Assert.assertEquals(user.getLogin(), "DracoBlue");
        Assert.assertNull(cache.get("NonExistingUser"));
    }

    @Test
    public void testGetUserResource() {
        final ResponseEntity<User> entity = restTemplate.getForEntity("http://localhost:9000/resume/user/{userName}", User.class, "DracoBlue");
        assertTrue(entity.getStatusCode().is2xxSuccessful());
        assertEquals(entity.getBody().getLogin(), "DracoBlue");
        assertCacheData();
    }

    @Test
    public void testGetRepositoryResource() {
        ResponseEntity<List<UserLanguage>> entity = restTemplate.exchange("http://localhost:9000/resume/languages/{userName}", HttpMethod.GET, null, new ParameterizedTypeReference<List<UserLanguage>>() {}, "DracoBlue");
        assertTrue(entity.getStatusCode().is2xxSuccessful());
        assertTrue(entity.getBody().size() > 0);
        entity = restTemplate.exchange("http://localhost:9000/resume/languages/{userName}", HttpMethod.GET, null, new ParameterizedTypeReference<List<UserLanguage>>() {}, "DracoBlue");// should
                                                                                                                                                                                        // goto
                                                                                                                                                                                        // cache
        assertTrue(entity.getStatusCode().is2xxSuccessful());
        assertTrue(entity.getBody().size() > 0);
    }
}
