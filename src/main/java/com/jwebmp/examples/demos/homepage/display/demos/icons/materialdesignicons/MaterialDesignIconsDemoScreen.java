package com.jwebmp.examples.demos.homepage.display.demos.icons.materialdesignicons;

import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.materialdesignicons.MaterialDesignIcon;
import com.jwebmp.plugins.materialdesignicons.MaterialDesignIcons;

public class MaterialDesignIconsDemoScreen
		extends PluginDemoScreen
{

	public MaterialDesignIconsDemoScreen()
	{
		super("Material Design Icons", "Icon Sets", "Material Design Icons", "SVG");

		BSRow row = BSRow.newInstance();

		SourceCodeContentPanel panel = new SourceCodeContentPanel("Material Design Icons", DisplayCodeParts.MaterialDesignIcons3, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

		addDiv(panel);
		for (MaterialDesignIcons icon : MaterialDesignIcons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 col-md-2 col-lg-1 text-center");
			d.add(new MaterialDesignIcon(icon));
			d.add(new Paragraph<>(icon.toString()));
			row.add(d);
		}
	}
}
