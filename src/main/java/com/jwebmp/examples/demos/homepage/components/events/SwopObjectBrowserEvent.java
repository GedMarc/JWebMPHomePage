package com.jwebmp.examples.demos.homepage.components.events;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.guicedinjection.GuiceContext;

import static com.jwebmp.core.utilities.StaticStrings.*;

public class SwopObjectBrowserEvent
		extends ClickAdapter
{
	public SwopObjectBrowserEvent()
	{
	}

	public SwopObjectBrowserEvent(ComponentHierarchyBase component)
	{
		super(component);
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		String screen = call.getEventId()
		                    .replace(CHAR_UNDERSCORE, CHAR_DOT);
		try
		{
			Class<? extends JavaScriptPart> clazz = (Class<? extends JavaScriptPart>) Class.forName(screen);
			OptionsBrowser ob = new OptionsBrowser(GuiceContext.get(clazz));
			ob.setID("pluginObjectBrowser");
			response.addComponent(ob);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
