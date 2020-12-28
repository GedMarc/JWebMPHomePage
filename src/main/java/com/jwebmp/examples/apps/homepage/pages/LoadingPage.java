package com.jwebmp.examples.apps.homepage.pages;

import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.plugins.themes.mintontheme.partials.BreadcrumbItem;
import com.jwebmp.plugins.themes.mintontheme.partials.Breadcrumbs;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Duotone;

@Accessors(chain = true)
@Getter
@Setter
public class LoadingPage extends SwapScreen<LoadingPage>
{
	public LoadingPage()
	{
		add(icon(FontAwesomeIcons.cog, Duotone).addClass("fa-5x")
		                                       .addClass("fa-spin"));
	}
	
	public LoadingPage(String replaceID)
	{
		this();
		setID(replaceID);
	}
	
	@Override
	public PageTitle pageTitle()
	{
		return new PageTitle("Loading...", new Breadcrumbs().addCrumb(new BreadcrumbItem("Loading...")));
	}
}
