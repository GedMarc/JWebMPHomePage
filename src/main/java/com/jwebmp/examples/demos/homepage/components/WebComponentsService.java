package com.jwebmp.examples.demos.homepage.components;

import com.google.inject.Singleton;
import com.jwebmp.examples.demos.homepage.components.display.DisplayAPITabView;
import com.jwebmp.examples.demos.homepage.components.general.PackagesBrowser;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;

@Singleton
public class WebComponentsService
{
	public WebComponentsService()
	{
	}

	@CacheResult
	public DisplayAPITabView getTabViewDisplay(@CacheKey Class clazz)
	{
		return new DisplayAPITabView(clazz);
	}

	@CacheResult
	public PackagesBrowser getPackagesBrowser(@CacheKey String packageName)
	{
		return getPackagesBrowser(packageName, true);
	}

	@CacheResult
	public PackagesBrowser getPackagesBrowser(@CacheKey String packageName, @CacheKey boolean includeSubPackages)
	{
		PackagesBrowser browser = new PackagesBrowser(packageName, includeSubPackages);
		browser.setID("packageBrowser");
		return browser;
	}
}
