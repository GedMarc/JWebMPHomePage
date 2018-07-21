package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.core.base.html.Link;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.options.BSMarginOptions;

import javax.validation.constraints.NotNull;

public class PrettyDangerButton<J extends PrettyDangerButton<J>>
		extends Link<J>
{
	public PrettyDangerButton()
	{
		this("#");
	}

	public PrettyDangerButton(String directToAddress)
	{
		super(directToAddress);
		addClass(BSButtonOptions.Btn);
		addClass(BSButtonOptions.Btn_Outline_Success);
		addClass(BSMarginOptions.Margin_1);

		addClass("waves-effect waves-primary");
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J asButton()
	{
		setTag("button");
		removeAttribute("href");
		removeAttribute("target");
		return (J) this;
	}
}
