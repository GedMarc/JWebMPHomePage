package com.jwebmp.examples.demos.homepage.entities;

import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.HomePageNotificationsBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HomePageNotifications
		extends CoreEntity<HomePageNotifications, HomePageNotificationsBuilder, Integer>
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer homePageNotificationsID;

	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String clazz;
	@Column
	private String icon;
	@Column
	private Date date;
	@Column
	private Boolean highlighted;

	@Override
	public Integer getId()
	{
		return getHomePageNotificationsID();
	}

	@Override
	public HomePageNotifications setId(Integer id)
	{
		setHomePageNotificationsID(id);
		return this;
	}

	public Integer getHomePageNotificationsID()
	{
		return homePageNotificationsID;
	}

	public void setHomePageNotificationsID(Integer homePageNotificationsID)
	{
		this.homePageNotificationsID = homePageNotificationsID;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getClazz()
	{
		return clazz;
	}

	public void setClazz(String clazz)
	{
		this.clazz = clazz;
	}

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Boolean getHighlighted()
	{
		return highlighted;
	}

	public void setHighlighted(Boolean highlighted)
	{
		this.highlighted = highlighted;
	}
}
