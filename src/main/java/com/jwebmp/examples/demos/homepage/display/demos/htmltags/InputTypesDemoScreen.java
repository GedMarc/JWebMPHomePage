package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.HorizontalRule;
import com.jwebmp.core.htmlbuilder.css.displays.Displays;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.apache.commons.text.StringEscapeUtils;

public class InputTypesDemoScreen
		extends PluginDemoScreen
{
	private static final ScanResult result = new ClassGraph().whitelistPackages("com.jwebmp.core.base.html.inputs")
	                                                         .whitelistModules("com.jwebmp.core")
	                                                         .whitelistJars("jwebmp-core*")
	                                                         .enableClassInfo()
	                                                         .scan();

	public InputTypesDemoScreen()
	{
		super("HtmlTags", "Base", "HTML Tags");
		Div displayDiv = new Div();
		displayDiv.getCss()
		          .getDisplay()
		          .setDisplay(Displays.Inline);
		for (ClassInfo allClass : result.getAllClasses())
		{
			Class<? extends ComponentHierarchyBase> clazz = (Class<? extends ComponentHierarchyBase>) allClass.loadClass();
			try
			{
				ComponentHierarchyBase component = clazz.getConstructor()
				                                        .newInstance();
				component.bind("demobinding." + clazz.getSimpleName());
				String htmlTag = component
						                 .setRenderIDAttribute(false)
						                 .removeAttribute("name")
						                 .toString(0);
				addComponentTile(clazz.getSimpleName(), StringEscapeUtils.escapeHtml4(htmlTag));

				displayDiv.add(clazz.getSimpleName() + " - {{demobinding." + clazz.getSimpleName() + "}}");
				displayDiv.add(component);
				displayDiv.add(HorizontalRule.getInstance());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		getAdditionalsRight().add(getCodeBlockJava(InputTypesDemoScreen.class, "inputtypes.txt"));
		getAdditionalsRight().add((Div) addWhiteAlert("This entire page is the output of the snippet above. " +
		                                              "<br/> Using bind on any input component prepares the variable for transport to the server." +
		                                              "<br/> You can see the bindings take place as you fill in the form" +
		                                              "<br/> The data is kept on the client until the variable is cleared. " +
		                                              "<br/>Navigating away will keep the data, refreshing will lose it").addClass(
				"col-12"));
		getAdditionalsRight().add(displayDiv);
	}

}
