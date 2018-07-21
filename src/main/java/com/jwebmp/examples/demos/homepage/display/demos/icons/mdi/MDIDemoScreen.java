package com.jwebmp.examples.demos.homepage.display.demos.icons.mdi;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.materialicons.MDIIcon;
import com.jwebmp.plugins.materialicons.MDIIcons;

public class MDIDemoScreen
		extends PluginDemoScreen
{

	public MDIDemoScreen()
	{
		super("MDI", "Icon Sets", "Material Design Icons", "Web Fonts");

		BSRow row = BSRow.newInstance();

		SourceCodeContentPanel panel = new SourceCodeContentPanel("Material Design Icons - Web Fonts", DisplayCodeParts.MaterialDesignIcons2, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

		addDiv(panel);

		for (MDIIcons icon : MDIIcons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 col-md-2 col-lg-1 text-center");
			d.add(new MDIIcon(icon));
			d.add(new Paragraph<>(icon.toString()));
			row.add(d);
		}
	}
}
