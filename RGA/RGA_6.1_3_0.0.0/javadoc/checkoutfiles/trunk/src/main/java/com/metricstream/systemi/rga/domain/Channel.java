package com.metricstream.systemi.rga.domain;

import java.util.Date;

/**
 * Channel Pojo
 * Created by munavar.basha on 7/8/2014.
 */
public class Channel {

	/** The Constant UNKNOWN. */
	public static final String UNKNOWN = "UNKNOWN";

	/** The name. */
	private String name;

	/** The id. */
	private String id;

	/** The type. */
	private ChannelType type = ChannelType.ALERT;

	/** The hdn channel id cnt. */
	private String hdnChannelIdCnt = "0";

	/** The status. */
	private ChannelStatus status = ChannelStatus.ACTIVE;

	/** The dd status flag. */
	private String ddStatusFlag = "Y";

	/** The cust field2. */
	private String custField2 = "2";

	/** The save attachments. */
	private String saveAttachments;

	/** The dd object type. */
	private final String ddObjectType = "MS_RGA_CHANNEL_DTLS";

	/** The process instance id. */
	private long processInstanceId;

	/** The instance id. */
	private long instanceId;

	/** The created on. */
	private Date createdOn;

	/** The created by. */
	private String createdBy = "SYSTEMI";

	/** The modified on. */
	private Date modifiedOn;

	/** The modified by. */
	private String modifiedBy = "SYSTEMI";

	/** The dd process code. */
	private String ddProcessCode = "MS_RGA_CHANNEL_DTLS";

	/** The server. */
	private String server;

	/** The sourceid. */
	private String sourceid;

	/**
	 * Instantiates a new channel.
	 */
	public Channel() {}

	/**
	 * Instantiates a new channel.
	 *
	 * @param name the name
	 */
	public Channel(String name)
	{
		this.name = name;
	}

	/**
	 * Instantiates a new channel.
	 *
	 * @param name the name
	 * @param id the id
	 * @param type the type
	 * @param server the server
	 */
	public Channel(String name, String id, ChannelType type, String server)
	{
		this.name = name;
		this.id = id;
		this.type = type;
		this.server = server;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId()
	{
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ChannelType getType()
	{
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(ChannelType type)
	{
		this.type = type;
	}

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
	 * Gets the hdn channel id cnt.
	 *
	 * @return the hdn channel id cnt
	 */
	public String getHdnChannelIdCnt()
	{
		return this.hdnChannelIdCnt;
	}

	/**
	 * Sets the hdn channel id cnt.
	 *
	 * @param hdnChannelIdCnt the new hdn channel id cnt
	 */
	public void setHdnChannelIdCnt(String hdnChannelIdCnt)
	{
		this.hdnChannelIdCnt = hdnChannelIdCnt;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public ChannelStatus getStatus()
	{
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(ChannelStatus status)
	{
		this.status = status;
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

	/**
	 * Gets the cust field2.
	 *
	 * @return the cust field2
	 */
	public String getCustField2()
	{
		return this.custField2;
	}

	/**
	 * Sets the cust field2.
	 *
	 * @param custField2 the new cust field2
	 */
	public void setCustField2(String custField2)
	{
		this.custField2 = custField2;
	}

	/**
	 * Gets the save attachments.
	 *
	 * @return the save attachments
	 */
	public String getSaveAttachments()
	{
		return this.saveAttachments;
	}

	/**
	 * Sets the save attachments.
	 *
	 * @param saveAttachments the new save attachments
	 */
	public void setSaveAttachments(String saveAttachments)
	{
		this.saveAttachments = saveAttachments;
	}

	/**
	 * Gets the dd object type.
	 *
	 * @return the dd object type
	 */
	public String getDdObjectType()
	{
		return "MS_RGA_CHANNEL_DTLS";
	}

	/**
	 * Gets the process instance id.
	 *
	 * @return the process instance id
	 */
	public long getProcessInstanceId()
	{
		return this.processInstanceId;
	}

	/**
	 * Sets the process instance id.
	 *
	 * @param processInstanceId the new process instance id
	 */
	public void setProcessInstanceId(long processInstanceId)
	{
		this.processInstanceId = processInstanceId;
	}

	/**
	 * Gets the instance id.
	 *
	 * @return the instance id
	 */
	public long getInstanceId()
	{
		return this.instanceId;
	}

	/**
	 * Sets the instance id.
	 *
	 * @param instanceId the new instance id
	 */
	public void setInstanceId(long instanceId)
	{
		this.instanceId = instanceId;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	public Date getCreatedOn()
	{
		return this.createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
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
	 * Gets the modified on.
	 *
	 * @return the modified on
	 */
	public Date getModifiedOn()
	{
		return this.modifiedOn;
	}

	/**
	 * Sets the modified on.
	 *
	 * @param modifiedOn the new modified on
	 */
	public void setModifiedOn(Date modifiedOn)
	{
		this.modifiedOn = modifiedOn;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy()
	{
		return this.modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the dd process code.
	 *
	 * @return the dd process code
	 */
	public String getDdProcessCode()
	{
		return this.ddProcessCode;
	}

	/**
	 * Sets the dd process code.
	 *
	 * @param ddProcessCode the new dd process code
	 */
	public void setDdProcessCode(String ddProcessCode)
	{
		this.ddProcessCode = ddProcessCode;
	}

	/**
	 * Sets the sourceid.
	 *
	 * @param sourceid the new sourceid
	 */
	public void setSourceid(String sourceid){
		this.sourceid = sourceid;
	}

	/**
	 * Gets the sourceid.
	 *
	 * @return the sourceid
	 */
	public String getSourceid(){
		return this.sourceid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "Channel{name='" + this.name + '\'' + ", id='" + this.id + '\'' + ", type=" + this.type + ", hdnChannelIdCnt='" + this.hdnChannelIdCnt + '\'' + ", status=" + this.status + ", ddStatusFlag='" + this.ddStatusFlag + '\'' + ", custField2='" + this.custField2 + '\'' + ", saveAttachments='" + this.saveAttachments + '\'' + ", ddObjectType='" + "MS_RGA_CHANNEL_DTLS" + '\'' + ", processInstanceId=" + this.processInstanceId + ", instanceId=" + this.instanceId + ", createdOn=" + this.createdOn + ", createdBy='" + this.createdBy + '\'' + ", modifiedOn=" + this.modifiedOn + ", modifiedBy='" + this.modifiedBy + '\'' + ", ddProcessCode='" + this.ddProcessCode + '\'' + ", server='" + this.server + '\'' + '}';
	}

}
