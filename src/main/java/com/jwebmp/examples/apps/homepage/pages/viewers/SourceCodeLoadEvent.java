package com.jwebmp.examples.apps.homepage.pages.viewers;

import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.core.htmlbuilder.javascript.events.commandevent.PerformCommandEvent;
import lombok.extern.java.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

@Log
public class SourceCodeLoadEvent extends PerformCommandEvent
{
	private static final Map<String, Class<? extends IComponentHierarchyBase<?, ?>>> sourcesToRenderCache = new ConcurrentHashMap<>();
	
	public SourceCodeLoadEvent()
	{
		this(null);
	}
	
	public SourceCodeLoadEvent(IComponentHierarchyBase<?, ?> component)
	{
		super(component);
	}
	
	@Override
	public void perform(AjaxCall<?> call, AjaxResponse<?> response)
	{
		String clazzS = call.getComponentId()
		                    .replace('_', '.');
		if (!sourcesToRenderCache.containsKey(clazzS))
		{
			Class<? extends IComponentHierarchyBase<?, ?>> clazz = null;
			try
			{
				//noinspection unchecked
				clazz = (Class<? extends IComponentHierarchyBase<?, ?>>) Class.forName(clazzS);
				sourcesToRenderCache.put(clazzS, clazz);
			}
			catch (ClassNotFoundException e)
			{
				log.log(Level.SEVERE, "Cannot send through source", e);
			}
		}
		SourceCodeViewer scv = new SourceCodeViewer(sourcesToRenderCache.get(clazzS));
		scv.setID(call.getComponentId());
		response.addComponent(scv);
	}
}
