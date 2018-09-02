package com.jwebmp.examples.demos.homepage.display.about.wildfly;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

public class WildflyScreen
		extends DisplayPart<WildflyScreen>
{
	public WildflyScreen()
	{
		BSCardBody all = new BSCardBody();
		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;");

		all.add("The Wildfly Addon reads your assigned standalone.xml file for all your datasource connections. It is reliant on the JTA Module.");
		all.add("The location of the configuration file is obtained from the system property &quot;jboss.server.config.dir&quot;, and the specific standalone file being used via the WildflyConnectionInfoBuilder.standaloneFileName property.");

		all.add("<br/>For XA the drivers will be matched accordingly to what is specified in the standalone configuration file." +
		        "<br/>Default drivers should be set for Non-XA. SQL and DB2 have already been catered for, adding your own or making a pull request for your driver is as simple as registering a new ServiceLoader for IWildflyDriverRegistration.");

		add(all);
	}
}
