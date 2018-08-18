package com.jwebmp.examples.demos.homepage.display.demos.pluginslist;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.datatable.DataTable;

import java.util.List;

import static com.jwebmp.core.utilities.StaticStrings.*;

public class PluginsList
		extends DisplayScreen
{
	BSRow displayRow = new BSRow();

	public PluginsList()
	{
		super(HTML_TAB +
		      HTML_TAB +
		      "Internally Developed Modules" +
		      HTML_TAB +
		      "");
	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer<?> container = new BSContainer<>();
		container.setContainerType(BSContainerOptions.Container_Fluid);

		displayRow.resetHorizontalSinks();
		container.add(displayRow);

		displayRow.addClass("card-box");
		displayRow.removeClass("row");

		displayRow.add(buildColumn());

		return container;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Plugins List"));
		return crumbs;
	}

	private ComponentHierarchyBase buildColumn()
	{
		TableHeaderGroup<?> thg = new TableHeaderGroup();
		DataTable<?, ?> dt = new DataTable("dt", thg);
		dt.getOptions()
		  .getResponsive();
		dt.addStyle("display:block;");
		dt.addClass("table table-responsive w-100 d-block d-md-table");

		int rows = 100;
		int cols = 5;

		TableRow thr = new TableRow();
		List<Plugins> pluginsList = new Plugins().findAll();

		for (int i = 0; i < cols; i++)
		{
			thr.add(new TableHeaderCell<>("Test Col " + (i + 1)));
		}

		thg.add(thr);

		for (int i = 0; i < rows; i++)
		{
			dt.add(new TableRow<>().add(new TableCell<>("Data " + (i + 1)))
			                       .add(new TableCell<>("Data Cell " + (i + 1)))
			                       .add(new TableCell<>("Data Cell " + (i + 1)))
			                       .add(new TableCell<>("Data Cell " + (i + 1)))
			                       .add(new TableCell<>("Data Cell " + (i + 1))));
		}
		return dt;
	}
}
