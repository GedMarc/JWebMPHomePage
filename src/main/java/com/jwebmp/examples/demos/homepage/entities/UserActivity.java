package com.jwebmp.examples.demos.homepage.entities;

import com.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.UserActivityBuilder;
import com.jwebmp.examples.demos.homepage.entities.enumerations.UserActivityGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity(name = "UserActivity")
@Table(name = "UserActivity")
public class UserActivity
		extends CoreEntity<UserActivity, UserActivityBuilder, Long>
{


	@Id
	@Column(name = "UserActivityID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UserActivityID;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1,
			max = 100)
	@Column(nullable = false,
			length = 100,
			name = "UserActivityGruop")
	@Enumerated(value = EnumType.STRING)
	private UserActivityGroup activityGroup;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1,
			max = 150)
	@Column(nullable = false,
			length = 100,
			name = "Title")
	private String title;

	@Basic(optional = false)
	@Null
	@Size(min = 1,
			max = 255)
	@Column(nullable = true,
			length = 255,
			name = "ImageURL")
	private String imageUrl;

	@Column(name = "ReadMoreURL")
	private String readMoreUrl;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1)
	@Column(nullable = false,
			name = "Activity")
	private String activity;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1)
	@Column(nullable = false,
			name = "JSON")
	private String json;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1)
	@Column(nullable = false,
			name = "ActivityDescription")
	private String description;


	public UserActivity()
	{
	}

	@Override
	public Long getId()
	{
		return getUserActivityID();
	}

	@Override
	public UserActivity setId(Long id)
	{
		setUserActivityID(id);
		return this;
	}

	public Long getUserActivityID()
	{
		return UserActivityID;
	}

	public void setUserActivityID(Long userActivityID)
	{
		UserActivityID = userActivityID;
	}

	public UserActivity create()
	{
		builder().setRunDetached(true)
		         .persist(this);
		return this;
	}

	public UserActivityGroup getActivityGroup()
	{
		return activityGroup;
	}

	public void setActivityGroup(UserActivityGroup activityGroup)
	{
		this.activityGroup = activityGroup;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getReadMoreUrl()
	{
		return readMoreUrl;
	}

	public void setReadMoreUrl(String readMoreUrl)
	{
		this.readMoreUrl = readMoreUrl;
	}

	public String getActivity()
	{
		return activity;
	}

	public void setActivity(String activity)
	{
		this.activity = activity;
	}

	public String getJson()
	{
		return json;
	}

	public void setJson(String json)
	{
		this.json = json;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
