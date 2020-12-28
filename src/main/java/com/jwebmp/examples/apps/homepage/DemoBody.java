package com.jwebmp.examples.apps.homepage;

import com.jwebmp.examples.apps.homepage.components.SwapContainer;
import com.jwebmp.examples.apps.homepage.pages.LoadingPage;
import com.jwebmp.plugins.themes.mintontheme.MintonBody;
import com.jwebmp.plugins.themes.mintontheme.options.LayoutOptions;
import com.jwebmp.plugins.themes.mintontheme.options.LayoutSidebarOptions;
import com.jwebmp.plugins.themes.mintontheme.options.LayoutTopbarOptions;

import static com.jwebmp.plugins.themes.mintontheme.options.LayoutMenuPosition.fixed;
import static com.jwebmp.plugins.themes.mintontheme.options.LayoutMenuSize.$default;
import static com.jwebmp.plugins.themes.mintontheme.options.LayoutMode.dark;
import static com.jwebmp.plugins.themes.mintontheme.options.LayoutMode.light;
import static com.jwebmp.plugins.themes.mintontheme.options.LayoutWidths.fluid;

public class DemoBody
		extends MintonBody<DemoBody>
{
	
	public DemoBody()
	{
		setDataLayout(new LayoutOptions().setMode(light)
		                                 .setWidth(fluid)
		                                 .setMenuPosition(fixed)
		                                 .setSidebar(new LayoutSidebarOptions()
						                                 .setColor(light)
						                                 .setSize($default)
						                                 .setShowUser(true)
		                                            )
		                                 .setTopBar(new LayoutTopbarOptions().setColor(dark))
		             );
		
		getContentView().getContentPage()
		                .getContentDiv()
		                .add(new SwapContainer(new LoadingPage()));
	}
}
