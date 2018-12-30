package com.jwebmp.examples.demos.homepage.display.about.entityassist;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.display.DefaultSmartWizard;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.smartwizard4.SmartWizardStep;

public class EntityAssistScreen
		extends DisplayPart<EntityAssistScreen>
{
	public EntityAssistScreen()
	{
		BSCardBody all = getDefaultBody();
		Div about = buildAbout();
		Div clazz = buildDBModule();
		Div spi = buildSpi();

		DefaultSmartWizard wizard = new DefaultSmartWizard("entityassistwizard");
		wizard.addStep(new SmartWizardStep(about, "About", "All your persistence needs"));
		wizard.addStep(new SmartWizardStep(clazz, "Entity Classes", "Getting the builder() method"));
		wizard.addStep(new SmartWizardStep(spi, "Class Types", "About the BaseEntity class"));

		all.add(wizard);
		all.add(buildGoToSource(getClass()));

		add(all);
	}

	private Div buildAbout()
	{
		Div all = new Div();
		all.add("The ultimate Criteria SQL Generator utlizing CRP, AOP and designed in a complete DDD environment." +
		        "Utilizing the Metamodel, and forcing the builder to be strictly tied to the entity, you can wrap your common statements in easy-to-use methods.");

		all.add("Standard operation allows for simplistic query writing." +
		        "The return methods are get(), and getAll() where get() will return an optional of a single result, and getAll() will return all results)");

		all.add("This site utilizes this framework for all database calls, both in open-in-view and backend asynchronous processes");
		addSourceToContainer(EntityAssistScreen.class, "entityassist_1.txt", SourceCodeLanguages.Java, all);
		all.add("JTA is enabled by default, all <code>Guiced Persistence</code> modules can be used");

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

		addSourceToContainer(EntityAssistScreen.class, "gettingstarted.txt", SourceCodeLanguages.Java, all);

		all.add("The API is incredibly easy to use, and extremely intuitive. It utilizes the Criteria API 1.7, and allows proper management of queries, and simplistic design." +
		        "<br/>Order By, Group By, Having, Count, Select Columns, Construct, Detach, Insert, Update, Batch Criteria Update Delete Insert and more all there ready for you.");

		return all;
	}

	private Div buildDBModule()
	{
		Div all = new Div();
		all.add("By default all entities inherit the builder() method that configures and constructs your entities");
		addSourceToContainer(EntityAssistScreen.class, "entityassist_2.txt", SourceCodeLanguages.Java, all);

		all.add("The builder class can also be used to dynamic query generation to group commonly used expressions into an easy-to-read format");
		addSourceToContainer(EntityAssistScreen.class, "entityassist_3.txt", SourceCodeLanguages.Java, all);

		return all;
	}


}
