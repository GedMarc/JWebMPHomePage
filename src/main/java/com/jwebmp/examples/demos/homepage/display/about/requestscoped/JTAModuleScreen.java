package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

public class JTAModuleScreen
		extends DisplayPart<JTAModuleScreen>
{
	public JTAModuleScreen()
	{
		BSCardBody all = new BSCardBody();
		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;");
		all.add("JTA is included with the usage of Bitronix BTM. This was chosen for its up-to-date implementation of connection pooling, " +
		        " its <strong><u>identical</u></strong> usage and transaction management between production and unit tests, simplicity in configuring," +
		        " and of course SharedTransactionConnections. It is not required (or suggested) to utilize other pools such as C3P0 in such instances.");
		all.add("The Guiced Persistence implementation of BTM allows you to map both XA and Non-XA resources, and will assign the LRC XA Handler for Non-XA persistence units." +
		        " This allows you to control and manage UserTransactions, Units of Work, and Transactional Rollbacks accordingly.");
		all.add("Implementing Session-Per-Transaction strategy is enabled by default, to enable lower level per DB call, you can use the automated handler for hassle free development. " +
		        "But don't rely on rolling back as each call is committed after persist/merge/delete, which is exactly what LRC/NonXA does in any case.");

		addSourceToContainer(JTAModuleScreen.class, "jta_auto.txt", SourceCodeLanguages.Java, all);
		addSourceToContainer(JTAModuleScreen.class, "jta_persistence_example.txt", SourceCodeLanguages.XML, all);

		all.add("The connection information is provided the exact same way as for JTA.");
		all.add("It is recommended to apply the default optimizations for JDBC 4 drivers.");

		addSourceToContainer(JTAModuleScreen.class, "jtaconfigure.txt", SourceCodeLanguages.Java, all);

		add(all);
	}
}
