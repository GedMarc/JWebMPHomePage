package com.jwebmp.examples.demos.homepage.display.demos.icons.glyphicons;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;

public class GlyphiconsDemoScreen
		extends PluginDemoScreen
{

	public GlyphiconsDemoScreen()
	{
		super("Glyphicons", "Icon Sets", "Glyphicons", "Bootstrap 3 Set");
		BSRow row = new BSRow();
/*		SourceCodeContentPanel panel = new SourceCodeContentPanel("Glyphicons Example", DisplayCodeParts.Glyphicons, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

		addDiv(panel);*/
/*
		for (Glyphicons glyphicon : Glyphicons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 col-md-2 col-lg-1 text-center");
			d.add(new Italic<>().addClass(glyphicon.toString() + " fa-2x"));
			d.add(new Paragraph<>(glyphicon.toString()));
			row.add(d);
		}*/
	}
}
