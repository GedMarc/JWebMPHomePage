package com.jwebmp.examples.demos.homepage.display.quickstart;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

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

		container.add(HorizontalRule.getNewInstance());
		container.add(new H3("Check out the video!"));

		return container;
	}

	private BSNavTabs tabs()
	{
		BSNavTabs tabs = new BSNavTabs();
		tabs.setBordered(true);
		tabs.getTabContents()
		    .addStyle("padding-top:0px;");
		tabs.addTab("JRE 8", buildJRE8(), true);
		//tabs.addTab("JRE 10", buildJRE10(), false);
		tabs.addTab("JRE 11", buildJRE11(), false);
		//	add("Any event can send back multiple variables when fired. The registered variables are retrievable through the AjaxCall");
		//add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_4.txt").setID("coce3"));

		//	add("Watch Angular Variables by utilizing the AngularFeature found on the Page Object");
		//	add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_6.txt").setID("coce4"));
		return tabs;
	}

	private Div buildJRE8()
	{
		Div mavenContent = new Div();
		Div pageContent = new Div();
		Div moduleInfoContent = new Div();

		Div stepper = new Div();
		//	tabs.addTab("Maven", stepper, true);


		mavenContent.add(new H3("Maven Dependencies"));
		mavenContent.add("Add the following to your maven dependencies to get instant access to your first application!");

		//mavenContent.add(getCodeBlockJava(getClass(), "maven_jre8.txt").setID("coce3"));
		addSourceToContainer(QuickStartScreen.class, "maven_jre8.txt", XML, mavenContent);

		//	tabs.addTab("Page", pageContent, false);

		pageContent.add(new H3("You will need a Page"));
		addSourceToContainer(QuickStartScreen.class, "page_jre8.txt", Java, pageContent);

		//tabs.addTab("Quick Server", moduleInfoContent, false);
		moduleInfoContent.add(new H3("Get Started!"));
		moduleInfoContent.add(new Link<>("https://maven.apache.org/plugins/maven-war-plugin/usage.html", "_blank")
				                      .setText("Package as a WAR and deploy!<br/>"));
		moduleInfoContent.add(new Strong("No Container?"));
		moduleInfoContent.add("Simply add the below into any class of your choice and hit run!");
		addSourceToContainer(QuickStartScreen.class, "getstarted_jre8.txt", Java, moduleInfoContent);
		moduleInfoContent.add("And then of course...");
		addSourceToContainer(QuickStartScreen.class, "undertow_jre8.txt", XML, moduleInfoContent);

		moduleInfoContent.add("For JDK 8, JWebMP is fully <a target=\"_blank\" href=\"http://dcevm.github.io/\"> " +
		                      "DCEVM</a> Compatible allowing an ultra hot swap experience");

		DefaultDisplayWizard wizard = new DefaultDisplayWizard("jre8Wizard");

		wizard.addStep(new SmartWizardStep(mavenContent, new SmartWizardStepItem("Configure Maven", new SmallText("How to configure maven"))));
		wizard.addStep(new SmartWizardStep(pageContent, new SmartWizardStepItem("Make A Page", new SmallText("Start building"))));
		wizard.addStep(new SmartWizardStep(moduleInfoContent, new SmartWizardStepItem("Provision", new SmallText("Expose your page"))));
		wizard.addStep(new SmartWizardStep(moduleInfoContent, new SmartWizardStepItem("Run It!", new SmallText("Hit that go button"))));

		stepper.add(wizard);
		return stepper;
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
}
