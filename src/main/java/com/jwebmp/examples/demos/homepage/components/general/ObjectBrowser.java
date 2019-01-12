package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import io.github.classgraph.*;
import org.apache.commons.text.StringEscapeUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

	private JSTreeListItem buildForObject(Class clazz, JSTreeListItem rootItem, List<MethodInfo> completedMethods)
	{
		String packageName = clazz.getCanonicalName()
		                          .substring(0, clazz.getCanonicalName()
		                                             .lastIndexOf('.'));
		try (ScanResult sr = new ClassGraph().whitelistPackages(packageName)
		                                     .enableClassInfo()
		                                     .enableFieldInfo()
		                                     .enableMethodInfo()
		                                     .ignoreFieldVisibility()
		                                     .scan(Runtime.getRuntime()
		                                                  .availableProcessors()))
		{

			List<MethodInfo> publicMethods = new ArrayList<>();
			List<MethodInfo> abstractMethods = new ArrayList<>();
			List<MethodInfo> publicStaticMethods = new ArrayList<>();
			List<FieldInfo> privateFields = new ArrayList<>();

			for (ClassInfo clazzy : sr.getAllClasses())
			{
				if (!clazzy.loadClass()
				           .equals(clazz))
				{
					continue;
				}


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
						//Method method = clazz.getDeclaredMethod(methodInfo.getName());
						if (Modifier.isAbstract(methodInfo.getModifiers()))
						{
							abstractMethods.add(methodInfo);
						}
						else if (Modifier.isStatic(methodInfo.getModifiers()) && Modifier.isPublic(methodInfo.getModifiers()))
						{
							publicStaticMethods.add(methodInfo);
						}
						else if (Modifier.isPublic(methodInfo.getModifiers()))
						{
							publicMethods.add(methodInfo);
						}
					}
					catch (Exception e)
					{
						//method not on this class
						continue;
					}
				}
				for (Field field : clazz.getDeclaredFields())
				{
					if (field.getName()
					         .equalsIgnoreCase("log"))
					{
						continue;
					}
					privateFields.add(clazzy.getFieldInfo(field.getName()));
				}
			}

			completedMethods.addAll(publicMethods);

			//	if (!publicMethods.isEmpty())
			//	{
			JSTreeListItem<?> abstractMethodsFolder = new JSTreeListItem<>().setText("Abstract Methods");
			abstractMethodsFolder.getOptions()
			                     .setIcon("fal fa-hand-holding-magic")
			                     .setOpened(true);
			rootItem.add(abstractMethodsFolder);

			JSTreeListItem<?> publicMethodsFolder = new JSTreeListItem<>().setText("Public Methods");
			publicMethodsFolder.getOptions()
			                   .setIcon("fal fa-hand-holding-magic")
			                   .setOpened(true);
			rootItem.add(publicMethodsFolder);

			for (MethodInfo treeItem : abstractMethods)
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
				abstractMethodsFolder.add(ev);
			}


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
				  .setIcon("fal fa-hand");
				publicMethodsFolder.add(ev);
			}
			//		}
			//	if (!publicStaticMethods.isEmpty())
			//	{
			JSTreeListItem<?> treeFolderpublicStatic = new JSTreeListItem<>().setText("Public Static ");
			treeFolderpublicStatic.getOptions()
			                      .setIcon("fal fa-bolt")
			                      .setOpened(true);
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
			                        .setIcon("fal fa-book-spells")
			                        .setOpened(true);
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

	public ObjectBrowser(@NotNull Class objectClassName, String id)
	{
		this.objectClassName = objectClassName;
		setID(id);
		setTheme(new JSTreeDefaultDarkTheme());
		if (!cachedDisplays.containsKey(objectClassName))
		{
			constructTree();
		}
	}

	private JQSourceCodePrettify<?> buildCodeBlock()
	{
		JQSourceCodePrettify prettify = new JQSourceCodePrettify();
		prettify.addStyle("background-color:#36404a;");
		prettify.setSourceCodeLanguage(SourceCodeLanguages.XML);
		prettify.setShowLineNums(false);
		String baseDep = buildToStringOutput();
		prettify.setText(StringEscapeUtils.escapeHtml4(baseDep));
		prettify.addStyle("padding-bottom:0px !important;");
		return prettify;
	}

	private String buildToStringOutput()
	{
		try
		{
			Class<? extends ComponentHierarchyBase> objectClassName = this.objectClassName;
			return objectClassName.getDeclaredConstructor()
			                      .newInstance()
			                      .toString(0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	@Override
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
