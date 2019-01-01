package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.core.base.html.Bold;
import com.jwebmp.core.base.html.DivSimple;

public class DefaultSlimScroll
		extends DivSimple<DefaultSlimScroll>
{
	@Override
	protected StringBuilder renderBeforeTag()
	{
		return new StringBuilder(new Bold("Scroll down for more").toString(0));
	}

	public DefaultSlimScroll()
	{
		DefaultSlimScrollFeature srolly = new DefaultSlimScrollFeature(this);
		srolly.getOptions()
		      .setHeight("400px");
		addFeature(srolly);
	}
}
