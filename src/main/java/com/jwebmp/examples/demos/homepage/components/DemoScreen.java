package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.SessionHelper;
import com.jwebmp.base.ComponentHierarchyBase;
import com.jwebmp.base.html.DivSimple;
import com.jwebmp.base.html.H3;
import com.jwebmp.base.html.Link;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.general.MintonPanel;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.guiceinjection.GuiceContext;
import com.jwebmp.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.containers.BSColumn;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.containers.BSRow;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class DemoScreen
		extends DisplayScreen
{
	private JavaScriptPart<?> optionsObject;
	private String componentName;
	private String demoName;
	private DivSimple<?> demoPage;
	private List<ComponentHierarchyBase> features = new ArrayList<>();
	private List<ComponentHierarchyBase> quicknotes = new ArrayList<>();
	private List<ComponentHierarchyBase> events = new ArrayList<>();
	private List<ComponentHierarchyBase> classesOfInterest = new ArrayList<>();

	private BSColumn leftSide = new BSColumn(Col_12, Col_Md_8);
	private BSColumn rightSide = new BSColumn(Col_12, Col_Md_4);

	private DivSimple<?> drawingPane = new DivSimple();

	public DemoScreen()
	{
		super();
		drawingPane.addStyle("display:flex;");
		drawingPane.addStyle("background-color:transparent");
	}


	@Override
	public BSContainer<?> getContentContainer()
	{
		BSContainer container = BSContainer.newInstance(BSContainerOptions.Container_Fluid);

		BSRow row = new BSRow();
		row.add(leftSide);
		row.add(rightSide);

		DivSimple workspace = GuiceContext.getInstance(SessionProperties.class)
		                                  .getWorkspace();
		workspace.getChildren()
		         .clear();

		getDemoPage().add(workspace);

		if (!features.isEmpty())
		{
			rightSide.add(new MintonPanel<>("Features", buildPrettyFromList(features)));
		}
		if (!events.isEmpty())
		{
			getDemoPage().add("<br/><br/>");
			getDemoPage().add(new MintonPanel<>("Events", buildPrettyFromList(events)));
		}
		if (!classesOfInterest.isEmpty())
		{
			getDemoPage().add(new MintonPanel<>("Classes of Interest", buildPrettyFromList(classesOfInterest)));
		}
		if (!quicknotes.isEmpty())
		{
			rightSide.add(new MintonPanel<>("Notes", buildPrettyFromList(quicknotes)));
		}

		if (optionsObject != null)
		{
			OptionsBrowser optionsBrowser = new OptionsBrowser(optionsObject);
			rightSide.add(new MintonPanel<>("Options API", optionsBrowser).setBgColor("bg-purple")
			                                                              .setFgColor("text-white"));
		}
		else
		{
			rightSide.add(getOptionsDiv());
		}

		leftSide.add(new MintonPanel<>("Demo Panel", getDemoPage()));


		drawingPane.addStyle("min-height:400px;")
		           .addClass(W_100)
		           .addClass(Row)
		           .addClass(Col_12);

		workspace.add(new BSRow<>().add(drawingPane));

		//		workspace.add(new BSRow<>().add(new PrettyPrimaryButton<>().setText("Add Work Item")));


		drawingPane.add(newWorkDiv());

		container.add(row);
		return container;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("/").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Web Frameworks"));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(true)
		                                             .setText(componentName));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setCrumbLink(new Link<>("#").setText(demoName)));
		return crumbs;
	}

	public DivSimple<?> getDemoPage()
	{
		if (demoPage == null)
		{
			demoPage = new DivSimple<>();
		}
		return demoPage;
	}

	private DivSimple buildPrettyFromList(List<ComponentHierarchyBase> list)
	{
		DivSimple d = new DivSimple();
		list.forEach(a ->
		             {
			             d.add(a);
		             });
		return d;
	}

	public DivSimple getOptionsDiv()
	{
		DivSimple d = new DivSimple();
		d.add(new H3<>());
		d.add(new DivSimple<>("This panel will display the options available with each component").setID("optionsPanel"));
		return d;
	}

	public WorkDiv newWorkDiv()
	{
		return new WorkDiv();
	}

	public void setDemoPage(DivSimple<?> demoPage)
	{
		this.demoPage = demoPage;
	}

	public DivSimple<?> getDrawingPane()
	{
		return drawingPane;
	}

	public List<ComponentHierarchyBase> getEventsList()
	{
		if (events == null)
		{
			events = new ArrayList<>();
		}
		return events;
	}

	public DemoScreen setOptionsObject(JavaScriptPart<?> optionsObject)
	{
		this.optionsObject = optionsObject;
		return this;
	}

	public String getDemoName()
	{
		return demoName;
	}

	public void setDemoName(String demoName)
	{
		this.demoName = demoName;
		setWelcomeText(demoName);
	}

	public String getComponentName()
	{
		return componentName;
	}

	public void setComponentName(String componentName)
	{
		this.componentName = componentName;
	}

	public List<ComponentHierarchyBase> getFeaturesList()
	{
		return features;
	}

	public void setFeatures(List<ComponentHierarchyBase> features)
	{
		this.features = features;
	}

	public List<ComponentHierarchyBase> getQuicknotes()
	{
		return quicknotes;
	}

	public void setQuicknotes(List<ComponentHierarchyBase> quicknotes)
	{
		this.quicknotes = quicknotes;
	}

	public void setEvents(List<ComponentHierarchyBase> events)
	{
		this.events = events;
	}

	public List<ComponentHierarchyBase> getClassesOfInterest()
	{
		return classesOfInterest;
	}

	public void setClassesOfInterest(List<ComponentHierarchyBase> classesOfInterest)
	{
		this.classesOfInterest = classesOfInterest;
	}

	public BSColumn getLeftSide()
	{
		return leftSide;
	}

	public void setLeftSide(BSColumn leftSide)
	{
		this.leftSide = leftSide;
	}

	public BSColumn getRightSide()
	{
		return rightSide;
	}

	public void setRightSide(BSColumn rightSide)
	{
		this.rightSide = rightSide;
	}

	protected String getJavadocLocation(Class clazz)
	{
		String url = SessionHelper.getServerPath();
		url += "//javadoc/";
		return url;
	}

	public class WorkDiv
			extends DivSimple<WorkDiv>
	{
		public WorkDiv()
		{

		}
	}
}
