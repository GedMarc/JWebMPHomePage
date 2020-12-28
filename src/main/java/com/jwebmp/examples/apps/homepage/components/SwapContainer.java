package com.jwebmp.examples.apps.homepage.components;

import com.jwebmp.core.base.html.DivSimple;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.Container_Fluid;

@Accessors(chain = true)
@Getter
@Setter
public class SwapContainer extends DivSimple<SwapContainer>
{
	private SwapScreen<?> swapScreen;
	
	public SwapContainer(SwapScreen<?> swapScreen)
	{
		addClass(Container_Fluid);
		setID("swap-container");
		this.swapScreen = swapScreen;
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			add(0, this.swapScreen.pageTitle());
			add(this.swapScreen);
		}
		super.init();
	}
}
