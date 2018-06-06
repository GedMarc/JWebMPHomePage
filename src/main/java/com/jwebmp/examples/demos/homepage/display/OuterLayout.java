package com.jwebmp.examples.demos.homepage.display;

import com.jwebmp.Page;
import com.jwebmp.PlaceHolder;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.guiceinjection.GuiceContext;
import com.jwebmp.plugins.jquerylayout.layout.components.BorderLayout;
import lombok.extern.java.Log;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.*;

@Log
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

		getEast().addStyle("z-index:100 !important;");

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
		getEast().getContentDiv()
		         .add(new PlaceHolder<>("rightBar"));

		getEast().getContentDiv()
		         .addClass(Bg_Dark);
	}

	@Override
	public void init()
	{
		Page p = GuiceContext.getInstance(Page.class);
		if (p.isMobileOrSmartTablet())
		{
			getWest().getOptions()
			         .setInitClosed(true);
			getEast().getOptions()
			         .setInitClosed(true);
		}
		super.init();
	}
}
