package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.Italic;
import com.jwebmp.core.base.html.Paragraph;
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
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;

import static com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class MavenPomPart<J extends MavenPomPart<J>>
		extends DisplayPart<J>
{
	public MavenPomPart()
	{
		Div<IComponentHierarchyBase, ?, GlobalFeatures, GlobalEvents, ?> jdk8QuickStart = new Div<>();
		Div<IComponentHierarchyBase, ?, GlobalFeatures, GlobalEvents, ?> jdk10QuickStart = new Div<>();
		Div<IComponentHierarchyBase, ?, GlobalFeatures, GlobalEvents, ?> jdk11QuickStart = new Div<>();
		Div<IComponentHierarchyBase, ?, GlobalFeatures, GlobalEvents, ?> nightlyBuilds = new Div<>();
		Div<IComponentHierarchyBase, ?, GlobalFeatures, GlobalEvents, ?> pageServicing = new Div<>();

		addSourceToContainer(HomePage.class, "pomdependency.txt", XML, jdk8QuickStart);
		addSourceToContainer(HomePage.class, "pomdependency_10.txt", XML, jdk10QuickStart);
		addSourceToContainer(HomePage.class, "pomdependency_11.txt", XML, jdk11QuickStart);
		addSourceToContainer(HomePage.class, "pomrepository.txt", XML, nightlyBuilds);

		jdk11QuickStart.add(
				new Italic("While waiting for a dependency update, please add <strong>--add-opens java.base/java.lang=com.google.guice</strong> to your execution paths"));

		BSNavTabs tabs = new BSNavTabs<>();
		tabs.getNavs()
		    .addClass(Tabs_Bordered)
		    .addClass(Nav_Justified);

		BSTabContainer tab1 = tabs.addTab("JDK 8", jdk8QuickStart, true);

		BSTabContainer tab2 = tabs.addTab("JDK 10",
		                                  jdk10QuickStart, false);

		BSTabContainer tab5 = tabs.addTab("JDK 11",
		                                  jdk11QuickStart, false);

		BSTabContainer tab4 = tabs.addTab("Structure",
		                                  pageServicing, false);

		BSTabContainer tab3 = tabs.addTab("Nightly",
		                                  nightlyBuilds, false);

		JSTree<?> directoryStructureExample = new JSTree<>();
		directoryStructureExample.setTheme(new JSTreeDefaultDarkTheme());

		JSTreeListItem<?> rootItem = new JSTreeListItem<>("src")
				                             .setOptions(new JSTreeNodeOptions<>().setDisabled(false)
				                                                                  .setIcon("far fa-caret-circle-down")
				                                                                  .setOpened(true));

		JSTreeListItem<?> folder1 = new JSTreeListItem<>("META-INF", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
		                                                                                      .setOpened(true));

		JSTreeListItem<?> folder2 = new JSTreeListItem<>("services", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
		                                                                                      .setOpened(true));

		JSTreeListItem<?> file1 = new JSTreeListItem<>("com.jwebmp.core.services.IPage", new JSTreeNodeOptions<>().setIcon("far fa-file"));

		JSTreeListItem<?> folderResources = new JSTreeListItem<>("resources", new JSTreeNodeOptions<>().setIcon("far fa-folder-open")
		                                                                                               .setOpened(true));

		JSTreeListItem<?> file2 = new JSTreeListItem<>("favicon.ico", new JSTreeNodeOptions<>().setIcon("far fa-file-alt"));

		folder2.add(file1);

		folder1.add(folder2);

		folderResources.add(file2);

		folder1.add(folderResources);
		rootItem.add(folder1);

		directoryStructureExample.addRoot(rootItem);
		directoryStructureExample.setID("directory-structure-example");

		pageServicing.add(directoryStructureExample);
		pageServicing.add(new Paragraph<>("For JDK 10 specify your provides list"));
		addSourceToContainer(HomePage.class, "requiresdepedency_10.txt", JS, pageServicing);

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
