package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;
import io.github.classgraph.*;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class OptionsBrowser
		extends JSTree<OptionsBrowser>
{

	private final Object optionsObject;

	public OptionsBrowser(@NotNull JavaScriptPart<?> optionsObject)
	{
		this.optionsObject = optionsObject;
		setID("optionsBrowser_" + optionsObject.getClass()
		                                       .getSimpleName());
		setTheme(new JSTreeDefaultDarkTheme());
		constructTree();
	}

	public OptionsBrowser(@NotNull ComponentHierarchyBase optionsObject)
	{
		this.optionsObject = optionsObject;
		setID("optionsBrowser_" + optionsObject.getClass()
		                                       .getSimpleName());
		setTheme(new JSTreeDefaultDarkTheme());
		constructTree();
	}

	private void constructTree()
	{
		JSTreeListItem<?> rootItem = new JSTreeListItem<>(optionsObject.getClass()
		                                                               .getSimpleName() + ":  <small><i>" + optionsObject.getClass()
		                                                                                                                 .getPackage()
		                                                                                                                 .getName()
		                                                                                                                 .replace("com.jwebmp.plugins.", "")
		                                                                                                                 .replace("com.jwebmp.", "") + "</i></small>");
		rootItem.getOptions()
		        .setDisabled(false)
		        .setIcon("fal fa-arrow-alt-square-down")
		        .setOpened(true);

		addRoot(rootItem);
		buildPart(rootItem, optionsObject);
	}

	private void buildPart(JSTreeListItem<?> rootItem, Class<? extends Enum> part)
	{
		int count = 0;
		for (Enum enumConstant : part.getEnumConstants())
		{
			JSTreeListItem<?> treeItem = new JSTreeListItem<>().setText(enumConstant.name());
			treeItem.getOptions()
			        .setIcon("fal fa-sort-numeric-down");
			count++;
			if (count > 5)
			{
				JSTreeListItem<?> countItem = new JSTreeListItem<>().setText(" and " + (part.getEnumConstants().length - 5) + " more");
				countItem.getOptions()
				         .setIcon("fal fa-ellipsis-h-alt");
				rootItem.add(countItem);
				break;
			}
			rootItem.add(treeItem);
		}
	}

	private void buildPart(JSTreeListItem<?> rootItem, Object part)
	{
		String packageName = part.getClass()
		                         .getCanonicalName()
		                         .substring(0, part.getClass()
		                                           .getCanonicalName()
		                                           .lastIndexOf('.'));
		try (
				ScanResult sr = new ClassGraph().enableFieldInfo()
				                                .enableAllInfo()
				                                .whitelistPackages(packageName)
				                                .scan()
		)
		{
			ClassInfo info = sr.getClassInfo(part.getClass()
			                                     .getCanonicalName());

			for (FieldInfo fieldInfo : info.getFieldInfo())
			{
				JSTreeListItem<?> treeItem = new JSTreeListItem<>();
				Field field = fieldInfo.loadClassAndGetField();
				Class clazz = field.getType();
				String paramaters = "";
				paramaters += "" + fieldInfo.getTypeSignatureOrTypeDescriptor();
				paramaters += "";

				if (clazz.equals(part.getClass()))
				{
					treeItem.setText(field.getName() + " : <small><i>" + paramaters + "</i></small>")
					        .getOptions()
					        .setIcon("fal fa-superpowers");
				}
				else if (clazz.equals(Date.class) || clazz.equals(LocalDate.class) || clazz.equals(LocalDateTime.class))
				{
					treeItem.setText(field.getName() + " : <small><i>" + paramaters + "</i></small>")
					        .getOptions()
					        .setIcon("fal fa-clock");
				}
				else if (clazz.equals(Boolean.class))
				{
					treeItem.setText(field.getName() + " : <small><i>" + paramaters + "</i></small>")
					        .getOptions()
					        .setIcon("fal fa-check-square");
				}
				else if (clazz.equals(Integer.class))
				{
					treeItem.setText(field.getName() + " : <small><i>" + paramaters + "</i></small>")
					        .getOptions()
					        .setIcon("fal fa-terminal");
				}
				else if (clazz.equals(String.class))
				{
					treeItem.setText(field.getName() + " : <small><i>" + paramaters + "</i></small>")
					        .getOptions()
					        .setIcon("fal fa-text-width");
				}
				else if (clazz.isEnum())
				{
					treeItem.setText(field.getName() + " : <small><i>enum&lt;" + clazz.getSimpleName() + "&gt;</i></small>")
					        .setAsParent(true)
					        .getOptions()
					        .setIcon("fal fa-list")
					        .setOpened(false);
					buildPart(treeItem, (Class<? extends Enum>) clazz);
				}
				else if (JavaScriptPart.class.isAssignableFrom(clazz))
				{
					treeItem.setText(field.getName() + ":  <small><i>" + clazz.getSimpleName()
					                                                          .replace("com.jwebmp.plugins.", "") + "</i></small>")
					        .setAsParent(true)
					        .getOptions()
					        .setIcon("fal fa-folder")
					        .setOpened(false);
					buildPart(treeItem, GuiceContext.getInstance(clazz));
				}
				else if ("serialVersionUID".equalsIgnoreCase(field.getName()))
				{
					//	rootItem.addItem(field.getName() + " : <small><i>version</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-id-badge"));
					continue;
				}
				else
				{
					treeItem.setText(field.getName() + " : <small><i>" + field.getType()
					                                                          .getSimpleName() + "</i></small>")
					        .getOptions()
					        .setIcon("fal fa-object-ungroup");
				}
				rootItem.add(treeItem);
			}
		}
	}
}
