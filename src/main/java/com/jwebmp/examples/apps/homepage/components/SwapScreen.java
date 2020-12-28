package com.jwebmp.examples.apps.homepage.components;

import com.jwebmp.core.FileTemplates;
import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.interfaces.GlobalChildren;
import com.jwebmp.core.base.interfaces.IComponentHierarchyBase;
import com.jwebmp.plugins.themes.mintontheme.faq.FAQCardLayout;
import com.jwebmp.plugins.themes.mintontheme.pages.PageTitle;
import com.jwebmp.examples.apps.homepage.pages.viewers.FileViewer;
import com.jwebmp.examples.apps.homepage.pages.viewers.SourceCodeViewer;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettify;
import com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Row;
import static com.jwebmp.plugins.bootstrap4.options.BSPaddingOptions.Padding_0;
import static com.jwebmp.plugins.fontawesome5.FontAwesome.icon;
import static com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons.code;
import static com.jwebmp.plugins.fontawesome5.options.FontAwesomeStyles.Duotone;

@Accessors(chain = true)
@Getter
@Setter
public abstract class SwapScreen<J extends SwapScreen<J>> extends DivSimple<J>
{
	private final List<Class<?>> sourceClasses = new ArrayList<>();
	private boolean renderSourceSection;
	
	public SwapScreen()
	{
		addClass(Row);
	}
	
	public abstract PageTitle pageTitle();
	
	public BSCardBody<?> getDefaultBody()
	{
		BSCardBody<?> all = new BSCardBody<>()
				.addClass(Padding_0);
		return all;
	}
	
	protected JQSourceCodePrettify<?> addSourceToContainer(Class<?> reference, String filename, SourceCodeLanguages language, IComponentHierarchyBase<?, ?> container)
	{
		StringBuilder contents = FileTemplates.getFileTemplate(reference, reference.getName() + filename, filename);
		if (contents != null)
		{
			JQSourceCodePrettify<?> prettify = new FileViewer(contents.toString(), language);
			@SuppressWarnings("unchecked")
			IComponentHierarchyBase<GlobalChildren, ?> containerTyped = (IComponentHierarchyBase<GlobalChildren, ?>) container;
			containerTyped.add(prettify);
			return prettify;
		}
		return null;
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			if (renderSourceSection && !sourceClasses.isEmpty())
			{
				FAQCardLayout cardLayout = new FAQCardLayout();
				for (Class<?> sourceClass : sourceClasses)
				{
					cardLayout.addSection(icon(code, Duotone), sourceClass.getSimpleName(), new SourceCodeViewer(sourceClass), sourceClasses.indexOf(sourceClass) == 0);
				}
				add(cardLayout);
			}
		}
		super.init();
	}
}
