import com.jwebmp.examples.demos.homepage.DemoGuiceConfigurator;
import com.jwebmp.guicedinjection.interfaces.IGuiceConfigurator;

module com.jwebmp.examples.demos.homepage {
	requires javax.servlet.api;
	requires com.jwebmp.core;
	requires com.jwebmp.logmaster;
	requires java.logging;
	requires undertow.servlet;
	requires com.jwebmp.guicedinjection;
	requires undertow.core;
	requires com.jwebmp.plugins.fontawesome5;
	requires com.jwebmp.components.d3.reingoldtilfordtree;
	requires com.jwebmp.plugins.jqueryui.nestablethemes;
	requires com.jwebmp.plugins.atmosphere;
	requires atmosphere.runtime;
	requires com.google.guice;
	requires com.jwebmp.plugins.toastr;
	requires com.google.guice.extensions.servlet;
	requires java.validation;
	requires java.activation;
	requires com.jwebmp.plugins.bootstrap4;
	requires com.jwebmp.plugins.fontawesome;
	requires java.persistence;
	requires com.jwebmp.entityassist;
	requires java.mail;
	requires java.management;
	requires commons.lang3;
	requires com.fasterxml.jackson.annotation;
	requires com.jwebmp.plugins.google.sourceprettify;
	requires com.jwebmp.plugins.jqlayout;
	requires com.jwebmp.plugins.metrojs;
	requires cache.api;
	requires com.jwebmp.plugins.angularslimscroll;
	requires com.jwebmp.plugins.jstree;
	requires com.jwebmp.plugins.ionic.ionicons;
	requires com.jwebmp.plugins.materialicons;
	requires com.jwebmp.plugins.softhistorychange;
	requires com.jwebmp.plugins.jqueryui;
	requires com.jwebmp.plugins.materialdesignicons;
	requires com.jwebmp.plugins.glyphicons;
	requires com.jwebmp.plugins.moment;
	requires com.jwebmp.components.pace;
	requires com.jwebmp.plugins.particlejs;
	requires java.naming;
	requires com.google.guice.extensions.persist;
	requires btm;
	requires com.jwebmp.plugins.plusastab;
	requires com.fasterxml.jackson.core;
	requires com.jwebmp.guicedpersistence;
	requires java.sql;
	requires com.jwebmp.guicedpersistence.btm;
	requires com.fasterxml.jackson.databind;

	exports com.jwebmp.examples.demos.homepage;

	provides IGuiceConfigurator with DemoGuiceConfigurator;
}
