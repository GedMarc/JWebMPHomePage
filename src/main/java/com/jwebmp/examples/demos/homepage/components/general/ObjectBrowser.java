package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.Event;
import com.jwebmp.core.Feature;
import com.jwebmp.core.base.ComponentHierarchyBase;
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
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ObjectBrowser
		extends JSTree<ObjectBrowser>
{

	private final Class objectClassName;

	public ObjectBrowser(@NotNull Class objectClassName)
	{
		this.objectClassName = objectClassName;
		setID("objectBrowser_" + objectClassName.getSimpleName()
		                                        .replace('.', '_'));
		setTheme(new JSTreeDefaultDarkTheme());
		constructTree();
	}

	private void constructTree()
	{
		String packageName = objectClassName.getClass()
		                                    .getCanonicalName()
		                                    .substring(0, objectClassName.getClass()
		                                                                 .getCanonicalName()
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
			JSTreeListItem<?> rootItem = new JSTreeListItem<>("Object Explorer " + ":  <small><i>" + objectClassName.getName() + "</i></small>");
			rootItem.getOptions()
			        .setDisabled(false)
			        .setIcon("fal fa-project-diagram")
			        .setOpened(true);
			addRoot(rootItem);

			List<MethodInfo> publicMethods = new ArrayList<>();
			List<MethodInfo> publicStaticMethods = new ArrayList<>();
			List<FieldInfo> privateFields = new ArrayList<>();
			List<ClassInfo> components = new ArrayList<>();
			List<ClassInfo> themes = new ArrayList<>();


			for (ClassInfo clazz : sr.getAllClasses())
			{
				for (MethodInfo methodInfo : clazz.getDeclaredMethodInfo())
				{
					if (methodInfo.getName()
					              .startsWith("get") || methodInfo.getName()
					                                              .startsWith("set")
					|| methodInfo.getName().startsWith("fireEvent") || methodInfo.getName().startsWith("hashCode")
					    || methodInfo.getName().startsWith("equals") || methodInfo.getName().startsWith("preConfigure")
					    || methodInfo.getName().startsWith("fireEvent")
					|| methodInfo.hasBody())
					{
						continue;
					}
					if (methodInfo.isPublic() && methodInfo.isStatic())
					{
						publicStaticMethods.add(methodInfo);
					}
					else if (methodInfo.isPublic())
					{
						publicMethods.add(methodInfo);
					}
				}
				for (FieldInfo fieldInfo : clazz.getDeclaredFieldInfo())
				{
					if(Modifier.isPrivate(fieldInfo.getModifiers()) && ! fieldInfo.isStatic())
					{
						privateFields.add(fieldInfo);
					}
				}
			}

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
						name.append(param.getTypeSignatureOrTypeDescriptor().toString().substring(param.getTypeSignatureOrTypeDescriptor().toString().lastIndexOf('.')))
						    .append(",");
					}
					if (treeItem.getParameterInfo().length > 0)
					{
						name = name.deleteCharAt(name.length() - 1);
					}
					name.append(")");
					name.append(" : " + treeItem.loadClassAndGetMethod().getReturnType().getCanonicalName());
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
		}
	}

}
