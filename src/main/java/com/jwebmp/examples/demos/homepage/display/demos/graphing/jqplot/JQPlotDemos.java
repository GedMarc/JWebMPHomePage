package com.jwebmp.examples.demos.homepage.display.demos.graphing.jqplot;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.display.ComingSoon;
import com.jwebmp.guicedinjection.GuiceContext;

public class JQPlotDemos
		extends PluginDemoScreen
{

	public JQPlotDemos()
	{
		super("JQ Plot", "Web Frameworks", "jQuery UI");
		//addFeatureTile("Bar", "Allow elements to be moved using the mouse", new JQUIDraggableDemoScreen());
		addComponentTile("Bar Graphs", "Shows how to construct and build Bar Graphs.", GuiceContext.get(ComingSoon.class));
	}
/*
	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = new BSContainer(BSContainerOptions.Container_Fluid);

		BSRow row = new BSRow();
		container.add(row);

		BSColumn leftColumn = new BSColumn(BSColumnOptions.Col_Md_8, BSColumnOptions.Col_12);
		BSColumn rightColumn = new BSColumn(BSColumnOptions.Col_Md_4, BSColumnOptions.Col_12);

		row.add(leftColumn);
		row.add(rightColumn);


		JQPlotBarGraph graph = new JQPlotBarGraph(Orientation.VERTICAL);

		graph.getOptions()
		     .getTitle()
		     .setText("1D Bar Graph");
		graph.getOptions()
		     .getTitle()
		     .setShow(true);
		graph.getCss()
		     .getDimensions()
		     .setWidth(260);

		graph.getOptions()
		     .setAnimate(Boolean.TRUE);
		graph.addBar(new JQPlotBar("Cat 1", 12.0));
		graph.addBar(new JQPlotBar("Cat 2", 14.0));
		graph.addBar(new JQPlotBar("Cat 3", 36.0));
		graph.addBar(new JQPlotBar("Cat 4", 94.0));

		graph.getOptions()
		     .getSeriesDefaults()
		     .getPointLabels()
		     .setShow(Boolean.TRUE);

		graph.getOptions()
		     .getHighlighter()
		     .setShow(true);

		SourceCodeContentPanel graphPanel = new SourceCodeContentPanel("Bar Graph", new DivSimple<>().add(graph));
		graphPanel.setShowHeader(true);

		leftColumn.add(graphPanel);
		leftColumn.add(multiBar());


		return container;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Graphing"));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("JQPlot"));
		return crumbs;
	}

	private JQPlotBarGraph multiBar()
	{
		JQPlotBarGraph<?> graph = graph = new JQPlotBarGraph<>(Orientation.VERTICAL);
		graph.getOptions()
		     .setAnimate(Boolean.TRUE);
		graph.getCss()
		     .getDimensions()
		     .setWidth(460);

		graph.addBar(new JQPlotBar("Category One", "Cat 1-1", 12.0));
		graph.addBar(new JQPlotBar("Category One", "Cat 1-2", 94.0));
		graph.addBar(new JQPlotBar("Category One", "Cat 1-3", 65.0));
		graph.addBar(new JQPlotBar("Category One", "Cat 1-4", 46.0));

		graph.addBar(new JQPlotBar("Cat 2", "Cat 2-1", 14.0));
		graph.addBar(new JQPlotBar("Cat 2", "Cat 2-2", 36.0));
		graph.addBar(new JQPlotBar("Cat 2", "Cat 2-3", 96.0));
		graph.addBar(new JQPlotBar("Cat 2", "Cat 2-4", 107.0));

		graph.addBar(new JQPlotBar("Cat 3", "Cat 3", 17.0));
		graph.addBar(new JQPlotBar("Cat 3", "Cat 3", 33.0));
		graph.addBar(new JQPlotBar("Cat 3", "Cat 3", 8.0));
		graph.addBar(new JQPlotBar("Cat 3", "Cat 3", 26.0));

		graph.addBar(new JQPlotBar("Cat 4", "Cat 4", 11.0));
		graph.addBar(new JQPlotBar("Cat 4", "Cat 4", 52.0));
		graph.addBar(new JQPlotBar("Cat 4", "Cat 4", 78.0));
		graph.addBar(new JQPlotBar("Cat 4", "Cat 4", 69.0));

		graph.getOptions()
		     .getSeriesDefaults()
		     .getPointLabels()
		     .setShow(Boolean.TRUE);
		graph.getOptions()
		     .getHighlighter()
		     .setShow(true);
		graph.getOptions()
		     .getCursor()
		     .setShow(false);
		return graph;


	}*/

}
