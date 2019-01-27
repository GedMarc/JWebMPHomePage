package com.jwebmp.examples.demos.homepage;

import com.jwebmp.core.*;
import com.jwebmp.core.base.angular.modules.*;
import com.jwebmp.core.generics.*;
import com.jwebmp.examples.demos.homepage.components.*;
import com.jwebmp.examples.demos.homepage.db.dao.*;
import com.jwebmp.guicedinjection.*;
import com.jwebmp.guicedpersistence.btm.implementation.*;
import com.jwebmp.guicedpersistence.readers.hibernateproperties.*;
import com.jwebmp.logger.*;
import com.jwebmp.logger.logging.*;
import com.jwebmp.plugins.angularanimate.*;
import com.jwebmp.plugins.angularanimatedchange.*;
import com.jwebmp.plugins.angularautoexpand.*;
import com.jwebmp.plugins.angularautofocus.*;
import com.jwebmp.plugins.angularfileupload.angular.*;
import com.jwebmp.plugins.angularionslider.*;
import com.jwebmp.plugins.angularnyabootstrapselector.*;
import com.jwebmp.plugins.angularprogressbuttonstyles.*;
import com.jwebmp.plugins.angularroute.*;
import com.jwebmp.plugins.angularsanitize.*;
import com.jwebmp.plugins.angularscrollposition.*;
import com.jwebmp.plugins.angulartouch.*;
import com.jwebmp.plugins.angulartrackwidth.*;
import com.jwebmp.plugins.angularuibootstrap.*;
import com.jwebmp.plugins.angularuiselect.*;
import com.jwebmp.plugins.angularuisortable.*;
import com.jwebmp.plugins.angularzoomanimation.*;
import com.jwebmp.plugins.blueimp.gallery.*;
import com.jwebmp.plugins.bootstrap.*;
import com.jwebmp.plugins.bootstrap.dialog.*;
import com.jwebmp.plugins.bootstrap.themes.sbadmin2.*;
import com.jwebmp.plugins.bootstrapselect.*;
import com.jwebmp.plugins.bootstrapswitch.*;
import com.jwebmp.plugins.bootstraptagsinput.*;
import com.jwebmp.plugins.bs4datetimedropdown.*;
import com.jwebmp.plugins.bsquickforms4.*;
import com.jwebmp.plugins.c3.*;
import com.jwebmp.plugins.d3.*;
import com.jwebmp.plugins.d3.radialreingoldtilfordtree.*;
import com.jwebmp.plugins.datatable.*;
import com.jwebmp.plugins.datatable.enumerations.*;
import com.jwebmp.plugins.fontawesome.*;
import com.jwebmp.plugins.fontawesome5.config.*;
import com.jwebmp.plugins.fullcalendar.*;
import com.jwebmp.plugins.glyphicons.*;
import com.jwebmp.plugins.google.sourceprettify.*;
import com.jwebmp.plugins.imagemap.*;
import com.jwebmp.plugins.ionic.ionicons.*;
import com.jwebmp.plugins.ionrangeslider.*;
import com.jwebmp.plugins.jqgradientlinear.*;
import com.jwebmp.plugins.jqplot.*;
import com.jwebmp.plugins.jqueryuidatetimepicker.*;
import com.jwebmp.plugins.jqxwidgets.*;
import com.jwebmp.plugins.leaflet.*;
import com.jwebmp.plugins.materialdesignicons.*;
import com.jwebmp.plugins.materialicons.*;
import com.jwebmp.plugins.metrojs.*;
import com.jwebmp.plugins.radialsvgslider.*;
import com.jwebmp.plugins.sixbitplatform.*;
import com.jwebmp.plugins.skycons.configurator.*;
import com.jwebmp.plugins.smartwizard.*;
import com.jwebmp.plugins.spectrum.colourpicker.*;
import com.jwebmp.plugins.textangular.*;
import com.jwebmp.plugins.themify.icons.*;
import com.jwebmp.plugins.verticaltimeline.*;
import com.jwebmp.plugins.weathericons.*;
import com.jwebmp.plugins.weblogappender.*;
import com.jwebmp.plugins.xeditable.*;
import com.jwebmp.undertow.*;
import com.jwebmp.websockets.*;

import java.util.logging.*;

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

		//TODO disable if no pro version of font awesome - some icons may go missing
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
	 * So that the page rendered doesn't include the entirety of every single library, a.k.a. the entire internet ;)
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
		JQImageMapPageConfigurator.setEnabled(false);
		JQMetroPageConfigurator.setEnabled(false);
		JQPlotPageConfigurator.setEnabled(false);
		JQueryUIDateTimePickerPageConfigurator.setEnabled(false);
		VerticalTimelinePageConfigurator.setEnabled(false);
		com.jwebmp.plugins.jqueryverticaltimeline.VerticalTimelinePageConfigurator.setEnabled(false);
		JQXWidgetsPageConfigurator.setEnabled(false);
		LeafletPageConfigurator.setEnabled(false);
		MaterialDesignIconsPageConfigurator.setEnabled(false);
		MaterialIconsPageConfigurator.setEnabled(false);
		RadialSVGSliderGemPageConfigurator.setEnabled(false);
		SB2AdminPageConfigurator.setEnabled(false);
		SmartWizardPageConfigurator.setEnabled(false);
		JQSpectrumPageConfigurator.setEnabled(false);
		TextAngularPageConfigurator.setEnabled(false);
		ThemifyIconsPageConfigurator.setEnabled(false);
		WeatherIconsPageConfigurator.setEnabled(false);
		WebLogAppenderPageConfigurator.setEnabled(false);
		XEditablePageConfigurator.setEnabled(false);

		com.jwebmp.plugins.jqueryui.themesnested.JQUINestableThemesPageConfigurator.setEnabled(false);
	}

	/**
	 * Wipe all caches? Haven't serialVersion'd to incremental yet so probably a good idea
	 */
	private void wipeCaches()
	{
		GuiceContext.get(WebComponentsService.class)
		            .wipeCaches();
		GuiceContext.get(PluginsService.class)
		            .wipeCaches();
	}
}
