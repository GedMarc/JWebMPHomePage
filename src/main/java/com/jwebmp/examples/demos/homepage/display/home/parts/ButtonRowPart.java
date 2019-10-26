package com.jwebmp.examples.demos.homepage.display.home.parts;

import com.jwebmp.core.Feature;
import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.Span;
import com.jwebmp.examples.demos.homepage.components.general.MintonCircleChart;
import com.jwebmp.examples.demos.homepage.db.dao.VisitsService;
import com.jwebmp.examples.demos.homepage.entities.Plugins;
import com.jwebmp.examples.demos.homepage.entities.Plugins_;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.guicedservlets.GuicedServletKeys;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.websockets.GuicedWebSocket;


import javax.servlet.http.HttpSession;

import static com.guicedee.guicedinjection.GuiceContext.*;

public class ButtonRowPart<J extends ButtonRowPart<J>>
		extends BSRow<J>
{
	public ButtonRowPart()
	{
		setID("buttonRowPart");
		resetHorizontalSinks();
		Div responsive = new Div();
		responsive.addFeature(new Feature("CirclifulCustom", responsive)
		{
			@Override
			protected void assignFunctionsToComponent()
			{
				addQuery("$('.circliful-chart').circliful();");
			}
		});
		responsive.addFeature(new Feature("CounterUpFeature", responsive)
		{
			@Override
			protected void assignFunctionsToComponent()
			{
				addQuery("$('.counter').counterUp({delay: 100,time: 1200});");
			}
		});

		responsive.addClass(BSColumnOptions.Col_Sm_6);
		responsive.addClass(BSColumnOptions.Col_Lg_3);

		Div widget = new Div();
		widget.addClass("widget-simple-chart text-right card-box");

		Div chart = new MintonCircleChart("35%", "100", "#5fbeaa", "#505A66");
		widget.add(chart);

		Long siteCount = get(VisitsService.class)
				                 .countVisits();

		widget.add(new H3<>(Long.toString(siteCount)).addClass("text-success counter m-t-10"));

/*
		FontAwesome fa1 = FontAwesome.icon(FontAwesomeIcons.code)
		                             .addStyle("cursor:pointer");
		fa1.addEvent(new SourceCodeModalDisplayEvent(DisplayCodeParts.ButtonRowChart1, fa1));
*/

		widget.add(new Span<>("Visits<br/> ").addClass("text-muted text-nowrap m-b-10"));
		responsive.add(widget);

		Div responsive2 = new Div();
		responsive2.addClass("col-sm-6");
		responsive2.addClass(BSColumnOptions.Col_Lg_3);

		Div widget2 = new Div();
		widget2.addClass("widget-simple-chart text-right card-box");

		Long totalSubscribers = new Subscribers().builder()
		                                         .inVisibleRange()
		                                         .getCount();

		Div chart2 = new MintonCircleChart(Long.toString(totalSubscribers), "100", "#3bafda", "#505A66");
		widget2.add(chart2);
		widget2.add(new H3<>(Long.toString(totalSubscribers)).addClass("text-primary counter m-t-10"));

		widget2.add(new Span<>("Subscribers<br/>  ").addClass("text-muted text-nowrap m-b-10"));

		responsive2.add(widget2);

		Div responsive3 = new Div();
		responsive3.addClass(BSColumnOptions.Col_Sm_6);
		responsive3.addClass(BSColumnOptions.Col_Lg_3);

		Div widget3 = new Div();
		widget3.addClass("widget-simple-chart text-right card-box");

		Integer totalComponents = new Plugins().builder()
		                                       .selectSum(Plugins_.pluginComponentCount)
		                                       .getAll(Integer.class)
		                                       .get(0);

		Div chart3 = new MintonCircleChart(totalComponents.toString(), "100", "#f76397", "#505A66");
		widget3.add(chart3);

		widget3.add(new H3<>(totalComponents + "").addClass("text-pink counter m-t-10"));

		widget3.add(new Span<>("Plugins").addClass("text-muted text-nowrap m-b-10"));

		responsive3.add(widget3);

		Div responsive4 = new Div();
		responsive4.addClass(BSColumnOptions.Col_Sm_6);
		responsive4.addClass(BSColumnOptions.Col_Lg_3);

		Div widget4 = new Div();
		widget4.addClass("widget-simple-chart text-right card-box");

		int count = 0;
		HttpSession session = get(GuicedServletKeys.getHttpSessionKey());
		if (!GuicedWebSocket.getWebSocketSessionBindings()
		                    .containsValue(session.getId()))
		{
			count = GuicedWebSocket.getWebSocketSessionBindings()
			                       .size() + 1;
		}
		else
		{
			count = GuicedWebSocket.getWebSocketSessionBindings()
			                       .size();
		}

		Div chart4 = new MintonCircleChart(count + "", "100", "#7266ba", "#505A66");
		widget4.add(chart4);

		widget4.add(new H3<>(count + "").addClass("text-inverse counter m-t-10 text-purple "));
		widget4.add(new Span<>("Online<br/> ").addClass("text-muted text-nowrap m-b-10"));
		responsive4.add(widget4);

		add(responsive);
		add(responsive2);
		add(responsive3);
		add(responsive4);
	}
}
