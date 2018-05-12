package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.base.html.Link;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.options.BSMarginOptions;
import com.jwebmp.base.html.Link;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.options.BSMarginOptions;

import javax.validation.constraints.NotNull;

public class PrettyPrimaryButton<J extends PrettyPrimaryButton<J>>
		extends Link<J>
{
	public PrettyPrimaryButton()
	{
		this("#");
	}

	public PrettyPrimaryButton(String directToAddress)
	{
		super(directToAddress);
		addClass(BSButtonOptions.Btn);
		addClass(BSButtonOptions.Btn_Outline_Primary);
		addClass(BSMarginOptions.Margin_1);
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
