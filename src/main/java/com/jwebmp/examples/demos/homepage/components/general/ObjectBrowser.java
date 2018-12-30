package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.Event;
import com.jwebmp.core.Feature;
import com.jwebmp.core.base.ComponentBase;
import com.jwebmp.core.base.ComponentDependancyBase;
import com.jwebmp.core.base.ComponentFeatureBase;
import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.interfaces.IComponentBase;
import com.jwebmp.core.base.interfaces.IComponentDependancyBase;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.core.base.servlets.interfaces.IComponent;
import com.jwebmp.core.htmlbuilder.css.themes.Theme;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import io.github.classgraph.*;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectBrowser
		extends JSTree<ObjectBrowser>
{
	private static final Map<String, String> cachedDisplays = new ConcurrentHashMap<>();
	private final Class objectClassName;

	public ObjectBrowser(@NotNull Class objectClassName)
	{
		this.objectClassName = objectClassName;
		setID("objectBrowser_" + objectClassName.getSimpleName()
		                                        .replace('.', '_'));
		setTheme(new JSTreeDefaultDarkTheme());
		if (!cachedDisplays.containsKey(objectClassName))
		{
			constructTree();
		}
	}

	private void constructTree()
	{
		JSTreeListItem<?> rootItem = new JSTreeListItem<>("Object Explorer " + ":  <small><i>" + objectClassName.getName() + "</i></small>");
		rootItem.getOptions()
		        .setDisabled(false)
		        .setIcon("fal fa-project-diagram")
		        .setOpened(true);

		buildForObject(objectClassName, rootItem, new ArrayList<>());
		addRoot(rootItem);

		JSTreeListItem<?> treeFolderPrivateMethods = new JSTreeListItem<>().setText("Inherited");
		treeFolderPrivateMethods.getOptions()
		                        .setIcon("fal fa-hand-point-up");
		//		rootItem.add(treeFolderPrivateMethods);

		//	buildHierarchy(objectClassName,treeFolderPrivateMethods);
	}

	private JSTreeListItem buildHierarchy(Class clazz, JSTreeListItem rootItem)
	{
		String packageName = clazz.getCanonicalName()
		                          .substring(0, clazz.getCanonicalName()
		                                             .lastIndexOf('.'));
		try (ScanResult sr = new ClassGraph().whitelistPackages(packageName)
		                                     .enableClassInfo()
		                                     .enableFieldInfo()
		                                     .enableMethodInfo()
		                                     .removeTemporaryFilesAfterScan()
		                                     .ignoreFieldVisibility()
		                                     .scan(Runtime.getRuntime()
		                                                  .availableProcessors()))
		{
			ClassInfo baseClass = sr.getClassInfo(clazz.getCanonicalName());
			Class clazy = baseClass.loadClass();

			List<MethodInfo> excludeMethods = new ArrayList<>();


			if (ComponentBase.class.isAssignableFrom(clazy))
			{
				buildForObject(ComponentBase.class, rootItem, excludeMethods);
			}
			if (ComponentDependancyBase.class.isAssignableFrom(clazy))
			{
				buildForObject(ComponentDependancyBase.class, rootItem, excludeMethods);
			}
			/*if(ComponentFeatureBase.class.isAssignableFrom(clazy))
			{
				buildForObject(ComponentFeatureBase.class, rootItem,excludeMethods);
			}*/
			if (IComponentHierarchyBase.class.isAssignableFrom(clazy))
			{
				buildForObject(IComponentHierarchyBase.class, rootItem, excludeMethods);
			}
		}

		return rootItem;
	}

	private JSTreeListItem buildForObject(Class clazz, JSTreeListItem rootItem, List<MethodInfo> completedMethods)
	{
		String packageName = clazz.getCanonicalName()
		                          .substring(0, clazz.getCanonicalName()
		                                             .lastIndexOf('.'));
		try (ScanResult sr = new ClassGraph().whitelistPackages(packageName)
		                                     .enableClassInfo()
		                                     .enableFieldInfo()
		                                     .enableMethodInfo()
		                                     .removeTemporaryFilesAfterScan()
		                                     .ignoreFieldVisibility()
		                                     .scan(Runtime.getRuntime()
		                                                  .availableProcessors()))
		{

			List<MethodInfo> publicMethods = new ArrayList<>();
			List<MethodInfo> publicStaticMethods = new ArrayList<>();
			List<FieldInfo> privateFields = new ArrayList<>();

			for (ClassInfo clazzy : sr.getAllClasses())
			{
				for (MethodInfo methodInfo : clazzy.getDeclaredMethodInfo())
				{
					if (methodInfo.getName()
					              .startsWith("get") || methodInfo.getName()
					                                              .startsWith("set")
					    || methodInfo.getName()
					                 .startsWith("fireEvent") || methodInfo.getName()
					                                                       .startsWith("hashCode")
					    || methodInfo.getName()
					                 .startsWith("equals") || methodInfo.getName()
					                                                    .startsWith("preConfigure")
					    || methodInfo.getName()
					                 .startsWith("fireEvent")
					    || methodInfo.hasBody())
					{
						continue;
					}
					try
					{
						//on the actual class
						if (methodInfo.isPublic() && methodInfo.isStatic())
						{
							publicStaticMethods.add(methodInfo);
						}
						else if (methodInfo.isPublic())
						{
							publicMethods.add(methodInfo);
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				for (FieldInfo fieldInfo : clazzy.getDeclaredFieldInfo())
				{
					if (Modifier.isPrivate(fieldInfo.getModifiers()) && !fieldInfo.isStatic())
					{
						privateFields.add(fieldInfo);
					}
				}
			}

			completedMethods.addAll(publicMethods);

			//	if (!publicMethods.isEmpty())
			//	{
			JSTreeListItem<?> treeFolder = new JSTreeListItem<>().setText("Methods");
			treeFolder.getOptions()
			          .setIcon("fal fa-hand-holding-magic");
			rootItem.add(treeFolder);

			for (MethodInfo treeItem : publicMethods)
			{
				StringBuilder name = new StringBuilder(treeItem.getName());
				name.append("<small><i>(");
				for (MethodParameterInfo param : treeItem.getParameterInfo())
				{
					try
					{
						name.append(param.getTypeSignatureOrTypeDescriptor()
						                 .toString()
						                 .substring(param.getTypeSignatureOrTypeDescriptor()
						                                 .toString()
						                                 .lastIndexOf('.')))
						    .append(",");
					}
					catch (Exception e)
					{
						//No types
					}
				}
				if (treeItem.getParameterInfo().length > 0)
				{
					name = name.deleteCharAt(name.length() - 1);
				}
				name.append(")");
				try
				{
					name.append(" : " + treeItem.loadClassAndGetMethod()
					                            .getReturnType()
					                            .getCanonicalName());
				}
				catch (Exception e)
				{
					//No method like this
				}
				name.append("</i></small>");
				JSTreeListItem<?> ev = new JSTreeListItem<>().setText(name);
				ev.getOptions()
				  .setIcon("fal fa-wand-magic");
				treeFolder.add(ev);
			}
			//		}
			//	if (!publicStaticMethods.isEmpty())
			//	{
			JSTreeListItem<?> treeFolderpublicStatic = new JSTreeListItem<>().setText("Public Static ");
			treeFolderpublicStatic.getOptions()
			                      .setIcon("fal fa-bolt");
			rootItem.add(treeFolderpublicStatic);

			for (MethodInfo treeItem : publicStaticMethods)
			{
				JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
				ev.getOptions()
				  .setIcon("fal fa-atom-alt");
				treeFolderpublicStatic.add(ev);
			}
			//	}

			//	if (!privateFields.isEmpty())
			//	{
			JSTreeListItem<?> treeFolderPrivateMethods = new JSTreeListItem<>().setText("Fields");
			treeFolderPrivateMethods.getOptions()
			                        .setIcon("fal fa-book-spells");
			rootItem.add(treeFolderPrivateMethods);

			for (FieldInfo treeItem : privateFields)
			{
				JSTreeListItem<?> ev = new JSTreeListItem<>().setText(treeItem.getName());
				ev.getOptions()
				  .setIcon("fal fa-alicorn");
				treeFolderPrivateMethods.add(ev);
			}
			//	}

			return rootItem;
		}
	}

	public String toString(Integer tabCount)
	{
		if (objectClassName == null)
		{
			return super.toString();
		}
		if (cachedDisplays.containsKey(objectClassName.getName()))
		{
			return cachedDisplays.get(objectClassName.getName());
		}
		else
		{
			cachedDisplays.put(objectClassName.getName(), super.toString(0));
			return cachedDisplays.get(objectClassName.getName());
		}
	}
}
