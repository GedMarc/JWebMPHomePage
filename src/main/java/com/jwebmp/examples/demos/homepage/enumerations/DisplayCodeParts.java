package com.jwebmp.examples.demos.homepage.enumerations;

import com.jwebmp.examples.demos.homepage.components.PrettyPrimaryButton;
import com.jwebmp.examples.demos.homepage.display.SourceCodeDetailsContainer;
import com.jwebmp.examples.demos.homepage.display.fontawesome.FontAwesomeScreen;
import com.jwebmp.examples.demos.homepage.display.forgotpassword.ForgotPasswordEvent;
import com.jwebmp.examples.demos.homepage.display.glyphicons.GlyphiconsScreen;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.home.parts.ButtonRowPart;
import com.jwebmp.examples.demos.homepage.display.home.parts.ContactUsPart;
import com.jwebmp.examples.demos.homepage.display.home.parts.ReasonsWhyPart;
import com.jwebmp.examples.demos.homepage.display.login.LoginPart;

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

	FontAwesome(new SourceCodeDetailsContainer<>().addCustomLocation("Display Code", FontAwesomeScreen.class, "fontawesomeexample.txt")),

	Glyphicons(new SourceCodeDetailsContainer<>().addCustomLocation("Display Code", GlyphiconsScreen.class, "glyphicons.txt")),


	ButtonRowChart1(new SourceCodeDetailsContainer<>().addCustomLocation("Site Visits", ButtonRowPart.class, "chart1.txt")),
	ButtonRowChart2(new SourceCodeDetailsContainer<>().addCustomLocation("Subscribers", ButtonRowPart.class, "chart2.txt")),
	ButtonRowChart3(new SourceCodeDetailsContainer<>().addCustomLocation("Available Components", ButtonRowPart.class, "chart3.txt")),
	ButtonRowChart4(new SourceCodeDetailsContainer<>().addCustomLocation("Online Users", ButtonRowPart.class, "chart4.txt")),

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
