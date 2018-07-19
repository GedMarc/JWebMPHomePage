package com.jwebmp.examples.demos.homepage;

import com.jwebmp.SessionHelper;
import com.jwebmp.components.d3.radialreingoldtilfordtree.D3ReingoldTilfordTreePageConfigurator;
import com.jwebmp.generics.WebReference;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.logger.logging.LogColourFormatter;
import com.jwebmp.plugins.fontawesome5.config.FontAwesome5PageConfigurator;
import com.jwebmp.plugins.jqueryui.nestablethemes.JQUIThemes;
import com.jwebmp.plugins.jqueryui.nestablethemes.JQUIThemesPageConfigurator;
import io.undertow.Undertow;
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

		configureConsoleColourOutput(Level.FINE);

		DeploymentInfo servletBuilder = Servlets.deployment()
		                                        .setClassLoader(HomePageStartup.class.getClassLoader())
		                                        .setContextPath("/")
		                                        .setDeploymentName("jwebswinghomepage.war");

		DeploymentManager manager = Servlets.defaultContainer()
		                                    .addDeployment(servletBuilder);

		GuiceContext.inject();
		manager.deploy();

		HttpHandler jwebSwingHandler = manager.start();

		HttpHandler encodingHandler = new EncodingHandler.Builder().build(null)
		                                                           .wrap(jwebSwingHandler);

		Undertow server = Undertow.builder()
		                          .addHttpListener(6002, "0.0.0.0")
		                          .setHandler(jwebSwingHandler)
		                          .build();

		FontAwesome5PageConfigurator.setIncludeRegular(true);
		FontAwesome5PageConfigurator.setRootReferenceDir("fontawesome-pro-5.1.0-web/js/");

		D3ReingoldTilfordTreePageConfigurator.setIsRadial(true);
		JQUIThemesPageConfigurator.setTheme(JQUIThemes.Smoothness);

		server.start();
		System.out.println(server.getWorker()
		                         .isShutdown() + " shutdown");
	}
}
