package com.jwebmp.examples.apps.homepage;

import com.google.common.base.Strings;
import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.core.Page;
import com.jwebmp.core.base.ContentSecurityPolicy;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.core.enumerations.AppleMobileStatusBarStyles;
import com.jwebmp.plugins.themes.mintontheme.Footer;
import com.jwebmp.examples.apps.homepage.components.SwapContainer;
import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.plugins.themes.mintontheme.dto.MintonUserDetails;
import com.jwebmp.examples.apps.homepage.pages.LoadingPage;
import com.jwebmp.examples.apps.homepage.pages.welcome.WelcomePage;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.RandomUtils;

import static com.jwebmp.examples.apps.homepage.BootHomePage.userAvatars;

public class HomePage extends Page<HomePage>
{
	public static final DemoLogoBox logoBox = new DemoLogoBox();
	private final DemoBody body = new DemoBody();
	
	public HomePage()
	{
		setBody(this.body);
		
		getOptions().setTitle("JWebMP Application");
		getOptions().setCanonicalLink("https://www.jwebmp.com/");
		
		getOptions().setIcon("logo.png", "192x192");
		getOptions().setAppleTouchIconPrecomposed("logo.png", "180x180");
		getOptions().setMSApplicationTileImage("logo.png", "270x270");
		getOptions().setAppleMobileAppCapable(true);
		getOptions().setAppleMobileStatusBarStyle(AppleMobileStatusBarStyles.Black_Translucent);
		
		getOptions().setApplicationNameMeta("JWebMP Application Core");
		getOptions().setAuthor("GedMarc");
		getOptions().setDescription("JWebMP Home and Demo Application!");
		getOptions().setFavIcon("logo.png");
		getOptions().setKeywords("jwebswing,jwebmp, java,jweb, web,development,framework,ui");
	}
	
	public static String randomUserAvatar()
	{
		return userAvatars[RandomUtils.nextInt(0, userAvatars.length)];
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			configureCSP();
			configureLeftSideBar();
			buildMenu();
			buildTopBar();
			configureRightSideBar();
			configureFooter();
		}
		super.init();
	}
	
	@Override
	public @NotNull AjaxResponse<?> onConnect(AjaxCall<?> call, AjaxResponse<?> response)
	{
		response.addDto("user", new MintonUserDetails().setFullName("Guest User")
		                                               .setRoleName("Guest Role")
		                                               .setUserImageURL(randomUserAvatar()));
		decideScreen(call, response);
		return super.onConnect(call, response);
	}
	
	public @NotNull AjaxResponse<?> decideScreen(AjaxCall<?> call, AjaxResponse<?> response)
	{
		if (call.getParameters()
		        .containsKey("p") && !Strings.isNullOrEmpty(call.getParameters()
		                                                        .get("p")))
		{
			String eventId = call.getParameters()
			                     .get("p");
			SwapScreen<?> component = null;
			try
			{
				component = (SwapScreen<?>) GuiceContext.get(Class.forName(eventId.replace("_", ".")));
			}
			catch (ClassNotFoundException classNotFoundException)
			{
				classNotFoundException.printStackTrace();
			}
			response.addComponent(new SwapContainer(component));
		}
		else
		{
			response.addComponent(new SwapContainer(GuiceContext.get(WelcomePage.class)));
		}
		return response;
	}
	
	private void configureCSP()
	{
		ContentSecurityPolicy<?> csp = new ContentSecurityPolicy<>();
		csp.registerSelf();
		csp.getScriptSrc()
		   .add("'unsafe-eval'");
		csp.getStyleSrc()
		   .add("fonts.googleapis.com");
		csp.getStyleSrc()
		   .add("'unsafe-inline'");
		csp.getFontSrc()
		   .add("fonts.gstatic.com");
		getHead().add(csp);
	}
	
	private void configureRightSideBar()
	{
		body.setRightSideBar(new DemoRightSideBar());
	}
	
	private void configureLeftSideBar()
	{
		this.body.getContentView()
		         .getLeftSideBar()
		         .setLogoBox(new DemoLogoBox());
		
		this.body.getContentView()
		         .getLeftSideBar()
		         .setUserBox(new DemoUserBox());
	}
	
	private void buildMenu()
	{
		this.body.getContentView()
		         .getLeftSideBar()
		         .setSideBarMenu(new DemoMenu());
	}
	
	private void buildTopBar()
	{
		this.body.getContentView()
		         .getContentPage()
		         .setTopBar(new DemoTopBar());
	}
	
	private void configureFooter()
	{
		this.body.getContentView()
		         .getContentPage()
		         .setFooter(new Footer()
				                    .setWriteUpText("JWebMP GPL 3 - Brought to you by <a target=\"_blank\" href=\"https://github.com/GedMarc\">GedMarc</a>")
				
				                    .addLink(new Link<>("https://guicedee.com").setTargetFrameName("_blank")
				                                                               .setText("Guiced EE"))
				                    .addLink(new Link<>("https://entityassist.com").setText("Entity Assist")
				                                                                   .setTargetFrameName("_blank"))
				                    .addLink(new Link<>("https://wrapbootstrap.com/theme/minton-admin-dashboard-landing-template-WB0858DB6").setTargetFrameName("_blank")
				                                                                                                                            .setText("Minton Theme"))
		         );
	}
}
