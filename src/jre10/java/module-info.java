import com.jwebmp.core.services.IPage;
import com.jwebmp.examples.demos.homepage.DemoGuiceConfigurator;
import com.jwebmp.examples.demos.homepage.db.HomePageDBModule;
import com.jwebmp.examples.demos.homepage.db.HomePageDBStartup;
import com.jwebmp.examples.demos.homepage.display.DisplayPage;
import com.jwebmp.guicedinjection.interfaces.IGuiceConfigurator;
import com.jwebmp.guicedinjection.interfaces.IGuiceModule;
import com.jwebmp.guicedpersistence.services.IDBStartup;

module com.jwebmp.examples.demos.homepage {
	exports com.jwebmp.examples.demos.homepage;


	requires javax.servlet.api;
	requires java.logging;

	requires undertow.core;
	requires undertow.servlet;

	requires com.jwebmp.guicedinjection;
	requires com.jwebmp.logmaster;
	requires com.jwebmp.core;
	requires com.jwebmp.guicedpersistence;
	requires com.jwebmp.guicedpersistence.jpa;

	requires com.google.guice.extensions.servlet;
	requires com.google.guice;
	requires com.google.guice.extensions.persist;

	requires com.jwebmp.undertow;
	requires com.jwebmp.websockets;

	requires com.jwebmp.plugins.fontawesome5;
	requires com.jwebmp.components.d3.reingoldtilfordtree;
	requires com.jwebmp.plugins.jqueryui.nestablethemes;
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
	requires undertow.websockets.jsr;

	provides IGuiceConfigurator with DemoGuiceConfigurator;
	provides IDBStartup with HomePageDBStartup;
	provides IPage with DisplayPage;
	provides IGuiceModule with HomePageDBModule;

	opens com.jwebmp.examples.demos.homepage;
	opens com.jwebmp.examples.demos.homepage.entities to org.hibernate.orm.core;
	exports com.jwebmp.examples.demos.homepage.entities.builders to com.jwebmp.entityassist;

	opens com.jwebmp.examples.demos.homepage.db to com.jwebmp.guicedinjection, com.google.guice;
	opens com.jwebmp.examples.demos.homepage.display to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.about to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.admin to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.confirmemail to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.contactus to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.forgotpassword to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.helloworld to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.login to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.menu to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.privacy to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.rightbar to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.subscribe to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.termsandconditions to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.home to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.components to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.components.sourcecode to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.components.events to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.components.general to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.entities.builders to com.google.guice, com.jwebmp.core;

}
