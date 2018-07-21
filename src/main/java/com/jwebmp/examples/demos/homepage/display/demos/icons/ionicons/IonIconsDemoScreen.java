package com.jwebmp.examples.demos.homepage.display.demos.icons.ionicons;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.ionic.ionicons.IonIcon;
import com.jwebmp.plugins.ionic.ionicons.IonIcons;

public class IonIconsDemoScreen
		extends PluginDemoScreen
{

	public IonIconsDemoScreen()
	{
		super("Ion Icons", "Icon Sets", "Ion Icons");

		BSRow row = BSRow.newInstance();

		SourceCodeContentPanel panel = new SourceCodeContentPanel("Ion Icons", DisplayCodeParts.FontAwesome, null);
		panel.getContent()
		     .add(row);
		panel.setShowHeader(true);

		addDiv(panel);

		for (IonIcons ionIcon : IonIcons.values())
		{
			DivSimple d = new DivSimple();
			d.addClass("col-3 col-md-2 col-lg-1 text-center");
			d.add(new IonIcon(ionIcon));
			d.add(new Paragraph<>(ionIcon.toString()));
			row.add(d);
		}

	}

}
