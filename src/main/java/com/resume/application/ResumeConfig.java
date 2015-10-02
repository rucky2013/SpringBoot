package com.resume.application;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.resume.factory.CacheConfigFactory;
import com.resume.resource.ResumeResource;

/**
 * All basic configuration needed by our resume application to work.
 */
@Configuration
@EnableAutoConfiguration
@EnableCaching
@EnableWebMvc
@ImportResource("classpath:spring-config.xml")
@Component
public class ResumeConfig extends ResourceConfig {

    public ResumeConfig() {
        register(ResumeResource.class);// register our rest resource with container.
    }

    /**
     * Register cache manager with Spring.
     * 
     * @return
     */
    @Bean
    public CacheManager getCacheManager() {
        return CacheConfigFactory.getCacheConfig().getCacheManager();
    }

    /**
     * Factory which will give away the cache manager according to type selected.
     * 
     * @return
     */
    @Bean
    @SuppressWarnings("rawtypes")
    public FactoryBean getCacheFactory() {
        return CacheConfigFactory.getCacheConfig().getFactoryBean();
    }
}
