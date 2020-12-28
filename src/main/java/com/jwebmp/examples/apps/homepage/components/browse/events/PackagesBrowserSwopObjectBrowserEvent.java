package com.jwebmp.examples.apps.homepage.components.browse.events;

import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.core.Component;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.examples.apps.homepage.components.browse.DisplayAPITabView;
import com.jwebmp.examples.apps.homepage.services.WebComponentsService;
import com.jwebmp.plugins.jstree.events.onselectedevent.JSTreeOnSelectedAdapter;
import com.jwebmp.plugins.jstree.options.selected.JSTreeNodeSelectedEventOptions;

public class PackagesBrowserSwopObjectBrowserEvent
		extends JSTreeOnSelectedAdapter
{
	public PackagesBrowserSwopObjectBrowserEvent()
	{
		this(null);
	}
	
	public PackagesBrowserSwopObjectBrowserEvent(Component component)
	{
		super(component);
	}
	
	@Override
	public void onSelected(AjaxCall call, AjaxResponse response)
	{
		try
		{
			JSTreeNodeSelectedEventOptions<?> selected = new JSTreeNodeSelectedEventOptions<>().From(call.getValue()
			                                                                                             .getData(), JSTreeNodeSelectedEventOptions.class);
			String clazzString = selected.getSelected()
			                             .getText();
			try
			{
				Class<?> clazz = Class.forName(clazzString);
				DisplayAPITabView tabs = GuiceContext.get(WebComponentsService.class)
				                                     .getTabViewDisplay(clazz);
				tabs.setID("displayapitabview");
				response.addComponent(tabs);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
