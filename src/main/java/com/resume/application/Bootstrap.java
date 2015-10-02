package com.resume.application;

import org.springframework.boot.SpringApplication;

/**
 * Instantiate and run the application.
 */
public class Bootstrap {

    /**
     * Entry point of resume application.
     * 
     * @param args - Would work even if you don't provide any arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(ResumeConfig.class, args);
    }
}
