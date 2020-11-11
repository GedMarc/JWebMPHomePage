package com.jwebmp.examples.demos.homepage;

import com.guicedee.guicedservlets.undertow.GuicedUndertow;
import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.base.angular.modules.AngularMessagesModule;
import com.jwebmp.core.generics.WebReference;
import com.guicedee.guicedinjection.GuiceContext;
import com.guicedee.guicedpersistence.btm.implementation.BTMAutomatedTransactionHandler;
import com.guicedee.guicedpersistence.readers.hibernateproperties.HibernateEntityManagerProperties;
import com.guicedee.logger.LogFactory;
import com.guicedee.logger.logging.LogColourFormatter;
import com.jwebmp.plugins.angularanimate.AngularAnimatePageConfigurator;
import com.jwebmp.plugins.angularanimatedchange.AngularAnimatedChangePageConfigurator;
import com.jwebmp.plugins.angularautofocus.AngularAutoFocusPageConfigurator;
import com.jwebmp.plugins.angularfileupload.angular.AngularFileUploadPageConfigurator;
import com.jwebmp.plugins.angularionslider.AngularIonSliderPageConfigurator;
import com.jwebmp.plugins.angularprogressbuttonstyles.AngularProgressButtonStylesPageConfigurator;
import com.jwebmp.plugins.angularroute.AngularRoutePageConfigurator;
import com.jwebmp.plugins.angularsanitize.AngularSanitizePageConfigurator;
import com.jwebmp.plugins.angularscrollposition.AngularScrollPositionPageConfigurator;
import com.jwebmp.plugins.angulartouch.AngularTouchPageConfigurator;
import com.jwebmp.plugins.angulartrackwidth.AngularTrackWidthPageConfigurator;
import com.jwebmp.plugins.angularzoomanimation.AngularZoomInAnimationPageConfigurator;
import com.jwebmp.plugins.blueimp.fileupload.BlueImpFileUploadPageConfigurator;
import com.jwebmp.plugins.blueimp.gallery.BlueImpGalleryPageConfigurator;
import com.jwebmp.plugins.bs4.bootswatch.Bootswatch4PageConfigurator;
import com.jwebmp.plugins.bs4.datetimepicker.BS4DateTimePageConfigurator;
import com.jwebmp.plugins.bs4.dialog.BSDialogPageConfigurator;
import com.jwebmp.plugins.bs4.quickforms.BSQuickFormsPageConfigurator;
import com.jwebmp.plugins.bs4.tagsinput.BS4TagsInputPageConfigurator;
import com.jwebmp.plugins.bs4.toggle.BSSwitch4PageConfigurator;
import com.jwebmp.plugins.c3.C3PageConfigurator;
import com.jwebmp.plugins.d3.D3PageConfigurator;
import com.jwebmp.plugins.datatable.DataTablePageConfigurator;
import com.jwebmp.plugins.datatable.enumerations.DataTablePlugins;
import com.jwebmp.plugins.datatable.enumerations.DataTableThemes;
import com.jwebmp.plugins.fontawesome5.config.FontAwesome5PageConfigurator;
import com.jwebmp.plugins.fullcalendar.FullCalendarPageConfigurator;
import com.jwebmp.plugins.glyphicons.GlyphiconsPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettifyPageConfigurator;
import com.jwebmp.plugins.google.sourceprettify.SourceCodePrettifyThemes;
import com.jwebmp.plugins.ionic.ionicons.IonIconsPageConfigurator;
import com.jwebmp.plugins.ionrangeslider.IonRangeSliderPageConfigurator;
import com.jwebmp.plugins.jqgradientlinear.JQGradientPageConfigurator;
import com.jwebmp.plugins.jqplot.JQPlotPageConfigurator;
import com.jwebmp.plugins.jqui.datetimepicker.JQUIDateTimePickerPageConfigurator;
import com.jwebmp.plugins.jqui.themes.JQUIThemesPageConfigurator;
import com.jwebmp.plugins.jqui.themesnested.JQUINestableThemesPageConfigurator;
import com.jwebmp.plugins.jqui.verticaltimeline.JQUIVerticalTimelinePageConfigurator;
import com.jwebmp.plugins.jqxwidgets.JQXWidgetsPageConfigurator;
import com.jwebmp.plugins.leaflet.LeafletPageConfigurator;
import com.jwebmp.plugins.materialdesignicons.MaterialDesignIconsPageConfigurator;
import com.jwebmp.plugins.materialicons.MaterialIconsPageConfigurator;
import com.jwebmp.plugins.metrojs.JQMetroPageConfigurator;
import com.jwebmp.plugins.ngslimscroll.NgSlimScrollPageConfigurator;
import com.jwebmp.plugins.radialsvgslider.RadialSVGSliderGemPageConfigurator;
import com.jwebmp.plugins.sixbitplatform.SixBitPageConfigurator;
import com.jwebmp.plugins.skycons.configurator.SkyconPageConfigurator;
import com.jwebmp.plugins.spectrum.colourpicker.JQSpectrumPageConfigurator;
import com.jwebmp.plugins.textangular.TextAngularPageConfigurator;
import com.jwebmp.plugins.textinputeffects.TextInputEventsPageConfigurator;
import com.jwebmp.plugins.themify.icons.ThemifyIconsPageConfigurator;
import com.jwebmp.plugins.verticaltimeline.VerticalTimelinePageConfigurator;
import com.jwebmp.plugins.weathericons.WeatherIconsPageConfigurator;
import com.jwebmp.plugins.xeditable.XEditablePageConfigurator;

import jakarta.cache.CacheManager;
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
		SessionHelper.setAddressToBeUsedWhenNull("https://jwebmp.com/");
		//Allows multiple host names rendering.
		//When behind a proxy, set the header flag "jwsiteurl https://jwebmp.com/"
		SessionHelper.setCacheAddress(false);
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
			GuicedUndertow.boot("0.0.0.0", 6002);
		}
		catch (Exception e)
		{
			LogFactory.getLog("Main")
			          .log(Level.SEVERE, "oops", e);
		}
		finally
		{
			startup.wipeCaches();
		}

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
		DataTablePageConfigurator.getPlugins()
		                         .add(DataTablePlugins.Responsive);
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
		AngularZoomInAnimationPageConfigurator.setEnabled(false);
		SixBitPageConfigurator.setEnabled(false);
		NgSlimScrollPageConfigurator.setEnabled(false
		                                       );
		BlueImpFileUploadPageConfigurator.setEnabled(false);
		//BlueImpGalleryPageConfigurator.setEnabled(false);

		Bootswatch4PageConfigurator.setEnabled(false);
		JQUIThemesPageConfigurator.setEnabled(false);

		//Bootstrap 3 disable
		BSDialogPageConfigurator.setEnabled(false);

		BSQuickFormsPageConfigurator.setEnabled(false);

		BS4DateTimePageConfigurator.setEnabled(false);
		com.jwebmp.plugins.bs4.nyaselect.NyaSelectPageConfigurator.setEnabled(false);
		BS4TagsInputPageConfigurator.setEnabled(false);
		BSSwitch4PageConfigurator.setEnabled(false);


		C3PageConfigurator.setEnabled(false);
		D3PageConfigurator.setEnabled(false);
		FullCalendarPageConfigurator.setEnabled(false);
		GlyphiconsPageConfigurator.setEnabled(false);
		IonIconsPageConfigurator.setEnabled(false);
		IonRangeSliderPageConfigurator.setEnabled(false);

		JQGradientPageConfigurator.setEnabled(false);
		JQMetroPageConfigurator.setEnabled(false);
		JQPlotPageConfigurator.setEnabled(false);
		JQUIDateTimePickerPageConfigurator.setEnabled(false);
		VerticalTimelinePageConfigurator.setEnabled(false);
		JQUIVerticalTimelinePageConfigurator.setEnabled(false);
		JQXWidgetsPageConfigurator.setEnabled(false);
		LeafletPageConfigurator.setEnabled(false);
		MaterialDesignIconsPageConfigurator.setEnabled(false);
		MaterialIconsPageConfigurator.setEnabled(false);
		RadialSVGSliderGemPageConfigurator.setEnabled(false);
		JQSpectrumPageConfigurator.setEnabled(false);
		TextAngularPageConfigurator.setEnabled(false);
		TextInputEventsPageConfigurator.setEnabled(false);
		ThemifyIconsPageConfigurator.setEnabled(false);
		WeatherIconsPageConfigurator.setEnabled(false);
		XEditablePageConfigurator.setEnabled(false);

		JQUINestableThemesPageConfigurator.setEnabled(false);
	}

	/**
	 * Wipe all caches? Haven't serialVersion'd to incremental yet so probably a good idea
	 */
	private void wipeCaches()
	{
		LogFactory.getLog("Startup")
		          .info("Wiping Caches");
		CacheManager cacheManager = GuiceContext.get(CacheManager.class);
		for (String cacheName : cacheManager.getCacheNames())
		{
			cacheManager.getCache(cacheName)
			            .clear();
		}
	}
}
