package com.jwebmp.examples.demos.homepage.display.menu;

import com.jwebmp.Page;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.OuterLayout;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.jqlayout.enumerations.JQLayoutArea;
import com.jwebmp.plugins.jqlayout.events.JQLayoutSlideCloseLayoutDivFeature;
import com.jwebmp.plugins.softhistorychange.SoftHistoryChangeAdapter;
import lombok.extern.java.Log;

import java.util.logging.Level;

import static com.jwebmp.utilities.StaticStrings.*;

@Log
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
			Page page = GuiceContext.getInstance(Page.class);
			if (page.isMobileOrSmartTablet())
			{
				OuterLayout layout = GuiceContext.getInstance(OuterLayout.class);
				JQLayoutSlideCloseLayoutDivFeature westClose = layout.createSlideCloseFeature(JQLayoutArea.West);
				JQLayoutSlideCloseLayoutDivFeature eastClose = layout.createSlideCloseFeature(JQLayoutArea.East);
				response.getFeatures()
				        .add(westClose);
				response.getFeatures()
				        .add(eastClose);
			}
			DisplayScreens screens = DisplayScreens.valueOf(screen);
			setQueryParameters("p=" + screens.name());
			DisplayScreen<?> screenCreated = GuiceContext.getInstance(screens.getScreen());
			response.addComponent(screenCreated);
		}
		catch (IllegalArgumentException iae)
		{
			String id = getID();
			id = id.replace(CHAR_UNDERSCORE, CHAR_DOT);
			try
			{
				Class clazz = Class.forName(id);
				if (DisplayScreen.class.isAssignableFrom(clazz))
				{
					DisplayScreen screenCreated = (DisplayScreen) GuiceContext.getInstance(clazz);
					response.addComponent(screenCreated);
				}
				setQueryParameters("p=" + id);
			}
			catch (ClassNotFoundException e)
			{
				log.log(Level.SEVERE, "Unable to find class [" + e.getMessage() + "]", e);
			}
		}
	}
}
