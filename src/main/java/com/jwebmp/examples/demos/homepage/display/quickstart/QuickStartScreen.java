package com.jwebmp.examples.demos.homepage.display.quickstart;

import com.jwebmp.core.base.html.*;
import com.jwebmp.core.htmlbuilder.css.colours.ColourNames;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.components.display.CircleDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.accordion.BSAccordion;
import com.jwebmp.plugins.bootstrap4.accordion.BSAccordionCollection;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonLightOutline;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonPrimaryOutline;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import java.io.IOException;

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

		wizard.addStep(new SmartWizardStep(buildGuiceProjectsMigration(), new SmartWizardStepItem("GUICE", new SmallText(""))));
		wizard.addStep(new SmartWizardStep(buildSpringProjectsMigration(), new SmartWizardStepItem("SPRING", new SmallText(""))));
		wizard.addStep(new SmartWizardStep(GuiceContext.get(DisplayScreens.GoDeeperScreen.getScreen()), new SmartWizardStepItem("TEST", new SmallText(""))));
		//wizard.addStep(new SmartWizardStep(buildMigration(), new SmartWizardStepItem("E.E.", new SmallText(""))));

		return wizard;
	}

	private Div buildJRE8()
	{
		Div mavenContent = new Div();
		Div pageContent = new Div();
		Div structureContent = new Div();
		Div moduleInfoContent = new Div();
		Div completePomContent = new Div();

		Div stepper = new Div();
		//	tabs.addTab("Maven", stepper, true);


		mavenContent.add(new H3("Maven Dependencies"));
		mavenContent.add("Add the following to your maven dependencies to get instant access to your first application!");
		mavenContent.add("Take note of the group used. This group's classes are built in JDK 8");
		//mavenContent.add(getCodeBlockJava(getClass(), "maven_jre8.txt").setID("coce3"));
		addSourceToContainer(QuickStartScreen.class, "maven_jre8.txt", XML, mavenContent);

		addDefaultJRE8Stuffs(mavenContent);


/*
		mavenContent.add(new H3("Complete Basic POM"));
		mavenContent.add("You can use the below POM file to run this demo.");


*/
		//	tabs.addTab("Page", pageContent, false);

		pageContent.add(new H3("You will need a Page"));
		pageContent.add("Pages are classes that extend a Page, Page implements IPage, so it is completely overridable, as we do with Cordova");

		addSourceToContainer(QuickStartScreen.class, "page_jre8.txt", Java, pageContent);

		addDefaultJRE8Stuffs(pageContent);

		structureContent.add(new H3("Tell JWebMP about it"));
		structureContent.add("Instant porting to JPMS is used via <a target=\"_blank\" href=\"https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html\">SPI</a>." +
		                     " The implementation for JRE 8 is different in JPMS, and you actually need to create a file<br/>" +
		                     " This is done with a provides clause in JPMS");
		structureContent.add(buildStructureTree());
		structureContent.add("");

		structureContent.add(new H3("Whats in the file?"));
		structureContent.add("A simple reference to your pages on each new line");
		addSourceToContainer(QuickStartScreen.class, "spifile.txt", SourceCodeLanguages.HTML, structureContent);

		addDefaultJRE8Stuffs(structureContent);

		structureContent.add("A reminder is added to the log files of where to place raw files.<br/>" +
		                     "Running as a pure jar file, resources should be put in META-INF/resources<br/>" +
		                     "This really makes life easy (webapp for war's generally place here)");


		//tabs.addTab("Quick Server", moduleInfoContent, false);
		moduleInfoContent.add(new H3("Get Started!"));
		moduleInfoContent.add(new Link<>("https://maven.apache.org/plugins/maven-war-plugin/usage.html", "_blank")
				                      .setText("Running on a container?</br>" +
				                               "Package as a WAR and deploy!<br/>" +
				                               "Taadaaaaa<br/><br/>"));
		moduleInfoContent.add(new Strong("No Container?"));

		moduleInfoContent.add("Simply add a static void main into any class of your choice, and use the undertow quickstart :)<br/>" +
		                      "The undertow library is used for WildFly plug and play, and for JPMS execution");

		addSourceToContainer(QuickStartScreen.class, "getstarted_jre8.txt", Java, moduleInfoContent);
		moduleInfoContent.add("And then of course...");
		addSourceToContainer(QuickStartScreen.class, "undertow_jre8.txt", XML, moduleInfoContent);

		completePomContent.add(new H3<>("Complete POM"));
		completePomContent.add("Hit the ground running with a fully operational pom file, or download this app below");
		addSourceToContainer(QuickStartScreen.class, "maven_complete_pom.txt", XML, completePomContent);

		addDefaultJRE8Stuffs(moduleInfoContent);

		DefaultSmartWizard wizard = new DefaultSmartWizard("jre8Wizard");

		wizard.addStep(new SmartWizardStep(mavenContent, new SmartWizardStepItem("Configure Maven", new SmallText("Add the dependency"))));
		wizard.addStep(new SmartWizardStep(pageContent, new SmartWizardStepItem("Make A Page", new SmallText("Start building"))));
		wizard.addStep(new SmartWizardStep(structureContent, new SmartWizardStepItem("Provision", new SmallText("Expose your page"))));
		wizard.addStep(new SmartWizardStep(moduleInfoContent, new SmartWizardStepItem("Run It", new SmallText("Hit that go button"))));
		wizard.addStep(new SmartWizardStep(completePomContent, new SmartWizardStepItem("Complete Pom", new SmallText("Up and go with maven"))));

		stepper.add(wizard);
		return stepper;
	}


	private Div buildJRE11()
	{
		Div stepper = new Div();

		DefaultSmartWizard wizard = new DefaultSmartWizard("jre11Wizard");

		wizard.addStep(new SmartWizardStep(buildPomConfigJRE11(), new SmartWizardStepItem("POM Config", new SmallText("Setup for JPMS"))));
		wizard.addStep(new SmartWizardStep(buildMakeAPageJRE11(), new SmartWizardStepItem("Make A Page", new SmallText("Gotta start somewhere"))));
		wizard.addStep(new SmartWizardStep(buildModulesJRE11(), new SmartWizardStepItem("Modules", new SmallText("Specify your modules"))));
		//wizard.addStep(new SmartWizardStep(buildGoJRE11(), new SmartWizardStepItem("Go", new SmallText("Time to run"))));
		wizard.addStep(new SmartWizardStep(buildCompletePomJRE11(), new SmartWizardStepItem("Complete POM", new SmallText("Ready to use!"))));

		stepper.add(wizard);
		return stepper;
	}

	private Div buildMigration()
	{


		Div aboutMigration = new Div();
		Div annotationsConect = new Div();
		Div dualExecution = new Div();
		Div moduleInfoContent = new Div();

		Div stepper = new Div();


		/*DefaultSlimScrollFeature scroll = new DefaultSlimScrollFeature(aboutMigration);
		scroll.getOptions()
		      .setHeight("400px");*/
		//	tabs.addTab("Maven", stepper, true);

		aboutMigration.add(new H3<>("Go from JRE 8 to JPMS Instantly"));
		aboutMigration.add("JWebMP allows you to instantly move from JRE8 to JPMS without any hassles of any kind.<br/>" +
		                   "True story.. Using <code>Moditect</code> to create the required naming classes, JDK 8 runs straight in JPMS with Named Modules.<br/>" +
		                   "As JVM releases roll-out a lot quicker, and deployments weekly, you can trust that you will always be up-to-date, have any problems resolved timely. " +
		                   "<br/>The safety of a full and open CI/Tracking system ensures that you can always support, log bugs, and get assistence whenever you need it.");

		aboutMigration.add(HorizontalRule.getInstance());
		aboutMigration.add("It literally stays like this");
		addSourceToContainer(QuickStartScreen.class, "maven_jre8.txt", XML, aboutMigration);

/*
		aboutMigration.add("Simply to (Changed Group)");
		//aboutMigration.add("Because Maven needs a slight version update to build JPMS, an additional portion is required.");
		addSourceToContainer(QuickStartScreen.class, "jre11_maven_dependencies.txt", XML, aboutMigration);
*/


		annotationsConect.add(new H3<>("Moving to Guice Injection"));
		annotationsConect.add("There are a few injections you may want to consider using the default <code>javax.inject</code> when porting across." +
		                      "<br/> Below are the items to look at");

		annotationsConect.add("<code>@javax.ejb.EJB</code> can be replaced with <code>@javax.inject.Inject</code>");
		annotationsConect.add("<code>@javax.ejb.Local/Remote</code> can be replaced, and a binding added for the interface (Should you wish to keep them). " +
		                      "<br/>I delete them all...");
		annotationsConect.add("<code>@javax.ejb.Stateless</code> can be removed");
		annotationsConect.add("<code>@javax.ejb.Stateful</code> can be replaced with <a target=\"_blank\" href=\"https://github.com/google/guice/wiki/Scopes\">custom scopes</a>");
		annotationsConect.add("<code>@javax.faces.SessionScoped</code> can be replaced with <code>@com.google.inject.servlet.SessionScoped</code>");
		annotationsConect.add("<code>@javax.faces.RequestScoped</code> can be replaced with <code>@com.google.inject.servlet.RequestScoped</code>");
		annotationsConect.add("<code>@javax.faces.ViewScoped</code> can be replaced with <code>@com.jwebmp.guicedservlets.jsf.ViewScoped</code>");
		annotationsConect.add("<code>@ApplicationScoped/Singleton</code> can be replaced with <code>@com.google.inject.Singleton</code>");

		/*pageContent.add("<code>@javax.ejb.MessageDrivenBean</code> unfortunately requires a JMS Provider. <a target=\"_blank\" href=\"https://www.rabbitmq.com/\">I recommend RabbitMQ</a>, <br/>" +
		                "<a target=\"_blank\" href=\"http://camel.apache.org/guice-jms-example.html\">Apache Camel is also supported</a>");*/


		/*pageContent.add("<code>@javax.ejb.ActivationConfigProperty</code> would be replaced with your JPMS MQ Provider");*/
		annotationsConect.add("<code>@javax.ejb.PostActivate</code>, the annotation should be removed, and the class should implement IGuicePostStartup." +
		                      "<br/>This class should be registered with SPI.");

		JSFDisplayPart jsf = new JSFDisplayPart();

		DefaultSmartWizard wizard = new DefaultSmartWizard("migrationWizard");

		wizard.addStep(new SmartWizardStep(aboutMigration, new SmartWizardStepItem("Zero to Hero", new SmallText("Switch instantly to JPMS"))));
		wizard.addStep(new SmartWizardStep(annotationsConect, new SmartWizardStepItem("Annotations", new SmallText("Considerations for annotations"))));


		wizard.addStep(new SmartWizardStep(jsf, new SmartWizardStepItem("Dual Execution", new SmallText("Run JSF alongside JWebMP"))));

		stepper.add(wizard);

		return stepper;
	}

	private Div buildGuiceProjectsMigration()
	{
		Div annotationsConect = new Div();

		annotationsConect.add(new H3<>("Existing Guice Projects"));
		annotationsConect.add(
				"Existing Guice projects can be ported over by simply implementing your Guice Modules with IGuiceModule, and then creating an SPI for the module to be loaded.");
		annotationsConect.add(buildIGuiceModuleTree());

		annotationsConect.add("<br/>For JPMS you specify your module in your module-info file. You do not need to expose the package these classes are located.");
		annotationsConect.add(new Code<>().setText("provides IGuiceModule with my.package.guicemodule"));

		annotationsConect.add("<br/>The entire Guice system is available through the GuiceContext object.");
		annotationsConect.add(new Code<>().setText("GuiceContext.get(MyClass.class);"));

		annotationsConect.add("This fetches the appropriate class directly from Guice, so you don't have to annotate or inject absolutely everything in your application.<br/>" +
		                      "");

		annotationsConect.add("You can read more about the controls for the Guice Injection in the Injection menu.");

		return annotationsConect;
	}

	private Div buildSpringProjectsMigration()
	{
		Div spring = new Div();

		spring.add(new H3<>("Spring Annotations"));
		spring.add(
				"This entire system utilizes Guice and SPI. A guice module <code>guice-spring</code> can be used for side-by-side execution" +
				"<br/>No annotations are used to perform any function in the core, meaning that no classpath scanning is actually necessary." +
				"</br>When using persistence/JCache/etc, these are separate modules that are added onto the system, and only the paths necessary are ever scanned for matching files keeping it small, fast, and non-intrusive.");
		spring.add("<br/><a target=\"_blank\" href=\"https://github.com/google/guice/wiki/SpringComparison\">Why Guice - Spring vs Guice</a>)");
		spring.add("Below is a scrollable table from a DZone article (With Guiced Injection addition) to see the differences");

		DivSimple<?> scrolly = new DivSimple<>();
		DefaultSlimScrollFeature scroll = new DefaultSlimScrollFeature(scrolly);
		scroll.getOptions()
		      .setHeight("400px");

		spring.add(scrolly);

		try
		{
			scrolly.add(new ImportFile("spring_table.html", QuickStartScreen.class.getResourceAsStream("spring_table.html")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		spring.add("You can read more about the controls for the Guice Injection in the Injection menu.");

		return spring;
	}

	private void addDefaultJRE8Stuffs(Div mavenContent)
	{
		mavenContent.add(new Link<>("https://github.com/GedMarc/JWebMP-Examples-Undertow-HelloWorld", "_blank").add(new BSButtonLightOutline<>().setText("View In GitHub")));
		mavenContent.add("<br/>For JDK 8 and JPMS, JWebMP is fully <a target=\"_blank\" href=\"http://dcevm.github.io/\"> " +
		                 "DCEVM</a> Compatible allowing the ultra hot swap experience");
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

		pageContent.add(new H3("Here we are..."));
		addSourceToContainer(QuickStartScreen.class, "jre11_open_module_info.txt", SourceCodeLanguages.JS, pageContent);
		pageContent.add("And you're done. Run your first program!");

		BSButtonPrimaryOutline openMoreButton = new BSButtonPrimaryOutline<>().setText("Read More about JPMS");
		BSButtonPrimaryOutline openStrictButton = new BSButtonPrimaryOutline<>().setText("Strict JPMS");

		DivSimple<?> more = new DivSimple<>();
		DivSimple<?> strict = new DivSimple<>();

		BSCollapse.link(openMoreButton, more, true);
		BSCollapse.link(openStrictButton, strict, true);

		BSRow row = new BSRow();

		row.add(openMoreButton);
		row.add(openStrictButton);
		pageContent.add(row);

		pageContent.add(more);
		pageContent.add(strict);

		more.add(new H3("What is this?"));
		more.add("It all starts with a module-info.java file in the root of your sources, structured a little differently to any other class");
		more.add(new Code<>().setText("In modules you provide your pages using the 'provides' syntax"));

		more.add(new H3<>("The Lazy Way"));
		more.add("The lazy way is the quickest option, and it quite simply just requires you to provide a page.");
		addSourceToContainer(QuickStartScreen.class, "jre11_open_module_info.txt", SourceCodeLanguages.JS, more);


		strict.add(new H3<>("The Proper Way"));
		strict.add("This is the new part about Java, and I hope you love it as much as I do.");
		addSourceToContainer(QuickStartScreen.class, "jre11_module_info.txt", SourceCodeLanguages.JS, strict);

		strict.add("This is a strict module, meaning it is going to adhere to all the rules of JPMS." +
		           "<br/>With Guice as the injection library, as well as being a named module, you have to open the package so that Guice can do what it likes.");

		strict.add(new Code<>().setText("opens my.package.that.has.classes to com.google.guice"));
		strict.add(
				"The thing about this is, not a lot of people like to add to their command line, but in JPMS, this is required for JVM fiddling and that injection libraries stuff.");
		strict.add(new Code<>().setText("java -jar --add-opens=java.base/java.lang=com.google.guice<br/><br/>"));

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
		dScroll.addFeature(new DefaultSlimScrollFeature(dScroll));

		return d;
	}

	private JSTree buildIGuiceModuleTree()
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

		JSTreeListItem<?> file1 = new JSTreeListItem<>("com.guicedee.guicedinjection.interfaces.IGuiceModule", new JSTreeNodeOptions<>().setIcon("far fa-file"));


		folder2.add(file1);

		folder1.add(folder2);

		rootItem.add(folder1);

		directoryStructureExample.addRoot(rootItem);
		directoryStructureExample.setID("iguicemoduletree");

		directoryStructureExample.getCss()
		                         .getBackground()
		                         .setBackgroundColor$(ColourNames.Black);
		return directoryStructureExample;
	}

	private void addDefaultJRE11Stuffs(Div mavenContent)
	{
		mavenContent.add(new BSButtonLightOutline<>().setText("View In GitHub"));
	}

	private Div buildGoJRE11()
	{
		Div d = new Div();

		BSCardBody<?> commandLine = new BSCardBody<>();
		BSCardBody<?> pureMaven = new BSCardBody<>();
		BSCardBody<?> packagedJar = new BSCardBody<>();
		BSCardBody<?> intellij = new BSCardBody<>();
		BSCardBody<?> eclipse = new BSCardBody<>();
		BSCardBody<?> netbeans = new BSCardBody<>();

		d.add(new H3("Packaging and Running"));
		d.add("Below you can find some assists to help in your development");

		commandLine.add(new BSButtonLightOutline<>().setText("Download Arguments File"));
		JQSourceCodePrettify<?> dInner = addSourceToContainer(QuickStartScreen.class, "jre11_argumentsfile.txt", XML, commandLine);
		commandLine.add("You can run straight from the command line");
		JQSourceCodePrettify<?> dInner2 = addSourceToContainer(QuickStartScreen.class, "jre11_argumentsfilerun.txt", XML, commandLine);

		BSAccordion<?> ideAccordion = new BSAccordion<>();
		BSAccordionCollection card4 = ideAccordion.addCard("Command Line", commandLine, true);
		BSAccordionCollection card5 = ideAccordion.addCard("Pure Maven", pureMaven, true);
		BSAccordionCollection card6 = ideAccordion.addCard("Packaged JAR", packagedJar, true);
		BSAccordionCollection card3 = ideAccordion.addCard("IDE - IntelliJ", intellij, true);
		BSAccordionCollection card1 = ideAccordion.addCard("IDE - Eclipse", eclipse, true);
		BSAccordionCollection card2 = ideAccordion.addCard("IDE - Netbeans", netbeans, true);


		card1.getHeadingButton()
		     .addClass("text-white w-100");

		card2.getHeadingButton()
		     .addClass("text-white w-100");
		card3.getHeadingButton()
		     .addClass("text-white w-100");
		card4.getHeadingButton()
		     .addClass("text-white w-100");
		card5.getHeadingButton()
		     .addClass("text-white w-100");
		card6.getHeadingButton()
		     .addClass("text-white w-100");

		d.add(ideAccordion);

		return d;
	}
}
