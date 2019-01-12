package com.jwebmp.examples.demos.homepage.components.display;

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
