package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.Event;
import com.jwebmp.core.Feature;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.htmlbuilder.css.themes.Theme;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class PackagesBrowser
		extends JSTree<PackagesBrowser>
{

	private final String packageName;

	public PackagesBrowser(@NotNull String packageName)
	{
		this.packageName = packageName;
		setID("pacakageBrowser_" + packageName.replace('.', '_'));
		setTheme(new JSTreeDefaultDarkTheme());
		constructTree();
	}

	private void constructTree()
	{

		try (ScanResult sr = new ClassGraph().whitelistPackages(packageName)
		                                     .enableClassInfo()
		                                     .enableFieldInfo()
		                                     .removeTemporaryFilesAfterScan()
		                                     .ignoreFieldVisibility()
		                                     .scan(Runtime.getRuntime()
		                                                  .availableProcessors()))
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


			for (ClassInfo clazz : sr.getAllClasses())
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
				}
			}

			JSTreeListItem<?> treeFolder = new JSTreeListItem<>().setText("Page Configurators");
			treeFolder.getOptions()
			          .setIcon("fal fa-hand-holding-magic");
			rootItem.add(treeFolder);

			for (ClassInfo treeItem : pageConfigurators)
			{
				JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
				treeFolder.add(ev);
			}


			JSTreeListItem<?> treeFolder2 = new JSTreeListItem<>().setText("Events");
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


			JSTreeListItem<?> treeFolder3 = new JSTreeListItem<>().setText("Features");
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


			JSTreeListItem<?> treeFolder4 = new JSTreeListItem<>().setText("Components");
			treeFolder4.getOptions()
			          .setIcon("fal fa-magic");
			rootItem.add(treeFolder4);

			for (ClassInfo treeItem : components)
			{
				JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
				treeFolder4.add(ev);
			}


			JSTreeListItem<?> treeFolder5 = new JSTreeListItem<>().setText("Themes");
			treeFolder5.getOptions()
			          .setIcon("fal fa-palette");
			rootItem.add(treeFolder5);

			for (ClassInfo treeItem : themes)
			{
				JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
				treeFolder5.add(ev);
			}

		}
	}
}
