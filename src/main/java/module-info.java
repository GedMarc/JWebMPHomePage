import com.guicedee.guicedinjection.interfaces.IGuiceConfigurator;
import com.jwebmp.examples.apps.homepage.HomePage;
import com.jwebmp.examples.apps.homepage.services.HomePageScannerConfiguration;

module jwebmphomepage {
	
	requires com.jwebmp.plugins.google.sourceprettify;
	requires com.jwebmp.plugins.softhistorychange;
	
	requires com.jwebmp.plugins.themes.mintontheme;
	
	requires com.jwebmp.plugins.bootstrap4;
	requires com.jwebmp.plugins.waveseffect;

	requires com.jwebmp.plugins.fontawesome5;
	requires com.jwebmp.plugins.materialicons;
	requires com.jwebmp.plugins.materialdesignicons;
	requires com.jwebmp.plugins.jstree;
	requires com.jwebmp.core.angular;
	requires com.jwebmp.plugins.toastr;
	
	requires com.jwebmp.plugins.jqueryui;
	requires com.guicedee.guicedservlets.undertow;
	requires com.jwebmp.plugins.datatable;
	requires com.jwebmp.plugins.dynamicsourcecode;
//	requires com.jwebmp.plugins.pace;
	//requires com.jwebmp.plugins.moment;
	
	requires com.jwebmp.plugins.plusastab;
	//requires com.jwebmp.plugins.particlejs;
	//requires com.jwebmp.plugins.modernizr;
	
	requires com.jwebmp.plugins.angularanimate;
	requires com.jwebmp.plugins.angularanimatedchange;
//	requires com.jwebmp.plugins.angularfastclick;
	requires com.jwebmp.plugins.angularfileupload;
	requires com.jwebmp.plugins.angularroute;
	requires com.jwebmp.plugins.angularsanitize;
	requires com.jwebmp.plugins.angularscrollposition;
	requires com.jwebmp.plugins.angularslimscroll;
	requires com.jwebmp.plugins.angulartouch;
	requires com.jwebmp.plugins.angulartrackwidth;
	requires com.jwebmp.plugins.angularzoomanimation;
	//requires com.jwebmp.plugins.ngslimscroll;
	
	
	requires com.jwebmp.plugins.security.localstorage;
	requires com.jwebmp.plugins.security.sessionstorage;
	
	requires com.jwebmp.plugins.smartwizard;

	requires static lombok;
	requires org.apache.commons.lang3;
	requires org.apache.commons.io;
	
	provides com.jwebmp.core.services.IPage with HomePage;
	provides IGuiceConfigurator with HomePageScannerConfiguration;

	opens com.jwebmp.examples.apps.homepage to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.apps.homepage.components to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.apps.homepage.components.browse to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.apps.homepage.components.browse.events to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.apps.homepage.services to com.google.guice, com.jwebmp.core;
	opens com.jwebmp.examples.apps.homepage.events to com.google.guice, com.jwebmp.core;
	
	opens com.jwebmp.examples.apps.homepage.pages to com.jwebmp.core, com.google.guice;
	opens com.jwebmp.examples.apps.homepage.pages.viewers to com.jwebmp.core, com.google.guice;
	opens com.jwebmp.examples.apps.homepage.pages.welcome to com.jwebmp.core, com.google.guice;
	opens com.jwebmp.examples.apps.homepage.pages.helloworld to com.jwebmp.core, com.google.guice;
	opens com.jwebmp.examples.apps.homepage.pages.fileupload to com.jwebmp.core, com.google.guice;

}