package com.jwebmp.examples.demos.homepage.display.demos.icons.fontawesome;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome.FontAwesomeProperties;

public class FontAwesomeDemoScreen
		extends PluginDemoScreen
{

	public FontAwesomeDemoScreen()
	{
		super("Font Awesome", "Icon Sets", "Font Awesome", "Version 4.7");

		BSRow row = BSRow.newInstance();

		SourceCodeContentPanel panel = new SourceCodeContentPanel("Font Awesome 4.7 Icons", DisplayCodeParts.FontAwesome, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

		addDiv(panel);

		for (FontAwesomeIcons fontAwesomeIcon : FontAwesomeIcons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 col-md-2 col-lg-1 text-center");
			d.add(new FontAwesome(fontAwesomeIcon, FontAwesomeProperties.$2x));
			d.add(new Paragraph<>(fontAwesomeIcon.toString()));
			row.add(d);
		}
	}
}
