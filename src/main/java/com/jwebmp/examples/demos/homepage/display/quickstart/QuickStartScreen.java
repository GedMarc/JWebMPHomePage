package com.jwebmp.examples.demos.homepage.display.quickstart;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class QuickStartScreen
		extends PluginDemoScreen
{
	public QuickStartScreen()
	{
		super(null, "JWebMP", "Quick Start");
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer();
		container.add(tabs());
		return container;
	}

	private BSNavTabs tabs()
	{
		BSNavTabs tabs = new BSNavTabs();
		tabs.setBordered(true);
		tabs.getTabContents()
		    .addStyle("padding-top:0px;");
		tabs.addTab("JRE 8", buildJRE8(), true);
		tabs.addTab("JRE 10", buildJRE10(), false);
		tabs.addTab("JRE 11", buildJRE11(), false);
		//	add("Any event can send back multiple variables when fired. The registered variables are retrievable through the AjaxCall");
		//add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_4.txt").setID("coce3"));

		//	add("Watch Angular Variables by utilizing the AngularFeature found on the Page Object");
		//	add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_6.txt").setID("coce4"));
		return tabs;
	}

	private BSNavTabs buildJRE8()
	{
		BSNavTabs tabs = new BSNavTabs();
		tabs.setBordered(true);


		Div mavenContent = new Div();
		Div pageContent = new Div();
		Div moduleInfoContent = new Div();

		tabs.addTab("Maven", mavenContent, true);

		mavenContent.add(new H3("Maven Dependencies"));
		//mavenContent.add(getCodeBlockJava(getClass(), "maven_jre8.txt").setID("coce3"));
		addSourceToContainer(QuickStartScreen.class, "maven_jre8.txt", XML, mavenContent);

		tabs.addTab("Page", pageContent, false);

		tabs.addTab("Service Location", moduleInfoContent, false);


		return tabs;
	}

	private BSNavTabs buildJRE10()
	{
		BSNavTabs tabs = new BSNavTabs();
		tabs.setBordered(true);


		Div mavenContent = new Div();
		Div pageContent = new Div();
		Div moduleInfoContent = new Div();

		tabs.addTab("Maven", mavenContent, true);

		tabs.addTab("Page", pageContent, false);

		tabs.addTab("Service Location", moduleInfoContent, false);

		return tabs;
	}

	private BSNavTabs buildJRE11()
	{
		BSNavTabs tabs = new BSNavTabs();
		tabs.setBordered(true);


		Div mavenContent = new Div();
		Div pageContent = new Div();
		Div moduleInfoContent = new Div();

		tabs.addTab("Maven", mavenContent, true);

		tabs.addTab("Page", pageContent, false);

		tabs.addTab("Service Location", moduleInfoContent, false);

		return tabs;
	}
}
