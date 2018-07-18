package com.jwebmp.examples.demos.homepage.display.demos.icons.fontawesome5;

import com.jwebmp.base.html.Div;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles;

public class FontAwesome5DemoScreen
		extends PluginDemoScreen
{

	public FontAwesome5DemoScreen()
	{
		super("Font Awesome 5", "Icon Sets", "Font Awesome", "Version 5");

		BSRow row = BSRow.newInstance();

		SourceCodeContentPanel panel = new SourceCodeContentPanel("Font Awesome 5 Icons", DisplayCodeParts.FontAwesome, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

		Div details = new Div<>().addClass("col-12");
		details.add(
				"Icons may display as missing - this highlights the differences between Free and Pro.<br/> To disable showing the icon entirely use the FontAwesome5PageConfigurator to configure accordingly.");
		details.add("Adding your Pro license is as simple as FontAwesome5PageConfigurator.setRootReferenceDir(\"bower_components/font...\");");
		addDiv(details);

		addDiv(panel);

		for (FontAwesomeIcons fontAwesomeIcon : FontAwesomeIcons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 col-md-2 col-lg-1 text-center");
			d.add(new FontAwesome(FontAwesomeStyles.Regular, fontAwesomeIcon));
			d.add(new Paragraph<>(fontAwesomeIcon.toString()));
			row.add(d);
		}
	}
}