package com.jwebmp.examples.demos.homepage.components.events;

import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.ajax.AjaxCall;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.plugins.softhistorychange.SoftHistoryChangeAdapter;

public class DefaultClick
		extends SoftHistoryChangeAdapter
{
	public DefaultClick()
	{
		this(null, null);

	}

	public DefaultClick(ComponentHierarchyBase component, String queryParameterString)
	{
		super(component, queryParameterString);
	}

	public DefaultClick(ComponentHierarchyBase component)
	{
		this(component, null);
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		//to be overriden
	}

	@Override
	public void onUrlChange(AjaxCall call, AjaxResponse response)
	{
		onClick(call, response);
	}
}
