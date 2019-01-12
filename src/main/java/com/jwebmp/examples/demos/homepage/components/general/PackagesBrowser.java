package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.Event;
import com.jwebmp.core.Feature;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.angular.services.IAngularController;
import com.jwebmp.core.base.angular.services.IAngularDirective;
import com.jwebmp.core.base.angular.services.IAngularFactory;
import com.jwebmp.core.base.angular.services.IAngularModule;
import com.jwebmp.core.htmlbuilder.css.themes.Theme;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.examples.demos.homepage.components.events.SwopObjectBrowserEvent;
import com.jwebmp.examples.demos.homepage.components.general.events.PackagesBrowserSwopObjectBrowserEvent;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PackagesBrowser
		extends JSTree<PackagesBrowser>
{
	private static final Map<String, List<ClassInfo>> cachedLists = new ConcurrentHashMap<>();
	private static final Map<String, String> cachedDisplays = new ConcurrentHashMap<>();

	private final String packageName;

	public PackagesBrowser(@NotNull String packageName)
	{
		this.packageName = packageName;
		setID("pacakageBrowser_" + packageName.replace('.', '_'));
		setTheme(new JSTreeDefaultDarkTheme());
		if (!cachedDisplays.containsKey(packageName))
		{
			constructTree();
		}

		addEvent(new PackagesBrowserSwopObjectBrowserEvent(this));
	}

	private void constructTree()
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
		if (cachedLists.containsKey(packageName))
		{
			foundClasses = cachedLists.get(packageName);
		}
		else
		{
			foundClasses = new ClassGraph().enableAllInfo()
			                               //.enableExternalClasses()
			                               .ignoreFieldVisibility()
			                               .ignoreMethodVisibility()
			                               .ignoreClassVisibility()
			                               .ignoreParentClassLoaders()
			                               .whitelistPackages(packageName)
			                               .scan(Runtime.getRuntime()
			                                            .availableProcessors())
			                               .getAllClasses();
			cachedLists.put(packageName, foundClasses);
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

		JSTreeListItem<?> treeFolder = new JSTreeListItem<>().setText("Page Configurators <small><i>SPI IPageConfigurator</i></small>");
		treeFolder.getOptions()
		          .setIcon("fal fa-hand-holding-magic");
		rootItem.add(treeFolder);

		for (ClassInfo treeItem : pageConfigurators)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			treeFolder.add(ev);
		}

		JSTreeListItem<?> treeFolder2 = new JSTreeListItem<>().setText("Events <small><i>extends Event</i></small>");
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


		JSTreeListItem<?> treeFolder3 = new JSTreeListItem<>().setText("Features <small><i>extends Feature</i></small>");
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


		JSTreeListItem<?> treeFolder4 = new JSTreeListItem<>().setText("Components <small><i>extends ComponentHierarchyBase</i></small>");
		treeFolder4.getOptions()
		           .setIcon("fal fa-magic");
		rootItem.add(treeFolder4);

		for (ClassInfo treeItem : components)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			treeFolder4.add(ev);
		}

		JSTreeListItem<?> treeFolder5 = new JSTreeListItem<>().setText("Themes <small><i>extends Theme</i></small>");
		treeFolder5.getOptions()
		           .setIcon("fal fa-palette");
		rootItem.add(treeFolder5);

		for (ClassInfo treeItem : themes)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			treeFolder5.add(ev);
		}

		JSTreeListItem<?> treeFolder6 = new JSTreeListItem<>().setText("Angular Directives <small><i>SPI IAngularDirective</i></small>");
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

		JSTreeListItem<?> treeFolder7 = new JSTreeListItem<>().setText("Angular Modules <small><i>SPI IAngularModule</i></small>");
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

		JSTreeListItem<?> treeFolder8 = new JSTreeListItem<>().setText("Angular Controllers <small><i>SPI IAngularController</i></small>");
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

		JSTreeListItem<?> treeFolder9 = new JSTreeListItem<>().setText("Angular Factories <small><i>SPI IAngularFactory</i></small>");
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

		JSTreeListItem<?> treeFolder10 = new JSTreeListItem<>().setText("Angular Configurations <small><i>SPI IAngularConfiguration</i></small>");
		treeFolder10.getOptions()
		            .setIcon("fal fa-badge-check");
		rootItem.add(treeFolder10);

		for (ClassInfo treeItem : configurations)
		{
			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
			ev.getOptions()
			  .setIcon("fal fa-asterisk");
			treeFolder10.add(ev);
			ev.addEvent(new SwopObjectBrowserEvent(ev).setID(treeItem.loadClass()
			                                                         .getCanonicalName()
			                                                         .replace('.', '_')));
		}
	}


	@Override
	public String toString(Integer tabCount)
	{
		if (packageName == null)
		{
			return super.toString();
		}
		if (cachedDisplays.containsKey(packageName))
		{
			return cachedDisplays.get(packageName);
		}
		else
		{
			cachedDisplays.put(packageName, super.toString(0));
			return cachedDisplays.get(packageName);
		}
	}
}
