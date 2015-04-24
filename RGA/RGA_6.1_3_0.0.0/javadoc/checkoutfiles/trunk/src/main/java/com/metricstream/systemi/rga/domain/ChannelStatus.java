package com.metricstream.systemi.rga.domain;

/**
 * Channel Status Pojo
 * Created by munavar.basha on 7/8/2014.
 */
public enum ChannelStatus {

	/** The active. */
	ACTIVE(1),  /** The inactive. */
	INACTIVE(2);

	/** The type. */
	private final int type;

	/**
	 * Instantiates a new channel status.
	 *
	 * @param type the type
	 */
	private ChannelStatus(int type)
	{
		this.type = type;
	}

	/**
	 * Gets the channel type value.
	 *
	 * @return the channel type value
	 */
	public int getChannelTypeValue()
	{
		return this.type;
	}
}
