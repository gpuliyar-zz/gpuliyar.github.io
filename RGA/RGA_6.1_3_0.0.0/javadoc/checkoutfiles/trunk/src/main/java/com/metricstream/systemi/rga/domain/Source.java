package com.metricstream.systemi.rga.domain;

/**
 * Source Pojo
 * Created by munavar.basha on 7/8/2014.
 */
public class Source {

    /** The server. */
    private String server;
    
    /** The src type. */
    private SourceType srcType;
    
    /** The src id. */
    private String srcId;
    
    /** The cust field6. */
    private String custField6;
    
    /** The created by. */
    private String createdBy;
    
    /** The channel id. */
    private String channelId;
    
    /** The dd status flag. */
    private String ddStatusFlag;

    /**
     * Gets the server.
     *
     * @return the server
     */
    public String getServer()
    {
        return this.server;
    }

    /**
     * Sets the server.
     *
     * @param server the new server
     */
    public void setServer(String server)
    {
        this.server = server;
    }

    /**
     * Gets the src type.
     *
     * @return the src type
     */
    public SourceType getSrcType()
    {
        return this.srcType;
    }

    /**
     * Sets the src type.
     *
     * @param srcType the new src type
     */
    public void setSrcType(SourceType srcType)
    {
        this.srcType = srcType;
    }

    /**
     * Gets the src id.
     *
     * @return the src id
     */
    public String getSrcId()
    {
        return this.srcId;
    }

    /**
     * Sets the src id.
     *
     * @param srcId the new src id
     */
    public void setSrcId(String srcId)
    {
        this.srcId = srcId;
    }

    /**
     * Gets the cust field6.
     *
     * @return the cust field6
     */
    public String getCustField6()
    {
        return this.custField6;
    }

    /**
     * Sets the cust field6.
     *
     * @param custField6 the new cust field6
     */
    public void setCustField6(String custField6)
    {
        this.custField6 = custField6;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy()
    {
        return this.createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    /**
     * Gets the channel id.
     *
     * @return the channel id
     */
    public String getChannelId()
    {
        return this.channelId;
    }

    /**
     * Sets the channel id.
     *
     * @param channelId the new channel id
     */
    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    /**
     * Gets the dd status flag.
     *
     * @return the dd status flag
     */
    public String getDdStatusFlag()
    {
        return this.ddStatusFlag;
    }

    /**
     * Sets the dd status flag.
     *
     * @param ddStatusFlag the new dd status flag
     */
    public void setDdStatusFlag(String ddStatusFlag)
    {
        this.ddStatusFlag = ddStatusFlag;
    }
}
