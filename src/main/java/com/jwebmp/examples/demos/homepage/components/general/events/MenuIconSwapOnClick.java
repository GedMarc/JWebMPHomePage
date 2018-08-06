package com.jwebmp.examples.demos.homepage.components.general.events;

import com.jwebmp.core.Feature;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.interfaces.ICSSClassName;

public class MenuIconSwapOnClick
		extends Feature
{
	private ICSSClassName icon1;
	private ICSSClassName icon2;

	public MenuIconSwapOnClick(ComponentHierarchyBase component, ICSSClassName icon1, ICSSClassName icon2)
	{
		super("MenuIconSwopOnClick", component);
		this.icon1 = icon1;
		this.icon2 = icon2;
	}

	@Override
	protected void assignFunctionsToComponent()
	{
		String feature = "$('" + getComponent().getID(true) + "').on('click', function () {\n" +
		                 "      $(this)\n" +
		                 "        .find('[data-fa-i2svg]')\n" +
		                 "        .toggleClass('" + icon1.toString() + "')\n" +
		                 "        .toggleClass('" + icon2.toString() + "');\n" +
		                 "    });";
		addQuery(feature);

	}
}
