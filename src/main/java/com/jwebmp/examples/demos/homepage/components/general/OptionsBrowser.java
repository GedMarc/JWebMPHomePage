package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class OptionsBrowser
		extends JSTree<OptionsBrowser>
{

	private final JavaScriptPart<?> optionsObject;

	public OptionsBrowser(@NotNull JavaScriptPart<?> optionsObject)
	{
		this.optionsObject = optionsObject;
		setID("optionsBrowser");
		setTheme(new JSTreeDefaultDarkTheme());
		constructTree();
	}

	private void constructTree()
	{
		JSTreeListItem<?> rootItem = new JSTreeListItem<>(optionsObject.getClass()
		                                                               .getSimpleName() + ":  <small><i>" + optionsObject.getClass()
		                                                                                                                 .getPackage()
		                                                                                                                 .getName()
		                                                                                                                 .replace("com.jwebmp.plugins.", "") + "</i></small>");
		rootItem.getOptions()
		        .setDisabled(false)
		        .setIcon("fa fa-toggle-down")
		        .setOpened(true);

		addRoot(rootItem);

		Field[] fieldArry = optionsObject.getClass()
		                                 .getDeclaredFields();
		List<Field> fields = Arrays.asList(fieldArry);

		fields.forEach(field ->
		               {
			               Class<?> clazz = field.getType();
			               if (clazz.equals(optionsObject.getClass()))
			               {
				               rootItem.addItem(field.getName() + " : <small><i>recursive</i></small>", new JSTreeNodeOptions<>().setIcon("fa fa-superpowers"));
			               }
			               else if (clazz.equals(Boolean.class))
			               {
				               rootItem.addItem(field.getName() + " : <small><i>boolean</i></small>", new JSTreeNodeOptions<>().setIcon("fa fa-check-square-o")
				                                                                                                               .setDisabled(false));
			               }
			               else if (clazz.equals(Integer.class))
			               {
				               rootItem.addItem(field.getName() + " : <small><i>integer</i></small>", new JSTreeNodeOptions<>().setIcon("fa fa-ellipsis-h")
				                                                                                                               .setDisabled(false));
			               }
			               else if (clazz.equals(String.class))
			               {
				               rootItem.addItem(field.getName() + " : <small><i>string</i></small>", new JSTreeNodeOptions<>().setIcon("fa fa-i-cursor")
				                                                                                                              .setDisabled(false));
			               }
			               else if (clazz.isEnum())
			               {
				               rootItem.addItem(field.getName() + " : <small><i>enum</i></small>", new JSTreeNodeOptions<>().setIcon("fa fa-list")
				                                                                                                            .setDisabled(false));
			               }
			               else if (JavaScriptPart.class.isAssignableFrom(clazz))
			               {
				               rootItem.addItem(field.getName() + " : <small><i>part</i></small>", new JSTreeNodeOptions<>().setIcon("fa fa-search-plus")
				                                                                                                            .setDisabled(false));
			               }
			               else if ("serialVersionUID".equalsIgnoreCase(field.getName()))
			               {
				               rootItem.addItem(field.getName() + " : <small><i>part</i></small>", new JSTreeNodeOptions<>().setIcon("fa fa-id-badge")
				                                                                                                            .setDisabled(false));
			               }
			               else
			               {
				               rootItem.addItem(field.getName() + " : <small><i>" + field.getType()
				                                                                         .getSimpleName() + "</i></small>", new JSTreeNodeOptions<>().setIcon("fa fa-object-group")
				                                                                                                                                     .setDisabled(true));
			               }
		               });
	}
}
