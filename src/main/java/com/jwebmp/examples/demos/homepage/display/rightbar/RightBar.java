package com.jwebmp.examples.demos.homepage.display.rightbar;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.examples.demos.homepage.entities.RightBarActivity;
import com.jwebmp.examples.demos.homepage.entities.RightBarActivity_;
import com.jwebmp.generics.LeftOrRight;
import com.jwebmp.htmlbuilder.css.colours.ColourCSSImpl;
import com.jwebmp.plugins.angularslimscroll.SlimScrollFeature;
import com.jwebmp.plugins.angularslimscroll.SlimScrollOptions;
import com.jwebmp.plugins.bootstrap4.listgroup.tabs.BSTabContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions;
import com.jwebmp.plugins.bootstrap4.options.BSColoursOptions;

import java.util.List;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.Bg_Dark;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.H_100;
import static za.co.mmagon.entityassist.enumerations.OrderByType.DESC;

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
		           .addClass(BSNavsOptions.Nav_Justified);

		add(displayTabs);
		displayTabs.addClass(H_100);
		displayTabs.addClass(Bg_Dark);

		displayTabs.getTabContents()
		           .addClass(H_100);
		displayTabs.getTabContents()
		           .addClass(Bg_Dark);


		DivSimple activityTab = buildActivityTab();
		DivSimple<?> settingsTab = new DivSimple<>();

		DivSimple<?> activityTabContents = new DivSimple<>();
		DivSimple<?> settingsTabContents = new DivSimple<>();


		activityTabContents.addFeature(new SlimScrollFeature(activityTabContents).setOptions(new SlimScrollOptions().setHeight("100%")
		                                                                                                            .setAlwaysVisible(true)
		                                                                                                            .setPosition(LeftOrRight.Right)
		                                                                                                            .setSize(5)
		                                                                                                            .setTouchScrollStep(50)
		                                                                                                            .setColor(new ColourCSSImpl("#98a6ad"))));

		settingsTabContents.addFeature(new SlimScrollFeature(settingsTabContents).setOptions(new SlimScrollOptions().setHeight("100%")
		                                                                                                            .setAlwaysVisible(true)
		                                                                                                            .setPosition(LeftOrRight.Right)
		                                                                                                            .setSize(5)
		                                                                                                            .setTouchScrollStep(50)
		                                                                                                            .setColor(new ColourCSSImpl("#98a6ad"))));

		BSTabContainer<?> activities = displayTabs.asMe()
		                                          .addTab("Activity", activityTab, true);


		BSTabContainer<?> settings = displayTabs.asMe()
		                                        .addTab("Settings", settingsTab, false);

		activityTab.add(activityTabContents);
		settingsTab.add(settingsTabContents);

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
