package com.jwebmp.examples.demos.homepage;

import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.base.angular.modules.AngularMessagesModule;
import com.jwebmp.core.generics.WebReference;
import com.jwebmp.examples.demos.homepage.components.WebComponentsService;
import com.jwebmp.examples.demos.homepage.db.dao.PluginsService;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
import com.jwebmp.guicedpersistence.readers.hibernateproperties.HibernateEntityManagerProperties;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.logger.logging.LogColourFormatter;
import com.jwebmp.plugins.blueimp.gallery.BlueImpGalleryPageConfigurator;
import com.jwebmp.plugins.datatable.DataTablePageConfigurator;
import com.jwebmp.plugins.datatable.enumerations.DataTableThemes;
import com.jwebmp.plugins.fontawesome5.config.FontAwesome5PageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettifyPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;
import com.jwebmp.plugins.skycons.configurator.SkyconPageConfigurator;
import com.jwebmp.undertow.JWebMPUndertow;
import com.jwebmp.undertow.JWebMPUndertowWebSocketConfiguration;
import com.jwebmp.websockets.WebSocketsConfiguration;

import java.util.logging.Level;

public class HomePageStartup
{
	public static void main(String[] args)
	{
		//Log testing start?
		System.out.println("Starting");
		LogColourFormatter.setRenderBlack(false);
		LogFactory.configureConsoleColourOutput(Level.FINE);

		//Global configurations
		WebSocketsConfiguration.setLocalStorageEnabled(true);
		SessionHelper.setAddressToBeUsedWhenNull("https://jwebmp.com/");
		//Allows multiple host names rendering.
		//When behind a proxy, set the header flag "jwsiteurl https://jwebmp.com/"
		SessionHelper.setCacheAddress(false);
		//Enable Web Sockets for Undertow?
		JWebMPUndertowWebSocketConfiguration.setEnabled(true);
		//Add the library version to the rendered web  references
		WebReference.setUseVersionIdentifier(true);
		//Forms will use messages
		AngularMessagesModule.setMesssgesModuleEnabled(true);

		//Default all db connections?
		HibernateEntityManagerProperties.getDefaultProperties()
		                                .setShowSql(true);
		HibernateEntityManagerProperties.getDefaultProperties()
		                                .setFormatSql(true);
		HibernateEntityManagerProperties.getDefaultProperties()
		                                .setUseQueryStartupCheck(false);

		//Some more configs but more related to this app specifically
		HomePageStartup startup = new HomePageStartup();
		startup.configureUsedPlugins();
		startup.blockUnusedPlugins();
		//Start her up
		try
		{
			JWebMPUndertow.boot("0.0.0.0", 6003);
		}
		catch (Exception e)
		{
			LogFactory.getLog("Main")
			          .log(Level.SEVERE, "oops", e);
		}
		//Nice example of in-line sorting calling
		startup.wipeCaches();
	}

	private void configureUsedPlugins()
	{
		BTMAutomatedTransactionHandler.setActive(true);
		BlueImpGalleryPageConfigurator.setIncludeIndicators(true);

		FontAwesome5PageConfigurator.setIncludeRegular(true);
		FontAwesome5PageConfigurator.setIncludeSolid(true);
		FontAwesome5PageConfigurator.setIncludeLight(true);
		FontAwesome5PageConfigurator.setIncludeBrands(true);

		//TODO disable if no pro version of font awesome
		FontAwesome5PageConfigurator.setRootReferenceDir("fontawesome-pro-5.4.1-web/js/");
		//This is the good stuff
		FontAwesome5PageConfigurator.getConfigOptions()
		                            .setSearchPseudoElements(true);
		//Bootstrap 4 as the theme core for most components
		DataTablePageConfigurator.switchTheme(DataTableThemes.Bootstrap4);
		DataTablePageConfigurator.configureButtons();
		//Weird way they did this one
		SkyconPageConfigurator.setColour("white");
		JQSourceCodePrettifyPageConfigurator.setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);
	}

	/**
	 * So that the page rendered doesn't include the entirety of every single library ;)
	 */
	private void blockUnusedPlugins()
	{

	}

	/**
	 * Wipe all caches? Haven't serialVersion'd to incremental yet so probably a good idea
	 */
	public void wipeCaches()
	{

		GuiceContext.get(WebComponentsService.class)
		            .wipeCaches();
		GuiceContext.get(PluginsService.class)
		            .wipeCaches();
	}
}
