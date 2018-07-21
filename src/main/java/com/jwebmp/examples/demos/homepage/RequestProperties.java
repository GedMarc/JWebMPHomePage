package com.jwebmp.examples.demos.homepage;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestParameters;
import com.google.inject.servlet.RequestScoped;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;

import java.util.Map;

@RequestScoped
public class RequestProperties
		extends JavaScriptPart<RequestProperties>
{
	@RequestParameters
	@Inject
	private Map<String, String> parameters;

	public Map<String, String> getParameters()
	{
		return parameters;
	}

	public void setParameters(Map<String, String> parameters)
	{
		this.parameters = parameters;
	}
}
