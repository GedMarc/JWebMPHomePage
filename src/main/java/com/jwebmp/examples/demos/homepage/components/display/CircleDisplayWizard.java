package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.generics.LeftOrRight;
import com.jwebmp.plugins.smartwizard4.SmartWizard;
import com.jwebmp.plugins.smartwizard4.SmartWizardThemes;
import com.jwebmp.plugins.smartwizard4.options.SmartWizardToolbarPosition;

public class CircleDisplayWizard
		extends SmartWizard<CircleDisplayWizard>
{
	/**
	 * Configures the page for this component
	 *
	 * @param id
	 */
	public CircleDisplayWizard(String id)
	{
		super(id);
		getOptions().setTheme(SmartWizardThemes.Circles);

		getOptions()
				.getToolbarSettings()
				.setToolbarButtonPosition(LeftOrRight.Left);
		getOptions().getToolbarSettings()
		            .setToolbarPosition(SmartWizardToolbarPosition.none);

		getOptions()
				.getAnchorSettings()
				.setEnableAllAnchors(true)
				.setMarkDoneStep(true)
				.setMarkAllPreviousStepsAsDone(true)
				.setAnchorClickable(true);
	}
}
