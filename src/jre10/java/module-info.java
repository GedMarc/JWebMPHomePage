import com.jwebmp.examples.demos.homepage.DemoGuiceConfigurator;
import com.jwebmp.examples.demos.homepage.IPackageScanner;
import com.jwebmp.examples.demos.homepage.db.HomePageDBStartupPostStartup;
import com.jwebmp.guicedinjection.interfaces.IGuiceConfigurator;
import com.jwebmp.guicedinjection.interfaces.IPackageContentsScanner;
import com.jwebmp.guicedpersistence.services.IDBStartup;

module com.jwebmp.examples.demos.homepage {
	exports com.jwebmp.examples.demos.homepage;

	requires javax.servlet.api;
	requires java.logging;

	requires undertow.core;
	requires undertow.servlet;
	requires atmosphere.runtime;

	requires com.jwebmp.guicedinjection;
	requires com.jwebmp.logmaster;
	requires com.jwebmp.core;
	requires com.jwebmp.guicedpersistence;
	requires com.jwebmp.guicedpersistence.jpa;

	requires com.google.guice.extensions.servlet;
	requires com.google.guice;
	requires com.google.guice.extensions.persist;

	requires com.jwebmp.undertow;

	requires com.jwebmp.plugins.fontawesome5;
	requires com.jwebmp.components.d3.reingoldtilfordtree;
	requires com.jwebmp.plugins.jqueryui.nestablethemes;
	requires com.jwebmp.plugins.atmosphere;
	requires com.jwebmp.plugins.bootstrap4;
	requires com.jwebmp.plugins.fontawesome;
	requires com.jwebmp.plugins.google.sourceprettify;
	requires com.jwebmp.plugins.jqlayout;
	requires com.jwebmp.plugins.metrojs;

	requires com.jwebmp.plugins.angularslimscroll;
	requires com.jwebmp.plugins.jstree;
	requires com.jwebmp.plugins.ionic.ionicons;
	requires com.jwebmp.plugins.materialicons;
	requires com.jwebmp.plugins.softhistorychange;
	requires com.jwebmp.plugins.jqueryui;
	requires com.jwebmp.plugins.materialdesignicons;
	requires com.jwebmp.plugins.glyphicons;
	requires com.jwebmp.plugins.moment;
	requires com.jwebmp.plugins.pace;
	requires com.jwebmp.plugins.particlejs;
	requires com.jwebmp.plugins.toastr;
	requires com.jwebmp.plugins.plusastab;

	requires com.jwebmp.entityassist;

	requires java.validation;
	requires java.activation;
	requires java.xml.bind;
	requires java.persistence;
	requires java.mail;

	requires commons.lang3;

	requires com.fasterxml.jackson.annotation;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;

	requires java.transaction;

	requires org.hibernate.orm.core;
	requires org.hibernate.orm.jcache;
	requires org.hibernate.validator;

	requires cache.api;

	provides IGuiceConfigurator with DemoGuiceConfigurator;
	provides IPackageContentsScanner with IPackageScanner;
	provides IDBStartup with HomePageDBStartupPostStartup;

	opens com.jwebmp.examples.demos.homepage;
	opens com.jwebmp.examples.demos.homepage.entities to org.hibernate.orm.core;
	exports com.jwebmp.examples.demos.homepage.db to com.jwebmp.guicedinjection, com.google.guice;
}
