package com.jwebmp.examples.demos.homepage.entities;

import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.UserActivityBuilder;
import com.jwebmp.examples.demos.homepage.entities.enumerations.UserActivityGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "UserActivity")
@Table(name = "UserActivity")
@XmlRootElement
@Data
@EqualsAndHashCode(callSuper = false)
public class UserActivity
		extends CoreEntity<UserActivity, UserActivityBuilder, Long>
{

	private static final long serialVersionUID = 1L;

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

	@Basic(optional = false)
	@Null
	@Size(min = 1,
			max = 255)
	@Column(nullable = true,
			length = 255,
			name = "ReadMoreURL")
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

	public UserActivity create()
	{
		builder().setRunDetached(true)
		         .persist(this);
		return this;
	}
}
