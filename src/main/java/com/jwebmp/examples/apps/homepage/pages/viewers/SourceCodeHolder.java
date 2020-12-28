package com.jwebmp.examples.apps.homepage.pages.viewers;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.examples.apps.homepage.pages.LoadingPage;

public class SourceCodeHolder extends DivSimple<SourceCodeHolder>
{
	private final DivSimple<?> codeDiv = new DivSimple<>();
	
	public SourceCodeHolder(Class<?> classToRender)
	{
		codeDiv.setID(classToRender.getCanonicalName()
		                           .replace('.', '_'));
		codeDiv.addEvent(new SourceCodeLoadEvent(this));
		codeDiv.add(new LoadingPage());
		add(codeDiv);
	}
}
