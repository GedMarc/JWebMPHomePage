package com.jwebmp.examples.apps.homepage.services;

import com.google.inject.Singleton;
import com.jwebmp.examples.apps.homepage.components.browse.DisplayAPITabView;
import com.jwebmp.examples.apps.homepage.components.browse.PackagesBrowser;

@Singleton
public class WebComponentsService
{
	public WebComponentsService()
	{
	}
	
	//@CacheResult
	public DisplayAPITabView getTabViewDisplay(Class clazz)
	{
		return new DisplayAPITabView(clazz);
	}
	
	//@CacheResult
	public PackagesBrowser getPackagesBrowser(String packageName)
	{
		return getPackagesBrowser(packageName, true);
	}
	
	//@CacheResult
	public PackagesBrowser getPackagesBrowser(String packageName, boolean includeSubPackages)
	{
		PackagesBrowser browser = new PackagesBrowser(packageName, includeSubPackages);
		browser.setID("packageBrowser");
		return browser;
	}
}
