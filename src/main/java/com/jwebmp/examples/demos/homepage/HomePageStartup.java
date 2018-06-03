package com.jwebmp.examples.demos.homepage;

import com.jwebmp.components.d3.radialreingoldtilfordtree.D3ReingoldTilfordTreePageConfigurator;
import com.jwebmp.examples.demos.homepage.db.HomePageDBFilter;
import com.jwebmp.plugins.jqueryui.themes.JQUIThemes;
import com.jwebmp.plugins.jqueryui.themes.JQUIThemesPageConfigurator;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.encoding.EncodingHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.FilterInfo;
import za.co.mmagon.guiceinjection.GuiceContext;
import za.co.mmagon.logger.logging.LogSingleLineFormatter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static za.co.mmagon.logger.LogFactory.configureConsoleColourOutput;

public class HomePageStartup
{
	public static void main(String[] args) throws ServletException
	{
		//SessionHelper.setCacheAddress(true);

		FileHandler fh;

		try
		{
			// This block configure the logger with handler and formatter
			fh = new FileHandler("/var/log.log");
			Logger.getLogger("")
			      .addHandler(fh);

			fh.setFormatter(new LogSingleLineFormatter());
			Logger.getLogger("")
			      .info("File Log Registered");
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		configureConsoleColourOutput(Level.FINE);

		DeploymentInfo servletBuilder = Servlets.deployment()
		                                        .setClassLoader(HomePageStartup.class.getClassLoader())
		                                        .setContextPath("/")
		                                        .setDeploymentName("jwebswinghomepage.war");


		servletBuilder.addFilter(new FilterInfo("FilterUnitOfWorks", HomePageDBFilter.class).setAsyncSupported(true));
		servletBuilder.addFilterUrlMapping("FilterUnitOfWorks", "/*", DispatcherType.REQUEST);

		DeploymentManager manager = Servlets.defaultContainer()
		                                    .addDeployment(servletBuilder);

		GuiceContext.inject();
		manager.deploy();

		HttpHandler jwebSwingHandler = manager.start();
		HttpHandler encodingHandler = new EncodingHandler.Builder().build(null)
		                                                           .wrap(jwebSwingHandler);

		Undertow server = Undertow.builder()
		                          .addHttpListener(6002, "0.0.0.0")
		                          .setHandler(encodingHandler)
		                          .build();

		D3ReingoldTilfordTreePageConfigurator.setIsRadial(true);
		JQUIThemesPageConfigurator.setTheme(JQUIThemes.Smoothness);

		server.start();
	}
}
