import com.jwebmp.core.services.IPage;
import com.jwebmp.examples.demos.homepage.DemoGuiceConfigurator;
import com.jwebmp.examples.demos.homepage.HomePageModuleScanner;
import com.jwebmp.examples.demos.homepage.db.HomePageDBModule;
import com.jwebmp.examples.demos.homepage.display.DisplayPage;
import com.guicedee.guicedinjection.interfaces.IGuiceConfigurator;
import com.guicedee.guicedinjection.interfaces.IGuiceModule;

//This is a closed module - it requires an opens clause
//--add-opens=java.base/java.lang=javassist,com.google.guice
open module com.jwebmp.examples.demos.homepage {
	exports com.jwebmp.examples.demos.homepage;

	//This library is used for class scanning (https://github.com/classgraph/classgraph)
	requires io.github.classgraph;
	requires java.mail;

	//These still require porting to jpms, otherwise they wouldn't need to be specified here
	requires org.apache.commons.lang3;
	requires org.apache.commons.text;
	requires com.google.common;

	requires undertow.core;
	requires undertow.servlet;
	requires cache.api;
	requires xnio.api;

	//Dev requirement (guice build)
	requires org.objectweb.asm;

	//JWebMP
	requires com.entityassist;
	requires com.jwebmp.undertow;

	//JWebMP Addons
	requires com.guicedee.guicedservlets.requestscoped;
	requires com.guicedee.guicedpersistence.readers.hibernateproperties;
	requires com.guicedee.guicedpersistence.btm;

	//JWebMP Lower Level
	requires com.guicedee.guicedinjection;
	requires com.guicedee.logmaster;
	requires com.jwebmp.core;
	requires com.guicedee.guicedservlets;

	//Guice
	requires com.google.guice;
	requires com.google.guice.extensions.persist;

	requires com.jwebmp.plugins.fontawesome5;
	requires com.jwebmp.plugins.bootstrap4;
	requires com.jwebmp.plugins.google.sourceprettify;
	requires com.jwebmp.plugins.jqlayout;
	requires com.jwebmp.plugins.metrojs;
	requires com.jwebmp.plugins.skycons;
	requires com.jwebmp.plugins.datatable;
	requires com.jwebmp.plugins.angularslimscroll;
	requires com.jwebmp.plugins.jstree;
	requires com.jwebmp.plugins.softhistorychange;
	requires com.jwebmp.plugins.jqueryui;
	requires com.jwebmp.plugins.moment;
	requires com.jwebmp.plugins.pace;
	requires com.jwebmp.plugins.particlejs;
	requires com.jwebmp.plugins.toastr;
	requires com.jwebmp.plugins.plusastab;
	requires com.jwebmp.plugins.blueimp.gallery;
	requires com.jwebmp.plugins.smartwizard4;

	//In storing visitors, we save the header information as JSON
	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires org.json;

	requires org.hibernate.orm.core;
	requires org.hibernate.orm.jcache;
	requires org.hibernate.validator;
	requires java.validation;
	requires javax.servlet.api;
	//for my dev
	requires static cglib;
	requires com.jwebmp.core.angularjs;
	requires java.persistence;
	requires com.google.guice.extensions.servlet;
	requires com.jwebmp.plugins.angularanimate;
	requires com.jwebmp.plugins.angularanimatedchange;
	requires com.jwebmp.plugins.angularautofocus;
	requires com.jwebmp.plugins.angularionslider;
	requires com.jwebmp.plugins.angularfileupload;
	requires com.jwebmp.plugins.angularprogressbuttonstyles;
	requires com.jwebmp.plugins.angularroute;
	requires com.jwebmp.plugins.angularsanitize;
	requires com.jwebmp.plugins.angularscrollposition;
	requires com.jwebmp.plugins.angulartouch;
	requires com.jwebmp.plugins.angulartrackwidth;
	requires com.jwebmp.plugins.angularzoomanimation;
	requires com.jwebmp.plugins.bs4.dialog;
	requires com.jwebmp.plugins.bs4.quickforms;
	requires com.jwebmp.plugins.c3;
	requires com.jwebmp.plugins.d3;
	requires com.jwebmp.plugins.fullcalendar;
	requires com.jwebmp.plugins.glyphicons;
	requires com.jwebmp.plugins.ionic.ionicons;
	requires com.jwebmp.plugins.ionrangeslider;
	requires com.jwebmp.plugins.jqgradientlinear;
	requires com.jwebmp.plugins.jqplot;
	requires com.jwebmp.plugins.jqui.datetimepicker;
	requires com.jwebmp.plugins.jqxwidgets;
	requires com.jwebmp.plugins.leaflet;
	requires com.jwebmp.plugins.materialdesignicons;
	requires com.jwebmp.plugins.materialicons;
	requires com.jwebmp.plugins.radialsvgslider;
	requires com.jwebmp.plugins.bit6;
	requires com.jwebmp.plugins.spectrum.colourpicker;
	requires com.jwebmp.plugins.textangular;
	requires com.jwebmp.plugins.themify.icons;
	requires com.jwebmp.plugins.verticaltimeline;
	requires com.jwebmp.plugins.weathericons;
	requires com.jwebmp.plugins.xeditable;
	requires com.jwebmp.plugins.jqueryverticaltimeline;
	requires com.jwebmp.plugins.jqueryui.themes.nestable;
	requires com.jwebmp.plugins.ngslimscroll;
	requires com.jwebmp.plugins.bs4.bootswatch;
	requires com.jwebmp.plugins.bs4.datetimepicker;
	requires com.jwebmp.plugins.bs4.nyaselect;
	requires com.jwebmp.plugins.bs4.toggle;
	requires com.jwebmp.plugins.dynamicsourcecode;
	requires com.jwebmp.plugins.easingeffects;
	requires com.jwebmp.plugins.angularfastclick;
	requires com.jwebmp.plugins.globalize.cultures;
	requires com.jwebmp.plugins.modernizr;
	requires com.jwebmp.plugins.angularprettycheckboxes;
	requires com.jwebmp.plugins.textinputeffects;
	requires com.jwebmp.plugins.easypiechart;
	requires com.jwebmp.plugins.jqueryui.themes;
	requires com.jwebmp.plugins.blueimp.fileupload;
	requires com.jwebmp.plugins.bs4.tagsinput;
	requires jakarta.activation;

	provides IGuiceConfigurator with DemoGuiceConfigurator;
	provides IPage with DisplayPage;
	provides IGuiceModule with HomePageDBModule;
}
