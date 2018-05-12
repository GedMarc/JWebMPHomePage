package com.jwebmp.examples.demos.homepage;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestParameters;
import com.google.inject.servlet.RequestScoped;
import lombok.Data;
import com.jwebmp.htmlbuilder.javascript.JavaScriptPart;

import java.util.Map;

@Data
@RequestScoped
public class RequestProperties
		extends JavaScriptPart<RequestProperties>
{
	@RequestParameters
	@Inject
	private Map<String, String> parameters;
}
