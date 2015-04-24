package com.metricstream.systemi.rga.domain;

/**
 * Source type Pojo
 * Created by munavar.basha on 7/8/2014.
 */
public enum SourceType {

    /** The email. */
    EMAIL(1),  /** The rss. */
  RSS(2),  /** The from structured content handler. */
  FROM_STRUCTURED_CONTENT_HANDLER(3);

    /** The type. */
    private final int type;

    /**
     * Instantiates a new source type.
     *
     * @param type the type
     */
    private SourceType(int type)
    {
        this.type = type;
    }

    /**
     * Gets the source type value.
     *
     * @return the source type value
     */
    public int getSourceTypeValue()
    {
        return this.type;
    }
}
