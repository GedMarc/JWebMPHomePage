package com.jwebmp.examples.demos.homepage.components.sourcecode;

import com.jwebmp.examples.demos.homepage.components.SourceCodeContentPanel;
import com.jwebmp.plugins.bootstrap4.modal.BSModal;
import com.jwebmp.plugins.bootstrap4.modal.parts.BSModalBody;
import com.jwebmp.plugins.bootstrap4.modal.parts.BSModalHeader;
import com.jwebmp.plugins.bootstrap4.modal.parts.BSModalTitle;
import com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions;
import com.jwebmp.plugins.bootstrap4.options.BSColoursOptions;

public class SourceCodeModal
		extends BSModal
{
	private SourceCodeContentPanel<?> sourceCodeContent;


	public SourceCodeModal(SourceCodeContentPanel<?> sourceCodeContent)
	{
		this();
		this.sourceCodeContent = sourceCodeContent;
		sourceCodeContent.setShowHeader(false);
	}

	public SourceCodeModal()
	{
		setID("sourceCodeModal");
		setModalDialogSize(true);
		setFade();
		BSModalHeader<?> header = addModalHeader(true);
		BSModalTitle<?> title = header.addTitle("Source Code Examples");
		header.addClass(BSBackgroundOptions.Bg_Primary);
		header.addClass(BSColoursOptions.Text_White);

		getModalDialog().addStyle("max-width", "90%");
	}

	public SourceCodeContentPanel<?> getSourceCodeContent()
	{
		return sourceCodeContent;
	}

	public void setSourceCodeContent(SourceCodeContentPanel<?> sourceCodeContent)
	{
		this.sourceCodeContent = sourceCodeContent;
		if (sourceCodeContent != null)
		{
			addModalBody().add(sourceCodeContent);
			sourceCodeContent.setShowHeader(false);
			sourceCodeContent.preConfigure();
		}
	}

	@Override
	public BSModalBody<?> addModalBody()
	{
		return (BSModalBody<?>) super.addModalBody()
		                             .addClass(BSBackgroundOptions.Bg_Dark);
	}
}
