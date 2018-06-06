package com.jwebmp.examples.demos.homepage.display.about;

import com.jwebmp.Page;
import com.jwebmp.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.guiceinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;

import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

public class AboutJWebMPScreen
		extends DisplayScreen<AboutJWebMPScreen>
{
	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(BSContainerOptions.Container_Fluid);

		BSRow row = new BSRow();

		BSColumn column1 = new BSColumn(BSColumnOptions.Col_Md_8);
		BSColumn column2 = new BSColumn(BSColumnOptions.Col_Md_4);

		Page page = GuiceContext.get(Page.class);
		if (page.isMobileOrSmartTablet())
		{
			column1.add(new MintonPanel<>("Compat", buildCompatibilityList(), "bg-primary").setShowHeader(false));
			column1.add(new MintonPanel<>("What is JWebMP?", buildWhyPanel(), "bg-primary").setShowHeader(false));
			column1.add(new MintonPanel<>("Testable First", buildTestPanel(), "bg-primary").setShowHeader(false));
			column1.add(new MintonPanel<>("How it's done", buildHowItsDone(), "bg-primary").setShowHeader(false));
			column1.add(new MintonPanel<>("Injection Control", buildInjectionsPanel(), "bg-primary").setShowHeader(false));

			column2.add(new MintonPanel<>("Push Me Baby", buildPushPanel(), "bg-primary").setShowHeader(false));
			column2.add(new MintonPanel<>("Fully Customizable", buildCompletelyCustomizablePanel(), "bg-primary").setShowHeader(false));
		}
		else
		{
			column1.add(new MintonPanel<>("What is JWebMP?", buildWhyPanel(), "bg-primary").setShowHeader(false));
			column1.add(new MintonPanel<>("How it's done", buildHowItsDone(), "bg-primary").setShowHeader(false));
			column1.add(new MintonPanel<>("Testable First", buildTestPanel(), "bg-primary").setShowHeader(false));
			column1.add(new MintonPanel<>("Injection Control", buildInjectionsPanel(), "bg-primary"));

			column2.add(new MintonPanel<>("Compat", buildCompatibilityList(), "bg-primary").setShowHeader(false));
			column2.add(new MintonPanel<>("Push Me Baby", buildPushPanel(), "bg-primary").setShowHeader(false));

			column2.add(new MintonPanel<>("Fully Customizable", buildCompletelyCustomizablePanel(), "bg-primary").setShowHeader(false));
		}
		row.add(column1);
		row.add(column2);

		container.add(row);
		return container;
	}

	private Div buildCompatibilityList()
	{
		Div div = new DivSimple();
		div.add(new H3("Compatibility List"));


		BSTable<?> compatibilityTable = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                               .addClass(Table_Hover);
		compatibilityTable.setSmall(true);
		compatibilityTable.setBordered(true);
		compatibilityTable.setStriped(true);

		compatibilityTable.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Name"))
		                                                                    //  .add(new TableHeaderCell<>("Notes"))
		                                                                    .add(new TableHeaderCell<>("Info"))));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Glassfish 4.x"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Web Fragment Specification</a>")));


		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Glassfish 5.x"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Web Fragment Specification</a>")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Payara 141^"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Web Fragment Specification</a>")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("WildFly 9^"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Web Fragment Specification</a>")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Tomcat 7^"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Web Fragment Specification</a>")));

		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Wildfly Swarm 10^"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Initialized with GuiceContext.inject();</a>")));


		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Payara MicroProfile"))
		                                                    //.add(new TableCell<>("Out the Box"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Initialized with GuiceContext.inject();</a>")));


		compatibilityTable.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("Undertow Embedded Container"))
		                                                    //.add(new TableCell<>("Plugin Required"))
		                                                    .add(new TableCell<>(
				                                                    "<a href=\"https://blogs.oracle.com/swchan/servlet-30-web-fragmentxml\">Initialized with GuiceContext.inject();</a>")));

		div.add(compatibilityTable);
		return div;
	}

	private Div buildWhyPanel()
	{
		DivSimple<?> div = new DivSimple<>();
		div.add("JWebMP is a Java Web and Mobile Hybrid Framework designed to be Modern, Efficient, Server-Side Driven, with absolute optimizations in both Delivery of Web Content, and Java Development of the System. It is designed purely in a Domain Driven Manner and allows you to finally build Request Driven, Tiny-Session enterprise application, whilte still  completely supporting MDE and BDE in every way.");

		div.add("Everything is completely open source! This means that any item you find can be logged, traced, and implemented in the most convient way.");
		return div;
	}

	private Div buildTestPanel()
	{
		Div div = new DivSimple();
		div.add(new H3("Get That Coverage"));
		div.add("Test Anything, Integration and Unit Testing - Straight Out the Box!<br/> You can view all the JUnit 5 test and Selenium Integration Cases currently built and their status using the GitHub, TeamCity and SonarQube links. These allow everyone the peace of mind, and stability that end-to-end testing systems provide.<br/>");
		return div;
	}

	private Div buildHowItsDone()
	{
		Div div = new DivSimple();
		div.add(new H3<>("Template Free Development"));
		div.add("The system constructs the HTML, CSS and JavaScripts dynamically (yes according to browser and even device) to ensure that only the correct scripts get delivered. With abstraction and injection points, you can easily manipulate any item to produce the output that you want.");
		div.add("Being able to render the complete output of the HTML, JavaScript and CSS of any component at any level and stage grants you an unbelievable amount of many, many benefits. Let your imagination run wild with that concept for a bit. Exactly how much coverage in your tests can you get? Want to render a component in JWebMP and push it to JSF?");
		div.add("Of course, you can still use File Templates (though this system isn't based on it at all) with variables and scoping to continue using HTML and Web Content files to render your information. ");
		return div;
	}

	private Div buildInjectionsPanel()
	{
		Div div = new DivSimple();
		div.add("Google's Guice and the Guice Context Handler provide complete and dynamic JDK 8 and 9 Injection as well as Built-In Multi-ClassPath Injection Configuration, Custom Path Object Scanning from FastClasspathScanner, RegEx Servlet Bindings, Complete AOP, and an  entirely optional near-full range of EE Capabilities with MicroProfile, everything is at your fingertips.<br/><br/><strong>Best of All</strong>, Adheres to the applicable JSRs! ");
		div.add("Completely Non-Invasive, Easily pull beans directly from Weld should you wish to do so (see the Injection Screen), With no interference on existing Servlets including JSP.");
		return div;
	}

	private Div buildPushPanel()
	{
		Div div = new DivSimple();
		div.add("With Atmosphere Push, Web Sockets are standard out the box, and absolutely everything available to every Ajax Call and Response is available in the exact same coding manner.<br/><br/> " + "Push Groups at a Global Injection Level allow you to push responses directly from your container layer, or asynchronously from ExecutionServices in your WAR, as we do on this site.<br/><br/> You can also enable Distributed Push Notifications utilizing HazelCast or a similar technology with ease.");
		return div;
	}

	private Div buildCompletelyCustomizablePanel()
	{
		Div div = new DivSimple();
		div.add("Everything can be customized *cough* optimized, <strong><u><i>EASILY</i></u></strong>, from specific tags being displayed for certain browsers and/or device combinations, to how the system boots and operates.");
		div.add("Optimize your startup utilizing features such as White Listing packages to be scanned, automated and manual asynchronous boot sequence executions, and easily manipulate the scanner or configuration as first item executions with the help of the Service Loader mechanisms.");
		div.add("This allows complete integration into any system including Enterprise Edition 7 and up (Tomcat, Glassfish, Payara, Wildfly, EAP etc) but also Embedded Containers such as Undertow. ");
		div.add("");
		return div;
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

	private Div buildBaseCapabilitiesPanel()
	{
		Div div = new DivSimple();
		div.add("");
		return div;
	}
}
