package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;

public class HtmlTagsDemoScreen
		extends PluginDemoScreen
{

	public HtmlTagsDemoScreen()
	{
		super("HtmlTags", "Base", "HTML Tags");

		BSRow row = BSRow.newInstance();

		SourceCodeContentPanel panel = new SourceCodeContentPanel("com.jwebmp.base.html", null, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

	}
}
