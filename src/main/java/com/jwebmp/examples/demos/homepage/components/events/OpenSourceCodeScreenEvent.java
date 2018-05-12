package com.jwebmp.examples.demos.homepage.components.events;


import com.jwebmp.examples.demos.homepage.display.SourceCodeDetailsContainer;

public class OpenSourceCodeScreenEvent
		extends DefaultClick
{
	private final SourceCodeDetailsContainer detailsContainer;

	public OpenSourceCodeScreenEvent()
	{
		this(null);
	}

	public OpenSourceCodeScreenEvent(SourceCodeDetailsContainer detailsContainer)
	{
		this.detailsContainer = detailsContainer;
	}


}
