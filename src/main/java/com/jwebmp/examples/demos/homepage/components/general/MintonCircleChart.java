package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.base.html.DivSimple;

import javax.validation.constraints.NotNull;

public class MintonCircleChart<J extends MintonCircleChart<J>>
		extends DivSimple<J>
{
	private String text;
	private String percent;
	private String fgColor;
	private String bgColor;

	public MintonCircleChart()
	{
	}

	public MintonCircleChart(String text, String percent, String fgColor, String bgColor)
	{
		this.text = text;
		this.percent = percent;
		this.fgColor = fgColor;
		this.bgColor = bgColor;
	}

	public String getText()
	{
		return text;
	}

	@Override
	@SuppressWarnings("unchecked")
	@NotNull
	public J setText(String text)
	{
		this.text = text;
		return (J) this;
	}

	public String getPercent()
	{
		return percent;
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J setPercent(String percent)
	{
		this.percent = percent;
		return (J) this;
	}

	public String getFgColor()
	{
		return fgColor;
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J setFgColor(String fgColor)
	{
		this.fgColor = fgColor;
		return (J) this;
	}

	public String getBgColor()
	{
		return bgColor;
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J setBgColor(String bgColor)
	{
		this.bgColor = bgColor;
		return (J) this;
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			addClass("circliful-chart");
			addAttribute("data-dimension", "90");
			addAttribute("data-text", text);
			addAttribute("data-width", "5");
			addAttribute("data-fontsize", "14");
			addAttribute("data-percent", percent);
			addAttribute("data-fgcolor", fgColor);
			addAttribute("data-bgcolor", bgColor);
		}
		super.preConfigure();
	}
}
