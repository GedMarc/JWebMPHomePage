package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.core.base.html.Bold;
import com.jwebmp.core.base.html.DivSimple;

public class DefaultSlimScroll
		extends DivSimple<DefaultSlimScroll>
{
	public DefaultSlimScroll()
	{
		add(new Bold("Scroll down for more"));

		DefaultSlimScrollFeature srolly = new DefaultSlimScrollFeature(this);
		srolly.getOptions()
		      .setHeight("400px");
		addFeature(srolly);
	}
}
