package com.jwebmp.examples.demos.homepage.display;

import com.jwebmp.core.Page;
import com.jwebmp.core.PlaceHolder;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.jqlayout.components.BorderLayout;
import com.jwebmp.plugins.jqlayout.enumerations.LayoutResponsiveSize;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;

public class OuterLayout
		extends BorderLayout<OuterLayout>
{
	/**
	 * Constructs a new JWLayout Layout Handler with the given parameters
	 * <p>
	 *
	 * @param component
	 * 		The component to apply the Layout Handler To
	 */
	public OuterLayout(ComponentHierarchyBase component)
	{
		this();
	}

	public OuterLayout()
	{
		super();
		setID("wrapper");

		getOptions().getNorth()
		            .setResizable(false)
		            .setMinSize(68)
		            .setMaxSize(68)
		            .setTogglerLengthOpen(175)
		            .setTogglerLengthClosed(175);

		getWest().getOptions()
		         .setMinSize(240)
		         .setMaxSize(240);

		getOptions().getDefaults()
		            .setResizerClass("btn-custom btn-primary")
		            .setTogglerClass("btn-custom btn-secondary")
		            .setResizable(false)
		            .setSpacingClosed(0)
		            .setSpacingOpen(0);

		getCenter().getContentDiv()
		           .add(new PlaceHolder<>("content-updatable"));
		getNorth().getContentDiv()
		          .add(new PlaceHolder<>("topbar"));
		getWest().getContentDiv()
		         .add(new PlaceHolder<>("west"));
		/*getWest().getOptions()
		         .setInitClosed(true);*/
	}
}
