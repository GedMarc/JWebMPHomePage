package com.jwebmp.examples.demos.homepage.display.home;

import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.home.parts.*;
import com.jwebmp.examples.demos.homepage.display.login.LoginPart;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.cards.BSCard;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.skycons.SkyIcon;
import com.jwebmp.plugins.skycons.Skycon;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.guicedinjection.GuiceContext.*;

public class HomePage
		extends DisplayScreen
{
	BSRow displayRow = new BSRow();
	BSColumn right = new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_Sm_12);
	BSColumn left = new BSColumn<>(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_Sm_12);

	String version = Runtime.class.getPackage()
	                              .getImplementationVersion();

	public HomePage()
	{
		super(HTML_TAB +
		      HTML_TAB +
		      "Welcome to JWebMP!" +
		      HTML_TAB +
		      "<small><i>" +
		      "JDK " + "" +
		      System.getProperty("java.version") + " running on " +
		      System.getProperty("os.name")
		      + "</i></small>");
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer<?> container = new BSContainer<>();
		container.setContainerType(BSContainerOptions.Container_Fluid);

		container.add(new ButtonRowPart<>());

		displayRow.add(left);
		displayRow.add(right);

		displayRow.resetHorizontalSinks();
		container.add(displayRow);

		if (getPage().isMobileOrSmartTablet())
		{
			orderForMobile();
		}
		else
		{
			orderForDesktop();
		}

		return container;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Home Page"));
		return crumbs;
	}

	private void orderForMobile()
	{
		orderForDesktop();
		/*right.getChildren()
		     .clear();
		left.getChildren()
		    .clear();

		left.add(buildSnap2());

		if (!GuiceContext.get(SessionProperties.class)
		                 .isLoggedIn())
		{
			left.add(new LoginPart());
		}
		left.add(new MavenPomPart());
*/
	}

	private void orderForDesktop()
	{
		right.getChildren()
		     .clear();
		left.getChildren()
		    .clear();

		left.add(buildContinuousIntegrationPane());

		left.add(buildSnap2());

		left.add(addWhiteAlert("<strong>JPMS that supports JPA, JTA and JCache</strong>"));

		left.add(new LoginPart());
		left.add(addWhiteAlert(
				"<strong>I use Google Guice as the injection provider. Mainly for zero classpath scanning. But it is also still just that much better than everything else.</strong>" +
				//"<strong>&quot;Thankfully, Guice has no beans. NO BEANS! How many beans do we have to remember and disambiguate before it is too much? Javabeans, Enterprise Javabeans, Spring Beans, Coffee Beans, Mr. Bean, and I might still have missed a few others!&quot;</strong>" +
				"<br/><a target=\"_blank\" href=\"https://dzone.com/articles/an-opinionless-comparison-of-spring-and-guice\">DZone An Opinionless Comparison of Spring and Guice</a>" +
				"<br/><a target=\"_blank\" href=\"https://github.com/google/guice/wiki/SpringComparison\">Why Guice - Spring vs Guice</a>"
		                      ));

		left.add(get(GalleryPart.class));
/*
		right.add(addWhiteAlert("<strong>Testable End-To-End. No more struggling with getting test cases up and running!</strong>" +
		                        "<br/><a target=\"_blank\" href=\"https://jwebmp.com/sonar/\">View SonarQube for Quality Control and Test Case Output</a>"));
*/

		right.add(addBlueAlert("Version 0.63.0.60 RC2 - 2019/02/04"));

		right.add(get(MavenPomPart.class));

		right.add(buildSnap4());

		right.add(addWhiteAlert(
				"<strong>Go back to coding pure Java with a 100% Domain Driven Design System, and <i><u>feel</u></i> the enormous performance benefit that comes with it </strong>" +
				""));
		right.add(buildSnap3());
		right.add(addWhiteAlert("<strong>Use JPMS and Private Modules for proper encapsulation without the weight of running an EE/MicroProfile server.</strong>"));

		right.add(new ContactUsPart());
		right.add(new ReasonsWhyPart());

		right.add(buildOpenSourceSponsors());
	}

	private BSCard buildSnap2()
	{
		BSCard<?> card = new BSCard();

		card.addStyle("display: -webkit-box;");
		card.addCardBody()
		    .addClass("col-2")
		    .addStyle("padding-top:0px;padding-bottom:0px;padding-left:10px;")
		    .add(new Skycon("skyconid1", 85, SkyIcon.Wind));

		FontAwesomeList list = new FontAwesomeList();
		addIconList(list, "Runs on everything with Google Guice", FontAwesomeIcons.check, "#6f42c1");
		addIconList(list, "Fully Optimized JDK 11 utilizing SPI", FontAwesomeIcons.check, "#6f42c1");
		addIconList(list, "100% Completely Modular", FontAwesomeIcons.check, "#6f42c1");

		card.addCardBody()
		    .addClass(" col-10")
		    .add(list);

		card.addClass(BSContainerOptions.No_Gutters);
		card.addStyle("margin-bottom:1rem;");

		return card;
	}

	private BSCard buildSnap4()
	{
		BSCard<?> card = new BSCard();

		FontAwesomeList list = new FontAwesomeList();
		list.addItem("SonarQube Quality Gate", new FontAwesome<>().setIcon(FontAwesomeIcons.check));
		list.addItem("Open Build and CI", new FontAwesome<>().setIcon(FontAwesomeIcons.check));
		list.addItem("Pull Requests Always Welcome", new FontAwesome<>().setIcon(FontAwesomeIcons.check));

		card.addCardBody()
		    .add(list);

		//card.addFooter("<a href=\"https://paypal.me/MarcMagon\" target=\"_blank\"><strong>Please consider donating towards the development</strong></a>");

		card.addStyle("margin-bottom:1rem;");
		return card;
	}

	private BSCard buildSnap3()
	{
		BSCard<?> card = new BSCard();

		FontAwesomeList list = new FontAwesomeList();
		addIconList(list, "JPA JTA JCache", FontAwesomeIcons.check, "#6f42c1");
		addIconList(list, "Hazelcast EhCache Hibernate BTM C3P0", FontAwesomeIcons.check, "#6f42c1");
		//addIconList(list, "RabbitMQ HTTP2 WS Atmosphere", FontAwesomeIcons.check, "#6f42c1");
		addIconList(list, "Glassfish, Payara, Tomcat, TomEE, Wildfly, Swarm, MP", FontAwesomeIcons.check, "#6f42c1");
		card.addCardBody()
		    .add(list);

		card.addStyle("margin-bottom:1rem;");

		return card;
	}

	private BSCard buildContinuousIntegrationPane()
	{
		ContinousIntegrationPart openSourcePane = new ContinousIntegrationPart();
		return openSourcePane;
	}

	private BSCard buildOpenSourceSponsors()
	{
		BSCard<?> card = new BSCard();
		card.addClass("mt-2 pb-3");
		card.addCardBody()
		    .add(addWhiteAlert("Completely Open Source" +
		                       "</br>We are supported for UI Testing by BrowserStack"));
		card.setTextCenter(true);
		card.addCardImageBottom("images/BrowserStack.png")
		    .setCentered()
		    .addAttribute("style", "max-height:50%;max-width:50%");
		return card;
	}
}
