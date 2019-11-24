package com.jwebmp.examples.demos.homepage.entities;

import com.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.RightBarActivityBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RightBarActivity
		extends CoreEntity<RightBarActivity, RightBarActivityBuilder, Integer>
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rightBarActivityID;

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
		return getRightBarActivityID();
	}

	@Override
	public RightBarActivity setId(Integer id)
	{
		setRightBarActivityID(id);
		return this;
	}

	public Integer getRightBarActivityID()
	{
		return rightBarActivityID;
	}

	public void setRightBarActivityID(Integer rightBarActivityID)
	{
		this.rightBarActivityID = rightBarActivityID;
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
