package com.jwebmp.examples.demos.homepage.display;

import com.google.inject.Singleton;
import com.jwebmp.PlaceHolder;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.examples.demos.homepage.components.sourcecode.SourceCodeModal;
import com.jwebmp.plugins.jquerylayout.layout.JQLayout;
import za.co.mmagon.guiceinjection.GuiceContext;

import static com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions.Bg_Dark;

@Singleton
public class OuterLayout
		extends JQLayout
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
		configureForComponent(component);
	}

	public OuterLayout()
	{
		super();
	}

	public void setup()
	{
		setID("wrapper");
		if (!isConfigured())
		{
			getOptions().getNorth()
			            .setResizable(false)
			            .setTogglerLengthOpen(175)
			            .setTogglerLengthClosed(175);

			getWest().getOptions()
			         .setMinSize(240);
			getNorth().getOptions()
			          .setMinSize(68);
			getEast().getOptions()
			         .setInitClosed(true);

			getOptions().getDefaults()
			            .setResizerClass("btn-custom btn-primary");
			getOptions().getDefaults()
			            .setTogglerClass("btn-custom btn-secondary");
			getOptions().getDefaults()
			            .setSpacingClosed(15)
			            .setSpacingOpen(15);

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
/*
			getCenter().getContentDiv()
			           .add(new HomePage());*/
			getCenter().getContentDiv()
			           .add(GuiceContext.getInstance(SourceCodeModal.class));
		}
	}

}
