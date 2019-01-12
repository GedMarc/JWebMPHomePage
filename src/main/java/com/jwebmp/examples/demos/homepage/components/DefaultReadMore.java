package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonPrimaryOutline;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;

public class DefaultReadMore
		extends DivSimple<DefaultReadMore>
{
	public DefaultReadMore(ComponentHierarchyBase componentHierarchyBase)
	{
		BSButtonPrimaryOutline button = new BSButtonPrimaryOutline<>().setText("Read More ");
		add(button);
		add(componentHierarchyBase);
		BSCollapse.link(button, componentHierarchyBase, true);
	}

	public DefaultReadMore(ComponentHierarchyBase componentHierarchyBase, String text)
	{
		BSButtonPrimaryOutline button = new BSButtonPrimaryOutline<>().setText(text);
		add(button);
		add(componentHierarchyBase);
		BSCollapse.link(button, componentHierarchyBase, true);
	}
}
