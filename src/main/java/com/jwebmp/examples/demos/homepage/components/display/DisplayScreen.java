package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.Page;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H4;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.plugins.angularslimscroll.SlimScrollFeature;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;

import jakarta.validation.constraints.NotNull;

public abstract class DisplayScreen<J extends DisplayScreen<J>>
		extends DisplayPart<J>
{

	private String welcomeText;
	private boolean renderBreadcrumb = true;

	public DisplayScreen(String welcomeText)
	{
		this();
		this.welcomeText = welcomeText;
	}

	public DisplayScreen()
	{

	}

	@Override
	public void init()
	{
		if (!isInitialized())
		{
			setID("content-updatable");
			addStyle("background-color:#333");
			addStyle("padding:0px;");
			addStyle("height:inherited !important;");

			Div d = getContentContainer();
			d.setID("content-updatable-content");

			BSCardBody body = addCardBody().add(d);
			body.addStyle("padding:0px;");

			SlimScrollFeature destroyFeature = new SlimScrollFeature(this).setSortOrder(40);
			destroyFeature.getOptions()
			              .setDestroy(true);
			addFeature(destroyFeature);

			addFeature(new DefaultSlimScrollFeature(this).setSortOrder(45));

			d.setID("innerContentContainer");
			if (getPage().isMobileOrSmartTablet())
			{
				d.addStyle("padding-left", "0px");
				d.addStyle("padding-right", "0px");
			}

			buildTitleRow();
			GuiceContext.get(AjaxResponse.class)
			            .addComponent(d);
		}
		super.init();
	}

	@NotNull
	public abstract BSContainer<?> getContentContainer();

	protected Div buildTitleRow()
	{
		Page<?> page = GuiceContext.get(Page.class);
		Div responsive = new Div<>().setID("innerNorth");
		if (!page.isMobileOrSmartTablet())
		{
			Div pageTitleBox = new Div();
			pageTitleBox.addClass("page-title-box");
			pageTitleBox.addStyle("height:60px;");
			pageTitleBox.addStyle("padding-bottom:5px;");

			responsive.add(pageTitleBox);
			H4<?> title = new H4<>(welcomeText);
			title.addClass("float-left");
			pageTitleBox.add(title);
			if (renderBreadcrumb && !page.isMobileOrSmartTablet())
			{
				BSBreadCrumb crumbs = getTitleBreadcrumbs();
				crumbs.addClass("float-right");
				pageTitleBox.add(crumbs);
			}
			responsive.add(pageTitleBox);
			responsive.addStyle("height:60px;");
		}
		GuiceContext.get(AjaxResponse.class)
		            .addComponent(responsive);
		return responsive;
	}

	@NotNull
	public abstract BSBreadCrumb<?> getTitleBreadcrumbs();

	public void setWelcomeText(String welcomeText)
	{
		this.welcomeText = welcomeText;
	}

	public DisplayScreen<J> setRenderBreadcrumb(boolean renderBreadcrumb)
	{
		this.renderBreadcrumb = renderBreadcrumb;
		return this;
	}

}
