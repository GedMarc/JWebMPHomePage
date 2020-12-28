package com.jwebmp.examples.apps.homepage.components;

import com.jwebmp.core.generics.LeftOrRight;
import com.jwebmp.plugins.bootstrap4.cards.BSCardChildren;
import com.jwebmp.plugins.smartwizard.SmartWizard;
import com.jwebmp.plugins.smartwizard.options.SmartWizardToolbarPosition;

public class DefaultSmartWizard
		extends SmartWizard<DefaultSmartWizard>
		implements BSCardChildren
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
