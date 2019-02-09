package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.plugins.bootstrap4.cards.parts.styles.BSCardButtonDarkOutline;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.bootstrap4.options.BSDisplayOptions.*;

public class PrettyCollapsable
		extends DivSimple<PrettyCollapsable>
{
	private ComponentHierarchyBase collapsingComponent;
	private BSCardButtonDarkOutline button;
	private boolean hideOnStart = true;

	public PrettyCollapsable(ComponentHierarchyBase component, String text)
	{
		this(component, text, true);
	}

	public PrettyCollapsable(ComponentHierarchyBase component, String text, boolean hideOnStart)
	{
		/*addClass(W_100, H_100);*/
		this.collapsingComponent = component;
		this.button = new BSCardButtonDarkOutline();
		button.addClass(Block);
		button.addStyle("background-color", "#3d4853")
		      .addStyle("color", "#3bafda");
		button.setText(text + HTML_TAB + FontAwesome.icon(FontAwesomeIcons.caret_circle_down, FontAwesomeStyles.Light)
		                                            .toString(0));

		add(button);
		add(collapsingComponent);
		setHideOnStart(hideOnStart);
		BSCollapse.link(button, collapsingComponent, isHideOnStart());
	}

	public boolean isHideOnStart()
	{
		return hideOnStart;
	}

	public void setHideOnStart(boolean hideOnStart)
	{
		this.hideOnStart = hideOnStart;
	}

	public ComponentHierarchyBase getCollapsingComponent()
	{
		return collapsingComponent;
	}

	public void setCollapsingComponent(ComponentHierarchyBase collapsingComponent)
	{
		this.collapsingComponent = collapsingComponent;
	}

	public BSCardButtonDarkOutline getButton()
	{
		return button;
	}

	public void setButton(BSCardButtonDarkOutline button)
	{
		this.button = button;
	}
}
