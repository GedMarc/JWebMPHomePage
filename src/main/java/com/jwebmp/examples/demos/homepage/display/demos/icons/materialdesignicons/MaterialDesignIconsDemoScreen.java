package com.jwebmp.examples.demos.homepage.display.demos.icons.materialdesignicons;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;

public class MaterialDesignIconsDemoScreen
		extends PluginDemoScreen
{

	public MaterialDesignIconsDemoScreen()
	{
		super("Material Design Icons", "Icon Sets", "Material Design Icons", "SVG");

		BSRow row = BSRow.newInstance();

/*		SourceCodeContentPanel panel = new SourceCodeContentPanel("Material Design Icons", DisplayCodeParts.MaterialDesignIcons3, null);
		panel.getContext()
		     .add(row);
		panel.setShowHeader(true);

		addDiv(panel);*/
	/*	for (MaterialDesignIcons icon : MaterialDesignIcons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 col-md-2 col-lg-1 text-center");
			d.add(new MaterialDesignIcon(icon));
			d.add(new Paragraph<>(icon.toString()));
			row.add(d);
		}*/
	}
}
