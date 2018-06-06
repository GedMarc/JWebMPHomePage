package com.jwebmp.examples.demos.homepage.enumerations;

import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.about.AboutJWebMPScreen;
import com.jwebmp.examples.demos.homepage.display.about.AboutThisSiteScreen;
import com.jwebmp.examples.demos.homepage.display.about.UnderTheHoodScreen;
import com.jwebmp.examples.demos.homepage.display.confirmemail.ConfirmEmailAddressScreen;
import com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.Bootstrap4DemoScreen;
import com.jwebmp.examples.demos.homepage.display.demos.graphing.jqplot.JQPlotDemos;
import com.jwebmp.examples.demos.homepage.display.demos.icons.fontawesome.FontAwesomeScreen;
import com.jwebmp.examples.demos.homepage.display.demos.icons.glyphicons.GlyphiconsScreen;
import com.jwebmp.examples.demos.homepage.display.demos.jqui.JQueryUIDemoScreen;
import com.jwebmp.examples.demos.homepage.display.demos.trees.jstree.JSTreeDemoScreen;
import com.jwebmp.examples.demos.homepage.display.forgotpassword.ForgotPasswordScreen;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;

public enum DisplayScreens
{
	HomePageScreen(HomePage.class, "home"),
	ForgotPassword(ForgotPasswordScreen.class, "ForgotPassword"),
	ConfirmEmailAddress(ConfirmEmailAddressScreen.class, "ConfirmEmailAddress"),
	ConfirmEmailAddressScreen(ConfirmEmailAddressScreen.class, "ConfirmEmailAddressScreen"),
	ComingSoon(com.jwebmp.examples.demos.homepage.display.ComingSoon.class, "ComingSoon"),

	AboutJWebMP(AboutJWebMPScreen.class, "AboutJWebMP"),
	UnderTheHood(UnderTheHoodScreen.class, "UnderTheHood"),
	AboutThisSIte(AboutThisSiteScreen.class, "AboutThisSite"),


	JQueryUI(JQueryUIDemoScreen.class, "JQueryUI"),
	Bootstrap4(Bootstrap4DemoScreen.class, "Bootstrap4"),

	FontAwesome(FontAwesomeScreen.class, "FontAwesome"),
	Glyphicons(GlyphiconsScreen.class, "Glyphicons"),

	JSTree(JSTreeDemoScreen.class, "JSTree"),
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

