package com.jwebmp.examples.demos.homepage.display.about.entityassist;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.PluginModulePart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;

public class EntityAssistScreen
		extends DisplayPart<EntityAssistScreen>
{
	public EntityAssistScreen()
	{
		BSCardBody all = getDefaultBody();

		all.add(new PluginModulePart("Entity Assist"));

		Div about = buildAbout();
		Div builder = buildBuilder();
		//Div plugItIn = buildBuilder();
		Div joins = buildJoins();
		Div clazz = buildDBModule();
		Div spi = buildSpi();

		DefaultSmartWizard wizard = new DefaultSmartWizard("entityassistwizard");

		wizard.addStep(new SmartWizardStep(about, "About", "All your persistence needs"));
		wizard.addStep(new SmartWizardStep(clazz, "Entity Class", "Looking at the builder() method"));
		wizard.addStep(new SmartWizardStep(builder, "Builder", "Piece it all together"));
		wizard.addStep(new SmartWizardStep(joins, "Joins", "Never been simpler, or smaller"));
		//wizard.addStep(new SmartWizardStep(plugItIn, "Connecting Entities", "Make your entities available"));
		wizard.addStep(new SmartWizardStep(spi, "Class Types", "About the BaseEntity class"));

		all.add(wizard);
		all.add(buildGoToSource(getClass()));

		add(all);
	}

	private Div buildAbout()
	{
		Div all = new Div();
		all.add("The ultimate Criteria SQL Generator utilizing CRP and AOP" +
		        "<br/><br/> Utilizing the JPA MetaModel with Criteria API, " +
		        "<br/> you get the best of everything in a simple, small, and easy to use library.");

		all.add("Standard operation allows for simplistic query writing in a fluent and type-safe manner." +
		        "<br/>The return methods are <code>get()</code>, and <code>getAll()</code>, " +
		        "<br/>where <code>get()</code> will return an optional of a single result, " +
		        "<br/>and <code>getAll()</code> will return all a list of all results");

		Div collapse = new Div();
		all.add(new DefaultReadMore(collapse));

		collapse.add("This site utilizes this framework for all database calls, both in open-in-view and backend asynchronous processes");
		addSourceToContainer(EntityAssistScreen.class, "entityassist_1.txt", SourceCodeLanguages.Java, collapse);

		collapse.add("JTA is enabled by default, and all <code>Guiced Persistence</code> modules can be used." +
		        "<br/>This framework does not interrupt or interfere with any other framework, and all features and usages remain available as they were before" +
		        "<br/> This makes the library very portable.");

		collapse.add("This builder that is strictly tied to your entity can then be used to form your queries." +
		             "<br/>Builder methods are shortcuts, for neatness, all of clauses can be used directly when building a query.");

		addSourceToContainer(EntityAssistScreen.class, "entityassist_4.txt", SourceCodeLanguages.Java, collapse);

		return all;
	}

	private Div buildBuilder()
	{
		Div all = new Div();

		all.add("The builder class can also be used to dynamic query generation to group commonly used expressions into an easy-to-read format." +
		        "<br/> Queries can be written from the calling class, or shortened inside the builder class itself for easy to read, DRY, code.");


		DefaultSlimScroll scrolly = new DefaultSlimScroll();
		all.add(scrolly);
		addSourceToContainer(EntityAssistScreen.class, "entityassist_3.txt", SourceCodeLanguages.Java, scrolly);

		return all;
	}

	private Div buildJoins()
	{
		Div all = new Div();

		all.add("Joins and filters are easily managed by using a simple <code>where()</code> clause.");

		addSourceToContainer(EntityAssistScreen.class, "entityassist_5.txt", SourceCodeLanguages.Java, all);

		return all;
	}

	private Div buildSpi()
	{
		Div all = new Div();
		all.add("Entities are configured through a type safety join, and are strictly tied to a builder. " +
		        "BaseEntity and QueryBuilder should be the inheriting classes. " +
		        "A default enhancement layer is provided that has some convenience methods and columns for SCD tables called CoreEntity and QueryBuilderCore." +
		        " The type inversions ensure the builder is meant for the particular entity." +
		        "In terms of an inherited @MappedSuperclass, the mapped superclass should also contain a typed builder that the inheriting entities abide by");

		DefaultSlimScroll scroll = new DefaultSlimScroll();
		all.add(scroll);
		addSourceToContainer(EntityAssistScreen.class, "gettingstarted.txt", SourceCodeLanguages.Java, scroll);

		all.add("The API is incredibly easy to use, and extremely intuitive. It utilizes the Criteria API 1.7, and allows proper management of queries, and simplistic design." +
		        "<br/>Order By, Group By, Having, Count, Select Columns, Construct, Detach, Insert, Update, Batch Criteria Update Delete Insert and more all there ready for you.");

		return all;
	}

	private Div buildDBModule()
	{
		Div all = new Div();

		all.add("At its very base, in order to use this library, entities should inherit the <code>BaseEntity</code> class, Through @MappedSuperclass or otherwise.");
		all.add("This class exposes a method <code>builder()</code> in your entity that is the entry point to your ");

		DefaultSlimScroll scroll = new DefaultSlimScroll();
		addSourceToContainer(EntityAssistScreen.class, "entityassist_2.txt", SourceCodeLanguages.Java, scroll);

		all.add(scroll);

		return all;
	}


}
