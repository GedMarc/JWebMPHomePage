package com.jwebmp.examples.demos.homepage.display.rightbar;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.base.servlets.enumarations.ComponentTypes;
import com.jwebmp.plugins.moment.Moment;

import java.util.Date;

import static com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions.Text_Muted;

public class RightBarTimeLineItem
		extends DivSimple<RightBarTimeLineItem>
{
	private DivSimple<?> itemInfo;

	public RightBarTimeLineItem(Date date, String info, String moreInformation)
	{
		addClass("time-item");

		itemInfo = new DivSimple<>();
		itemInfo.addClass("item-info");
		add(itemInfo);

		Moment<?> timeAgo = new Moment(date);
		timeAgo.setTag(ComponentTypes.SmallText.getComponentTag())
		       .addClass(Text_Muted);

		itemInfo.add(timeAgo);

		Paragraph<?> p = new Paragraph<>(info);
		itemInfo.add(p);

		if (moreInformation != null)
		{
			Paragraph<?> p2 = new Paragraph<>(moreInformation);
			itemInfo.add(p2);
		}
	}
}
