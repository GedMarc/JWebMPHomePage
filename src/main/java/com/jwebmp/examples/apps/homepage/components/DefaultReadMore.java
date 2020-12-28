package com.jwebmp.examples.apps.homepage.components;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.plugins.bootstrap4.buttons.styles.BSButtonPrimaryOutline;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;

public class DefaultReadMore
		extends DivSimple<DefaultReadMore>
{
	public DefaultReadMore(IComponentHierarchyBase<?,?> componentHierarchyBase)
	{
		BSButtonPrimaryOutline<?> button = new BSButtonPrimaryOutline<>().setText("Read More ");
		add(button);
		add(componentHierarchyBase);
		BSCollapse.link(button, componentHierarchyBase, true);
	}

	public DefaultReadMore(IComponentHierarchyBase<?,?> componentHierarchyBase, String text)
	{
		this(componentHierarchyBase, text, true);
	}

	public DefaultReadMore(IComponentHierarchyBase<?,?> componentHierarchyBase, String text, boolean hideOnStart)
	{
		BSButtonPrimaryOutline<?> button = new BSButtonPrimaryOutline<>().setText(text);
		add(button);
		add(componentHierarchyBase);
		BSCollapse.link(button, componentHierarchyBase, hideOnStart);
	}
}
