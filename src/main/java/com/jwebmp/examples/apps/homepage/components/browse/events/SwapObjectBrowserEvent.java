package com.jwebmp.examples.apps.homepage.components.browse.events;

import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.examples.apps.homepage.components.browse.OptionsBrowser;

import static com.guicedee.services.jsonrepresentation.json.StaticStrings.CHAR_DOT;
import static com.guicedee.services.jsonrepresentation.json.StaticStrings.CHAR_UNDERSCORE;

public class SwapObjectBrowserEvent
		extends ClickAdapter<SwapObjectBrowserEvent>
{
	public SwapObjectBrowserEvent()
	{
		this(null);
	}
	
	public SwapObjectBrowserEvent(ComponentHierarchyBase component)
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
			ob.setID("optionsBrowser");
			response.addComponent(ob);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
