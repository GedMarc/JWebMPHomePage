package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.google.inject.Singleton;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.interfaces.GlobalFeatures;
import com.jwebmp.core.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.examples.demos.homepage.components.PrettyInverseButton;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardFooter;
import com.jwebmp.plugins.bootstrap4.listgroup.tabs.BSTabContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.options.BSMarginOptions;

import static com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

@Singleton
public class MavenPomPart<J extends MavenPomPart<J>>
		extends DisplayPart<J>
{
	public MavenPomPart()
	{
		Div<IComponentHierarchyBase, ?, GlobalFeatures, GlobalEvents, ?> jdk8QuickStart = new Div<>();
		Div<IComponentHierarchyBase, ?, GlobalFeatures, GlobalEvents, ?> nightlyBuilds = new Div<>();

		addSourceToContainer(HomePage.class, "pomdependency.txt", XML, jdk8QuickStart);
		addSourceToContainer(HomePage.class, "pomrepository.txt", XML, nightlyBuilds);

		BSNavTabs tabs = new BSNavTabs<>();
		tabs.getNavs()
		    .addClass(Tabs_Bordered)
		    .addClass(Nav_Justified);

		BSTabContainer tab1 = tabs.addTab("Maven", jdk8QuickStart, true);
		BSTabContainer tab3 = tabs.addTab("Nightly",
		                                  nightlyBuilds, false);

		addCardBody().add(tabs);
		BSCardFooter footer = addFooter();

		footer.add(new PrettyInverseButton<>("https://github.com/GedMarc/JWebMP-Examples-Undertow-HelloWorld/archive/master.zip").setTargetFrameName("_blank")
		                                                                                                                         .setText("Download HelloWorld"));
		footer.add(new PrettyInverseButton<>("https://github.com/GedMarc/JWebMPHomePage").setTargetFrameName("_blank")
		                                                                                 .addClass(BSMarginOptions.MarginLeft_1)
		                                                                                 .setText("Download Site Source"));
		addStyle("margin-bottom:1rem;");
	}

}
