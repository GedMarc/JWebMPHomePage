package com.jwebmp.examples.demos.homepage.display.home;

import com.jwebmp.Feature;
import com.jwebmp.FileTemplates;
import com.jwebmp.base.html.*;
import com.jwebmp.base.html.attributes.ImageAttributes;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.components.PrettyPrimaryButton;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.MintonCheckBox;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.examples.demos.homepage.display.home.parts.ButtonRowPart;
import com.jwebmp.examples.demos.homepage.display.home.parts.ContactUsPart;
import com.jwebmp.examples.demos.homepage.display.home.parts.MavenPomPart;
import com.jwebmp.examples.demos.homepage.display.home.parts.ReasonsWhyPart;
import com.jwebmp.examples.demos.homepage.display.login.LoginPart;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.jumbotron.BSJumbotron;
import com.jwebmp.plugins.bootstrap4.listgroup.BSListGroup;
import com.jwebmp.plugins.bootstrap4.navs.BSNavTabs;
import com.jwebmp.plugins.bootstrap4.navs.BSNavsOptions;
import com.jwebmp.plugins.bootstrap4.options.*;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettifyPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;
import org.apache.commons.lang3.StringEscapeUtils;
import za.co.mmagon.guiceinjection.GuiceContext;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.Bg_Secondary;
import static com.jwebmp.utilities.StaticStrings.HTML_TAB;

public class HomePage
		extends DisplayScreen
{
	public HomePage()
	{
		super(HTML_TAB + HTML_TAB + "Welcome to JWeb MicroProfile!" + HTML_TAB + "<small><i>Demo Site Under Construction<a href=\"https://github.com/GedMarc/JWebMPHomePage\" target=\"_blank\">View Site Source Code</i></small></a>");
		JQSourceCodePrettifyPageConfigurator.setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer<?> container = new BSContainer<>();
		container.setContainerType(BSContainerOptions.Container_Fluid);

		BSJumbotron<?> jumbo = new BSJumbotron<>();
		jumbo.asMe()
		     .setFluid()
		     .asMe()
		     .addJumboText("JWebMP");
		jumbo.asMe()
		     .addLead("Truly Everything");
		jumbo.asMe()
		     .addLead("Ultra Rapid Development");
		jumbo.asMe()
		     .addLead("Beginner and Expert Alike");
		jumbo.addClass(BSBorderOptions.Border_Primary);
		jumbo.addClass(BSBackgroundOptions.Bg_Dark);
		jumbo.addClass(BSColoursOptions.Text_White);

		//	container.add(jumbo);
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

		BSRow displayRow = new BSRow();
		BSColumn leftColumn = new BSColumn(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_Sm_12);
		BSColumn rightColumn = new BSColumn(BSColumnOptions.Col_Md_6, BSColumnOptions.Col_Sm_12);

		displayRow.add(rightColumn);
		displayRow.add(leftColumn);


		Div showcaseDiv = new Div();
		leftColumn.add(new SourceCodeContentPanel<>("View Our Showcase", DisplayCodeParts.ViewOurShowcase, showcaseDiv, "bg-success").setShowHeader(true)
		                                                                                                                             .setShowCode(true));

		showcaseDiv.add("Jump straight into the showcase to see what we're all about. <br/>" + "View code examples, find plugins, or join the community");

		showcaseDiv.add(new PrettyPrimaryButton<>().asButton()
		                                           .setText("Hello World!")
		                                           .addAttribute("disabled", ""));
		showcaseDiv.add(new PrettyPrimaryButton<>().asButton()
		                                           .setText("Take The Tour")
		                                           .addAttribute("disabled", ""));
		showcaseDiv.add(new PrettyPrimaryButton<>().asButton()
		                                           .setText("Plugins")
		                                           .addAttribute("disabled", ""));
		showcaseDiv.add(new PrettyPrimaryButton<>().asButton()
		                                           .setText("API")
		                                           .addAttribute("disabled", ""));

		if (GuiceContext.getInstance(SessionProperties.class)
		                .isLoggedIn())
		{
			showcaseDiv.add(new PrettyPrimaryButton<>().setText("Forums"));
			showcaseDiv.add(new PrettyPrimaryButton<>().setText("Request Plugins"));
		}
		//===================================================================================
		// Left Side
		//==================================================================================

		leftColumn.add(new SourceCodeContentPanel<>("Contact Us", DisplayCodeParts.ContactUs, new ContactUsPart(), "bg-purple").setShowHeader(true)
		                                                                                                                       .setShowCode(true));

		if (!GuiceContext.getInstance(SessionProperties.class)
		                 .isLoggedIn())
		{
			leftColumn.add(new ReasonsWhyPart());
		}
		leftColumn.add(buildContinuousIntegrationPane());


		//----------------------------------------------------------------------------
		// Right side
		//----------------------------------------------------------------------------

		leftColumn.addStyle("padding-left:0px;");
		leftColumn.addStyle("padding-right:0px;");
		rightColumn.addStyle("padding-left:0px;");
		rightColumn.addStyle("padding-right:0px;");
		container.add(displayRow);


		if (!GuiceContext.getInstance(SessionProperties.class)
		                 .isLoggedIn())
		{
			rightColumn.add(new LoginPart());
			rightColumn.add(buildOpenSourceSponsors());
			rightColumn.add(buildReasonsToRegisterDiv());
			rightColumn.add(new MintonPanel("Maven", new MavenPomPart(), "bg-pink"));
		}
		else
		{
			rightColumn.add(new MintonPanel("Maven", new MavenPomPart(), "bg-pink"));
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

	private Div buildContinuousIntegrationPane()
	{
		Div openSourcePane = new Div();
		Div portlet = buildPortlet("Continuous Integration", BSBackgroundOptions.Bg_Primary, false, openSourcePane);

		openSourcePane.add("From Start to Finish this project is completely visible on every front.");
		BSListGroup<?> listGroup = new BSListGroup();
		listGroup.asMe()
		         .addLinkItem(FontAwesome.icon(FontAwesomeIcons.github)
		                                 .toString(0) + "&nbsp;GitHub Repositories")
		         .setUrl("https://github.com/GedMarc?tab=repositories")
		         .addAttribute("target", "_blank")
		         .addClass(BSColoursOptions.Text_White)
		         .addClass(BSButtonOptions.Btn)
		         .addClass(BSButtonOptions.Btn_Outline_Primary);
		listGroup.asMe()
		         .addLinkItem(FontAwesome.icon(FontAwesomeIcons.train)
		                                 .toString(0) + "&nbsp;Jira Project Tracking")
		         .setUrl("https://jwebmp.com/jira/")
		         .addAttribute("target", "_blank")
		         .addClass(BSButtonOptions.Btn)
		         .addClass(BSColoursOptions.Text_White)
		         .addClass(BSButtonOptions.Btn_Outline_Primary);
		listGroup.asMe()
		         .addLinkItem(FontAwesome.icon(FontAwesomeIcons.cog)
		                                 .toString(0) + "&nbsp;TeamCity CI and Builds")
		         .setUrl("https://jwebmp.com/teamcity/")
		         .addAttribute("target", "_blank")
		         .addClass(BSButtonOptions.Btn)
		         .addClass(BSColoursOptions.Text_White)
		         .addClass(BSButtonOptions.Btn_Outline_Primary);
		listGroup.asMe()
		         .addLinkItem(FontAwesome.icon(FontAwesomeIcons.check_square)
		                                 .toString(0) + "&nbsp;SonarQube Code Quality Gate")
		         .setUrl("https://jwebmp.com/sonar/")
		         .addAttribute("target", "_blank")
		         .addClass(BSButtonOptions.Btn)
		         .addClass(BSColoursOptions.Text_White)
		         .addClass(BSButtonOptions.Btn_Outline_Primary);
		listGroup.asMe()
		         .addLinkItem(FontAwesome.icon(FontAwesomeIcons.magic)
		                                 .toString(0) + "&nbsp;Artifactory Maven Repository")
		         .setUrl("https://jwebmp.com/artifactory/")
		         .addAttribute("target", "_blank")
		         .addClass(BSButtonOptions.Btn)
		         .addClass(BSColoursOptions.Text_White)
		         .addClass(BSButtonOptions.Btn_Outline_Primary);

		openSourcePane.add(listGroup);

		openSourcePane.add(
				"<br/> This project is all about community input and continuous improvement. <br/>" + "<br/>There is a suggestions box once you've logged in, or you can simply make pull requests in GitHub.");

		return portlet;
	}

	private Div buildOpenSourceSponsors()
	{
		DivSimple<?> contentDiv = new DivSimple<>();
		MintonPanel<?> panel = new MintonPanel<>("GPL 3.0 Open Source Licenced", contentDiv, "bg-purple");

		contentDiv.add("The components and products are supported and tested with assistance from these great companies");

		contentDiv.add(new Link<>("http://www.browserstack.com", "_blank").add(new Image("images/BrowserStack.png"))
		                                                                  .addClass(BSSpacingOptions.Margin_Right_4));
		contentDiv.add(new Link<>("http://www.jetbrains.com", "_blank").add(new Image("images/jetbrains-variant-3.svg")));

		contentDiv.add(new Link<>("https://www.atlassian.com", "_blank").add(new Image<>("images/Atlassian180x180.PNG").addAttribute(ImageAttributes.Height, "80px;"))
		                                                                .addStyle("margin-left:15px;"));

		return panel;
	}

	private Div buildReasonsToRegisterDiv()
	{
		Div openSourcePane = new Div();
		Div portlet = buildPortlet("Reasons to Register", "bg-primary", false, openSourcePane);
		Div contents = new Div();
		contents.add(buildCheckbox("Absolutely Free. Everything. Free. Always.", true, "checkbox-default"));
		contents.add(buildCheckbox("Built from a collaboration of many open source projects", true, "checkbox-inverse"));
		contents.add(buildCheckbox("Video Guides", true, "checkbox-primary"));
		contents.add(buildCheckbox("Access to the Forums", true, "checkbox-primary"));
		contents.add(buildCheckbox("Can Request For Plugins", true, "checkbox-success"));
		contents.add(buildCheckbox("No Spam/Newsletters", true, "checkbox-info"));
		contents.add(buildCheckbox("First hand access to the developer", true, "checkbox-purple"));
		openSourcePane.add(contents);

		return portlet;
	}

	private Div buildCheckbox(String label, boolean checked, String clazz)
	{
		return (Div) new MintonCheckBox(checked, label, clazz).addClass("col-12");
	}

	private DivSimple<?> buildSourceExampleDiv()
	{
		DivSimple<?> sourceExampleDiv = new DivSimple();
		DivSimple<?> portlet = buildPortlet("Source Examples", Bg_Secondary, false, sourceExampleDiv);

		BSNavTabs<?> sourceTabs = new BSNavTabs<>();
		sourceTabs.addClass(BSNavsOptions.Tabs_Bordered);
		DivSimple<?> javaExample = new DivSimple<>();
		javaExample.add(new H4("Make web sites directly in Java using a completely optimized JDK 8 framework").addClass(BSTypographyOptions.Text_Center));
		javaExample.add(new JQSourceCodePrettify<>().addStyle("background:black;")
		                                            .setSourceCodeLanguage(SourceCodeLanguages.Java)
		                                            .setText(StringEscapeUtils.escapeHtml4(
				                                            FileTemplates.getFileTemplate(HomePage.class, "homepage_javaexample", "javaexample.txt", true)
				                                                         .toString())));
		sourceTabs.addTab("Java", javaExample, true);

		sourceTabs.addTab("Html", new Div(), false);
		sourceTabs.addTab("Javascript", new Div(), false);
		sourceTabs.addTab("CSS", new Div(), false);
		sourceTabs.addTab("Tests", new Div(), false);
		sourceTabs.addTab("<i>DB *</i><small>optional</small>", new Div(), false);

		sourceExampleDiv.add(sourceTabs);
		return portlet;
	}
}
