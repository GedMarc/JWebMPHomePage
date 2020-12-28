package com.jwebmp.examples.apps.homepage.events;

import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
//import com.jwebmp.examples.apps.homepage.components.SwapContainer;
import com.jwebmp.examples.apps.homepage.components.SwapContainer;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.softhistorychange.SoftHistoryChangeAdapter;
import lombok.Getter;

public class SwapScreenEvent extends SoftHistoryChangeAdapter<SwapScreenEvent>
{
	@Getter
	private SwapContainer container;
	
	/**
	 * Ajax Receiver Constructor
	 */
	public SwapScreenEvent()
	{
		super(null, "");
	}
	
	public SwapScreenEvent(SwapContainer container, ComponentHierarchyBase component)
	{
		super(component, container.getSwapScreen()
		                          .getClassCanonicalName());
		this.container = container;
		setID(container.getSwapScreen()
		               .getClassCanonicalName());
	}
	
	@Override
	public void onUrlChange(AjaxCall<?> call, AjaxResponse<?> response)
	{
		try
		{
			SwapScreen<?> e = (SwapScreen<?>) GuiceContext.get(Class.forName(call.getEventId()
			                                                                     .replace("_", ".")));
			e.addFeature(getFeature().setQueryParameters("p=" + call.getEventId()));
			response.addComponent(new SwapContainer(e));
		}
		catch (ClassNotFoundException classNotFoundException)
		{
			classNotFoundException.printStackTrace();
		}
	}
}
