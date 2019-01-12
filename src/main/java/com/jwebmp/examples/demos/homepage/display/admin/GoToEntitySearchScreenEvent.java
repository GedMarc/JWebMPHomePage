package com.jwebmp.examples.demos.homepage.display.admin;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.entityassist.CoreEntity;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

public class GoToEntitySearchScreenEvent
		extends ClickAdapter<GoToEntitySearchScreenEvent>
		implements BSCardEvents
{
	private Class<? extends CoreEntity> entityClass;

	GoToEntitySearchScreenEvent()
	{
		super(null);
	}

	public GoToEntitySearchScreenEvent(Class<? extends CoreEntity> entityClass, ComponentHierarchyBase component)
	{
		super(component);
		this.entityClass = entityClass;
		setID(entityClass.getCanonicalName()
		                 .replace('.', '_'));
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		String classToBuild = call.getEventId();
		classToBuild = classToBuild.replace('_', '.');
		Class clazz;
		try
		{
			clazz = Class.forName(classToBuild);
			//EntitySearchScreen searchScreen = new EntitySearchScreen(clazz);
			//response.addComponent(searchScreen);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		call.getParameters();
	}
}
