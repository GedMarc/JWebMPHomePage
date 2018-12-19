package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DefaultDisplayWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonPrimaryOutline;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

public class AboutJWebMPScreen
		extends DisplayScreen<AboutJWebMPScreen>
{
	public AboutJWebMPScreen()
	{
		super("About JWebMP");
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(BSContainerOptions.Container_Fluid);

		Div left = new Div();
		Div right = new Div();
		left.addClass("col-12 col-md-6");
		right.addClass("col-12 col-md-6");

		Div whatIsIt = new Div();
		Div dependencies = new Div();
		Div runsOnList = new Div();
		Div pushMechanism = new Div();
		Div testing = new Div();


		DefaultDisplayWizard wizard = new DefaultDisplayWizard("aboutjwebmpwizard");

		wizard.addStep(new SmartWizardStep<>(whatIsIt,new SmartWizardStepItem("What Is It",new SmallText("About JWebMP"))));
		wizard.addStep(new SmartWizardStep<>(dependencies,new SmartWizardStepItem("Dependencies",new SmallText("A list of requirements"))));
		wizard.addStep(new SmartWizardStep<>(runsOnList,new SmartWizardStepItem("Runs On",new SmallText("Compatibility Guide"))));

		container.add(wizard);



		BSRow row = new BSRow();
		row.add(left);
		row.add(right);


		whatIsIt.add(row);
		left.add(buildWhyPanel());
		right.add(buildTempalteFreeDev());
		left.add(buildHencePanel());


		dependencies.add(buildDependencies());


		testing.add(buildTestPanel());

		runsOnList.add(buildCompatibilityList());

		pushMechanism.add(buildPushPanel());

		pushMechanism.add(addWhiteAlert("Cordova/PhoneGap can be used for advanced device functions"));
		return container;
	}

	private Div buildWhyPanel()
	{
		DisplayCard card = new DisplayCard();
		//card.addClass("col-12 col-md-6");
		card.addCardHeader(new H2("Next Level Development").toString(true));
		Div div = card.addCardBody();
		div.add("JWebMP is a Java Web Framework that can provide any web framework available on the internet without any interference." +
		        "<br/>Designed to serve Modern, Efficient, and Server-Side Driven");

		Div moreDiv = new Div();
		BSButtonPrimaryOutline button = new BSButtonPrimaryOutline<>().setText("Read More");
		moreDiv.add(button);
		Div readMoreDiv = new Div();
		moreDiv.add(readMoreDiv);
		div.add(moreDiv);
		BSCollapse.link(button,readMoreDiv,true);

		readMoreDiv.add("<br/><br/>Built in JPMS, Develop Enterprise Scale Applications quicker than ever before, with less code than you can imagine." +
		                "<br/><br/>You can seamlessly move from any JSF to JWebMP, even better,  from the straight JRE8 to JPMS in a single change");

		readMoreDiv.add(new H3("This framework is completely open source and 100% free."));
		readMoreDiv.add("There is a donate button in the top right, this is only location, I don't want to burst it out there. " +
		        "<br/>If you like this product, please help! Pull Requests, Issue Logging, Donating, anything.");
		return card;
	}

	private Div buildHencePanel()
	{
		DisplayCard card = new DisplayCard();
	//	card.addClass("col-12 col-md-6");
		card.addCardHeader(new H2("Only what you need").toString(true));
		Div div = card.addCardBody();
		div.add("JWebMP is 100% Modular, and works perfectly with JLink application. Each module is strictly named and added to your application to configure the rendering.");

		Div moreDiv = new Div();
		BSButtonPrimaryOutline button = new BSButtonPrimaryOutline<>().setText("Read More");
		moreDiv.add(button);
		Div readMoreDiv = new Div();
		moreDiv.add(readMoreDiv);
		div.add(moreDiv);
		BSCollapse.link(button,readMoreDiv,true);


		readMoreDiv.add("Basic injections such as <code>@Inject ReadableUserAgent</code> and many others that are always available, you can always do whatever you need to ");
		readMoreDiv.add("Security is paramount, and the framework allows and caters for all forms of security integration. Local Storage for instance places a unique identifier per browser instance," +
		        "<br/> and IP Locking, SAML and everything else is incredibly quick to implement.");
		return card;
	}

	private Div buildTempalteFreeDev()
	{
		DisplayCard card = new DisplayCard();
	//	card.addClass("col-12 col-md-4");
		card.addCardHeader(new H2("Template Free Development").toString(true));
		Div div = card.addCardBody();
		//div.add(new H3<>("Template Free Development"));
		div.add("The system constructs the HTML, CSS and JavaScripts dynamically (yes according to browser and even device) to ensure that only the correct scripts get delivered. With abstraction and injection points, you can easily manipulate any item to produce the output that you want.");

		Div moreDiv = new Div();
		BSButtonPrimaryOutline button = new BSButtonPrimaryOutline<>().setText("Read More");
		moreDiv.add(button);
		Div readMoreDiv = new Div();
		moreDiv.add(readMoreDiv);
		div.add(moreDiv);
		BSCollapse.link(button,readMoreDiv,true);

		readMoreDiv.add("Being able to render the complete output of the HTML, JavaScript and CSS of any component at any level and stage grants you an unbelievable amount of many, many benefits. Let your imagination run wild with that concept for a bit. Exactly how much coverage in your tests can you get? Want to render a component in JWebMP and push it to JSF?");
		readMoreDiv.add("Of course, you can still use File Templates (though this system isn't based on it at all) with variables and scoping to continue using HTML and Web Content files to render your information. ");

		return card;
	}


	private Div buildDependencies()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Core Libraries"));
		BSTable<?> table = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                  .addClass(Table_Hover);
		table.setSmall(true);
		table.setBordered(true);
		table.setStriped(true);

		table.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                       .add(new TableHeaderCell<>("Version"))
		                                                       .add(new TableHeaderCell<>("Info"))
		                                                       // .add(new TableHeaderCell<>("Maven Property"))
		                                                       .add(new TableHeaderCell<>("Purpose"))));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("ClassGraph"))
		                                       .add(new TableCell<>("4.6.9"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/lukehutch/fast-classpath-scanner\" target=\"_blank\">Link</a>"))
		                                       //  .add(new TableCell<>("fastclasspath.version"))
		                                       .add(new TableCell<>("Scanner")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Google Guice"))
		                                       .add(new TableCell<>("4.2.3 - JWebMP"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/google/guice\" target=\"_blank\">Link</a>"))
		                                       //  .add(new TableCell<>("guice.version"))
		                                       .add(new TableCell<>("DI Provider. Locally strictly named")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Jackson JSON"))
		                                       .add(new TableCell<>("2.9.8 - JWebMP"))
		                                       .add(new TableCell<>("<a href=\"https://github.com/FasterXML/jackson\" target=\"_blank\">Link</a>"))
		                                       //   .add(new TableCell<>("jackson.version"))
		                                       .add(new TableCell<>("JSON API - Locally strictly named")));

		div.add(table);

		div.add(new H3("Core Web Libraries"));

		BSTable<?> webTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                     .addClass(Table_Hover);
		webTable.setSmall(true);
		webTable.setBordered(true);
		webTable.setStriped(true);

		webTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                          .add(new TableHeaderCell<>("Version"))
		                                                          //    .add(new TableHeaderCell<>("Info"))
		                                                          .add(new TableHeaderCell<>("Source"))
		                                                          .add(new TableHeaderCell<>("Purpose"))));

		webTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("JQuery"))
		                                          .add(new TableCell<>("3.3.1"))
		                                          //  .add(new TableCell<>("JQueryPageConfigurator.class"))
		                                          .add(new TableCell<>("bower"))
		                                          .add(new TableCell<>("JavaScript API")));

		webTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Angular"))
		                                          .add(new TableCell<>("1.7.6"))
		                                          //   .add(new TableCell<>("AngularPageConfigurator.class"))
		                                          .add(new TableCell<>("bower"))
		                                          .add(new TableCell<>("Data Binder")));

		div.add(webTable);

		div.add(new H3("Servlet Libraries"));

		BSTable<?> servletInfoTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                             .addClass(Table_Hover);
		servletInfoTable.setSmall(true);
		servletInfoTable.setBordered(true);
		servletInfoTable.setStriped(true);

		servletInfoTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                                  .add(new TableHeaderCell<>("Version"))
		                                                                  //    .add(new TableHeaderCell<>("Info"))
		                                                                  .add(new TableHeaderCell<>("Source"))
		                                                                  .add(new TableHeaderCell<>("Purpose"))));

		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("ua-detector"))
		                                                  .add(new TableCell<>("0.9.22 - JWebMP"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Device Info Provider - Strictly Named")));

		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("commons-lang3"))
		                                                  .add(new TableCell<>("3.7 - JWebMP"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Assists with String manipulation - Strictly Named")));

		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("commons-text"))
		                                                  .add(new TableCell<>("1.4 - JWebMP"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Assists with String manipulation - Strictly Named")));

		servletInfoTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("commons-io"))
		                                                  .add(new TableCell<>("2.6 - JWebMP"))
		                                                  //  .add(new TableCell<>("Accessed in Page.class"))
		                                                  .add(new TableCell<>("maven"))
		                                                  .add(new TableCell<>("Assist with IO usage - Strictly Named")));

		div.add(servletInfoTable);

		//div.add(new Image("images/guiceinjection/ModuleDepedency.png").addClass("img-fluid d-block"));
		//div.add("<br/>");
		//div.add(new Image("images/guiceinjection/CompleteModuleDepedency.png").addClass("img-fluid d-block"));

		//div.add(new H4<>("Total Standalone Size : 3.8MB"));

		return card;
	}

	private Div buildTestPanel()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Get That Coverage"));
		div.add("Test Anything, Integration and Unit Testing - Straight Out the Box!<br/> You can view all the JUnit 5 test and Selenium Integration Cases currently built and their status using the GitHub, TeamCity and SonarQube links. These allow everyone the peace of mind, and stability that end-to-end testing systems provide.<br/>");
		return card;
	}

	private Div buildCompatibilityList()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();

		div.add(new H3("Doesn't Interfere"));
		div.add("You can easily run JWebMP alongside any project without causing any interference." +
		        "<br/>Run next to JSF with the simple inclusion of a module, and a small faces-config update, " +
		        "<br/>As a 'Add to use' platform, only modules you add are ever used, and even they can be disabled (as with this site).");

		div.add(new H3("Compatibility List"));

	/*	div.add("You can also run your web sites through a provided boot from a static void main. " +
		        "<br/>Unit Testing, Integration Testing, and Developing become instantaneously available.");

		addSourceToContainer(AboutJWebMPScreen.class, "staticvoidmain.txt", SourceCodeLanguages.Java, div);
*/
		BSTable<?> compatibilityTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                               .addClass(Table_Hover);
		compatibilityTable.setSmall(true);
		compatibilityTable.setBordered(true);
		compatibilityTable.setStriped(true);

		compatibilityTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                                    //
		                                                                    .add(new TableHeaderCell<>("Info")
				                                                                         .add(new TableHeaderCell<>("Notes")))));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Glassfish"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\" target=\"_blank\">Web Fragment Specification</a>"))
		                                                    .add(new TableCell<>(
				                                                    "Simply including the library sets it up"))
		                      );

/*
		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Glassfish 5.x"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Web Fragment Specification</a>")));
*/

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Payara"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Web Fragment Specification</a>"))
		                                                    .add(new TableCell<>(
				                                                    "Simply including the library sets it up")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("JBoss/WildFly/EAP "))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\" target=\"_blank\">Web Fragment Specification</a>"))
		                                                    .add(new TableCell<>(
				                                                    "Simply including the library sets it up")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Tomcat"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\" target=\"_blank\">Web Fragment Specification</a>"))
		                                                    .add(new TableCell<>(
				                                                    "Simply including the library sets it up")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Wildfly Swarm"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\" target=\"_blank\">Initialized with GuiceContext.inject();</a>"))
		                                                    .add(new TableCell<>(
				                                                    "Simply including the library sets it up")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("MicroProfile"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\" target=\"_blank\">Initialized with GuiceContext.inject();</a>"))
		                                                    .add(new TableCell<>(
				                                                    "Simply including the library sets it up")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Undertow"))
		                                                    //.add(new TableCell<>("Plugin Required"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\" target=\"_blank\">Initialized with GuiceContext.inject();</a>"))
		                                                    .add(new TableCell<>(
				                                                    "This library requires the module <strong>jwebmp-undertow</strong> to register the filters")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Jetty"))
		                                                    //.add(new TableCell<>("Plugin Required"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\" target=\"_blank\">Initialized with GuiceContext.inject();</a>"))
		                                                    .add(new TableCell<>(
				                                                    "You need to call GuiceContext.inject() during your boot cycle.")));

		div.add(compatibilityTable);
		return card;
	}

	private Div buildPushPanel()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add("Push and Web Sockets are standard out the box, and absolutely everything available to every Ajax Call and Response is available in the exact same coding manner.<br/><br/> " +
		        "Push Groups at a Global Injection Level allow you to push responses directly from your container layer, or asynchronously from ExecutionServices in your WAR, as we do on this site.<br/><br/> You can also enable Distributed Push Notifications utilizing HazelCast or a similar technology like EhCache with ease.");
		return card;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("About JWebMP"));
		return crumbs;
	}

	private Div buildInjectionsPanel()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add("Using Google's Guice Injection Framework, paired with Guice Context Handler, and you get a complete JDK 10 Injection set as well as Built-In Multi-ClassPath Injection Configuration." +
		        "Custom Path Object Scanning from ClassGraph, RegEx Servlet Bindings, Complete Programmatic AOP, and go for a completely modular entirely optional near-full range of EE Capabilities with MQ, JTA, and much much more." +
		        "Everything is at your fingertips.");
		div.add("This library is also completely non-invasive. Easily pull beans directly from the initial context in your enterprise environment, with no interference on existing Servlets including JSP.");
		return card;
	}

	private Div buildCompletelyCustomizablePanel()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add("Everything can be optimized, <strong><u><i>EASILY</i></u></strong>, from specific tags being displayed for certain browsers and/or device combinations, to how the system boots and operates. Even your persistence units can now be configured programmatically with complete support for JTA.");
		div.add("Optimize your startup, Build up your modules, and jump straight into JPMS with the the Service Loader mechanisms.");
		div.add("This allows complete integration into any system including Enterprise Edition 7 and up (Tomcat, Glassfish, Payara, Wildfly, EAP etc) but also Embedded Containers such as Undertow. ");
		div.add("");
		return card;
	}
}
