package com.jwebmp.examples.demos.homepage.display.menu;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import za.co.mmagon.guiceinjection.GuiceContext;
import za.co.mmagon.plugins.softhistorychange.SoftHistoryChangeAdapter;

public class ChangeScreenEvent
		extends SoftHistoryChangeAdapter
{
	public ChangeScreenEvent()
	{
		this(null, "");
	}

	/**
	 * Performs a click
	 *
	 * @param component
	 * 		The component this click is going to be acting on
	 * @param queryParameterString
	 */
	public ChangeScreenEvent(ComponentHierarchyBase component, String queryParameterString)
	{
		super(component, queryParameterString);
	}

	@Override
	public void onUrlChange(AjaxCall call, AjaxResponse response)
	{
		String screen = call.getEventId();
		try
		{
			DisplayScreens screens = DisplayScreens.valueOf(screen);
			setQueryParameters("p=" + screens.name());
			DisplayScreen<?> screenCreated = GuiceContext.getInstance(screens.getScreen());
			response.addComponent(screenCreated);
		}
		catch (IllegalArgumentException iae)
		{
			String id = getID();
			id = id.replace('_', '.');
			try
			{
				Class clazz = Class.forName(id);
				if (DisplayScreen.class.isAssignableFrom(clazz))
				{
					DisplayScreen screenCreated = (DisplayScreen) GuiceContext.getInstance(clazz);
					response.addComponent(screenCreated);
				}
			}
			catch (ClassNotFoundException e)
			{

			}
		}
	}
}
