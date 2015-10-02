package com.resume.exception;

/**
 * Exception throws when there are issues around initialising the resume application.
 */
public class RequestExecutionException extends RuntimeException {

    private static final long serialVersionUID = -1095482579065582014L;

    /**
     * Exception when we face any issue around initialising resume application.
     * 
     * @param message
     * @param throwable
     */
    public RequestExecutionException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
