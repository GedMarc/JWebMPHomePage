package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.google.inject.Singleton;
import com.jwebmp.core.base.html.Abbreviation;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

@Singleton
public class HtmlTagsDemoScreen
		extends DemoScreen
{
	@SuppressWarnings("unchecked")
	public HtmlTagsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.core.base.html", Abbreviation.class, "JWebMP Core"));

		/*
		for (ClassInfo allClass : result.getAllClasses())
		{
			Class<? extends ComponentHierarchyBase> clazz = (Class<? extends ComponentHierarchyBase>) allClass.loadClass();
			String packageName = clazz.getPackage()
			                          .toString();
			if (packageName.replace("package ","").equals("com.jwebmp.core.base.html"))
			{
				try
				{
					String htmlTag = clazz.getConstructor()
					                      .newInstance()
					                      .setRenderIDAttribute(false)
					                      .toString(0);
					addComponentTile(clazz.getSimpleName(), StringEscapeUtils.escapeHtml4(htmlTag)).addStyle("cursor", "default");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}*/
		addSourceToContainer(HtmlTagsDemoScreen.class, "rendering.txt", XML, this);
	}
}
