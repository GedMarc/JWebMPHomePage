package com.jwebmp.examples.demos.homepage.display.admin.management;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.entityassist.CoreEntity;
import com.jwebmp.plugins.bootstrap4.cards.BSCardEvents;

public class GoToEntityEditScreenEvent
		extends ClickAdapter
		implements BSCardEvents
{
	private Class<? extends CoreEntity> entityClass;

	GoToEntityEditScreenEvent()
	{
		super(null);
	}

	public GoToEntityEditScreenEvent(Class<? extends CoreEntity> entityClass, ComponentHierarchyBase component)
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
			//EntityEditScreen editScreen = new EntityEditScreen(clazz);
			//	response.addComponent(editScreen);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		call.getParameters();
	}


}
