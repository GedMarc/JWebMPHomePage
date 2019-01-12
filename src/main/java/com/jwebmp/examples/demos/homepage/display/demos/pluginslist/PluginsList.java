package com.jwebmp.examples.demos.homepage.display.demos.pluginslist;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.html.*;
import com.jwebmp.core.htmlbuilder.css.measurement.MeasurementCSSImpl;
import com.jwebmp.core.htmlbuilder.css.measurement.MeasurementTypes;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.datatable.DataTable;
import com.jwebmp.plugins.datatable.options.DataTablesDomOptions;

import javax.cache.annotation.CacheResult;
import java.util.List;

import static com.jwebmp.core.utilities.StaticStrings.*;
import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.datatable.options.DataTablesDomOptions.*;

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

		BSColumn col = new BSColumn(Col_12);
		col.addClass("table-responsive");
		col.add(buildColumn());
		displayRow.add(col);

		//	System.out.println(displayRow.toString());

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
		TableRow thr = new TableRow();
		thr.add(new TableHeaderCell<>("Icon"));
		thr.add(new TableHeaderCell<>("Name"));
		thr.add(new TableHeaderCell<>("Version"));
		thr.add(new TableHeaderCell<>("Description"));
		//thr.add(new TableHeaderCell<>("Components"));
		thr.add(new TableHeaderCell<>("Donate"));
		thr.add(new TableHeaderCell<>("Links"));

		thg.add(thr);

		DataTable<?, ?> dt = new DataTable("dt", thg);

		dt.getOptions()
		  .getColReorder()
		  .setRealtime(true)
		  .setFixedColumnsLeft(3);

		dt.getOptions()
		  .getFixedColumns()
		  .setLeftColumns(3);


		if (getPage().isMobileOrSmartTablet())
		{
			dt.getOptions()
			  .setScrollY(new MeasurementCSSImpl(300, MeasurementTypes.Pixels));
			dt.getOptions()
			  .setScrollCollapse(true);
			dt.getOptions()
			  .setScrollX(true);

			dt.getOptions()
			  .getFixedHeader()
			  .setFooter(true)
			  .setHeader(true);

		}

		dt.addCopyButton("btn " + BSButtonOptions.Btn_Outline_Primary.toString())
		  .addCsvButton("btn " + BSButtonOptions.Btn_Outline_Primary.toString())
		  .addExcelButton("btn " + BSButtonOptions.Btn_Outline_Primary.toString())
		  .addPdfButton("btn " + BSButtonOptions.Btn_Outline_Primary.toString())
		  .addPrintButton("btn " + BSButtonOptions.Btn_Outline_Primary.toString());


		dt.getOptions()
		  .setDom(DataTablesDomOptions.getDefaultTopAndBottomBSJustified());

		dt.getOptions()
		  .getDom()
		  .add(2, Buttons);

		List<Plugins> pluginsList = getAllPlugins();
		for (Plugins plugin : pluginsList)
		{
			dt.add(new TableRow<>().add(new TableCell<>().add(new Image<>(plugin.getPluginLogoUrl()).addStyle("max-width:45px;")))
			                       .add(new TableCell<>(plugin.getPluginName()))
			                       .add(new TableCell<>(plugin.getPluginVersion()))
			                       .add(new TableCell<>(plugin.getPluginDescription()))
			                       //.add(new TableCell<>("" + plugin.getPluginComponentCount()))
			                       .add(new TableCell<>("Donate"))
			                       .add(new TableCell<>("links")));
		}

		return dt;
	}

	@CacheResult
	public List<Plugins> getAllPlugins()
	{
		return new Plugins().builder()
		                    .inActiveRange()
		                    .getAll();
	}
}
