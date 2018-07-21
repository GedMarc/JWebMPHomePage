package com.jwebmp.examples.demos.homepage.display.rightbar;

import com.jwebmp.core.base.html.DivSimple;

public class RightBarTimeLine
		extends DivSimple<RightBarTimeLine>
{
	public RightBarTimeLine()
	{
		addClass("timeline-2");
	}

	public RightBarTimeLine addItem(RightBarTimeLineItem item)
	{
		add(item);
		return this;
	}
}
