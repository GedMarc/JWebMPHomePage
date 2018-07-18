package com.jwebmp.examples.demos.homepage.display.demos.display.metro;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.metrojs.JQMetroOptions;
public class MetroJSDemoScreen
		extends PluginDemoScreen
{
	public MetroJSDemoScreen()
	{
		super("JQ Metro", "Display", "Metro Tiles");
		addOptionsBrowser(new JQMetroOptions());
	}
}
