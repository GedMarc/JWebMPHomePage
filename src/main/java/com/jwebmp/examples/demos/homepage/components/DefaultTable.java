package com.jwebmp.examples.demos.homepage.components;

import com.jwebmp.core.base.html.*;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;

import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.*;

public class DefaultTable<J extends DefaultTable<J>> extends BSTable<J>
{
	public DefaultTable()
	{
		addTheme(BSTableOptions.Table_Dark)
				.addClass(Table_Hover)
				.fitInContainerBreakWord();
		setSmall(true);
		setBordered(true);
		setStriped(true);


	}

	@SuppressWarnings("unchecked")
	public J addHeader(String... text)
	{
		TableHeaderGroup thg = new TableHeaderGroup();
		for (String s : text)
		{
			TableHeaderCell cell = new TableHeaderCell();
			cell.setText(s);
			thg.add(cell);
		}
		add(thg);
		return (J) this;
	}

	@SuppressWarnings("unchecked")
	public J addRow(String... text)
	{
		TableRow r = new TableRow();
		add(r);
		for (String s : text)
		{
			TableCell cell = new TableCell();
			cell.setText(s);
			r.add(cell);
		}
		return (J) this;
	}
}
