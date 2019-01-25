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
import com.jwebmp.plugins.angularanimate.AngularAnimatePageConfigurator;
import com.jwebmp.plugins.angularanimatedchange.AngularAnimatedChangePageConfigurator;
import com.jwebmp.plugins.angularautoexpand.AngularAutoExpandPageConfigurator;
import com.jwebmp.plugins.angularautofocus.AngularAutoFocusPageConfigurator;
import com.jwebmp.plugins.angularfileupload.angular.AngularFileUploadPageConfigurator;
import com.jwebmp.plugins.angularionslider.AngularIonSliderPageConfigurator;
import com.jwebmp.plugins.angularnyabootstrapselector.NyaSelectPageConfigurator;
import com.jwebmp.plugins.angularprogressbuttonstyles.AngularProgressButtonStylesPageConfigurator;
import com.jwebmp.plugins.angularroute.AngularRoutePageConfigurator;
import com.jwebmp.plugins.angularsanitize.AngularSanitizePageConfigurator;
import com.jwebmp.plugins.angularscrollposition.AngularScrollPositionPageConfigurator;
import com.jwebmp.plugins.angulartouch.AngularTouchPageConfigurator;
import com.jwebmp.plugins.angulartrackwidth.AngularTrackWidthPageConfigurator;
import com.jwebmp.plugins.angularuibootstrap.AngularUIBootstrapPageConfigurator;
import com.jwebmp.plugins.angularuiselect.AngularUISelectPageConfigurator;
import com.jwebmp.plugins.angularuisortable.AngularUISortablePageConfigurator;
import com.jwebmp.plugins.angularzoomanimation.AngularZoomInAnimationPageConfigurator;
import com.jwebmp.plugins.blueimp.gallery.BlueImpGalleryPageConfigurator;
import com.jwebmp.plugins.bootstrap.BootstrapPageConfigurator;
import com.jwebmp.plugins.bootstrap.dialog.BSDialogPageConfigurator;
import com.jwebmp.plugins.bootstrapselect.BootstrapSelectPageConfigurator;
import com.jwebmp.plugins.bootstrapswitch.BootstrapSwitchPageConfigurator;
import com.jwebmp.plugins.bootstraptagsinput.BootstrapTagsInputPageConfigurator;
import com.jwebmp.plugins.bs4datetimedropdown.BSDateTimePageConfigurator;
import com.jwebmp.plugins.bsquickforms4.BSQuickFormsPageConfigurator;
import com.jwebmp.plugins.c3.C3PageConfigurator;
import com.jwebmp.plugins.d3.D3PageConfigurator;
import com.jwebmp.plugins.d3.radialreingoldtilfordtree.D3ReingoldTilfordTreePageConfigurator;
import com.jwebmp.plugins.datatable.DataTablePageConfigurator;
import com.jwebmp.plugins.datatable.enumerations.DataTableThemes;
import com.jwebmp.plugins.fontawesome.FontAwesomePageConfigurator;
import com.jwebmp.plugins.fontawesome5.config.FontAwesome5PageConfigurator;
import com.jwebmp.plugins.fullcalendar.FullCalendarPageConfigurator;
import com.jwebmp.plugins.glyphicons.GlyphiconsPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettifyPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;
import com.jwebmp.plugins.ionic.ionicons.IonIconsPageConfigurator;
import com.jwebmp.plugins.ionrangeslider.IonRangeSliderPageConfigurator;
import com.jwebmp.plugins.jqgradientlinear.JQGradientPageConfigurator;
import com.jwebmp.plugins.sixbitplatform.SixBitPageConfigurator;
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
	 * So that the page rendered doesn't include the entirety of every single library, a.k.a. the internet ;)
	 */
	private void blockUnusedPlugins()
	{
		AngularAnimatePageConfigurator.setEnabled(false);
		AngularAnimatedChangePageConfigurator.setEnabled(false);
		AngularAutoExpandPageConfigurator.setEnabled(false);
		AngularAutoFocusPageConfigurator.setEnabled(false);
		AngularIonSliderPageConfigurator.setEnabled(false);
		AngularFileUploadPageConfigurator.setEnabled(false);
		AngularProgressButtonStylesPageConfigurator.setEnabled(false);
		AngularRoutePageConfigurator.setEnabled(false);
		AngularSanitizePageConfigurator.setEnabled(false);
		AngularScrollPositionPageConfigurator.setEnabled(false);
		//AngularSlimScrollPageConfigurator.setEnabled(false);
		AngularTouchPageConfigurator.setEnabled(false);
		AngularTrackWidthPageConfigurator.setEnabled(false);
		AngularUIBootstrapPageConfigurator.setEnabled(false);
		AngularUISelectPageConfigurator.setEnabled(false);
		AngularUISortablePageConfigurator.setEnabled(false);
		AngularZoomInAnimationPageConfigurator.setEnabled(false);
		SixBitPageConfigurator.setEnabled(false);
		//BlueImpFileUploadPageConfigurator.setEnabled(false);
		//BlueImpGalleryPageConfigurator.setEnabled(false);

		//Bootstrap 3 disable
		BSDialogPageConfigurator.setEnabled(false);
		BootstrapPageConfigurator.setEnabled(false);
		BootstrapSelectPageConfigurator.setEnabled(false);
		BootstrapSwitchPageConfigurator.setEnabled(false);
		BootstrapTagsInputPageConfigurator.setEnabled(false);
		BSDateTimePageConfigurator.setEnabled(false);
		NyaSelectPageConfigurator.setEnabled(false);
		BSQuickFormsPageConfigurator.setEnabled(false);


		C3PageConfigurator.setEnabled(false);
		D3PageConfigurator.setEnabled(false);
		D3ReingoldTilfordTreePageConfigurator.setEnabled(false);
		FontAwesomePageConfigurator.setEnabled(false);
		FullCalendarPageConfigurator.setEnabled(false);
		GlyphiconsPageConfigurator.setEnabled(false);
		IonIconsPageConfigurator.setEnabled(false);
		IonRangeSliderPageConfigurator.setEnabled(false);

		JQGradientPageConfigurator.setEnabled(false);


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
