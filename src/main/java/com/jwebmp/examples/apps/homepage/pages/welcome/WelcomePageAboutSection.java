package com.jwebmp.examples.apps.homepage.pages.welcome;

import com.jwebmp.plugins.themes.mintontheme.faq.FAQCardItemLayout;

public class WelcomePageAboutSection extends FAQCardItemLayout
{
	public WelcomePageAboutSection()
	{
		addItem("Q", "What is JWebMP", "JWebMP is a Web/Mobile Framework designed for JPMS, and works best in JDK 14 and up. JRE8 compatibility is available, and releases are available for every JDK currently in circulation");
		addItem("O", "Object-Orientated", "The absolute object orientated approach removes all necessity for any templates or external configuration. There is no need to learn xml structures, tag libraries or any of that. A 100% Pure API");
		addItem("T", "Test-Driven", "All output is built to be test capable, including servlet controls. Never get lost in a black hole of test and integration cases ever again. " +
				"JWebMP can produce your output without running on any server. You do not need anything to view the outputs of any component. Integration and Unit Test like never before");
		addItem("M", "Modular", "JWebMP is 100% fully modular, and JLink compatible. With <a target=\"_blank\" href=\"https://guicedee.com\">Guiced-EE</a> use everything you know and love from OpenPDF to Hibernate. Create enterprise applications that are container-friendly by default, and built for web and mobile off the bat");
		addItem("R", "Request-Scoped", "JWebMP supports request scoping, and fully supports distributed persistent sessions. Thin-UI's are now a thing. Data can be located on the browser, sounds almost perfect for Microservices doesn't it. Go cookie-less!");
		addItem("!", "Unbelievable Performance", "Run with JLink on the JRT filesystem! Get that crazy performance, and push it even further on inherited objects with JDK14's Valhalla. You will cry with how fast you will develop, test, and deploy");
	}
}
