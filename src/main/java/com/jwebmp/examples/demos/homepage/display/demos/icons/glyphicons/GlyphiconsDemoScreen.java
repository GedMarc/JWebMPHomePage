package com.jwebmp.examples.demos.homepage.display.demos.icons.glyphicons;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Italic;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.glyphicons.Glyphicons;

public class GlyphiconsDemoScreen
		extends PluginDemoScreen
{

	public GlyphiconsDemoScreen()
	{
		super("Glyphicons", "Icon Sets", "Glyphicons", "Bootstrap 3 Set");
		BSRow row = new BSRow();
		SourceCodeContentPanel panel = new SourceCodeContentPanel("Glyphicons Example", DisplayCodeParts.Glyphicons, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);


		addDiv(panel);

		for (Glyphicons glyphicon : Glyphicons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 text-center");
			d.add(new Italic<>().addClass(glyphicon.toString() + " fa-2x"));
			d.add(new Paragraph<>(glyphicon.toString()));
			row.add(d);
		}
	}
}
