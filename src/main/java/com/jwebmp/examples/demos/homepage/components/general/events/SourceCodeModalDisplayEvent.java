package com.jwebmp.examples.demos.homepage.components.general.events;

import com.jwebmp.core.base.ComponentHierarchyBase;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.interfaces.events.GlobalEvents;
import com.jwebmp.core.events.click.ClickAdapter;
import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.examples.demos.homepage.components.sourcecode.SourceCodeModal;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.guicedinjection.GuiceContext;

public class SourceCodeModalDisplayEvent
		extends ClickAdapter<SourceCodeModalDisplayEvent>
		implements GlobalEvents
{

	public SourceCodeModalDisplayEvent()
	{
		this(null, null);
	}

	public SourceCodeModalDisplayEvent(DisplayCodeParts part, ComponentHierarchyBase codeLink)
	{
		super(codeLink);
		if (part != null)
		{
			setID(part.name());
		}
	}

	@Override
	public void onClick(AjaxCall call, AjaxResponse response)
	{
		SourceCodeModal modal = GuiceContext.getInstance(SourceCodeModal.class);

		modal.addFeature(modal.createShowFeature());
		DisplayCodeParts displayCodeParts = DisplayCodeParts.valueOf(getID());
		SourceCodeContentPanel sourceCodeContent = new SourceCodeContentPanel<>(displayCodeParts.name(), displayCodeParts, null);
		sourceCodeContent.setCodeButtonPanel(false);
		sourceCodeContent.setShowHeader(false);
		modal.setSourceCodeContent(sourceCodeContent);

		response.addComponent(modal);
	}
}
