package com.jwebmp.examples.demos.homepage.display.menu;

import com.jwebmp.core.Feature;
import com.jwebmp.core.Page;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.OuterLayout;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.guicedee.guicedinjection.GuiceContext;
import com.guicedee.logger.LogFactory;
import com.jwebmp.plugins.jqlayout.enumerations.JQLayoutArea;
import com.jwebmp.plugins.jqlayout.events.JQLayoutSlideCloseLayoutDivFeature;
import com.jwebmp.plugins.softhistorychange.SoftHistoryChangeAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.guicedee.guicedinjection.json.StaticStrings.CHAR_DOT;
import static com.guicedee.guicedinjection.json.StaticStrings.CHAR_UNDERSCORE;
import static com.jwebmp.core.utilities.StaticStrings.*;

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
	public void preConfigure()
	{
		super.preConfigure();
	}

	@Override
	public void onUrlChange(AjaxCall call, AjaxResponse response)
	{
		String screen = call.getEventId();
		try
		{
			Page<?> page = GuiceContext.get(Page.class);
			if (page.isMobileOrSmartTablet())
			{
				OuterLayout layout = GuiceContext.get(OuterLayout.class);
				JQLayoutSlideCloseLayoutDivFeature westClose = layout.createSlideCloseFeature(JQLayoutArea.West);
				JQLayoutSlideCloseLayoutDivFeature eastClose = layout.createSlideCloseFeature(JQLayoutArea.East);
				response.getFeatures()
				        .add(westClose);
				response.getFeatures()
				        .add(eastClose);
			}
			DisplayScreens screens = DisplayScreens.valueOf(screen);
			setQueryParameters("p=" + screens.name());
			DisplayScreen<?> screenCreated = null;// GuiceContext.get(screens.getScreen());
			try
			{
				screenCreated = screens.getScreen()
				                       .getDeclaredConstructor()
				                       .newInstance();
				GuiceContext.inject()
				            .injectMembers(screenCreated);
			}
			catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
			{
				LogFactory.getLog(getClass())
				          .log(Level.SEVERE, "Unable to get screen", e);
			}
			if(screenCreated != null)
			response.addComponent(screenCreated);
			response.getFeatures()
			        .add(new Feature("TriggerWindowResize", screenCreated)
			        {
				        @Override
				        protected void assignFunctionsToComponent()
				        {
					        addQuery("$(window).trigger('resize');");
				        }
			        });

		}
		catch (IllegalArgumentException iae)
		{
			LogFactory.getLog("ChangeScreenEvent")
			          .log(Level.SEVERE, "Unable to load component", isJavascriptRenderedElsewhere());
			String id = getID();
			id = id.replace(CHAR_UNDERSCORE, CHAR_DOT);
			try
			{
				Class clazz = Class.forName(id);
				if (DisplayScreen.class.isAssignableFrom(clazz))
				{
					DisplayScreen screenCreated = (DisplayScreen) GuiceContext.get(clazz);
					response.addComponent(screenCreated);
				}
				setQueryParameters("p=" + id);
			}
			catch (ClassNotFoundException e)
			{
				Logger.getLogger("ChangeScreenEvent")
				      .log(Level.SEVERE, "Unable to find class [" + e.getMessage() + "]", e);
			}
		}
	}
}
