package com.jwebmp.examples.demos.homepage.display.about.ehcache;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

public class EHCacheScreen
		extends DisplayPart<EHCacheScreen>
{
	public EHCacheScreen()
	{
		BSCardBody all = new BSCardBody();
		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;");

		all.add("EhCache Addon allows automated configuration for utilizing the EhCache Hibernate Plugin, using the JCache strategy.");
		all.add("The module configures your persistence units for instant usage with no configuration required at all. Simply include the module.");
		all.add("Properties are configured through the EhCacheEntityManagerProperties class, and no change to your persistence file is necessary");

		add(all);
	}
}
