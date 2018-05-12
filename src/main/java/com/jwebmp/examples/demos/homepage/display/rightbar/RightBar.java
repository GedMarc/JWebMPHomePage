package com.jwebmp.examples.demos.homepage.display.rightbar;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.plugins.bootstrap4.listgroup.tabs.BSTabContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions;

public class RightBar
		extends DivSimple<RightBar>
{
	//private static final IO io = makeDefaultPrettyPrint();

	public RightBar()
	{
		setID("rightBar");
		addClass("side-bar right-bar");


		BSNavTabs<?> displayTabs = new BSNavTabs<>();
		displayTabs.getNavs()
		           .addClass(BSNavsOptions.Tabs_Bordered);
		displayTabs.getNavs()
		           .addClass(BSNavsOptions.Nav_Justified);
		add(displayTabs);

		DivSimple activityTab = buildActivityTab();
		DivSimple<?> settingsTab = new DivSimple<>();

		BSTabContainer<?> activities = displayTabs.asMe()
		                                          .addTab("Activity", activityTab, true);
		BSTabContainer<?> settings = displayTabs.asMe()
		                                        .addTab("Settings", settingsTab, false);
	}

	private DivSimple<?> buildActivityTab()
	{
		DivSimple<?> simple = new DivSimple<>();
		simple.add(new RightBarTimeLine());


		return simple;
	}
}
