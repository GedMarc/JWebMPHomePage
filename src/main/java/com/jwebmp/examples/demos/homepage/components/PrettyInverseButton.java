package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.core.base.html.Link;

import javax.validation.constraints.NotNull;

public class PrettyInverseButton<J extends PrettyInverseButton<J>>
		extends Link<J>
{
	public PrettyInverseButton()
	{
		this("#");
	}

	public PrettyInverseButton(String directToAddress)
	{
		super(directToAddress);
		addClass("btn btn-dark btn-custom waves-effect w-md waves-light m-b-5");
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
