package com.jwebmp.examples.demos.homepage.display.rightbar;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.entities.RightBarActivity;
import com.jwebmp.examples.demos.homepage.entities.RightBarActivity_;
import com.jwebmp.plugins.bootstrap4.listgroup.tabs.BSTabContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions;
import com.jwebmp.plugins.bootstrap4.options.BSColoursOptions;

import java.util.List;

import static com.entityassist.enumerations.OrderByType.*;
import static com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class RightBar
		extends DivSimple<RightBar>
{
	public RightBar()
	{
		setID("rightBar");
		addClass("side-bar right-bar");

		addClass(Bg_Dark);
		addStyle("background:inherit !important;");

		BSNavTabs<?> displayTabs = new BSNavTabs<>();
		displayTabs.getNavs()
		           .addClass(BSNavsOptions.Tabs_Bordered);
		displayTabs.getNavs()
		           .addClass(Nav_Justified);

		add(displayTabs);
		displayTabs.addClass(H_100);
		displayTabs.addClass(Bg_Dark);

		displayTabs.getTabContents()
		           .addClass(H_100);
		displayTabs.getTabContents()
		           .addClass(Bg_Dark);
		displayTabs.addStyle("overflow:hidden");


		DivSimple activityTab = buildActivityTab();
		DivSimple<?> settingsTab = new DivSimple<>().add("Settings Tab");

		BSTabContainer<?> activities = displayTabs.asMe()
		                                          .addTab("Activity", activityTab, true);
		BSTabContainer<?> settings = displayTabs.asMe()
		                                        .addTab("Settings", settingsTab, false);

		displayTabs.getTabContents()
		           .addClass(H_100)
		           .addClass(W_100)
		           .addStyle("overflow-y:auto")
		           .setID("activityTabContents")
		           .addFeature(new DefaultSlimScrollFeature(displayTabs.getTabContents()));
	}

	private DivSimple<?> buildActivityTab()
	{
		DivSimple<?> simple = new DivSimple<>();

		RightBarTimeLine timeLine = new RightBarTimeLine();
		simple.add(timeLine);

		simple.addClass(H_100);
		simple.addClass(Bg_Dark);

		timeLine.addAttribute("style", "overflow-y:auto;");

		List<RightBarActivity> activities = new RightBarActivity().builder()
		                                                          .inActiveRange()
		                                                          .orderBy(RightBarActivity_.date, DESC)
		                                                          .setMaxResults(15)
		                                                          .getAll();

		activities.forEach(a ->
		                   {
			                   RightBarTimeLineItem item = new RightBarTimeLineItem(a.getDate(), a.getTitle(), a.getDescription());
			                   item.addClass(Bg_Dark);
			                   item.addClass(BSColoursOptions.Text_White);
			                   if (a.getHighlighted() != null && a.getHighlighted())
			                   {
				                   item.addClass(Bg_Dark);
			                   }
			                   timeLine.addItem(item);
		                   });

		return simple;
	}
}
