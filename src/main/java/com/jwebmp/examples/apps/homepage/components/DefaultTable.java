package com.jwebmp.examples.apps.homepage.components;

import com.jwebmp.core.base.html.TableCell;
import com.jwebmp.core.base.html.TableHeaderCell;
import com.jwebmp.core.base.html.TableHeaderGroup;
import com.jwebmp.core.base.html.TableRow;
import com.jwebmp.plugins.bootstrap4.options.BSTableOptions;
import com.jwebmp.plugins.bootstrap4.tables.BSTable;

import static com.jwebmp.plugins.bootstrap4.options.BSTableOptions.Table_Hover;

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
		TableHeaderGroup<?> thg = new TableHeaderGroup<>();
		for (String s : text)
		{
			TableHeaderCell<?> cell = new TableHeaderCell<>();
			cell.setText(s);
			thg.add(cell);
		}
		add(thg);
		return (J) this;
	}
	
	@SuppressWarnings("unchecked")
	public J addRow(String... text)
	{
		TableRow<?> r = new TableRow<>();
		add(r);
		for (String s : text)
		{
			TableCell<?> cell = new TableCell<>();
			cell.setText(s);
			r.add(cell);
		}
		return (J) this;
	}
}
