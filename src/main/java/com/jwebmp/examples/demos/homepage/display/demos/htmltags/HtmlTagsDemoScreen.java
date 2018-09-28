package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.google.inject.Singleton;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.apache.commons.text.StringEscapeUtils;

@Singleton
public class HtmlTagsDemoScreen
		extends PluginDemoScreen
{
	private static final ScanResult result = new ClassGraph().whitelistPackages("com.jwebmp.core.base.html")
	                                                         .enableClassInfo()
	                                                         .scan();

	@SuppressWarnings("unchecked")
	public HtmlTagsDemoScreen()
	{
		super("HtmlTags", "Base", "HTML Tags");
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
		}
		getAdditionalsRight().add(getCodeBlockJava(HtmlTagsDemoScreen.class, "rendering.txt"));
	}

}
