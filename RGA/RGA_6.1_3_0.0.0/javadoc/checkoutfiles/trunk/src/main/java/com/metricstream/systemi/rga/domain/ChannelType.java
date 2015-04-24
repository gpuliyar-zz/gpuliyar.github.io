package com.metricstream.systemi.rga.domain;

/**
 * Channel Type Pojo
 * Created by munavar.basha on 7/8/2014.
 */
public enum ChannelType {

	/** The alert. */
	ALERT(1),  /** The structured content. */
	STRUCTURED_CONTENT(2);

	/** The type. */
	private final int type;

	/**
	 * Instantiates a new channel type.
	 *
	 * @param type the type
	 */
	private ChannelType(int type)
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
