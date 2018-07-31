package com.jwebmp.examples.demos.homepage.display.home;

import com.jwebmp.core.Feature;
import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.AlertMessage;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.PrettyPrimaryButton;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.MintonCheckBox;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.examples.demos.homepage.display.home.parts.*;
import com.jwebmp.examples.demos.homepage.display.login.LoginPart;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions;
import com.jwebmp.plugins.bootstrap4.options.*;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome.FontAwesomeProperties;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettifyPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;
import org.apache.commons.lang3.StringEscapeUtils;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.bootstrap4.alerts.BSAlertOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;

public class HomePage
		extends DisplayScreen
{
	BSRow displayRow = new BSRow();
	BSColumn right = new BSColumn(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_Sm_12);
	BSColumn left = new BSColumn(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_Sm_12);

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
		else
		{
			left.add(buildSnap4());
		}

		right.add(new MintonPanel("Maven", new MavenPomPart(), "bg-pink").setShowHeader(false));

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
		left.add(new MintonPanel("Maven", new MavenPomPart(), "bg-pink").setShowHeader(false));

	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer<?> container = new BSContainer<>();
		container.setContainerType(BSContainerOptions.Container_Fluid);

		container.addFeature(new Feature("CirclifulCustom", container)
		{
			@Override
			protected void assignFunctionsToComponent()
			{
				addQuery("$('.circliful-chart').circliful();");
			}
		});
		container.addFeature(new Feature("CounterUpFeature", container)
		{
			@Override
			protected void assignFunctionsToComponent()
			{
				addQuery("$('.counter').counterUp({delay: 100,time: 1200});");
			}
		});

		container.add(new ButtonRowPart<>());
		//container.add(buildAdvertCard());

		displayRow.add(left);
		displayRow.add(right);
		displayRow.resetHorizontalSinks();

		left.add(buildSnap2());

		Div showcaseDiv = new Div();
		showcaseDiv.add(new AlertMessage("<strong>Code Icons " + FontAwesome.icon(FontAwesomeIcons.code, FontAwesomeProperties.$2x)
		                                                                    .setTiny(true)
		                                                                    .toString(0) + "</strong> display quick snippets", Alert_Dark).addClass(Bg_Light)
				                .setAddDismissButton(false)
				                .setID("useCodeIconsAM"));
		right.add(new MintonPanel("Maven", new MavenPomPart(), "bg-pink").setShowHeader(false));

		right.add(buildSnap3());

		right.add(new SourceCodeContentPanel<>("View Our Showcase", DisplayCodeParts.ViewOurShowcase, showcaseDiv, "bg-success").setShowHeader(false)
		                                                                                                                        .setShowCode(false));

		if (GuiceContext.getInstance(SessionProperties.class)
		                .isLoggedIn())
		{
			showcaseDiv.add(new PrettyPrimaryButton<>().setText("Forums"));
			showcaseDiv.add(new PrettyPrimaryButton<>().setText("Request Plugins"));
		}
		//===================================================================================
		// Left Side
		//==================================================================================

		right.add(new SourceCodeContentPanel<>("Contact Us", DisplayCodeParts.ContactUs, new ContactUsPart(), "bg-purple").setShowHeader(false)
		                                                                                                                  .setShowCode(false));

		right.add(new ReasonsWhyPart());

		//----------------------------------------------------------------------------
		// Right side
		//----------------------------------------------------------------------------

		right.addStyle("padding-left:0px;");
		right.addStyle("padding-right:0px;");
		left.addStyle("padding-left:0px;");
		left.addStyle("padding-right:0px;");

		container.add(displayRow);

		left.add(new LoginPart());
		left.add(buildOpenSourceSponsors());
		left.add(buildSnap4());

		left.add(buildContinuousIntegrationPane());

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

	private Div buildOpenSourceSponsors()
	{
		DivSimple<?> contentDiv = new DivSimple<>().addClass("row");
		contentDiv.addClass(BSAlignmentHorizontalOptions.Align_Center);
		contentDiv.addClass(BSTypographyOptions.Text_Center);
		MintonPanel<?> panel = new MintonPanel<>("GPL 3.0 Open Source Licenced", contentDiv, "bg-purple").setShowHeader(false);

		contentDiv.add("JWebMP is completely open source" +
		               "<br/>JWebMP is licensed under GPL 3.0<br/>" +
		               "<i>We test the components using Browser Stack</i>");
		contentDiv.add(new Link<>("http://www.browserstack.com", "_blank").add(new Image("images/BrowserStack.png"))
		                                                                  .addClass(BSSpacingOptions.Margin_Right_4));

		return panel;
	}

	private Div buildSnap2()
	{
		Div openSourcePane = new Div();
		Div portlet = buildPortlet("Reasons two", "bg-primary", false, openSourcePane, false);
		Div contents = new Div();
		contents.add(buildCheckbox("Runs on everything with Google Guice",
		                           true, "checkbox-purple"));
		contents.add(buildCheckbox("Fully Optimized JDK 10", true, "checkbox-success"));
		contents.add(buildCheckbox("JDK 8 on Dual Build", true, "checkbox-primary"));
		openSourcePane.add(contents);
		return portlet;
	}

	private Div buildSnap3()
	{
		Div openSourcePane = new Div();
		Div portlet = buildPortlet("Reasons two", "bg-primary", false, openSourcePane, false);
		Div contents = new Div();
		contents.add(buildCheckbox("Plugins for ", true, "checkbox-inverted"));
		contents.add(buildCheckbox("JPA JTA JCache", true, "checkbox-purple"));
		contents.add(buildCheckbox("Hazelcast EhCache Hibernate BTM", true, "checkbox-pink"));
		contents.add(buildCheckbox("RabbitMQ HTTP2 WS", true, "checkbox-success"));

		openSourcePane.add(contents);
		return portlet;
	}

	private Div buildSnap4()
	{
		Div openSourcePane = new Div();
		Div portlet = buildPortlet("Reasons to Register", "bg-primary", false, openSourcePane, false);
		Div contents = new Div();
		contents.add(buildCheckbox("SonarQube Quality Gate", true, "checkbox-purple"));
		contents.add(buildCheckbox("Open Build and CI", true, "checkbox-success"));
		contents.add(buildCheckbox("Pull Requests Always Welcome", true, "checkbox-dark"));
		/*contents.add(buildCheckbox("<a href=\"https://paypal.me/MarcMagon\" target=\"_blank\"><strong>Please consider donating towards the development</strong></a>", true,
		                           "checkbox-primary"));*/
		openSourcePane.add(contents);

		return portlet;
	}

	private Div buildContinuousIntegrationPane()
	{
		ContinousIntegrationPart openSourcePane = new ContinousIntegrationPart();
		Div portlet = buildPortlet("Continuous Integration", BSBackgroundOptions.Bg_Primary.toString(), false, openSourcePane, false);
		/*openSourcePane.add(
				"<br/> This project is all about community input and continuous improvement. <br/>" + "<br/>There is a suggestions box once you've logged in, or you can simply make pull requests in GitHub.");
*/
		return portlet;
	}

	private Div buildCheckbox(String label, boolean checked, String clazz)
	{
		return (Div) new MintonCheckBox(checked, label, clazz).addClass("col-12");
	}
}
