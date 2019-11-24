package com.jwebmp.examples.demos.homepage.display.admin;

import com.entityassist.CoreEntity;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;
import com.jwebmp.plugins.bootstrap4.cards.prebuilt.DefaultCard;
import com.jwebmp.plugins.bootstrap4.modal.BSModal;

import java.util.Objects;

public class AdministrationScreenCard
		extends DefaultCard
{
	private Class<? extends CoreEntity> entity;

	public AdministrationScreenCard(Class<? extends CoreEntity> entity, String title, String subtitle)
	{
		super(title, null, subtitle, null);
		this.entity = entity;
		addClass("col-md-3");
		addClass("hvr-rectangle-out  hvr-glow");
		addStyle("cursor:pointer;margin:15px;");

		add(getContentBlock());

		addEvent(new GoToEntitySearchScreenEvent(this.entity, this));
	}


	public BSCardBody getContentBlock()
	{
		BSCardBody block = new BSCardBody();
		BSModal entitySearchModal = new BSModal();


		block.add(entitySearchModal);
		return block;
	}

	/**
	 * The equals
	 *
	 * @param o
	 *
	 * @return
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof AdministrationScreenCard))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}
		AdministrationScreenCard that = (AdministrationScreenCard) o;
		return Objects.equals(entity, that.entity);
	}

	/**
	 * The default hash code
	 *
	 * @return
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), entity);
	}
}
