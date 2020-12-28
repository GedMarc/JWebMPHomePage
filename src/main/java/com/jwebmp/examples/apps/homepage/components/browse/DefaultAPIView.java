package com.jwebmp.examples.apps.homepage.components.browse;

import com.jwebmp.core.base.html.DivSimple;

public class DefaultAPIView
		extends DivSimple<DefaultAPIView>
{
	private final String pluginName;
	
	public DefaultAPIView(String pluginName)
	{
		this.pluginName = pluginName;
		add(new PluginModulePart(pluginName));
	}
}
