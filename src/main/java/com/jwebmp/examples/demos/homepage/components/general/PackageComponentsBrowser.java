package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.examples.demos.homepage.components.events.SwopPackageDisplayEvent;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonPrimaryOutline;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import io.github.classgraph.ClassInfo;
import org.apache.commons.lang3.tuple.Pair;

public class PackageComponentsBrowser
		extends BSCardBody<PackageComponentsBrowser>
{
	public PackageComponentsBrowser(String pluginName, Pair<String, Class>... components)
	{
		addStyle("margin:0px;padding:0px;text-align:center;");
		for (Pair<String, Class> component : components)
		{
			BSButtonPrimaryOutline badge = new BSButtonPrimaryOutline<>().setText(component.getKey());
			badge.addClass("m-2");
			//JDK 8 doesn't have packageInfo method, doesn't dual compile
			ClassInfo clazz = GuiceContext.instance()
			                              .getScanResult()
			                              .getClassInfo(component.getValue()
			                                                     .getCanonicalName());
			String packageName = clazz.getPackageName();
			SwopPackageDisplayEvent swopDisplayEvent = new SwopPackageDisplayEvent(clazz.loadClass(), packageName, pluginName);
			badge.addEvent(swopDisplayEvent);
			add(badge);
		}
	}
}
