package com.jwebmp.examples.demos.homepage.display.injection;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.components.DefaultTable;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

@SuppressWarnings("Duplicates")
public class InjectionsClassGraphScreen
		extends DisplayScreen
{
	public InjectionsClassGraphScreen()
	{

	}

	@Override
	public @NotNull BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(Container_Fluid);
		//container.addClass("row");

		//container.add(new H3<>("Injection Control<br/>"));
		container.add(buildSmartWizard());

		return container;
	}

	private Div buildSmartWizard()
	{
		Div container = new Div();


		Div aboutContent = buildClassPath();
		Div whatAvailableContainer = buildOverview();
		Div pageContent = buildSPI();
		Div pageContentRow = new BSRow();

		DefaultSlimScrollFeature scroll = new DefaultSlimScrollFeature(pageContentRow);
		scroll.getOptions()
		      .setHeight("500px");

		DefaultSmartWizard wizard = new DefaultSmartWizard("classgraphWizard");

		wizard.addStep(new SmartWizardStep(aboutContent, new SmartWizardStepItem("About", new SmallText("This thing is amazing"))));
		wizard.addStep(new SmartWizardStep(pageContent, new SmartWizardStepItem("SPI", new SmallText("Service based configuration"))));
		wizard.addStep(new SmartWizardStep(whatAvailableContainer, new SmartWizardStepItem("Overview", new SmallText("Overview of the configuration"))));

		container.add(wizard);
		container.add(buildGoToSource(getClass()));
		return container;
	}

	private Div buildClassPath()
	{
		Div div = new Div();
		div.add(new H3("Classpath Scanning with <a href=\"https://github.com/classgraph/classgraph\" target=\"_blank\">ClassGraph</a>"));
		div.add("" +
		        "<br/>Class Path scanning is completely optional, and allows you to manage and scan as necessary utilizing ClassGraph." +
		        "<br/>These Services allow you to perform basic operations to filter and clean any items from the scan yielding a great performance increase." +
		        "" +
		        "<br/><br/>You can also use <code>GuiceContext.setScanResult()</code> scan result directly.");
		return div;
	}

	private Div buildOverview()
	{
		Div div = new Div();
		div.add(new Image<>("images/guiceinjection/ClasspathScanningConfiguration.png").addClass("d-block img-fluid"));
		return div;
	}

	private Div buildSPI()
	{
		Div card = new Div();
		DefaultTable<?> table = new DefaultTable<>();
		table.addHeader("Service Loader", "Purpose");
		table.addRow("IFileContentsScanner",
		             "Registers a filename to be collected, such as persistence.xml or hazelcast-client.xml. Ensure that the path is located in a PathContentsScanner");
		table.addRow("IPackageContentsScanner",
		             "Registers the given package to be included during the optional classpath scan. Only these packages will be included in retrieved results");
		table.addRow("IPathContentsScanner", "Registers the given path (No Class Files) to search for IFileContentsScanners.<br/> Usually META-INF");
		table.addRow("IGuiceScanJarExclusions", "Registers JAR files to be excluded from all scans. Performance enhancement");
		table.addRow("IGuiceScanModuleExclusions", "Registers Modules (JPMS) to be excluded from all scans. Performance enhancement");

		card.add(table);
		return card;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();

		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));

		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Injection"));

		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(true)
		                                             .setText("ClassGraph"));
		return crumbs;
	}

}
