package com.metricstream.systemi.rga.exception;

/**
 * Grci Exception Class
 * Created by munavar.basha on 7/8/2014.
 */
public class GrciException extends Exception {

    /**
     * Instantiates a new grci exception.
     */
    public GrciException() {}

    /**
     * Instantiates a new grci exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public GrciException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Instantiates a new grci exception.
     *
     * @param message the message
     */
    public GrciException(String message)
    {
        super(message);
    }

    /**
     * Instantiates a new grci exception.
     *
     * @param cause the cause
     */
    public GrciException(Throwable cause)
    {
        super(cause);
    }
}
