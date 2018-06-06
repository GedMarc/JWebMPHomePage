package com.jwebmp.examples.demos.homepage.entities;

import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.examples.demos.homepage.entities.builders.RightBarActivityBuilder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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
}
