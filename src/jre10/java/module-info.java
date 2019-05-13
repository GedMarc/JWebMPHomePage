import com.jwebmp.core.services.IPage;
import com.jwebmp.examples.demos.homepage.DemoGuiceConfigurator;
import com.jwebmp.examples.demos.homepage.HomePageModuleScanner;
import com.jwebmp.examples.demos.homepage.db.HomePageDBModule;
import com.jwebmp.examples.demos.homepage.display.DisplayPage;
import com.jwebmp.guicedinjection.interfaces.*;

open module com.jwebmp.examples.demos.homepage {
	exports com.jwebmp.examples.demos.homepage;

	requires javax.servlet.api;
	requires java.logging;

	requires undertow.core;
	requires undertow.servlet;

	requires com.jwebmp.guicedinjection;
	requires com.jwebmp.logmaster;
	requires com.jwebmp.core;
	requires com.jwebmp.guicedpersistence;

	requires com.google.guice.extensions.servlet;
	requires com.google.guice;
	requires com.google.guice.extensions.persist;

	requires com.jwebmp.undertow;
	requires com.jwebmp.websockets;

	requires com.jwebmp.plugins.fontawesome5;
	//requires com.jwebmp.components.d3.reingoldtilfordtree;
	//requires com.jwebmp.plugins.jqueryui.themes;
	requires com.jwebmp.plugins.bootstrap4;
	//requires com.jwebmp.plugins.fontawesome;
	requires com.jwebmp.plugins.google.sourceprettify;
	requires com.jwebmp.plugins.jqlayout;
	requires com.jwebmp.plugins.metrojs;

	requires com.jwebmp.plugins.angularslimscroll;
	requires com.jwebmp.plugins.jstree;
	//requires com.jwebmp.plugins.ionic.ionicons;
	//requires com.jwebmp.plugins.materialicons;
	requires com.jwebmp.plugins.softhistorychange;
	requires com.jwebmp.plugins.jqueryui;
	//requires com.jwebmp.plugins.materialdesignicons;
	//requires com.jwebmp.plugins.glyphicons;
	requires com.jwebmp.plugins.moment;
	requires com.jwebmp.plugins.pace;
	requires com.jwebmp.plugins.particlejs;
	requires com.jwebmp.plugins.toastr;
	requires com.jwebmp.plugins.plusastab;

	requires com.jwebmp.entityassist;

	requires java.validation;
	requires jakarta.activation;
	requires java.xml.bind;
	requires java.persistence;
	requires java.mail;

	requires jdk.unsupported;

	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;

	requires java.transaction;

	requires org.hibernate.orm.core;
	requires org.hibernate.orm.jcache;
	requires org.hibernate.validator;

	requires cache.api;
	requires undertow.websockets.jsr;

	requires xnio.api;

	requires com.jwebmp.guicedpersistence.btm;
	requires org.json;
	requires com.jwebmp.plugins.skycons;
	requires com.jwebmp.plugins.datatable;
	requires com.jwebmp.guicedservlets;
	requires com.jwebmp.plugins.blueimp.gallery;
	requires org.apache.commons.lang3;
	requires org.apache.commons.text;
	requires com.jwebmp.guicedservlets.requestscoped;
	requires com.google.common;
	requires io.github.classgraph;

	requires com.jwebmp.guicedpersistence.readers.hibernateproperties;
	requires com.jwebmp.plugins.smartwizard4;

	provides IGuiceConfigurator with DemoGuiceConfigurator;
	provides IPage with DisplayPage;
	provides IGuiceModule with HomePageDBModule;
	provides IGuicePostStartup with HomePageDBModule;
	provides IGuiceScanModuleInclusions with HomePageModuleScanner;
	provides IGuiceScanJarInclusions with HomePageModuleScanner;

}
