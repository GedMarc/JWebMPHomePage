package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.base.html.*;
import com.jwebmp.generics.LeftOrRight;
import com.jwebmp.htmlbuilder.css.colours.ColourCSSImpl;
import com.jwebmp.plugins.angularslimscroll.SlimScrollFeature;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions;
import com.jwebmp.plugins.bootstrap4.options.BSClearfixOptions;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.jquerylayout.layout.JQLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class DisplayScreen<J extends DisplayScreen<J>>
		extends DivSimple<J>
{
	private final Div content;
	private final BSRow<?> titleRow;
	private String welcomeText;
	private JQLayout innerLayout;

	private boolean renderBreadcrumb = true;

	public DisplayScreen(String welcomeText)
	{
		this();
		this.welcomeText = welcomeText;
	}

	public DisplayScreen()
	{
		addClass("content-page");
		setID("content-updatable");
		content = new Div();
		content.addClass("content");
		content.setID("content-updatable-content");
		innerLayout = new JQLayout(content);
		titleRow = new BSRow<>();
		innerLayout.setID("innerLayoutContainer");

		addClass(BSColumnOptions.H_100);
		addClass(BSColumnOptions.W_100);

		content.addClass(BSColumnOptions.H_100);
		content.addClass(BSColumnOptions.W_100);
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			add(content);
			Div d = getContentContainer();
			if (d != null)
			{
				d.setID("innerContentContainer");
				d.addClass(BSColumnOptions.H_100);
				d.addStyle("margin-top", "15px;");

				SlimScrollFeature scrollFeature = new SlimScrollFeature(d);
				scrollFeature.getOptions()
				             .setHeight("100%")
				             .setAlwaysVisible(true)
				             .setPosition(LeftOrRight.Right)
				             .setSize(5)
				             .setColor(new ColourCSSImpl("#98a6ad"))
				             .setWheelStep(5);

				innerLayout.getCenter()
				           .getContentDiv()
				           .add(d);
			}
			titleRow.getChildren()
			        .clear();
			titleRow.add(buildTitleRow());

			innerLayout.getCenter()
			           .addHeader(titleRow);

		}
		super.preConfigure();
	}

	@NotNull
	public abstract BSContainer<?> getContentContainer();

	protected Div buildTitleRow()
	{


		Div responsive = new Div();
		responsive.addClass(BSColumnOptions.Col_Sm_12);
		if (renderBreadcrumb)
		{
			Div pageTitleBox = new Div();
			pageTitleBox.addClass("page-title-box");
			pageTitleBox.addStyle("padding-bottom:5px;");
			responsive.add(pageTitleBox);

			H4<?> title = new H4<>(welcomeText);
			title.addClass("float-left");
			pageTitleBox.add(title);

			BSBreadCrumb crumbs = getTitleBreadcrumbs();
			crumbs.addClass("float-right");
			pageTitleBox.add(crumbs);

			Div clearFix = new Div();
			clearFix.addClass(BSClearfixOptions.Clearfix);
			pageTitleBox.add(clearFix);

			responsive.add(pageTitleBox);
		}
		return responsive;
	}

	@NotNull
	public abstract BSBreadCrumb<?> getTitleBreadcrumbs();

	protected DivSimple<?> buildPortlet(String header, BSBackgroundOptions bgColor, boolean showRefresh, @NotNull Div content)
	{
		return buildPortlet(header, bgColor.toString(), showRefresh, content);
	}

	protected DivSimple<?> buildPortlet(String header, String bgColor, boolean showRefresh, @NotNull Div content)
	{
		DivSimple<?> responsive = new DivSimple();
		responsive.addClass(BSColumnOptions.Col_12);

		Div portlet = new Div();
		portlet.addClass("portlet");
		responsive.add(portlet);

		Div heading = new Div();
		heading.addClass("portlet-heading");
		heading.addClass(bgColor);
		portlet.add(heading);

		H3 headingTitle = new H3(header);
		headingTitle.addClass("portlet-title");
		heading.add(headingTitle);

		Div portletHeadingWidgets = new Div();
		portletHeadingWidgets.addClass("portlet-widgets");
		heading.add(portletHeadingWidgets);

		if (showRefresh)
		{
			Link link = new Link<>();
			link.setDirectToAddress("javascript:;");
			link.addAttribute("data-toggle", "reload");

			Italic refreshIcon = new Italic();
			refreshIcon.addClass("ion-refresh");
			link.add(refreshIcon);

			portletHeadingWidgets.add(link);
			Span divider = new Span();
			divider.addClass("divider");
			portletHeadingWidgets.add(divider);
		}
		Link collapseLink = new Link(content.getID(true));
		collapseLink.addAttribute("data-parent", "#accordion1");
		Italic refreshIcon = new Italic();
		refreshIcon.addClass("ion-minus-round");
		collapseLink.add(refreshIcon);

		content.addClass("panel-collapse");
		BSCollapse.link(collapseLink, content, false);
		portletHeadingWidgets.add(collapseLink);

		Span divider = new Span();
		divider.addClass("divider");
		portletHeadingWidgets.add(divider);

		Link removeLink = new Link("#");
		removeLink.addAttribute("data-toggle", "remove");
		Italic removeIcon = new Italic();
		removeIcon.addClass("ion-close-round");
		removeLink.add(removeIcon);
		portletHeadingWidgets.add(removeLink);

		heading.add(new Div().addClass(BSClearfixOptions.Clearfix));

		Div portletContentHolder = new Div();
		portletContentHolder.addClass("portlet-body");
		portletContentHolder.add(content);
		portlet.add(portletContentHolder);

		return responsive;
	}

	public Div getContent()
	{
		return content;
	}

	public BSRow<?> getTitleRow()
	{
		return titleRow;
	}


	public String getWelcomeText()
	{
		return welcomeText;
	}

	public void setWelcomeText(String welcomeText)
	{
		this.welcomeText = welcomeText;
	}

	public JQLayout getInnerLayout()
	{
		return innerLayout;
	}

	public void setInnerLayout(JQLayout innerLayout)
	{
		this.innerLayout = innerLayout;
	}

	public boolean isRenderBreadcrumb()
	{
		return renderBreadcrumb;
	}

	public DisplayScreen<J> setRenderBreadcrumb(boolean renderBreadcrumb)
	{
		this.renderBreadcrumb = renderBreadcrumb;
		return this;
	}
}
