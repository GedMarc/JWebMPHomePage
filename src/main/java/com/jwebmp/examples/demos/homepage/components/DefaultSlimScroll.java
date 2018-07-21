package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.htmlbuilder.css.colours.ColourCSSImpl;
import com.jwebmp.plugins.angularslimscroll.SlimScrollFeature;

public class DefaultSlimScroll
		extends SlimScrollFeature
{
	public DefaultSlimScroll(ComponentHierarchyBase component)
	{
		super(component);
		getOptions().setHeight("100%")
		            .setTouchScrollStep(50)
		            .setColor(new ColourCSSImpl("#98a6ad"));
	}

}

