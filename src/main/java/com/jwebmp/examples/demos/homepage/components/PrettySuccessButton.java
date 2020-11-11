package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.core.base.html.Link;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;

import jakarta.validation.constraints.NotNull;

public class PrettySuccessButton<J extends PrettySuccessButton<J>>
		extends Link<J>
{
	public PrettySuccessButton()
	{
		this("#");
	}

	public PrettySuccessButton(String directToAddress)
	{
		super(directToAddress);
		addClass(BSButtonOptions.Btn);
		addClass("btn btn-success btn-custom waves-effect w-md waves-light m-b-5");
		addClass("mr-2");
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
