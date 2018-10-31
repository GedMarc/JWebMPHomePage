import com.jwebmp.core.services.IPage;
import com.jwebmp.examples.demos.homepage.DemoGuiceConfigurator;
import com.jwebmp.examples.demos.homepage.db.HomePageDBModule;
import com.jwebmp.examples.demos.homepage.display.DisplayPage;
import com.jwebmp.guicedinjection.interfaces.IGuiceConfigurator;
import com.jwebmp.guicedinjection.interfaces.IGuiceModule;
import com.jwebmp.guicedinjection.interfaces.IGuicePostStartup;

open module com.jwebmp.examples.demos.homepage {
	exports com.jwebmp.examples.demos.homepage;

	requires com.jwebmp.core;

	requires com.jwebmp.undertow;
	requires com.jwebmp.websockets;

	requires com.jwebmp.plugins.fontawesome5;
	//requires com.jwebmp.components.d3.reingoldtilfordtree;
	//requires com.jwebmp.plugins.jqueryui.nestablethemes;
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

	requires java.xml.bind;
	requires java.mail;

	requires com.jwebmp.guicedpersistence.btm;
	requires com.jwebmp.plugins.skycons;
	requires com.jwebmp.plugins.datatable;

	requires com.jwebmp.plugins.blueimp.gallery;
	requires com.jwebmp.guicedservlets.requestscoped;

	provides IGuiceConfigurator with DemoGuiceConfigurator;
	provides IPage with DisplayPage;
	provides IGuiceModule with HomePageDBModule;
	provides IGuicePostStartup with HomePageDBModule;
}
