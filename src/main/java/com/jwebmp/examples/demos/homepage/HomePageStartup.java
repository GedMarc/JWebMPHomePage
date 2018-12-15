package com.jwebmp.examples.demos.homepage;

import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.base.angular.modules.AngularMessagesModule;
import com.jwebmp.core.generics.WebReference;
import com.jwebmp.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
import com.jwebmp.guicedpersistence.readers.hibernateproperties.HibernateEntityManagerIProperties;
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

import java.util.logging.Level;

public class HomePageStartup
{
	public static void main(String[] args)
	{
		System.out.println("Starting");
		LogColourFormatter.setRenderBlack(false);
		LogFactory.configureConsoleColourOutput(Level.FINE);

		SessionHelper.setAddressToBeUsedWhenNull("https://jwebmp.com/");

		SessionHelper.setCacheAddress(false);

		JWebMPUndertowWebSocketConfiguration.setEnabled(true);

		WebReference.setUseVersionIdentifier(true);
		AngularMessagesModule.setMesssgesModuleEnabled(true);

		HibernateEntityManagerIProperties.getDefaultProperties()
		                                 .setShowSql(true);
		HibernateEntityManagerIProperties.getDefaultProperties()
		                                 .setFormatSql(true);


		HibernateEntityManagerIProperties.getDefaultProperties()
		                                 .setUseQueryStartupCheck(false);

		/*HibernateEntityManagerIProperties.getDefaultProperties()
		                                .enableQuickestBoot();*/

		BTMAutomatedTransactionHandler.setActive(true);
		BlueImpGalleryPageConfigurator.setIncludeIndicators(true);

		FontAwesome5PageConfigurator.setIncludeRegular(true);
		FontAwesome5PageConfigurator.setIncludeSolid(true);
		FontAwesome5PageConfigurator.setIncludeLight(true);

		FontAwesome5PageConfigurator.setRootReferenceDir("fontawesome-pro-5.4.1-web/js/");

		FontAwesome5PageConfigurator.setIncludeBrands(true);

		FontAwesome5PageConfigurator.getConfigOptions()
		                            .setSearchPseudoElements(true);

		DataTablePageConfigurator.switchTheme(DataTableThemes.Bootstrap4);
		DataTablePageConfigurator.configureButtons();

		SkyconPageConfigurator.setColour("white");

		JQSourceCodePrettifyPageConfigurator.setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);

		pluginBlocks();

		try
		{
			JWebMPUndertow.boot("0.0.0.0", 6002);
		}
		catch (Exception e)
		{
			LogFactory.getLog("Main")
			          .log(Level.SEVERE, "oops", e);
		}
	}

	private static void pluginBlocks()
	{

	}
}
