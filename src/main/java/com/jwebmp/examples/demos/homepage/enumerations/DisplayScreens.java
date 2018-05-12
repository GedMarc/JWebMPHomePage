package com.jwebmp.examples.demos.homepage.enumerations;

import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.confirmemail.ConfirmEmailAddressScreen;
import com.jwebmp.examples.demos.homepage.display.demos.jqplot.JQPlotDemos;
import com.jwebmp.examples.demos.homepage.display.fontawesome.FontAwesomeScreen;
import com.jwebmp.examples.demos.homepage.display.forgotpassword.ForgotPasswordScreen;
import com.jwebmp.examples.demos.homepage.display.glyphicons.GlyphiconsScreen;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.jqueryui.JQueryUIDemoScreen;

public enum DisplayScreens
{
	HomePageScreen(HomePage.class, "home"),
	ForgotPassword(ForgotPasswordScreen.class, "ForgotPassword"),
	ConfirmEmailAddress(ConfirmEmailAddressScreen.class, "ConfirmEmailAddress"),
	ConfirmEmailAddressScreen(ConfirmEmailAddressScreen.class, "ConfirmEmailAddressScreen"),
	ComingSoon(com.jwebmp.examples.demos.homepage.display.ComingSoon.class, "ComingSoon"),

	JQueryUI(JQueryUIDemoScreen.class, "JQueryUI"),

	FontAwesome(FontAwesomeScreen.class, "FontAwesome"),
	Glyphicons(GlyphiconsScreen.class, "Glyphicons"),

	JQPlot(JQPlotDemos.class, "JQPlot"),;

	private Class<? extends DisplayScreen> screen;
	private String linkName;

	DisplayScreens(Class<? extends DisplayScreen> screen, String linkName)
	{
		this.screen = screen;
		this.linkName = linkName;
	}

	public static DisplayScreens fromLinkName(String linkName)
	{
		for (DisplayScreens displayScreens : DisplayScreens.values())
		{
			if (displayScreens.getLinkName()
			                  .equals(linkName))
			{
				return displayScreens;
			}
		}
		return null;
	}

	public String getLinkName()
	{
		return linkName;
	}

	public Class<? extends DisplayScreen> getScreen()
	{
		return screen;
	}
}

