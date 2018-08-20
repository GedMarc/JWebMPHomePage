package com.jwebmp.examples.demos.homepage.display.home;

import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.AlertMessage;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.home.parts.*;
import com.jwebmp.examples.demos.homepage.display.login.LoginPart;
import com.jwebmp.guicedinjection.GuiceContext;
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
import com.jwebmp.plugins.fontawesome5.options.FontAwesomeSizes;
import com.jwebmp.plugins.skycons.SkyIcon;
import com.jwebmp.plugins.skycons.Skycon;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;

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

	private void orderForMobile()
	{
		right.getChildren()
		     .clear();
		left.getChildren()
		    .clear();

		left.add(buildSnap2());

		if (!GuiceContext.getInstance(SessionProperties.class)
		                 .isLoggedIn())
		{
			left.add(new LoginPart());
		}
		left.add(new MavenPomPart());

	}

	private void orderForDesktop()
	{
		right.getChildren()
		     .clear();
		left.getChildren()
		    .clear();

		left.add(buildSnap2());

		if (!GuiceContext.getInstance(SessionProperties.class)
		                 .isLoggedIn())
		{
			left.add(new LoginPart());
		}

		left.add(buildOpenSourceSponsors());
		left.add(buildSnap4());
		left.add(buildContinuousIntegrationPane());

		right.add(new MavenPomPart());
		right.add(buildSnap3());
		right.add(new AlertMessage("<strong>Code Icons " + FontAwesome.icon(FontAwesomeIcons.code, FontAwesomeSizes.$2x)
		                                                              .setTiny(true)
		                                                              .toString(0) + "</strong> display quick snippets", Alert_Dark).addClass(Bg_Light)
		                                                                                                                            .setAddDismissButton(false)
		                                                                                                                            .setID("useCodeIconsAM"));

		right.add(new ContactUsPart());
		right.add(new ReasonsWhyPart());
	}

	private BSCard buildSnap2()
	{
		BSCard<?> card = new BSCard();

		card.addStyle("display: -webkit-box;");
		card.addCardBody()
		    .addStyle("float:left;")
		    .addClass("col-2")
		    .add(new Skycon("skyconid1", 60, SkyIcon.Wind));

		FontAwesomeList list = new FontAwesomeList();
		addIconList(list, "Runs on everything with Google Guice", FontAwesomeIcons.check, "#6f42c1");
		addIconList(list, "Fully Optimized JDK 10", FontAwesomeIcons.check, "#6f42c1");
		addIconList(list, "Completely Modular", FontAwesomeIcons.check, "#6f42c1");

		card.addCardBody()
		    .addClass(" col-10")
		    .add(list);

		card.addClass(BSContainerOptions.No_Gutters);
		card.addStyle("margin-bottom:1rem;");

		return card;
	}

	private BSCard buildOpenSourceSponsors()
	{
		BSCard<?> card = new BSCard();
		card.setTextCenter(true);
		card.addCardText("JWebMP is completely open source" +
		                 "<br/>JWebMP is licensed under GPL 3.0<br/>" +
		                 "<i>We Use Browser Stack</i>");
		card.addCardImageBottom("images/BrowserStack.png");

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

		card.addFooter("<a href=\"https://paypal.me/MarcMagon\" target=\"_blank\"><strong>Please consider donating towards the development</strong></a>");

		card.addStyle("margin-bottom:1rem;");
		return card;
	}

	private BSCard buildContinuousIntegrationPane()
	{
		ContinousIntegrationPart openSourcePane = new ContinousIntegrationPart();
		return openSourcePane;
	}

	private BSCard buildSnap3()
	{
		BSCard<?> card = new BSCard();
		card.addCardHeader("Pre Built Plugins Already Exist For");

		FontAwesomeList list = new FontAwesomeList();
		addIconList(list, "JPA JTA JCache", FontAwesomeIcons.check, "#6f42c1");
		addIconList(list, "Hazelcast EhCache Hibernate BTM", FontAwesomeIcons.check, "#6f42c1");
		addIconList(list, "RabbitMQ HTTP2 WS", FontAwesomeIcons.check, "#6f42c1");
		card.addCardBody()
		    .add(list);

		card.addStyle("margin-bottom:1rem;");

		return card;
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
}
