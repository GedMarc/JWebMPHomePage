package com.jwebmp.examples.demos.homepage.display.about.requestscoped;

import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

public class RequestScopedTransactionsScreen
		extends DisplayPart<RequestScopedTransactionsScreen>
{
	public RequestScopedTransactionsScreen()
	{
		BSCardBody all = new BSCardBody();

		all.addClass("row bg-dark");
		all.addStyle("display:grid;overflow-y:auto;");
		all.add("Request scoped transactions and Units of Work greatly increase performance when used with Shared Transaction Connections with huge volumes of concurrent users");
		all.add("So far, we've seen the session-per-transaction strategy. In web environments this is atypical, and generally a session-per-http-request is preferred (sometimes also called open-session-in-view). To enable this strategy, simply include the module GuicedServletsRequestScoping with your application.");

		all.add("<a target=\"_blank\" href=\"https://github.com/google/guice/wiki/JPA\">Using Different Session- Strategies</a>");
		all.add("<a target=\"_blank\" href=\"https://github.com/bitronix/btm/wiki/JDBC-pools-configuration\">View Enhancing Connection Pools</a>");

		add(all);
	}
}
