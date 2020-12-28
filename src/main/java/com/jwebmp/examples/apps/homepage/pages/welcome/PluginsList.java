package com.jwebmp.examples.apps.homepage.pages.welcome;

import com.jwebmp.core.base.html.*;
import com.jwebmp.core.htmlbuilder.css.measurement.MeasurementCSSImpl;
import com.jwebmp.core.htmlbuilder.css.measurement.MeasurementTypes;
import com.jwebmp.examples.apps.homepage.components.dto.Plugins;
import com.jwebmp.examples.apps.homepage.services.PluginsService;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.datatable.DataTable;
import com.jwebmp.plugins.datatable.options.DataTablesDomOptions;

import java.util.List;

import static com.guicedee.guicedinjection.GuiceContext.get;
import static com.jwebmp.plugins.datatable.options.DataTablesDomOptions.Buttons;

public class PluginsList extends DivSimple<PluginsList>
{
	public PluginsList()
	{
		TableHeaderGroup<?> thg = new TableHeaderGroup<>();
		TableRow<?> thr = new TableRow<>();
		thr.add(new TableHeaderCell<>("Name"));
		thr.add(new TableHeaderCell<>("Version"));
		thr.add(new TableHeaderCell<>("Description"));
		thr.add(new TableHeaderCell<>("Donate"));
		thr.add(new TableHeaderCell<>("Links"));
		
		thg.add(thr);
		
		DataTable<?, ?> dt = new DataTable<>("dt", thg);
		
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
		
		List<Plugins> pluginsList = get(PluginsService.class).getAllPlugins();
		for (Plugins plugin : pluginsList)
		{
			dt.add(new TableRow<>().add(new TableCell<>(plugin.getPluginName()))
			                       .add(new TableCell<>(plugin.getPluginVersion()))
			                       .add(new TableCell<>(plugin.getPluginDescription()))
			                       //.add(new TableCell<>("" + plugin.getPluginComponentCount()))
			                       .add(new TableCell<>("Donate"))
			                       .add(new TableCell<>("links")));
		}
		
		add(dt);
	}

}
