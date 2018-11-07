package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.generics.LeftOrRight;
import com.jwebmp.plugins.smartwizard4.SmartWizard;
import com.jwebmp.plugins.smartwizard4.options.SmartWizardToolbarPosition;

public class DefaultDisplayWizard
		extends SmartWizard<DefaultDisplayWizard>
{
	/**
	 * Configures the page for this component
	 *
	 * @param id
	 */
	public DefaultDisplayWizard(String id)
	{
		super(id);

		getOptions()
				.getToolbarSettings()
				.setToolbarButtonPosition(LeftOrRight.Left);
		getOptions().getToolbarSettings()
		            .setToolbarPosition(SmartWizardToolbarPosition.top);

		getOptions()
				.getAnchorSettings()
				.setEnableAllAnchors(true)
				.setMarkDoneStep(true)
				.setAnchorClickable(true);
	}
}
