package com.jwebmp.examples.apps.homepage;

import com.guicedee.guicedservlets.undertow.GuicedUndertow;

import com.guicedee.logger.logging.LogColourFormatter;
import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.base.angular.modules.AngularMessagesModule;
import com.jwebmp.core.generics.WebReference;
import com.jwebmp.plugins.bootstrap4.BootstrapReferencePool;
import com.jwebmp.plugins.datatable.DataTablePageConfigurator;
import com.jwebmp.plugins.datatable.enumerations.DataTablePlugins;
import com.jwebmp.plugins.datatable.enumerations.DataTableThemes;
import com.jwebmp.plugins.fontawesome5.config.FontAwesome5PageConfigurator;
import com.jwebmp.plugins.fontawesome5.config.FontAwesomeReferenceType;

import java.util.logging.Level;

public class BootHomePage
{
	static String[] userAvatars = new String[]{
			"default-user-profile-photo-2.jpg",
			"default-user-profile-photo-3.jpg",
			"default-user-profile-photo-4.jpg",
			"images/users/avatar-1.jpg",
			"images/users/avatar-2.jpg",
			"images/users/avatar-3.jpg",
			"images/users/avatar-4.jpg",
			"images/users/avatar-5.jpg",
			"images/users/avatar-6.jpg",
			"images/users/avatar-7.jpg",
			"images/users/avatar-8.jpg",
			"images/users/avatar-9.jpg",
			"images/users/avatar-10.jpg"
	};
	
	static
	{
		SessionHelper.setCacheAddress(false);
		WebReference.setUseVersionIdentifier(true);

		
		AngularMessagesModule.setMesssgesModuleEnabled(true);
		
		FontAwesome5PageConfigurator.setIncludeAll(true);
		FontAwesome5PageConfigurator.setFontAwesomeReferenceType(FontAwesomeReferenceType.WebFontCSS);
		FontAwesome5PageConfigurator.setRootCssReferenceDir("/font-awesome/css/");
		
		//Bootstrap 4 as the theme core for most components
		DataTablePageConfigurator.switchTheme(DataTableThemes.Bootstrap4);
		DataTablePageConfigurator.configureButtons();
		DataTablePageConfigurator.getPlugins()
		                         .add(DataTablePlugins.Responsive);
		
		BootstrapReferencePool.Bootstrap4CoreReference.getCssReference()
		                                              .setLeft("css/bootstrap_minton.min.css")
		                                              .setLinkId("bs-default-stylesheet");
	}
	
	public static void main(String[] args)
	{
		LogColourFormatter.setRenderBlack(false);
		LogFactory.configureDefaultLogHiding();
		LogFactory.configureConsoleColourOutput(Level.FINE);
		
		try
		{
			GuicedUndertow.boot("0.0.0.0", 6002);
		}
		catch (Exception e)
		{
			LogFactory.getLog("Main")
			          .log(Level.SEVERE, "oops", e);
		}
	}
}
