package com.jwebmp.examples.demos.homepage.display.demos.angular.animate;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.Paragraph;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.plugins.angularanimate.AngularAnimatePageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class AngularAnimateDemoScreen
		extends DemoScreen
{
	public AngularAnimateDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularanimate", AngularAnimatePageConfigurator.class, "Angular Animate", true, true));
		add(buildGoToSource(AngularAnimateDemoScreen.class, DefaultPackageAPI.class, OptionsBrowser.class));

		Div sourceDiv = new Div<>().addClass(Col_12, Row);

		sourceDiv.add(new Paragraph("An example CSS may look like<br/>").addClass(Col_12));
		addSourceToContainer(AngularAnimateDemoScreen.class, "angularanimatecss.css", SourceCodeLanguages.CSS, sourceDiv);
		sourceDiv.add(new Paragraph("With the accompanying component</br>").addClass(Col_12));
		addSourceToContainer(AngularAnimateDemoScreen.class, "angularanimate.txt", SourceCodeLanguages.Java, sourceDiv);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);
	}
}
