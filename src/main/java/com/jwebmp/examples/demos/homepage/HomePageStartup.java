package com.jwebmp.examples.demos.homepage;

import com.jwebmp.SessionHelper;
import com.jwebmp.components.d3.radialreingoldtilfordtree.D3ReingoldTilfordTreePageConfigurator;
import com.jwebmp.examples.demos.homepage.db.HomePageDBFilter;
import com.jwebmp.guiceinjection.GuiceContext;
import com.jwebmp.logger.logging.LogColourFormatter;
import com.jwebmp.plugins.jqueryui.themes.JQUIThemes;
import com.jwebmp.plugins.jqueryui.themes.JQUIThemesPageConfigurator;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.encoding.EncodingHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.FilterInfo;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import java.util.logging.Level;

import static com.jwebmp.logger.LogFactory.*;

public class HomePageStartup
{
	public static void main(String[] args) throws ServletException
	{
		SessionHelper.setAddressToBeUsedWhenNull("https://jwebmp.com/");
		LogColourFormatter.setRenderBlack(false);

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
