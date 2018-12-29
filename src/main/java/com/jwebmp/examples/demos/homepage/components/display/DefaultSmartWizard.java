package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.generics.LeftOrRight;
import com.jwebmp.plugins.smartwizard4.SmartWizard;
import com.jwebmp.plugins.smartwizard4.options.SmartWizardToolbarPosition;

public class DefaultSmartWizard
		extends SmartWizard<DefaultSmartWizard>
{
	/**
	 * Configures the page for this component
	 *
	 * @param id
	 */
	public DefaultSmartWizard(String id)
	{
		super(id);

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

		//addStyle("max-height", "550px");
		//addStyle("height", "550px");
		//addStyle("overflow-y", "auto");
	}
}
