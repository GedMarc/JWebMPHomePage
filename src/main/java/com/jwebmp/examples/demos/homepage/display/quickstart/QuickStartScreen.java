package com.jwebmp.examples.demos.homepage.display.quickstart;

import com.jwebmp.core.base.html.*;
import com.jwebmp.core.htmlbuilder.css.colours.ColourNames;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.CircleDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.bootstrap4.accordion.BSAccordion;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonLightOutline;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
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

		//		container.add(HorizontalRule.getNewInstance());
/*
		container.add(new AlertMessage("Quick Hint! Build with DCEVM for awesome hot-swapping <br/>" +
		                               " Instantly port to JPMS without any problem!. Developer heaven!", BSAlertOptions.Alert_Danger));

		container.add(new H3("Check out the video!"));
*/
		return container;
	}

	private Div tabs()
	{
		BSNavTabs tabs = new BSNavTabs();
		tabs.setBordered(true);
		tabs.getTabContents()
		    .addStyle("padding-top:0px;");
		tabs.addTab("Java 8", buildJRE8(), true);
		//tabs.addTab("JRE 10", buildJRE10(), false);
		tabs.addTab("Java 11", buildJRE11(), false);
		//	add("Any event can send back multiple variables when fired. The registered variables are retrievable through the AjaxCall");
		//add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_4.txt").setID("coce3"));

		//	add("Watch Angular Variables by utilizing the AngularFeature found on the Page Object");
		//	add(getCodeBlockJava(FormBasicsDemoScreen.class, "bindingsbasic_6.txt").setID("coce4"));

		CircleDisplayWizard wizard = new CircleDisplayWizard("allJresWizard");

		wizard.addStep(new SmartWizardStep(buildJRE11(), new SmartWizardStepItem("JPMS", new SmallText(""))));
		wizard.addStep(new SmartWizardStep(buildJRE8(), new SmartWizardStepItem("JRE 8", new SmallText(""))));
		wizard.addStep(new SmartWizardStep(buildMigration(), new SmartWizardStepItem("MIGRATE", new SmallText(""))));
		wizard.addStep(new SmartWizardStep(buildMigration(), new SmartWizardStepItem("JAR", new SmallText(""))));
		wizard.addStep(new SmartWizardStep(buildMigration(), new SmartWizardStepItem("E.E.", new SmallText(""))));

		return wizard;
	}

	private Div buildJRE8()
	{
		Div mavenContent = new Div();
		Div pageContent = new Div();
		Div structureContent = new Div();
		Div moduleInfoContent = new Div();

		Div stepper = new Div();
		//	tabs.addTab("Maven", stepper, true);


		mavenContent.add(new H3("Maven Dependencies"));
		mavenContent.add("Add the following to your maven dependencies to get instant access to your first application!");

		//mavenContent.add(getCodeBlockJava(getClass(), "maven_jre8.txt").setID("coce3"));
		addSourceToContainer(QuickStartScreen.class, "maven_jre8.txt", XML, mavenContent);

		addDefaultJRE8Stuffs(mavenContent);


		mavenContent.add(new H3("Complete Basic POM"));
		mavenContent.add("You can use the below POM file to run this demo.");

		addSourceToContainer(QuickStartScreen.class, "maven_complete_pom.txt", XML, mavenContent);


		//	tabs.addTab("Page", pageContent, false);

		pageContent.add(new H3("You will need a Page"));
		addSourceToContainer(QuickStartScreen.class, "page_jre8.txt", Java, pageContent);

		addDefaultJRE8Stuffs(pageContent);

		structureContent.add(new H3("Tell JWebMP about it"));
		structureContent.add("Instant porting to JPMS is used via <a target=\"_blank\" href=\"https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html\">SPI</a>." +
		                     " The implementation for JRE 8 is different in JPMS<br/>");

		structureContent.add(buildStructureTree());
		structureContent.add("");

		structureContent.add(new H3("Whats in the file?"));
		structureContent.add("A simple reference to your pages on each new line");
		addSourceToContainer(QuickStartScreen.class, "spifile.txt", SourceCodeLanguages.HTML, structureContent);

		addDefaultJRE8Stuffs(structureContent);

		structureContent.add("A reminder is added to the log files of where to place raw files.<br/>" +
		                     "As a pure jar file, resources should be put in META-INF/resources");

		//tabs.addTab("Quick Server", moduleInfoContent, false);
		moduleInfoContent.add(new H3("Get Started!"));
		moduleInfoContent.add(new Link<>("https://maven.apache.org/plugins/maven-war-plugin/usage.html", "_blank")
				                      .setText("Package as a WAR and deploy!<br/>"));
		moduleInfoContent.add(new Strong("No Container?"));
		moduleInfoContent.add("Simply add the below into any class of your choice and hit run!");


		addSourceToContainer(QuickStartScreen.class, "getstarted_jre8.txt", Java, moduleInfoContent);
		moduleInfoContent.add("And then of course...");
		addSourceToContainer(QuickStartScreen.class, "undertow_jre8.txt", XML, moduleInfoContent);

		addDefaultJRE8Stuffs(moduleInfoContent);

		DefaultDisplayWizard wizard = new DefaultDisplayWizard("jre8Wizard");

		wizard.addStep(new SmartWizardStep(mavenContent, new SmartWizardStepItem("Configure Maven", new SmallText("Add the dependency"))));
		wizard.addStep(new SmartWizardStep(pageContent, new SmartWizardStepItem("Make A Page", new SmallText("Start building"))));
		wizard.addStep(new SmartWizardStep(structureContent, new SmartWizardStepItem("Provision", new SmallText("Expose your page"))));
		wizard.addStep(new SmartWizardStep(moduleInfoContent, new SmartWizardStepItem("Run It", new SmallText("Hit that go button"))));

		stepper.add(wizard);
		return stepper;
	}


	private Div buildJRE11()
	{
		Div stepper = new Div();

		DefaultDisplayWizard wizard = new DefaultDisplayWizard("jre11Wizard");

		wizard.addStep(new SmartWizardStep(buildPomConfigJRE11(), new SmartWizardStepItem("POM Config", new SmallText("Setup for JPMS"))));
		wizard.addStep(new SmartWizardStep(buildMakeAPageJRE11(), new SmartWizardStepItem("Make A Page", new SmallText("Gotta start somewhere"))));
		wizard.addStep(new SmartWizardStep(buildModulesJRE11(), new SmartWizardStepItem("Modules", new SmallText("Specify your modules"))));
		wizard.addStep(new SmartWizardStep(buildGoJRE11(), new SmartWizardStepItem("Go", new SmallText("Time to run"))));
		wizard.addStep(new SmartWizardStep(buildCompletePomJRE11(), new SmartWizardStepItem("Complete POM", new SmallText("Ready to use!"))));

		stepper.add(wizard);
		return stepper;
	}

	private Div buildMigration()
	{
		Div mavenContent = new Div();
		Div pageContent = new Div();
		Div structureContent = new Div();
		Div moduleInfoContent = new Div();

		Div stepper = new Div();
		//	tabs.addTab("Maven", stepper, true);


		DefaultDisplayWizard wizard = new DefaultDisplayWizard("migrationWizard");

		wizard.addStep(new SmartWizardStep(mavenContent, new SmartWizardStepItem("Configure Maven", new SmallText("Add the dependency"))));
		wizard.addStep(new SmartWizardStep(pageContent, new SmartWizardStepItem("Make A Page", new SmallText("Start building"))));
		wizard.addStep(new SmartWizardStep(structureContent, new SmartWizardStepItem("Provision", new SmallText("Expose your page"))));
		wizard.addStep(new SmartWizardStep(moduleInfoContent, new SmartWizardStepItem("Run It", new SmallText("Hit that go button"))));

		stepper.add(wizard);

		return stepper;
	}

	private void addDefaultJRE8Stuffs(Div mavenContent)
	{
		mavenContent.add(new BSButtonLightOutline<>().setText("View In GitHub"));
		mavenContent.add("<br/>For JDK 8, JWebMP is fully <a target=\"_blank\" href=\"http://dcevm.github.io/\"> " +
		                 "DCEVM</a> Compatible allowing an ultra hot swap experience");
	}

	private JSTree buildStructureTree()
	{
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

		directoryStructureExample.getCss()
		                         .getBackground()
		                         .setBackgroundColor$(ColourNames.Black);
		return directoryStructureExample;
	}

	private Div buildPomConfigJRE11()
	{
		Div d = new Div();

		d.add(new H3("So Basically..."));
		d.add("This is all that's needed in your pom.");
		addSourceToContainer(QuickStartScreen.class, "jre11_maven_dependencies.txt", XML, d);
		addDefaultJRE11Stuffs(d);


		return d;
	}

	private Div buildMakeAPageJRE11()
	{
		Div pageContent = new Div();
		pageContent.add(new H3("You will need a Page"));
		pageContent.add("This is the Hello World app, Components are added directly to each other through add()" +
		                "<br/>A string creates a Paragraph.");

		addSourceToContainer(QuickStartScreen.class, "page_jre8.txt", Java, pageContent);

		pageContent.add("You can annotate this class as you like, by default it is request scoped.");
		pageContent.add(new Link<>("https://github.com/google/guice/wiki/Scopes", "_blank")
				                .setText("You can read about scoping here. " +
				                         "<br/>@Singleton, @SessionScoped and @RequestScoped</br><br/>"));

		addDefaultJRE11Stuffs(pageContent);
		return pageContent;
	}

	private Div buildModulesJRE11()
	{
		Div pageContent = new Div();
		pageContent.add(new H3("OK - Time for the module"));
		pageContent.add("You can do it the strict way, and get all the JPMS benefits.");

		pageContent.add("To enable this, you need to place a Java file, module-info.java, into the root directory/folder of your sources"
		                + "<br/>Next, we need to declare the module name, and what this module requires" +
		                "<br/>We are going to require JWebMP, And provide the HelloWorld page to it");
		pageContent.add("To enable AOP &amp; Method Interception, An opens clause to Google's Guice must be placed as well.");

		addSourceToContainer(QuickStartScreen.class, "jre11_module_info.txt", SourceCodeLanguages.JS, pageContent);
/*

		//addSourceToContainer(QuickStartScreen.class, "jre11_execution_params.txt", JS, pageContent);
		pageContent.add(new H3("Or....."));
		pageContent.add("You can of course be incredibly lazy, for which the cost is performance related, but a much neater module-info" +
		                "<br/>You also won't need any execution parameters, or exposes and opens clauses");

		addSourceToContainer(QuickStartScreen.class, "jre11_open_module_info.txt", JS, pageContent);
*/

		addDefaultJRE11Stuffs(pageContent);
		return pageContent;
	}

	private Div buildCompletePomJRE11()
	{
		Div d = new Div();

		d.add(new H3("Complete POM"));
		d.add("You can use this POM to generate a complete executable, and deployable application.");
		d.add("You may need to change the main-class definition");

		JQSourceCodePrettify<?> dScroll = addSourceToContainer(QuickStartScreen.class, "jre11_maven_complete_pom.txt", XML, d);
		dScroll.addStyle("max-height", "400px");
		dScroll.addFeature(new DefaultSlimScroll(dScroll));

		return d;
	}

	private Div buildGoJRE11()
	{
		Div d = new Div();

		d.add(new H3("Time to Run"));
		d.add("You can run this program using a JRE 11 Runtime by specifying an arguments file, or specifying a command line");
		d.add("Arguments files are definitely preferred");

		d.add(new BSButtonLightOutline<>().setText("Download Arguments File"));
		JQSourceCodePrettify<?> dInner = addSourceToContainer(QuickStartScreen.class, "jre11_argumentsfile.txt", XML, d);
		d.add("You can run straight from the command line");
		JQSourceCodePrettify<?> dInner2 = addSourceToContainer(QuickStartScreen.class, "jre11_argumentsfilerun.txt", XML, d);

		BSAccordion<?> ideAccordion = new BSAccordion<>();
		ideAccordion.addCard("Configure Eclipse");
		ideAccordion.addCard("Configure Netbeans");
		ideAccordion.addCard("Configure IntelliJ");
		d.add(ideAccordion);

		JQSourceCodePrettify<?> dScroll = addSourceToContainer(QuickStartScreen.class, "jre11_run.txt", XML, d);
		dScroll.addStyle("max-height", "400px");
		dScroll.addFeature(new DefaultSlimScroll(dScroll));

		return d;
	}

	private void addDefaultJRE11Stuffs(Div mavenContent)
	{
		mavenContent.add(new BSButtonLightOutline<>().setText("View In GitHub"));
	}
}
