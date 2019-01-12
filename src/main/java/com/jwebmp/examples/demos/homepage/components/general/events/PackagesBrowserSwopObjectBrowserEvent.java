package com.jwebmp.examples.demos.homepage.components.general.events;

import com.jwebmp.core.Component;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.examples.demos.homepage.components.general.ObjectBrowser;
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
			JSTreeNodeSelectedEventOptions selected = new JSTreeNodeSelectedEventOptions<>().From(call.getValue()
			                                                                                          .getData(), JSTreeNodeSelectedEventOptions.class);
			String clazzString = selected.getSelected()
			                             .getText();
			try
			{
				Class<?> clazz = Class.forName(clazzString);
				ObjectBrowser browser = new ObjectBrowser(clazz, "pluginObjectBrowser");
				browser.addStyle("height:100%");
				response.addComponent(browser);
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
