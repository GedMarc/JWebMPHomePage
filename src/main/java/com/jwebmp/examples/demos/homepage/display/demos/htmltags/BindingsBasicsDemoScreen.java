package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.apache.commons.text.StringEscapeUtils;

public class BindingsBasicsDemoScreen
		extends PluginDemoScreen
{
	private static final ScanResult result = new ClassGraph().whitelistPackages("com.jwebmp.core.base.html.inputs")
	                                                         .whitelistModules("com.jwebmp.core")
	                                                         .whitelistJars("jwebmp-core*")
	                                                         .enableClassInfo()
	                                                         .scan();

	public BindingsBasicsDemoScreen()
	{
		super("HtmlTags", "Base", "HTML Tags");
		for (ClassInfo allClass : result.getAllClasses())
		{
			Class<? extends ComponentHierarchyBase> clazz = (Class<? extends ComponentHierarchyBase>) allClass.loadClass();
			if (clazz.getPackageName()
			         .equals("com.jwebmp.core.base.html.inputs"))
			{
				try
				{
					String htmlTag = clazz.getConstructor()
					                      .newInstance()
					                      .setRenderIDAttribute(false)
					                      .toString(0);
					addComponentTile(clazz.getSimpleName(), StringEscapeUtils.escapeHtml4(htmlTag));
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		getAdditionalsRight().add(getCodeBlockJava(BindingsBasicsDemoScreen.class, "rendering.txt"));
	}

}
