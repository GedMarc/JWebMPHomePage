package com.jwebmp.examples.demos.homepage.entities;

import com.jwebmp.examples.demos.homepage.entities.builders.HomePageNotificationsBuilder;
import lombok.Data;
import za.co.mmagon.entityassist.CoreEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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
}
