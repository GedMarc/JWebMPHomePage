package com.jwebmp.examples.demos.homepage.display.demos.angular.animate;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularAnimateDemoScreen
		extends PluginDemoScreen
{
	public AngularAnimateDemoScreen()
	{
		super("Angular Animate", "Angular", "Utilities", "Animations");

		addComponentTile("AngularAnimateModule",
		                 "AngularJS provides animation hooks for common directives such as ngRepeat, ngSwitch, and ngView, as well as custom directives via the $animate service");

		getAdditionalsRight().add(new Div<>().add("Simply including this module installs the ngAnimate module." +
		                                          "<br/>You can add custom CSS either as a reference or a component to modify as below.." +
		                                          "<br/> View the demo site above for more in depth details about this module"));

		Div sourceDiv = new Div<>().addClass(Col_12);
		sourceDiv.add("An example CSS may look like");
		addSourceToContainer(AngularAnimateDemoScreen.class, "angularanimatecss.css", SourceCodeLanguages.CSS, sourceDiv);
		sourceDiv.add("With the accompanying component");
		addSourceToContainer(AngularAnimateDemoScreen.class, "angularanimate.txt", SourceCodeLanguages.Java, sourceDiv);
		getAdditionals().add(sourceDiv);
	}

}
