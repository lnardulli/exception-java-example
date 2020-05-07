package com.exception.demo.model.exceptions;

import com.exception.demo.model.Young;

/**
 * Explain
 */

public class YoungAgeException extends RuntimeException {

    public static YoungAgeException of(Young young) {
        return new YoungAgeException(young);
    }

    /**
     * Explain deference between static and private
     * @param customMessage
     */
    public YoungAgeException(String customMessage) {
        super(customMessage);
    }

    /**
     * Explain
     * @param young
     */
    private YoungAgeException(Young young) {
        super(String.format("Young %s with age %d is considered under adult age.",
                young.getName(),
                young.getAge()));
    }
}
