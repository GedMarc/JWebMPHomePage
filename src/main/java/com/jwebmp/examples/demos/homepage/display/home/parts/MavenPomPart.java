package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.base.html.interfaces.GlobalChildren;
import com.jwebmp.core.base.html.interfaces.GlobalFeatures;
import com.jwebmp.core.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.examples.demos.homepage.components.PrettyInverseButton;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardFooter;
import com.jwebmp.plugins.bootstrap4.listgroup.tabs.BSListGroupTabs;
import com.jwebmp.plugins.bootstrap4.listgroup.tabs.BSTabContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.options.BSColoursOptions;
import com.jwebmp.plugins.bootstrap4.options.BSMarginOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

import static com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class MavenPomPart<J extends MavenPomPart<J>>
		extends DisplayPart<J>
{
	public MavenPomPart()
	{
		Div<GlobalChildren, ?, GlobalFeatures, GlobalEvents, ?> jdk8QuickStart = new Div<>();
		Div<GlobalChildren, ?, GlobalFeatures, GlobalEvents, ?> jdk10QuickStart = new Div<>();
		Div<GlobalChildren, ?, GlobalFeatures, GlobalEvents, ?> nightlyBuilds = new Div<>();
		Div<GlobalChildren, ?, GlobalFeatures, GlobalEvents, ?> pageServicing = new Div<>();

		addSourceToContainer(HomePage.class, "pomdependency.txt", XML, jdk8QuickStart);
		addSourceToContainer(HomePage.class, "pomdependency_10.txt", XML, jdk10QuickStart);
		addSourceToContainer(HomePage.class, "pomrepository.txt", XML, nightlyBuilds);

		BSNavTabs tabs = new BSNavTabs<>();
		tabs.getNavs().addClass(Tabs_Bordered)
				                .addClass(Nav_Justified);

		BSTabContainer tab1 = tabs.addTab("JDK 8",jdk8QuickStart, true);

		BSTabContainer tab2 = tabs.addTab("JDK 10",
		                                  jdk10QuickStart, false);

		BSTabContainer tab4 = tabs.addTab("Structure",
		                                  pageServicing, false);

		BSTabContainer tab3 = tabs.addTab("Nightly",
		                                  nightlyBuilds, false);

		JSTree<?> directoryStructureExample = new JSTree<>();
		directoryStructureExample.setTheme(new JSTreeDefaultDarkTheme());


		JSTreeListItem<?> rootItem = new JSTreeListItem<>("src");
		rootItem.getOptions()
		        .setDisabled(false)
		        .setIcon("far fa-caret-circle-down")
		        .setOpened(true);
		JSTreeListItem<?> folder1 = rootItem.addItem("META-INF", new JSTreeNodeOptions<>().setIcon("far fa-folder-open").setOpened(true));
		JSTreeListItem<?> folder2 = folder1.addItem("services", new JSTreeNodeOptions<>().setIcon("far fa-folder-open").setOpened(true));
		JSTreeListItem<?> file1 = folder2.addItem("com.jwebmp.core.services.IPage", new JSTreeNodeOptions<>().setIcon("far fa-file"));
		JSTreeListItem<?> folderResources = folder1.addItem("resources", new JSTreeNodeOptions<>().setIcon("far fa-folder-open").setOpened(true));
		JSTreeListItem<?> file2 = folderResources.addItem("favicon.ico", new JSTreeNodeOptions<>().setIcon("far fa-file-alt"));

		directoryStructureExample.addRoot(rootItem);
		directoryStructureExample.setID("directory-structure-example");

		pageServicing.add(directoryStructureExample);
		pageServicing.add(new Paragraph<>("For JDK 10 specify your provides list"));
		addSourceToContainer(HomePage.class, "requiresdepedency_10.txt", JS, pageServicing);

		addCardBody().add(tabs);
		BSCardFooter footer = addFooter();

		footer.add(new PrettyInverseButton<>("https://github.com/GedMarc/JWebMP-Examples-Undertow-HelloWorld/archive/master.zip").setTargetFrameName("_blank")
		                                                                                                                  .setText("Download Standalone HelloWorld"));
		footer.add(new PrettyInverseButton<>("https://github.com/GedMarc/JWebMPHomePage").setTargetFrameName("_blank")
		                                                                          .addClass(BSMarginOptions.MarginLeft_1)
		                                                                          .setText("Download Demo Site Source Code"));

		addStyle("margin-bottom:1rem;");
	}

}
