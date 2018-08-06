package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.core.htmlbuilder.javascript.JavaScriptPart;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.jstree.JSTree;
import com.jwebmp.plugins.jstree.JSTreeListItem;
import com.jwebmp.plugins.jstree.options.JSTreeNodeOptions;
import com.jwebmp.plugins.jstree.themes.JSTreeDefaultDarkTheme;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
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
			rootItem.addItem(enumConstant.name(), new JSTreeNodeOptions<>().setIcon("fal fa-sort-numeric-down"));
			count++;
			if (count > 5)
			{
				rootItem.addItem(" and " + (part.getEnumConstants().length - 5) + " more", new JSTreeNodeOptions<>().setIcon("fal fa-ellipsis-h-alt"));
				break;
			}
		}
	}

	private void buildPart(JSTreeListItem<?> rootItem, JavaScriptPart part)
	{
		Field[] fieldArry = part.getClass()
		                        .getDeclaredFields();
		List<Field> fields = Arrays.asList(fieldArry);

		fields.sort((a, b) ->
		            {
			            if (JavaScriptPart.class.isAssignableFrom(a.getType()))
			            {
				            return -1;
			            }
			            else
			            {
				            return 0;
			            }
		            });

		for (Field field : fields)
		{
			Class<?> clazz = field.getType();
			if (clazz.equals(part.getClass()))
			{
				rootItem.addItem(field.getName() + " : <small><i>recursive</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-superpowers"));
			}
			else if (clazz.equals(Date.class) || clazz.equals(LocalDate.class) || clazz.equals(LocalDateTime.class))
			{
				rootItem.addItem(field.getName() + " : <small><i>date</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-clock"));
			}
			else if (clazz.equals(Boolean.class))
			{
				rootItem.addItem(field.getName() + " : <small><i>boolean</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-check-square"));
			}
			else if (clazz.equals(Integer.class))
			{
				rootItem.addItem(field.getName() + " : <small><i>integer</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-terminal"));
			}
			else if (clazz.equals(String.class))
			{
				rootItem.addItem(field.getName() + " : <small><i>string</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-text-width"));
			}
			else if (clazz.isEnum())
			{

				JSTreeListItem<?> item = rootItem.addItem(field.getName() + " : <small><i>enum</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-list"));
				item.getOptions()
				    .setOpened(false);
				buildPart(item, (Class<? extends Enum>) clazz);
			}
			else if (JavaScriptPart.class.isAssignableFrom(clazz))
			{
				JSTreeListItem<?> item = rootItem.addItem(field.getName() + ":  <small><i>" + clazz.getSimpleName()
				                                                                                   .replace("com.jwebmp.plugins.", "") + "</i></small>");
				item.setOptions(new JSTreeNodeOptions<>().setIcon("fal fa-folder"));
				buildPart(item, (JavaScriptPart) GuiceContext.getInstance(clazz));
			}
			else if ("serialVersionUID".equalsIgnoreCase(field.getName()))
			{
				rootItem.addItem(field.getName() + " : <small><i>version</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-id-badge"));
			}
			else
			{
				rootItem.addItem(field.getName() + " : <small><i>" + field.getType()
				                                                          .getSimpleName() + "</i></small>", new JSTreeNodeOptions<>().setIcon("fal fa-object-ungroup"));
			}
		}
	}
}
