package com.jwebmp.examples.demos.homepage.components.general;

import com.jwebmp.base.html.*;
import com.jwebmp.examples.demos.homepage.components.general.events.SourceCodeModalDisplayEvent;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayCodeParts;
import com.jwebmp.plugins.bootstrap4.collapse.BSCollapse;
import com.jwebmp.plugins.bootstrap4.options.BSBackgroundOptions;
import com.jwebmp.plugins.bootstrap4.options.BSClearfixOptions;
import com.jwebmp.plugins.bootstrap4.options.BSColoursOptions;
import com.jwebmp.plugins.bootstrap4.options.BSColumnOptions;
import com.jwebmp.plugins.fontawesome.FontAwesome;
import com.jwebmp.plugins.fontawesome.FontAwesomeIcons;

import javax.validation.constraints.NotNull;

public class MintonPanel<J extends MintonPanel<J>>
		extends DivSimple<J>
{
	private final Div portlet;
	private final Div heading;
	private final H3 headingTitle;
	private final Div portletHeadingWidgets;
	private final Div portletContentHolder;

	private final String header;
	private final Link link;
	private final Italic refreshIcon;
	private final Italic codeIcon;
	private final Italic closeOpenIcon;
	private final Link removeLink;
	private final Span divider;
	private final Italic removeIcon;
	private Link collapseLink;
	private Div content;

	private String bgColor;
	private String fgColor;
	private boolean showRefresh;
	private boolean showCode;
	private boolean showHeader = true;

	private DisplayCodeParts codeParts;
	private boolean codeButtonPanel = true;

	public MintonPanel()
	{
		this(new Div());
	}

	public MintonPanel(@NotNull Div content)
	{
		this(null, null, null, content, false);
	}

	public MintonPanel(String text, String backgroundOptions, BSColoursOptions coloursOptions, Div content, boolean showRefresh)
	{
		super();
		addClass(BSColumnOptions.Col_12);
		heading = new Div();
		portletHeadingWidgets = new Div();
		link = new Link<>();
		refreshIcon = new Italic();
		closeOpenIcon = new Italic();
		divider = new Span();
		removeLink = new Link("#");
		removeIcon = new Italic();
		portletContentHolder = new Div();

		header = text;
		if (backgroundOptions != null)
		{
			bgColor = backgroundOptions;
		}
		else
		{
			bgColor = BSBackgroundOptions.Bg_Primary.toString();
		}
		if (coloursOptions != null)
		{
			fgColor = coloursOptions.toString();
		}

		headingTitle = new H3(header);
		portlet = new Div();
		portlet.addClass("portlet");
		add(portlet);
		this.showRefresh = showRefresh;
		codeIcon = FontAwesome.icon(FontAwesomeIcons.code);
		setContent(content);
	}

	public MintonPanel(String text)
	{
		this(text, null, null, null, false);
	}

	public MintonPanel(String text, Div content)
	{
		this(text, null, null, content, false);
	}

	public MintonPanel(String text, Div content, boolean showRefresh)
	{
		this(text, null, null, content, showRefresh);
	}

	public MintonPanel(String text, Div content, String backgroundOptions)
	{
		this(text, backgroundOptions, null, content, false);
	}

	public MintonPanel(String text, String backgroundOptions, Div content)
	{
		this(text, backgroundOptions, null, content, false);
	}

	public MintonPanel(String text, String backgroundOptions, Div content, boolean showRefresh)
	{
		this(text, backgroundOptions, null, content, showRefresh);
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			if (showHeader)
			{
				heading.addClass("portlet-heading");
				heading.addClass(bgColor);
				portlet.add(heading);

				headingTitle.addClass("portlet-title");
				heading.add(headingTitle);
				portletHeadingWidgets.addClass("portlet-widgets");

				heading.add(portletHeadingWidgets);
				if (showCode)
				{
					Link codeLink = new Link("#");
					codeLink.add(codeIcon);
					portletHeadingWidgets.add(codeLink);
					portletHeadingWidgets.add(new Span().addClass("divider"));
					codeLink.addEvent(new SourceCodeModalDisplayEvent(codeParts, codeLink));
				}

				collapseLink = new Link(getContent().getID(true));
				collapseLink.addAttribute("data-parent", "#accordion1");

				closeOpenIcon.addClass("ion-minus-round");
				collapseLink.add(closeOpenIcon);

				content.addClass("panel-collapse");
				BSCollapse.link(collapseLink, getContent(), false);
				portletHeadingWidgets.add(collapseLink);

				divider.addClass("divider");
				portletHeadingWidgets.add(divider);

				removeLink.addAttribute("data-toggle", "remove");

				removeIcon.addClass("ion-close-round");
				removeLink.add(removeIcon);
				portletHeadingWidgets.add(removeLink);

				heading.add(new Div().addClass(BSClearfixOptions.Clearfix));
			}

			portletContentHolder.addClass("portlet-body");
			portletContentHolder.add(getContent());
			portlet.add(portletContentHolder);

			if (showRefresh)
			{
				link.setDirectToAddress("javascript:;");
				link.addAttribute("data-toggle", "reload");

				refreshIcon.addClass("ion-refresh");
				link.add(refreshIcon);

				Span divider = new Span();
				divider.addClass("divider");
				portletHeadingWidgets.add(divider);

				portletHeadingWidgets.add(link);
			}
		}
		super.preConfigure();
	}

	@NotNull
	public Div getContent()
	{
		if (content == null)
		{
			content = new Div();
		}
		return content;
	}

	public void setContent(Div content)
	{
		this.content = content;
	}

	public Div getPortlet()
	{
		return portlet;
	}

	public Div getHeading()
	{
		return heading;
	}

	public H3 getHeadingTitle()
	{
		return headingTitle;
	}

	public Div getPortletHeadingWidgets()
	{
		return portletHeadingWidgets;
	}

	public String getHeader()
	{
		return header;
	}

	public String getBgColor()
	{
		return bgColor;
	}

	public J setBgColor(String bgColor)
	{
		this.bgColor = bgColor;
		return (J) this;
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J setBgColor(BSBackgroundOptions bgColor)
	{
		this.bgColor = bgColor.toString();
		return (J) this;
	}

	public String getFgColor()
	{
		return fgColor;
	}

	public J setFgColor(String fgColor)
	{
		this.fgColor = fgColor;
		return (J) this;
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J setFgColor(BSColoursOptions fgColor)
	{
		this.fgColor = fgColor.toString();
		return (J) this;
	}

	public boolean isShowRefresh()
	{
		return showRefresh;
	}

	@SuppressWarnings("unchecked")
	@NotNull
	public J setShowRefresh(boolean showRefresh)
	{
		this.showRefresh = showRefresh;
		return (J) this;
	}

	public Div getPortletContentHolder()
	{
		return portletContentHolder;
	}

	public Link getLink()
	{
		return link;
	}

	public Italic getRefreshIcon()
	{
		return refreshIcon;
	}

	public Italic getCloseOpenIcon()
	{
		return closeOpenIcon;
	}

	public Link getRemoveLink()
	{
		return removeLink;
	}

	public Link getCollapseLink()
	{
		return collapseLink;
	}

	public void setCollapseLink(Link collapseLink)
	{
		this.collapseLink = collapseLink;
	}

	public Span getDivider()
	{
		return divider;
	}

	public Italic getRemoveIcon()
	{
		return removeIcon;
	}

	public boolean isShowCode()
	{
		return showCode;
	}

	public J setShowCode(boolean showCode)
	{
		this.showCode = showCode;
		return (J) this;
	}

	public boolean isShowHeader()
	{
		return showHeader;
	}

	public J setShowHeader(boolean showHeader)
	{
		this.showHeader = showHeader;
		return (J) this;
	}

	public Italic getCodeIcon()
	{
		return codeIcon;
	}

	public DisplayCodeParts getCodeParts()
	{
		return codeParts;
	}

	public void setCodeParts(DisplayCodeParts codeParts)
	{
		this.codeParts = codeParts;
	}

	public boolean isCodeButtonPanel()
	{
		return codeButtonPanel;
	}

	public void setCodeButtonPanel(boolean codeButtonPanel)
	{
		this.codeButtonPanel = codeButtonPanel;
	}
}
