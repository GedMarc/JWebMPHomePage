package com.jwebmp.examples.apps.homepage.components.browse;

import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.core.Event;
import com.jwebmp.core.Feature;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.angular.services.IAngularController;
import com.jwebmp.core.base.angular.services.IAngularDirective;
import com.jwebmp.core.base.angular.services.IAngularFactory;
import com.jwebmp.core.base.angular.services.IAngularModule;
import com.jwebmp.core.base.html.Strong;
import com.jwebmp.core.htmlbuilder.css.themes.Theme;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.examples.apps.homepage.components.browse.events.PackagesBrowserSwopObjectBrowserEvent;
import com.jwebmp.examples.apps.homepage.components.browse.events.SwapObjectBrowserEvent;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import com.jwebmp.plugins.toastr.ToastrFeature;
import io.github.classgraph.ClassInfo;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static com.jwebmp.core.utilities.StaticStrings.HTML_TAB;

public class PackagesBrowser
		extends JSTree<PackagesBrowser>
		implements Serializable
{
	private static final long serialVersionUID = 1L;
	private final String packageName;
	
	public PackagesBrowser(@NotNull String packageName)
	{
		this(packageName, true);
	}
	
	public PackagesBrowser(@NotNull String packageName, boolean includeSubPackages)
	{
		this.packageName = packageName;
		setID("pacakageBrowser");
		setTheme(new JSTreeDefaultDarkTheme());
		constructTree(includeSubPackages);
		addEvent(new PackagesBrowserSwopObjectBrowserEvent(this));
	}
	
	private void constructTree(boolean includeSubPackages)
	{
		JSTreeListItem<?> rootItem = new JSTreeListItem<>("Package Explorer " + ":  <small><i>" + packageName + "</i></small>");
		rootItem.getOptions()
		        .setDisabled(false)
		        .setIcon("fal fa-arrow-alt-square-down")
		        .setOpened(true);
		addRoot(rootItem);
		
		List<ClassInfo> pageConfigurators = new ArrayList<>();
		List<ClassInfo> events = new ArrayList<>();
		List<ClassInfo> features = new ArrayList<>();
		List<ClassInfo> components = new ArrayList<>();
		List<ClassInfo> themes = new ArrayList<>();
		
		List<ClassInfo> directives = new ArrayList<>();
		List<ClassInfo> controllers = new ArrayList<>();
		List<ClassInfo> factories = new ArrayList<>();
		List<ClassInfo> configurations = new ArrayList<>();
		List<ClassInfo> modules = new ArrayList<>();
		
		List<ClassInfo> allClasses = new ArrayList<>();
		List<ClassInfo> foundClasses;
		try
		{
			if (includeSubPackages)
			{
				foundClasses = GuiceContext.instance()
				                           .getScanResult()
				                           .getPackageInfo(packageName)
				                           .getClassInfoRecursive();
			}
			else
			{
				foundClasses = GuiceContext.instance()
				                           .getScanResult()
				                           .getPackageInfo(packageName)
				                           .getClassInfo();
			}
		}
		catch (NullPointerException npe)
		{
			AjaxResponse ar = GuiceContext.get(AjaxResponse.class);
			ar.addFeature(new ToastrFeature<>("Invalid Package Requested " + packageName));
			foundClasses = new ArrayList<>();
		}
		allClasses.addAll(foundClasses);
		for (ClassInfo clazz : allClasses)
		{
			if (Modifier.isPublic(clazz.getModifiers()))
			{
				if (clazz.implementsInterface(IPageConfigurator.class.getCanonicalName()))
				{
					pageConfigurators.add(clazz);
				}
				else if (clazz.extendsSuperclass(Event.class.getCanonicalName()))
				{
					events.add(clazz);
				}
				else if (clazz.extendsSuperclass(Feature.class.getCanonicalName()))
				{
					features.add(clazz);
				}
				else if (clazz.extendsSuperclass(ComponentHierarchyBase.class.getCanonicalName()))
				{
					components.add(clazz);
				}
				else if (clazz.extendsSuperclass(Theme.class.getCanonicalName()))
				{
					themes.add(clazz);
				}
				else if (clazz.implementsInterface(IAngularDirective.class.getCanonicalName()))
				{
					directives.add(clazz);
				}
				else if (clazz.implementsInterface(IAngularModule.class.getCanonicalName()))
				{
					modules.add(clazz);
				}
				else if (clazz.implementsInterface(IAngularFactory.class.getCanonicalName()))
				{
					factories.add(clazz);
				}
				else if (clazz.implementsInterface(IAngularController.class.getCanonicalName()))
				{
					controllers.add(clazz);
				}
				else if (clazz.implementsInterface(IAngularFactory.class.getCanonicalName()))
				{
					configurations.add(clazz);
				}
			}
		}
		
		JSTreeListItem<?> treeFolder = new JSTreeListItem<>().setText(
				"Page Configurators <small><i>SPI IPageConfigurator</i></small>" + HTML_TAB + "<small>(" + pageConfigurators.size() + ")</small>");
		if (!pageConfigurators.isEmpty())
		{
			treeFolder.setText(new Strong<>(treeFolder.getText()).toString(0));
		}
		treeFolder.getOptions()
		          .setIcon("fal fa-hand-holding-magic");
		rootItem.add(treeFolder);
		
		for (ClassInfo treeItem : pageConfigurators)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			treeFolder.add(ev);
		}
		
		JSTreeListItem<?> treeFolder2 = new JSTreeListItem<>().setText("Events <small><i>extends Event</i></small>" + HTML_TAB + "<small>(" + events.size() + ")</small>");
		if (!events.isEmpty())
		{
			treeFolder2.setText(new Strong<>(treeFolder2.getText()).toString(0));
		}
		treeFolder2.getOptions()
		           .setIcon("fal fa-bolt");
		rootItem.add(treeFolder2);
		
		for (ClassInfo treeItem : events)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-atom-alt");
			treeFolder2.add(ev);
		}
		
		
		JSTreeListItem<?> treeFolder3 = new JSTreeListItem<>().setText("Features <small><i>extends Feature</i></small>" + HTML_TAB + "<small>(" + features.size() + ")</small>");
		if (!features.isEmpty())
		{
			treeFolder3.setText(new Strong<>(treeFolder3.getText()).toString(0));
		}
		treeFolder3.getOptions()
		           .setIcon("fal fa-book-spells");
		rootItem.add(treeFolder3);
		
		for (ClassInfo treeItem : features)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-alicorn");
			treeFolder3.add(ev);
		}
		
		
		JSTreeListItem<?> treeFolder4 = new JSTreeListItem<>().setText(
				"Components <small><i>extends ComponentHierarchyBase</i></small>" + HTML_TAB + "<small>(" + components.size() + ")</small>");
		if (!components.isEmpty())
		{
			treeFolder4.setText(new Strong<>(treeFolder4.getText()).toString(0));
		}
		treeFolder4.getOptions()
		           .setIcon("fal fa-magic");
		rootItem.add(treeFolder4);
		
		for (ClassInfo treeItem : components)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-satellite-dish");
			treeFolder4.add(ev);
		}
		
		JSTreeListItem<?> treeFolder5 = new JSTreeListItem<>().setText("Themes <small><i>extends Theme</i></small>" + HTML_TAB + "<small>(" + themes.size() + ")</small>");
		if (!themes.isEmpty())
		{
			treeFolder5.setText(new Strong<>(treeFolder5.getText()).toString(0));
		}
		treeFolder5.getOptions()
		           .setIcon("fal fa-palette");
		rootItem.add(treeFolder5);
		
		for (ClassInfo treeItem : themes)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			treeFolder5.add(ev);
		}
		
		JSTreeListItem<?> treeFolder6 = new JSTreeListItem<>().setText(
				"Angular Directives <small><i>SPI IAngularDirective</i></small>" + HTML_TAB + "<small>(" + directives.size() + ")</small>");
		if (!directives.isEmpty())
		{
			treeFolder6.setText(new Strong<>(treeFolder6.getText()).toString(0));
		}
		treeFolder6.getOptions()
		           .setIcon("fal fa-puzzle-piece");
		rootItem.add(treeFolder6);
		
		for (ClassInfo treeItem : directives)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-asterisk");
			treeFolder6.add(ev);
		}
		
		JSTreeListItem<?> treeFolder7 = new JSTreeListItem<>().setText(
				"Angular Modules <small><i>SPI IAngularModule</i></small>" + HTML_TAB + "<small>(" + modules.size() + ")</small>");
		if (!modules.isEmpty())
		{
			treeFolder7.setText(new Strong<>(treeFolder7.getText()).toString(0));
		}
		treeFolder7.getOptions()
		           .setIcon("fal fa-plane-departure");
		rootItem.add(treeFolder7);
		
		for (ClassInfo treeItem : modules)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-house-damage");
			treeFolder7.add(ev);
		}
		
		JSTreeListItem<?> treeFolder8 = new JSTreeListItem<>().setText(
				"Angular Controllers <small><i>SPI IAngularController</i></small>" + HTML_TAB + "<small>(" + controllers.size() + ")</small>");
		if (!controllers.isEmpty())
		{
			treeFolder8.setText(new Strong<>(treeFolder8.getText()).toString(0));
		}
		treeFolder8.getOptions()
		           .setIcon("fal fa-chair-office");
		rootItem.add(treeFolder8);
		
		for (ClassInfo treeItem : controllers)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-x-ray");
			treeFolder8.add(ev);
		}
		
		JSTreeListItem<?> treeFolder9 = new JSTreeListItem<>().setText(
				"Angular Factories <small><i>SPI IAngularFactory</i></small>" + HTML_TAB + "<small>(" + factories.size() + ")</small>");
		if (!factories.isEmpty())
		{
			treeFolder9.setText(new Strong<>(treeFolder9.getText()).toString(0));
		}
		treeFolder9.getOptions()
		           .setIcon("fal fa-industry-alt");
		rootItem.add(treeFolder9);
		
		for (ClassInfo treeItem : factories)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-claw-marks");
			treeFolder9.add(ev);
		}
		
		JSTreeListItem<?> treeFolder10 = new JSTreeListItem<>().setText(
				"Angular Configurations <small><i>SPI IAngularConfiguration</i></small>" + HTML_TAB + "<small>(" + configurations.size() + ")</small>");
		if (!configurations.isEmpty())
		{
			treeFolder10.setText(new Strong<>(treeFolder10.getText()).toString(0));
		}
		treeFolder10.getOptions()
		            .setIcon("fal fa-badge-check");
		rootItem.add(treeFolder10);
		
		for (ClassInfo treeItem : configurations)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-asterisk");
			treeFolder10.add(ev);
			ev.addEvent(new SwapObjectBrowserEvent(ev).setID(treeItem.loadClass()
			                                                         .getCanonicalName()
			                                                         .replace('.', '_')));
		}
	}
}
