package com.jwebmp.examples.demos.homepage.display;

import com.jwebmp.guiceinjection.OptionalPair;
import com.jwebmp.guiceinjection.Pair;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class SourceCodeDetailsContainer<J extends SourceCodeDetailsContainer<J>>
		implements Serializable
{

	private static final long serialVersionUID = 1L;

	private final OptionalPair<Class, String> htmlSnippetLocations = new OptionalPair<>();
	private final OptionalPair<Class, String> javaSnippetLocations = new OptionalPair<>();
	private final OptionalPair<Class, String> javaScriptSnippetLocations = new OptionalPair<>();
	private final OptionalPair<Class, String> testsScriptSnippetLocations = new OptionalPair<>();
	private final OptionalPair<Class, String> cssSnippetLocations = new OptionalPair<>();
	private final OptionalPair<Class, String> quickTipsSnippetLocations = new OptionalPair<>();

	private final Map<String, Pair<Class, String>> customLocations = new LinkedHashMap<>();

	public OptionalPair<Class, String> getHtmlSnippetLocations()
	{
		return htmlSnippetLocations;
	}

	public OptionalPair<Class, String> getJavaSnippetLocations()
	{
		return javaSnippetLocations;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setJavaLocation(Class referenceClass, String filename)
	{
		javaSnippetLocations.setKey(referenceClass);
		javaSnippetLocations.setValue(filename);
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setJavaScriptLocation(Class referenceClass, String filename)
	{
		javaScriptSnippetLocations.setKey(referenceClass);
		javaScriptSnippetLocations.setValue(filename);
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setCssLocation(Class referenceClass, String filename)
	{
		cssSnippetLocations.setKey(referenceClass);
		cssSnippetLocations.setValue(filename);
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setHtmlLocation(Class referenceClass, String filename)
	{
		htmlSnippetLocations.setKey(referenceClass);
		htmlSnippetLocations.setValue(filename);
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setTestsLocation(Class referenceClass, String filename)
	{
		testsScriptSnippetLocations.setKey(referenceClass);
		testsScriptSnippetLocations.setValue(filename);
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J setQuickTipsLocation(Class referenceClass, String filename)
	{
		quickTipsSnippetLocations.setKey(referenceClass);
		quickTipsSnippetLocations.setValue(filename);
		return (J) this;
	}

	@NotNull
	@SuppressWarnings("unchecked")
	public J addCustomLocation(String tabName, Class referenceClass, String fileName)
	{
		Pair<Class, String> reference = new Pair<>(referenceClass, fileName);
		getCustomLocations().put(tabName, reference);
		return (J) this;
	}


	public OptionalPair<Class, String> getJavaScriptSnippetLocations()
	{
		return javaScriptSnippetLocations;
	}

	public OptionalPair<Class, String> getTestsScriptSnippetLocations()
	{
		return testsScriptSnippetLocations;
	}

	public OptionalPair<Class, String> getCssSnippetLocations()
	{
		return cssSnippetLocations;
	}

	public OptionalPair<Class, String> getQuickTipsSnippetLocations()
	{
		return quickTipsSnippetLocations;
	}
}
