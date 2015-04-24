package com.metricstream.systemi.rga.exception;

/**
 * Illegal Channel State Exception Class
 * Created by munavar.basha on 7/8/2014.
 */
public class IllegalChannelStateException extends GrciException {

    /**
     * Instantiates a new illegal channel state exception.
     */
    public IllegalChannelStateException() {}

    /**
     * Instantiates a new illegal channel state exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public IllegalChannelStateException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Instantiates a new illegal channel state exception.
     *
     * @param message the message
     */
    public IllegalChannelStateException(String message)
    {
        super(message);
    }

    /**
     * Instantiates a new illegal channel state exception.
     *
     * @param cause the cause
     */
    public IllegalChannelStateException(Throwable cause)
    {
        super(cause);
    }
}
