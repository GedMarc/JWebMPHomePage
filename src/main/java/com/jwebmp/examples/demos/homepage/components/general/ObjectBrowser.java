package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.FieldInfo;
import io.github.classgraph.MethodInfo;
import io.github.classgraph.MethodParameterInfo;
import org.apache.commons.text.StringEscapeUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.jwebmp.core.utilities.StaticStrings.*;

public class ObjectBrowser
		extends JSTree<ObjectBrowser>
{
	private static final Map<String, String> cachedDisplays = new ConcurrentHashMap<>();
	private final Class objectClassName;

	private final String[] propertyMethods = new String[]{"get", "set", "is"};
	private final String[] ignoredMethods = new String[]{"equals", "hashCode", "fireEvent"};
	private final String[] defaultMethods = new String[]{"equals", "hashCode", "fireEvent"};

	public ObjectBrowser(@NotNull Class objectClassName)
	{
		this.objectClassName = objectClassName;
		setID("objectBrowser");
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

	}

	private JSTreeListItem buildForObject(Class clazz, JSTreeListItem rootItem, List<MethodInfo> completedMethods)
	{
		ClassInfo clazzy = GuiceContext.instance()
		                               .getScanResult()
		                               .getClassInfo(clazz.getCanonicalName());
		List<MethodInfo> publicMethods = new ArrayList<>();
		List<MethodInfo> abstractMethods = new ArrayList<>();
		List<MethodInfo> propertyMethods = new ArrayList<>();
		List<MethodInfo> publicStaticMethods = new ArrayList<>();
		List<FieldInfo> privateFields = new ArrayList<>();

		//Direct Methods
		for (MethodInfo methodInfo : clazzy.getDeclaredMethodInfo())
		{
			if (isIgnored(methodInfo) || isProperty(methodInfo) || methodInfo.hasBody())
			{
				continue;
			}
			try
			{
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
		//Inheritted Methods
		//Direct Methods
		for (MethodInfo methodInfo : clazzy.getMethodInfo())
		{
			if (isIgnored(methodInfo) || isProperty(methodInfo) || !methodInfo.hasBody())
			{
				continue;
			}
			try
			{
				if (Modifier.isPublic(methodInfo.getModifiers()))
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

		//Property Methods
		for (MethodInfo methodInfo : clazzy.getMethodInfo())
		{
			if (!isProperty(methodInfo))
			{
				continue;
			}
			try
			{
				if (Modifier.isPublic(methodInfo.getModifiers()))
				{
					propertyMethods.add(methodInfo);
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
		completedMethods.addAll(publicMethods);

		JSTreeListItem<?> abstractMethodsFolder = new JSTreeListItem<>().setText("Abstract Methods <small>(" + abstractMethods.size() + ")</small>");
		abstractMethodsFolder.getOptions()
		                     .setIcon("fal fa-hand-holding-magic")
		                     .setOpened(true);
		rootItem.add(abstractMethodsFolder);

		JSTreeListItem<?> publicMethodsFolder = new JSTreeListItem<>().setText("Public Methods <small>(" + publicMethods.size() + ")</small>");
		publicMethodsFolder.getOptions()
		                   .setIcon("fal fa-hand-holding-magic")
		                   .setOpened(false);
		rootItem.add(publicMethodsFolder);


		for (MethodInfo treeItem : abstractMethods)
		{
			StringBuilder name = getParametersString(treeItem);

			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(name);
			ev.getOptions()
			  .setIcon("fal fa-wand-magic");
			abstractMethodsFolder.add(ev);
		}

		Set<String> loadedMethods = new HashSet<>();
		publicMethods.sort(new Comparator<MethodInfo>()
		{
			@Override
			public int compare(MethodInfo o1, MethodInfo o2)
			{
				return o1.getName()
				         .compareTo(o2.getName());
			}
		});
		for (MethodInfo treeItem : publicMethods)
		{
			if (loadedMethods.contains(treeItem.getName()))
			{
				continue;
			}
			else
			{
				loadedMethods.add(treeItem.getName());
			}
			StringBuilder name = getParametersString(treeItem);

			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(name);
			ev.getOptions()
			  .setIcon("fal fa-skating");
			publicMethodsFolder.add(ev);
		}

		JSTreeListItem<?> propertyMethodsFolder = new JSTreeListItem<>().setText("Property Methods <small>(" + publicMethods.size() + ")</small>");
		propertyMethodsFolder.getOptions()
		                     .setIcon("fal fa-hand-holding-magic")
		                     .setOpened(false);
		rootItem.add(propertyMethodsFolder);

		loadedMethods = new HashSet<>();
		propertyMethods.sort(new Comparator<MethodInfo>()
		{
			@Override
			public int compare(MethodInfo o1, MethodInfo o2)
			{
				return o1.getName()
				         .compareTo(o2.getName());
			}
		});
		for (MethodInfo treeItem : propertyMethods)
		{
			if (loadedMethods.contains(treeItem.getName()))
			{
				continue;
			}
			else
			{
				loadedMethods.add(treeItem.getName());
			}
			StringBuilder name = getParametersString(treeItem);

			JSTreeListItem<?> ev = new JSTreeListItem<>().setText(name);
			ev.getOptions()
			  .setIcon("fal fa-baby-carriage");
			propertyMethodsFolder.add(ev);
		}

		JSTreeListItem<?> treeFolderpublicStatic = new JSTreeListItem<>().setText("Public Static <small>(" + publicStaticMethods.size() + ")</small>");
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


		JSTreeListItem<?> treeFolderPrivateMethods = new JSTreeListItem<>().setText("Fields <small>(" + privateFields.size() + ")</small>");
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

		return rootItem;

	}

	private boolean isIgnored(MethodInfo info)
	{
		for (String ignoredMethod : ignoredMethods)
		{
			if (info.getName()
			        .startsWith(ignoredMethod))
			{
				return true;
			}
		}
		return false;
	}

	private boolean isProperty(MethodInfo info)
	{
		for (String propMethod : propertyMethods)
		{
			if (info.getName()
			        .startsWith(propMethod))
			{
				return true;
			}
		}
		return false;
	}

	private StringBuilder getParametersString(MethodInfo treeItem)
	{
		StringBuilder name = new StringBuilder(treeItem.getName());
		name.append(HTML_TAB + "<small><i>(");
		for (MethodParameterInfo param : treeItem.getParameterInfo())
		{
			try
			{
				String typeString = param.getTypeSignatureOrTypeDescriptor()
				                         .toString()
				                         .substring(param.getTypeSignatureOrTypeDescriptor()
				                                         .toString()
				                                         .lastIndexOf('.'));
				if (typeString.startsWith("."))
				{
					typeString = typeString.substring(1);
				}
				name.append(typeString)
				    .append(",");

			}
			catch (Exception e)
			{
				//No types
			}
		}
		if (name.toString()
		        .contains(",") && treeItem.getParameterInfo().length > 0)
		{
			name = name.deleteCharAt(name.length() - 1);
		}
		name.append(")");
		try
		{
			name.append(" : " + treeItem.loadClassAndGetMethod()
			                            .getReturnType()
			                            .getCanonicalName()
			                            .replace("com.jwebmp.", ""));
		}
		catch (Exception e)
		{
			//No method like this
		}
		name.append("</i></small>");
		return name;
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
