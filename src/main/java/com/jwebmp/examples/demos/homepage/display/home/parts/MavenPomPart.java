package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.base.html.interfaces.GlobalChildren;
import com.jwebmp.core.base.html.interfaces.GlobalFeatures;
import com.jwebmp.core.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.examples.demos.homepage.components.PrettyInverseButton;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
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

import static com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions.*;

public class MavenPomPart<J extends MavenPomPart<J>>
		extends DivSimple<J>
{
	public MavenPomPart()
	{
		Div<GlobalChildren, ?, GlobalFeatures, GlobalEvents, ?> jdk8QuickStart = new Div<>();
		Div<GlobalChildren, ?, GlobalFeatures, GlobalEvents, ?> jdk10QuickStart = new Div<>();
		Div<GlobalChildren, ?, GlobalFeatures, GlobalEvents, ?> nightlyBuilds = new Div<>();
		Div<GlobalChildren, ?, GlobalFeatures, GlobalEvents, ?> pageServicing = new Div<>();

		JQSourceCodePrettify<?> prettifyPom = new JQSourceCodePrettify<>();
		prettifyPom.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettifyPom.setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(HomePage.class, "PomDependency", "pomdependency.txt")
		                                                               .toString()));
		prettifyPom.addStyle("background:black;");

		jdk8QuickStart.add(prettifyPom);

		JQSourceCodePrettify<?> prettifyPomJRE10 = new JQSourceCodePrettify<>();
		prettifyPomJRE10.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettifyPomJRE10.setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(HomePage.class, "PomDependency10", "pomdependency_10.txt")
		                                                               .toString()));
		prettifyPomJRE10.addStyle("background:black;");

		jdk10QuickStart.add(prettifyPomJRE10);

		JQSourceCodePrettify<?> prettifyPomRepository = new JQSourceCodePrettify<>();
		prettifyPomRepository.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettifyPomRepository.setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);
		prettifyPomRepository.addStyle("background:black;");
		prettifyPomRepository.setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(HomePage.class, "PomRepository", "pomrepository.txt")
		                                                                         .toString()));
		nightlyBuilds.add(prettifyPomRepository);

		BSNavTabs tabs = new BSNavTabs<>();
		tabs.getNavs().addClass(Tabs_Bordered)
				                .addClass(Nav_Justified);

		BSTabContainer tab1 = tabs.addTab("JDK 8",jdk8QuickStart, true);

		BSTabContainer tab2 = tabs.addTab("JDK 10",
		                                  jdk10QuickStart, false);

		BSTabContainer tab3 = tabs.addTab("Nightly Builds",
		                                  nightlyBuilds, false);

		BSTabContainer tab4 = tabs.addTab("Page Providing",
		                                  pageServicing, false);

		JSTree<?> directoryStructureExample = new JSTree<>();
		directoryStructureExample.setTheme(new JSTreeDefaultDarkTheme());


		JSTreeListItem<?> rootItem = new JSTreeListItem<>("src");
		rootItem.getOptions()
		        .setDisabled(false)
		        .setIcon("fa fa-toggle-down")
		        .setOpened(true);
		JSTreeListItem<?> folder1 = rootItem.addItem("META-INF", new JSTreeNodeOptions<>().setIcon("fa fa-folder").setOpened(true));
		JSTreeListItem<?> folder2 = folder1.addItem("services", new JSTreeNodeOptions<>().setIcon("fa fa-folder").setOpened(true));
		JSTreeListItem<?> file1 = folder2.addItem("com.jwebmp.core.services.IPage", new JSTreeNodeOptions<>().setIcon("fa fa-file"));

		directoryStructureExample.addRoot(rootItem);
		directoryStructureExample.setID("directory-structure-example");

		pageServicing.add(directoryStructureExample);

		pageServicing.add(new Paragraph<>("For JDK 10 specify your provides list"));

		JQSourceCodePrettify<?> pageServicingExample = new JQSourceCodePrettify<>();
		pageServicingExample.setSourceCodeLanguage(SourceCodeLanguages.XML);
		pageServicingExample.setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);
		pageServicingExample.addStyle("background:black;");
		pageServicingExample.setText(StringEscapeUtils.escapeHtml4(FileTemplates.getFileTemplate(HomePage.class, "JRE10Requires", "requiresdepedency_10.txt")
		                                                                         .toString()));
		pageServicing.add(pageServicingExample);

		add(tabs);


		add(new PrettyInverseButton<>("https://github.com/GedMarc/JWebMP-Examples-Undertow-HelloWorld/archive/master.zip").setTargetFrameName("_blank")
		                                                                                                                  .setText("Download Standalone HelloWorld"));
		add(new PrettyInverseButton<>("https://github.com/GedMarc/JWebMPHomePage").setTargetFrameName("_blank")
		                                                                          .addClass(BSMarginOptions.MarginLeft_1)
		                                                                          .setText("Download Demo Site Source Code"));
	}

}
