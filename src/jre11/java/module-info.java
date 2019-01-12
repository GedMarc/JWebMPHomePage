import com.jwebmp.core.services.IPage;
import com.jwebmp.examples.demos.homepage.DemoGuiceConfigurator;
import com.jwebmp.examples.demos.homepage.HomePageModuleScanner;
import com.jwebmp.examples.demos.homepage.db.HomePageDBModule;
import com.jwebmp.examples.demos.homepage.display.DisplayPage;
import com.jwebmp.guicedinjection.interfaces.*;

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
	requires com.jwebmp.entityassist;
	requires com.jwebmp.undertow;

	//JWebMP Addons
	requires com.jwebmp.guicedservlets.requestscoped;
	requires com.jwebmp.guicedpersistence.readers.hibernateproperties;
	requires com.jwebmp.guicedpersistence.btm;

	//JWebMP Lower Level
	requires com.jwebmp.guicedinjection;
	requires com.jwebmp.logmaster;
	requires com.jwebmp.core;
	requires com.jwebmp.guicedservlets;

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

	provides IGuiceConfigurator with DemoGuiceConfigurator;
	provides IPage with DisplayPage;
	provides IGuiceModule with HomePageDBModule;
	provides IGuicePostStartup with HomePageDBModule;
	provides IGuiceScanModuleInclusions with HomePageModuleScanner;
	provides IGuiceScanJarInclusions with HomePageModuleScanner;

	//Open for all the reflection, but only to specific packages
	/*opens com.jwebmp.examples.demos.homepage;
	opens com.jwebmp.examples.demos.homepage.entities to org.hibernate.orm.core, com.jwebmp.entityassist, com.fasterxml.jackson.databind, com.google.guice;
	opens com.jwebmp.examples.demos.homepage.entities.persistasync to org.hibernate.orm.core, com.jwebmp.entityassist, com.fasterxml.jackson.databind, com.google.guice;
	opens com.jwebmp.examples.demos.homepage.entities.builders to org.hibernate.orm.core, com.jwebmp.entityassist, com.fasterxml.jackson.databind, com.google.guice;
	opens com.jwebmp.examples.demos.homepage.entities.enumerations to org.hibernate.orm.core, com.jwebmp.entityassist, com.fasterxml.jackson.databind, com.google.guice;

	opens com.jwebmp.examples.demos.homepage.display.persistence to org.hibernate.orm.core, com.jwebmp.entityassist, com.fasterxml.jackson.databind, com.google.guice,com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.events to org.hibernate.orm.core, com.jwebmp.entityassist, com.fasterxml.jackson.databind, com.google.guice,com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.quickstart to org.hibernate.orm.core, com.jwebmp.entityassist, com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.db to com.jwebmp.guicedinjection, com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.about to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.about.persistencehandling to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.about.requestscoped to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.about.entityassist to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.about.c3p0module to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.about.ehcache to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.admin to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.confirmemail to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.contactus to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.forgotpassword to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.helloworld to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.login to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.menu to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.privacy to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.rightbar to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.subscribe to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.termsandconditions to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.home to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.components to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	*//*opens com.jwebmp.examples.demos.homepage.components.sourcecode to com.fasterxml.jackson.databind,com.google.guice, com.jwebmp.core;*//*
	opens com.jwebmp.examples.demos.homepage.components.events to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.components.general to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.pluginslist to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	exports com.jwebmp.examples.demos.homepage.entities to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	exports com.jwebmp.examples.demos.homepage.display.home.parts to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.angular.animate to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.animatedchange to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.autoexpand to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.autofocus to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.fileupload to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.ionslider to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.ngslimscroll to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.sanitize to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.scrollposition to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.slimscroll to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.touch to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.trackwidth to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.zoomin to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.ui.bootstrap to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.ui.select to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.angular.ui.sortable to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.forms.ionrangeslider to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.forms.prettycheckboxes to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.forms.progressbuttons to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.forms.quickforms to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.forms.smartwizard to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.forms.textangular to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.forms.textinputeffects to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.graphing.d3 to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.graphing.c3 to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.graphing.d3reingold to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.graphing.easypiechart to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.graphing.gradientlinear to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.graphing.imageheatmap to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.graphing.jqplot to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.graphing.particlesjs to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.htmltags to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.icons.fontawesome to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.icons.fontawesome5 to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.icons.glyphicons to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.icons.ionicons to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.icons.materialdesignicons to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.icons.mdi to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.jqui to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.jqui.demos to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.jqui.verticaltimeline to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.jqui.themes to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.jqui.themesnestable to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.jqui.datetimepicker to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.jqui.layout to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.jqui.spectrumcolourpicker to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.jqxwidgets to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.mapping.leafletjs to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.push to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.tables.xeditable to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.tables.datatables to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.trees.jstree to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4 to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.bootswatch to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.datetimepicker to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.dialog to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.nyaselect to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.quickforms to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.switcher to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;

	opens com.jwebmp.examples.demos.homepage.display.demos.display.globalize to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.dynamicsourcecodeviewer to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.easingeffects to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.fastclick to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.fullcalendar to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.metro to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.modernizr to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.moment to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.pace to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.plusastab to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.radialslidergem to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.softhistorychange to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.sourcecodeprettifier to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.toastr to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.verticaltimelinegem to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.demos.homepage.display.demos.display.weblogappender to com.fasterxml.jackson.databind, com.google.guice, com.jwebmp.core;


	opens com.jwebmp.examples.demos.homepage.components.general.events to com.google.guice;
	*/
}
