package com.jwebmp.examples.demos.homepage;

import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.generics.WebReference;
import com.jwebmp.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
import com.jwebmp.guicedpersistence.db.services.HibernateEntityManagerProperties;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.logger.logging.LogColourFormatter;
import com.jwebmp.plugins.fontawesome5.config.FontAwesome5PageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettifyPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;
import com.jwebmp.undertow.JWebMPUndertow;

import javax.servlet.ServletException;
import java.util.logging.Level;

public class HomePageStartup
{
	public static void main(String[] args) throws ServletException
	{
		SessionHelper.setAddressToBeUsedWhenNull("https://jwebmp.com/");
		SessionHelper.setCacheAddress(false);
		LogColourFormatter.setRenderBlack(false);
		WebReference.setUseVersionIdentifier(true);

		HibernateEntityManagerProperties.setShowSql(true);
		HibernateEntityManagerProperties.setFormatSql(true);

		BTMAutomatedTransactionHandler.setActive(true);

		FontAwesome5PageConfigurator.setIncludeRegular(true);
		FontAwesome5PageConfigurator.setIncludeSolid(true);
		FontAwesome5PageConfigurator.setIncludeLight(true);
		FontAwesome5PageConfigurator.setRootReferenceDir("fontawesome-pro-5.1.0-web/js/");
		FontAwesome5PageConfigurator.setIncludeBrands(true);
		FontAwesome5PageConfigurator.getConfigOptions()
		                            .setSearchPseudoElements(true);

		JQSourceCodePrettifyPageConfigurator.setTheme(SourceCodePrettifyThemes.Sons_Of_Obsidian_Fixed_BG);

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
}
