package com.jwebmp.examples.demos.homepage.components.display;

import com.jwebmp.core.Page;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.angularslimscroll.SlimScrollFeature;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.options.BSClearfixOptions;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.FontAwesomeList;
import com.jwebmp.plugins.fontawesome5.IFontAwesomeIcon;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import io.undertow.util.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	@NotNull
	public abstract BSContainer<?> getContentContainer();

	protected Div buildTitleRow()
	{
		Page page = GuiceContext.getInstance(Page.class);
		Div responsive = new Div<>().setID("innerNorth");
		if(!page.isMobileOrSmartTablet())
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

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			setID("content-updatable");
			addStyle("background-color:#333");
			addStyle("padding:0px;");

			Div d = getContentContainer();
			d.setID("content-updatable-content");
			if(!getPage().isMobileOrSmartTablet())
			{
				d.addStyle("margin-top:15px");
			}


			BSCardBody body = addCardBody().add(d);
			body.addStyle("padding:0px;");

			SlimScrollFeature destroyFeature = new SlimScrollFeature(this);
			destroyFeature.getOptions()
			              .setDestroy(true);
			addFeature(destroyFeature);
			addFeature(new DefaultSlimScroll(this));

			d.setID("innerContentContainer");
			if(getPage().isMobileOrSmartTablet())
			{
				d.addStyle("padding-left", "0px");
				d.addStyle("padding-right", "0px");
			}

			Div slimScrollerSpacer = new Div<>().setID("slimScrollSpacer");
			slimScrollerSpacer.addStyle("height:70px;");
			d.add(slimScrollerSpacer);

			buildTitleRow();
		}
		super.preConfigure();
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
