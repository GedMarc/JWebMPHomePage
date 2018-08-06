package com.jwebmp.examples.demos.homepage.display;

import com.google.inject.Singleton;
import com.jwebmp.core.Feature;
import com.jwebmp.core.Page;
import com.jwebmp.core.base.html.*;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions;

@Singleton
public class ComingSoon
		extends DisplayScreen
{
	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(BSContainerOptions.Container);
		BSRow row = new BSRow();

		BSColumn<?> column = new BSColumn<>(BSColumnOptions.Col_12);
		Page page = GuiceContext.get(Page.class);
		if (!page.isMobileOrSmartTablet())
		{
			column.addClass(BSTypographyOptions.Text_Center);
		}

		DivSimple<?> wrapper = new DivSimple<>().addClass("home-wrapper");
		wrapper.add(new H1<>("<span class=\"rotate\">JWebMP, MicroProfile, Modern, Simple, Domain Driven, Single-Page, Multi-Page</span>").addClass("home-text"));
		wrapper.add(new Paragraph<>("Still doing the demo screens for this one, Test Cases for the widgets are complete<br/>and you can view the status directly in SonarQube"));

		wrapper.add(buildCountdown());

		addFeature(new Feature("CountdownFeature", this)
		{
			@Override
			protected void assignFunctionsToComponent()
			{
				addQuery(buildCounterQuery());
			}
		});

		container.add(row);
		row.add(column);
		column.add(wrapper);

		return container;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Coming Soon"));
		return crumbs;
	}

	private DivSimple<?> buildCountdown()
	{
		DivSimple<?> divSimple = new DivSimple<>().addClass("row m-t-40");
		DivSimple<?> countdown = new DivSimple<>().addClass("col-sm-12 app-countdown");

		BSRow row = new BSRow();
		DivSimple countDowndayHourContainer = new DivSimple();

		divSimple.add(countdown);
		countdown.add(row);

		row.add(countDowndayHourContainer);

		DivSimple daysCountDown = new DivSimple<>();

		Span daysCountSpan = new Span("0");
		Span daysLabelSpan = new Span("days");

		daysCountDown.add(daysCountSpan);
		daysCountDown.add(daysLabelSpan);

		DivSimple hoursCountDown = new DivSimple<>();
		Span hoursCountSpan = new Span("0");
		Span hoursLabelSpan = new Span("hours");

		hoursCountDown.add(hoursCountSpan);
		hoursCountDown.add(hoursLabelSpan);

		countDowndayHourContainer.add(daysCountDown);
		countDowndayHourContainer.add(hoursCountDown);

		DivSimple<?> minuteCountDownContainer = new DivSimple<>().addClass("app-countdown-ms");

		DivSimple<?> minuteCountDown = new DivSimple<>();
		Span minutesCountSpan = new Span("0");
		Span minutesLabelSpan = new Span("minutes");

		minuteCountDown.add(minutesCountSpan);
		minuteCountDown.add(minutesLabelSpan);

		DivSimple<?> secondCountDown = new DivSimple<>();
		Span secondsCountSpan = new Span("0");
		Span secondsLabelSpan = new Span("seconds");

		secondCountDown.add(secondsCountSpan);
		secondCountDown.add(secondsLabelSpan);

		minuteCountDownContainer.add(minuteCountDown);
		minuteCountDownContainer.add(secondCountDown);

		row.add(minuteCountDownContainer);

		return divSimple;
	}

	private StringBuilder buildCounterQuery()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(
				"$(function () {\n" +
				"                    var endDate = \"August 1, 2018 00:00:00\";\n" +
				"                    $('.app-countdown .row').countdown({\n" +
				"                        date: endDate,\n" +
				"                        render: function (data) {\n" +
				"                            $(this.el).html('<div><div><span class=\"text-primary\">' + (parseInt(this.leadingZeros(data.years, 2) * 365) + parseInt(this.leadingZeros(data.days, 2))) + '</span><span><b>Days</b></span></div><div><span class=\"text-primary\">' + this.leadingZeros(data.hours, 2) + '</span><span><b>Hours</b></span></div></div><div class=\"\"><div><span class=\"text-primary\">' + this.leadingZeros(data.min, 2) + '</span><span><b>Minutes</b></span></div><div><span class=\"text-primary\">' + this.leadingZeros(data.sec, 2) + '</span><span><b>Seconds</b></span></div></div>');\n" +
				"                        }\n" +
				"                    });\n" +
				"                });\n" +
				"\n" +
				"                // Text rotate\n" +
				"                $(\".home-text .rotate\").textrotator({\n" +
				"                    animation: \"fade\",\n" +
				"                    speed: 3000\n" +
				"                });\n");
		return sb;
	}

}
