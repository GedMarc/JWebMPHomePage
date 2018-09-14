package com.jwebmp.examples.demos.homepage.display;

import com.jwebmp.core.PlaceHolder;
import com.jwebmp.plugins.jqlayout.JQLayoutHeaderDiv;
import com.jwebmp.plugins.jqlayout.components.BorderLayout;
import com.jwebmp.plugins.jqlayout.enumerations.LayoutResponsiveSize;

public class OuterLayout
		extends BorderLayout<OuterLayout>
{
	private BorderLayout<?> innerLayout;

	public OuterLayout()
	{
		super();
		setID("wrapper");
		innerLayout = new BorderLayout();
		getCenter().setContentDiv(innerLayout);

		getOptions().getNorth()
		            .setResizable(false)
		            .setMinSize(68)
		            .setMaxSize(68)
		            .setTogglerLengthOpen(200)
		            .setTogglerLengthClosed(200);

		getOptions().getDefaults()
		            .getResponsive()
		            .setEnabled(true)
		            .setWhen(LayoutResponsiveSize.Medium)
		            .getSizes()
		            .put(LayoutResponsiveSize.Medium, 480);

		getOptions().getWest()
		            .getResponsive()
		            .setEnabled(true)
		            .setWhen(LayoutResponsiveSize.Small)
		            .getSizes()
		            .put(LayoutResponsiveSize.Small, 240);

		getNorth().getContentDiv()
		          .add(new PlaceHolder<>("topbar").addStyle("height", "70px"));

		getWest().getContentDiv()
		         .add(new PlaceHolder<>("west"));

		innerLayout.setFullScreen(true);
		innerLayout.setID("innerLayoutContainer");
		innerLayout.getCenter()
		           .getContentDiv()
		           .setID("innerlayout-center-content");

		innerLayout.getCenter()
		           .getContentDiv()
		           .add(new PlaceHolder<>("content-updatable"));

		if (!getPage().isMobileOrSmartTablet())
		{
			innerLayout.getCenter()
			           .getHeaders()
			           .add(new JQLayoutHeaderDiv<>().add(new PlaceHolder<>("innerNorth").addStyle("height:60px;")));
		}

		if (getPage().isMobileOrSmartTablet())
		{
			getOptions().getNorth()
			            //.setInitClosed(true)
			            .setSpacingClosed(15)
			            .setResizable(true)
			            .setResizerClass("btn-custom btn-primary")
			            .setTogglerClass("btn-custom btn-secondary")
			            .setSpacingOpen(15)
			            .setSpacingClosed(15);

			getOptions().getWest()
			            .setResizerClass("btn-custom btn-primary")
			            .setTogglerClass("btn-custom btn-secondary")
			            .setSpacingOpen(15)
			            .setSpacingClosed(15)
			            .setTogglerLengthOpen(200)
			            .setTogglerLengthClosed(200);
		}
		else
		{
			getWest().getOptions()
			         .setMinSize(240)
			         .setMaxSize(240);

			getOptions().getDefaults()
			            .setResizerClass("btn-custom btn-primary")
			            .setTogglerClass("btn-custom btn-secondary")
			            .setResizable(false)
			            .setSpacingClosed(0)
			            .setSpacingOpen(0);
		}
	}
}
