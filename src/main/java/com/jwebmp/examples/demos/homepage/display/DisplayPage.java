package com.jwebmp.examples.demos.homepage.display;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jwebmp.core.Feature;
import com.jwebmp.core.Page;
import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.CSSLink;
import com.jwebmp.core.base.html.Meta;
import com.jwebmp.core.base.servlets.SessionStorageProperties;
import com.jwebmp.core.utilities.StaticStrings;
import com.jwebmp.core.utilities.regex.RegularExpressionsDTO;
import com.jwebmp.entityassist.enumerations.ActiveFlag;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.Visits;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.plugins.plusastab.PlusAsTabFeature;
import com.jwebmp.plugins.toastr.ToastrFeature;
import com.jwebmp.plugins.toastr.ToastrType;

import javax.persistence.NoResultException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.jwebmp.guicedinjection.GuiceContext.*;

/**
 * @author Marc Magon
 * @since 30 Jul 2017
 */
@SuppressWarnings("unused")
public class DisplayPage
		extends Page
{

	private static final Logger log = LogFactory.getLog(DisplayPage.class.getName());

	private static final long serialVersionUID = 1L;

	/*
	 * Constructs a new Home Page
	 */
	public DisplayPage()
	{

	}

	@Override
	public AjaxResponse onConnect(AjaxCall call, AjaxResponse response)
	{
		getLocalStorageKey();
		updateVisitorInformation();
		response.addDto("regex", new RegularExpressionsDTO().addDefaults()
		                                                    .addPasswordRegex(6, false, true, true, false));

		response.addComponent(GuiceContext.getInstance(West.class));
		response.addComponent(GuiceContext.getInstance(TopBar.class));

		if (isMobileOrSmartTablet())
		{
			response.getFeatures()
			        .add(new Feature("SlideCloseWest")
			        {
				        @Override
				        protected void assignFunctionsToComponent()
				        {
					        addQuery("lay_wrapper.slideClose('west');");
				        }
			        });
		}

		if (!call.getParameters()
		         .containsKey("p"))
		{
			response.addComponent(GuiceContext.getInstance(HomePage.class));
		}
		else
		{
			deeplinkScreen(call);
		}
		return response;
	}

	@Override
	public void init()
	{
		SessionHelper.setAddressToBeUsedWhenNull("https://www.jwebmp.com");

		getPageFields().setTitle("JWebMP Application");

		getHead().add(new CSSLink("canonical", null, "https://www.jwebmp.com/"));

		getHead().add(new CSSLink<>(null, "icon", "logo.png").addAttribute("sizes", "192x192"));
		getHead().add(new CSSLink<>(null, "apple-touch-icon-precomposed", "logo.png").addAttribute("sizes", "180x180"));
		getHead().add(new CSSLink<>(null, "msapplication-TileImage", "logo.png").addAttribute("sizes", "270x270"));

		Meta appleMeta = new Meta();
		appleMeta.addAttribute("name", "apple-mobile-web-app-capable");
		appleMeta.addAttribute("content", "yes");
		getHead().add(appleMeta);

		Meta appleMeta2 = new Meta();
		appleMeta2.addAttribute("name", "apple-mobile-web-app-status-bar-style");
		appleMeta2.addAttribute("content", "black-translucent");
		getHead().add(appleMeta2);

		Meta appleMeta3 = new Meta();
		appleMeta3.addAttribute("CACHE-CONTROL", "NO-CACHE");
		appleMeta3.addAttribute("content", "black-translucent");
		getHead().add(appleMeta3);

		getPageFields().setApplicationNameMeta("JWebMP Application Core");
		getPageFields().setAuthor("Marc Magon");
		getPageFields().setDescription("JWebMP Home and Demo Application!");
		getPageFields().setFavIcon("logo.png");
		getPageFields().setKeywords("Rapid Application Development,jwebswing,jwebmp, java,jweb, web,development,framework,ui,rad,urad,bootstrap,jqueryui,jquery,bootstrapdialog");

		setBody(new DisplayBody());
		getBody().addFeature(new PlusAsTabFeature(getBody()));
		getOptions().setLocalStorage(true);

		addAttribute("style", "height:100%;width:100%;");

		SessionHelper.getServerPath();
		super.init();
	}

	/**
	 * Gets the local storage key from the system
	 */
	@SuppressWarnings("unchecked")
	private void getLocalStorageKey()
	{
		SessionStorageProperties sp = getInstance(SessionStorageProperties.class);
		Map<String, String> localStorage = sp.getLocalStorage();
		if (!localStorage.containsKey(StaticStrings.LOCAL_STORAGE_PARAMETER_KEY))
		{
			String uuid = UUID.randomUUID()
			                  .toString();
			getInstance(AjaxResponse.class).getLocalStorage()
			                               .put(StaticStrings.LOCAL_STORAGE_PARAMETER_KEY, uuid);
			localStorage.put(StaticStrings.LOCAL_STORAGE_PARAMETER_KEY, uuid);
		}
		String guid = localStorage.get(StaticStrings.LOCAL_STORAGE_PARAMETER_KEY);
		GuiceContext.getInstance(SessionProperties.class)
		            .setGuid(guid);
	}

	private void updateVisitorInformation()
	{
		try
		{
			Optional<Visitors> returningVisitor = new Visitors().builder()
			                                                    .findByGuid(getInstance(SessionProperties.class).getGuid())
			                                                    .get();
			if (!returningVisitor.isPresent())
			{
				throw new NoResultException("New visitor needed");
			}
			getInstance(SessionProperties.class).setVisitor(returningVisitor.get());

			Optional<SubscriberVisitors> svs = new SubscriberVisitors().builder()
			                                                           .findByVisitorID(returningVisitor.get())
			                                                           .inVisibleRange()
			                                                           .get();
			if (svs.isPresent())
			{
				SubscriberVisitors sv = svs.get();

				getInstance(SessionProperties.class).setSubscriber(sv.getSubscriberID());
				if (sv.getSubscriberID()
				      .isRememberMe() && sv.getSubscriberID()
				                           .isLogInActive() && sv.getActiveFlag()
				                                                 .ordinal() >= ActiveFlag.Archived.ordinal())
				{
					getInstance(SessionProperties.class).setLoggedIn(true);
				}
			}
			Visits.create(returningVisitor.get(), this);
			//getInstance(AjaxResponse.class).addComponent(new HomePage());
		}
		catch (NoResultException nre)
		{
			Visitors newVisitor = Visitors.createNew(getInstance(SessionProperties.class).getGuid());
			getInstance(SessionProperties.class).setVisitor(newVisitor);
			try
			{
				Visits.create(newVisitor, this);
			}
			catch (JsonProcessingException e)
			{
				e.printStackTrace();
			}
			DisplayPage.log.info("Created a new visitor [" + newVisitor + "]");
			//New user Text
			AjaxResponse<?> response = getInstance(AjaxResponse.class);
			ToastrFeature toastr = new ToastrFeature(ToastrType.Warning, "First Use",
			                                         "We think this is the first time you've visited this site on this device and browser combination\n" +
			                                         "\n" +
			                                         "You should only see this message once for each browser.\n" +
			                                         "This allows for life-time logins on any application.");
			toastr.getOptions()
			      .setProgressBar(true)
			      .setEscapeHtml(true)
			      .setCloseButton(true)
			      .setShowDuration(50000)
			      .setPreventDuplicates(true);
			response.getFeatures()
			        .add(toastr);
		}
		catch (JsonProcessingException e)
		{
			LogFactory.getLog("log")
			          .log(Level.SEVERE, "Message", e);
			e.printStackTrace();
		}
	}

	/**
	 * Reads the "p" query parameter for a class name to load
	 *
	 * @param call
	 */
	@SuppressWarnings("unchecked")
	private void deeplinkScreen(AjaxCall<?> call)
	{
		if (!call.getParameters()
		         .isEmpty())
		{
			if (call.getParameters()
			        .containsKey("p"))
			{
				String page = call.getParameters()
				                  .get("p");
				try
				{
					DisplayScreens comp = DisplayScreens.valueOf(page);
					DisplayScreen<?> screen = GuiceContext.getInstance(comp.getScreen());
					getInstance(AjaxResponse.class).addComponent(screen);
				}
				catch (Exception e)
				{
					Class<? extends DisplayScreen> ds = null;
					try
					{
						ds = (Class<? extends DisplayScreen>) Class.forName(page);
						DisplayScreen<?> screen = GuiceContext.getInstance(ds);
						getInstance(AjaxResponse.class).addComponent(screen);
					}
					catch (ClassNotFoundException e1)
					{
						DisplayPage.log.log(Level.SEVERE, "Unable to render page : " + page, e1);
					}
				}
			}
		}
	}
}

