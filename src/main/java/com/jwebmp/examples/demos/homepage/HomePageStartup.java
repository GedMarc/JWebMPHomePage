package com.jwebmp.examples.demos.homepage;

import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.generics.WebReference;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.guicedpersistence.db.services.HibernateEntityManagerProperties;
import com.jwebmp.guicedpersistence.jpa.implementations.JPAAutomatedTransactionHandler;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.logger.logging.LogColourFormatter;
import com.jwebmp.plugins.d3.radialreingoldtilfordtree.D3ReingoldTilfordTreePageConfigurator;
import com.jwebmp.plugins.fontawesome5.config.FontAwesome5PageConfigurator;
import com.jwebmp.plugins.jqueryui.nestablethemes.JQUIThemes;
import com.jwebmp.plugins.jqueryui.nestablethemes.JQUIThemesPageConfigurator;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.encoding.EncodingHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import javax.servlet.ServletException;
import java.util.logging.Level;

import static com.jwebmp.logger.LogFactory.*;

public class HomePageStartup
{
	public static void main(String[] args) throws ServletException
	{
		SessionHelper.setAddressToBeUsedWhenNull("https://jwebmp.com/");
		LogColourFormatter.setRenderBlack(false);
		WebReference.setUseVersionIdentifier(true);

		HibernateEntityManagerProperties.setShowSql(true);
		HibernateEntityManagerProperties.setFormatSql(true);

		JPAAutomatedTransactionHandler.setActive(true);

		configureConsoleColourOutput(Level.FINE);
		LogFactory.setLogToConsole(true);
		LogFactory.configureConsoleColourOutput(Level.FINE);

		FontAwesome5PageConfigurator.setIncludeRegular(true);
		FontAwesome5PageConfigurator.setRootReferenceDir("fontawesome-pro-5.1.0-web/js/");

		D3ReingoldTilfordTreePageConfigurator.setIsRadial(true);
		JQUIThemesPageConfigurator.setTheme(JQUIThemes.Smoothness);

		DeploymentInfo deploymentInfo = Servlets.deployment()
		                                        .setClassLoader(HomePageStartup.class.getClassLoader())
		                                        .setContextPath("/")
		                                        .setDeploymentName("HomePageStartup.war");

		DeploymentManager manager = Servlets.defaultContainer()
		                                    .addDeployment(deploymentInfo);

		GuiceContext.inject();
		manager.deploy();

		HttpHandler jwebSwingHandler = manager.start();
		HttpHandler encodingHandler = new EncodingHandler.Builder().build(null)
		                                                           .wrap(jwebSwingHandler);

		Undertow server = Undertow.builder()
		                          .setServerOption(UndertowOptions.ENABLE_HTTP2, true)
		                          .addHttpListener(6002, "localhost")
		                          .setHandler(encodingHandler)
		                          .build();
		server.start();
	}
}
