package com.jwebmp.examples.demos.homepage.enumerations;

import com.jwebmp.examples.demos.homepage.components.PrettyPrimaryButton;
import com.jwebmp.examples.demos.homepage.display.SourceCodeDetailsContainer;
import com.jwebmp.examples.demos.homepage.display.about.UnderTheHoodScreen;
import com.jwebmp.examples.demos.homepage.display.demos.icons.fontawesome.FontAwesomeDemoScreen;
import com.jwebmp.examples.demos.homepage.display.demos.icons.fontawesome5.FontAwesome5DemoScreen;
import com.jwebmp.examples.demos.homepage.display.demos.icons.glyphicons.GlyphiconsDemoScreen;
import com.jwebmp.examples.demos.homepage.display.demos.icons.mdi.MDIDemoScreen;
import com.jwebmp.examples.demos.homepage.display.forgotpassword.ForgotPasswordEvent;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.home.parts.ButtonRowPart;
import com.jwebmp.examples.demos.homepage.display.home.parts.ContactUsPart;
import com.jwebmp.examples.demos.homepage.display.home.parts.ReasonsWhyPart;
import com.jwebmp.examples.demos.homepage.display.login.LoginPart;
import com.jwebmp.plugins.materialdesignicons.MaterialDesignIcons;

public enum DisplayCodeParts
{

	ViewOurShowcase(new SourceCodeDetailsContainer<>().addCustomLocation("Panel Code", HomePage.class, "viewourshowcase.txt")
	                                                  .addCustomLocation("Pretty Primary Button", PrettyPrimaryButton.class, "prettyprimarybutton.txt")
	                                                  .setQuickTipsLocation(HomePage.class, "viewourshowcasetips.txt")),

	Login(new SourceCodeDetailsContainer<>().addCustomLocation("Login Part", LoginPart.class, "loginpart.txt")
	                                        .addCustomLocation("Email Field", LoginPart.class, "loginemailfield.txt")
	                                        .addCustomLocation("Password Field", LoginPart.class, "loginpasswordfield.txt")
	                                        .addCustomLocation("Login Event", LoginPart.class, "loginevent.txt")
	                                        .addCustomLocation("Login Async", LoginPart.class, "loginasync.txt")
	                                        .addCustomLocation("Logout Event", LoginPart.class, "logoutevent.txt")
	                                        .addCustomLocation("Forgot Password Event", ForgotPasswordEvent.class, "forgotpasswordevent.txt")
	                                        .addCustomLocation("Forgot Password Async", ForgotPasswordEvent.class, "forgotpasswordasync.txt")),

	ReasonsWhy(new SourceCodeDetailsContainer<>().addCustomLocation("Reasons Why", ReasonsWhyPart.class, "reasonswhy.txt")),

	ContactUs(new SourceCodeDetailsContainer<>().addCustomLocation("Contact Us", ContactUsPart.class, "contactus.txt")),

	FontAwesome(new SourceCodeDetailsContainer<>().addCustomLocation("Display Code", FontAwesomeDemoScreen.class, "fontawesomeexample.txt")),
	FontAwesome5(new SourceCodeDetailsContainer<>().addCustomLocation("Display Code", FontAwesome5DemoScreen.class, "fontawesomeexample.txt")),

	MaterialDesignIcons3(new SourceCodeDetailsContainer<>().addCustomLocation("Display Code", MaterialDesignIcons.class, "mdiexample.txt")),
	MaterialDesignIcons2(new SourceCodeDetailsContainer<>().addCustomLocation("Display Code", MDIDemoScreen.class, "mdiexample.txt")),

	Glyphicons(new SourceCodeDetailsContainer<>().addCustomLocation("Display Code", GlyphiconsDemoScreen.class, "glyphicons.txt")),

	ButtonRowChart1(new SourceCodeDetailsContainer<>().addCustomLocation("Site Visits", ButtonRowPart.class, "chart1.txt")),
	ButtonRowChart2(new SourceCodeDetailsContainer<>().addCustomLocation("Subscribers", ButtonRowPart.class, "chart2.txt")),
	ButtonRowChart3(new SourceCodeDetailsContainer<>().addCustomLocation("Available Components", ButtonRowPart.class, "chart3.txt")),
	ButtonRowChart4(new SourceCodeDetailsContainer<>().addCustomLocation("Online Users", ButtonRowPart.class, "chart4.txt")),

	ComponentUtilityMethods(new SourceCodeDetailsContainer<>().addCustomLocation("HTML Utilities", UnderTheHoodScreen.class, "ComponentHTML.txt")
	                                                          .addCustomLocation("Attribute Handling", UnderTheHoodScreen.class, "ComponentAttribute.txt")
	                                                          .addCustomLocation("Style Management", UnderTheHoodScreen.class, "ComponentStyle.txt")),

	InjectionControlMethods(new SourceCodeDetailsContainer<>().addCustomLocation("Intercepting Calls", UnderTheHoodScreen.class, "InjectionAnnotations.txt")
	                                                          .addCustomLocation("Services Available", UnderTheHoodScreen.class, "ServicesAvailable.txt")
	                                                          .addCustomLocation("Scoped Classes", UnderTheHoodScreen.class, "RequestScopedClasses.txt")
	                                                          .addCustomLocation("Page Rendering", UnderTheHoodScreen.class, "PageRendering.txt")),

	GPLLicence,
	ContinuousIntegration,;

	private SourceCodeDetailsContainer<?> sourceCodeDetailsContainer;

	DisplayCodeParts()
	{
	}

	DisplayCodeParts(SourceCodeDetailsContainer<?> sourceCodeDetailsContainer)
	{
		this.sourceCodeDetailsContainer = sourceCodeDetailsContainer;
	}

	public SourceCodeDetailsContainer<?> getSourceCodeDetailsContainer()
	{
		return sourceCodeDetailsContainer;
	}

	public void setSourceCodeDetailsContainer(SourceCodeDetailsContainer<?> sourceCodeDetailsContainer)
	{
		this.sourceCodeDetailsContainer = sourceCodeDetailsContainer;
	}
}
