package com.jwebmp.examples.apps.homepage.pages;

import com.jwebmp.core.base.html.DivSimple;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.Image;
import com.jwebmp.core.base.html.Paragraph;
import com.jwebmp.core.base.html.attributes.ImageAttributes;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.jwebmp.plugins.bootstrap4.options.BSMarginOptions.MarginTop_4;
import static com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions.Text_Center;
import static com.jwebmp.plugins.bootstrap4.options.BSTypographyOptions.Text_Muted;

@Accessors(chain = true)
@Getter
@Setter
public class ComingSoon extends DivSimple<ComingSoon>
{
	private Image<?> img;
	private H3<?> h3;
	private Paragraph<?> paragraph;
	
	public ComingSoon(String heading, String paragraph, @Null String image)
	{
		addClass(Text_Center);
		if (image != null)
		{
			
			img = new Image<>(image);
		}
		h3 = new H3<>(heading).addClass(MarginTop_4);
		this.paragraph = new Paragraph<>(paragraph).addClass(Text_Muted);
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			if (img != null)
			{
				img.addAttribute(ImageAttributes.Height, "160px");
				add(img);
			}
			if (h3 != null)
			{
				add(h3);
			}
			if (paragraph != null)
			{
				add(paragraph);
			}
		}
		super.init();
	}
}
