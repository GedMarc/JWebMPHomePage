package com.jwebmp.examples.demos.homepage.display.injection;

import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScrollFeature;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayCard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;
import com.jwebmp.plugins.bootstrap4.tables.BSTableRow;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;
import com.jwebmp.plugins.smartwizard4.SmartWizardStepItem;

import javax.validation.constraints.NotNull;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

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

		Div aboutContent = new Div();
		Div whatAvailableContainer = new Div();
		Div pageContent = new Div();
		Div dataContent = new Div();
		Div pageContentRow = new BSRow();


		DefaultSlimScrollFeature scroll = new DefaultSlimScrollFeature(pageContentRow);
		scroll.getOptions()
		      .setHeight("500px");

		DefaultSmartWizard wizard = new DefaultSmartWizard("eventWizard");

		wizard.addStep(new SmartWizardStep(aboutContent, new SmartWizardStepItem("About", new SmallText("The Important Things"))));
		wizard.addStep(new SmartWizardStep(whatAvailableContainer, new SmartWizardStepItem("What's Available", new SmallText("Overview of the objects"))));
		wizard.addStep(new SmartWizardStep(pageContent, new SmartWizardStepItem("Defaults", new SmallText("Standard Events Available"))));
		wizard.addStep(new SmartWizardStep(dataContent, new SmartWizardStepItem("Data", new SmallText("Return data from client"))));

		container.add(buildClassPath());
		//		container.add(wizard);

		return container;
	}


	private Div buildClassPath()
	{
		DisplayCard card = new DisplayCard();
		Div div = card.addCardBody();
		div.add(new H3("Classpath Scanning with <a href=\"https://github.com/classgraph/classgraph\" target=\"_blank\">ClassGraph</a>"));
		div.add("Class Path scanning is completely optional, and allows you to manage and scan as necessary utilizing ClassGraph. " +
		        "<br/>These Services allow you to perform basic operations to filter and clean any items from the scan yielding a great performance increase." +
		        "<br/>You can also use <code>GuiceContext.setScanResult()</code> scan result directly.");

		BSTable<?> table = new BSTable<>().addTheme(BSTableOptions.Table_Dark)
		                                  .addClass(Table_Hover);
		table.setSmall(true);
		table.setBordered(true);
		table.setStriped(true);

		table.add(new TableHeaderGroup<>().add(new TableRow<>().add(new TableHeaderCell<>("Service Loader"))
		                                                       .add(new TableHeaderCell<>("Purpose"))
		                                      ));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IFileContentsScanner"))
		                                       .add(new TableCell<>(
				                                       "Registers a filename to be collected, such as persistence.xml or hazelcast-client.xml. Ensure that the path is located in a PathContentsScanner")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPackageContentsScanner"))
		                                       .add(new TableCell<>(
				                                       "Registers the given package to be included during the optional classpath scan. Only these packages will be included in retrieved results")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPathContentsScanner"))
		                                       .add(new TableCell<>("Registers the given path (No Class Files) to search for IFileContentsScanners.<br/> Usually META-INF")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IPathContentsBlacklistScanner"))
		                                       .add(new TableCell<>("Registers paths to be excluded from all scans. Performance enhancement.")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceScanJarExclusions"))
		                                       .add(new TableCell<>("Registers JAR files to be excluded from all scans. Performance enhancement")));

		table.add(new BSTableRow<>(Table_Hover).add(new TableCell<>("IGuiceScanModuleExclusions"))
		                                       .add(new TableCell<>("Registers Modules (JPMS) to be excluded from all scans. Performance enhancement")));

		div.add(table);

		div.add(new Image("images/guiceinjection/ClasspathScanningConfiguration.png"));

		return card;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));

		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Events"));
		return crumbs;
	}

}
