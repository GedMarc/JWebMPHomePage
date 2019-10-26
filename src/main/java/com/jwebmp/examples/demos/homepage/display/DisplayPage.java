package com.jwebmp.examples.demos.homepage.display;

import com.jwebmp.core.Feature;
import com.jwebmp.core.Page;
import com.jwebmp.core.SessionHelper;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.CSSLink;
import com.jwebmp.core.base.html.Meta;
import com.jwebmp.core.enumerations.AppleMobileStatusBarStyles;
import com.jwebmp.core.utilities.regex.RegularExpressionsDTO;
import com.jwebmp.entityassist.enumerations.ActiveFlag;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.db.dao.VisitorsService;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.Visits;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.guicedee.guicedinjection.GuiceContext;
import com.guicedee.guicedpersistence.db.exceptions.NoConnectionInfoException;
import com.guicedee.logger.LogFactory;
import com.jwebmp.plugins.plusastab.PlusAsTabFeature;
import com.jwebmp.plugins.toastr.ToastrFeature;
import com.jwebmp.plugins.toastr.ToastrType;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.guicedee.guicedinjection.GuiceContext.*;

/**
 * @author GedMarc
 * @since 30 Jul 2017
 */
@SuppressWarnings("unused")
public class DisplayPage
		extends Page<DisplayPage>
{

	private static final Logger log = LogFactory.getLog(DisplayPage.class.getName());

	/*
	 * Constructs a new Home Page
	 */
	public DisplayPage()
	{
		//Here's where it happens
		getOptions().setDynamicRender(false);
	}

	@Override
	public AjaxResponse onConnect(AjaxCall call, AjaxResponse response)
	{
		getLocalStorageKey();
		//updateVisitorInformation();
		response.addDto("regex", new RegularExpressionsDTO().addDefaults()
		                                                    .addPasswordRegex(
				                                                    6, //minLength
				                                                    false, //Special Chars
				                                                    true,  //Numbers
				                                                    true, //Lowercase
				                                                    false //Uppercase
		                                                                     ));

		response.addComponent(GuiceContext.get(West.class));
		response.addComponent(GuiceContext.get(TopBar.class));

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
			response.addComponent(GuiceContext.get(HomePage.class));
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
		getOptions().setKeywords("Rapid Application Development,jwebswing,jwebmp, java,jweb, web,development,framework,ui,rad,urad,bootstrap,jqueryui,jquery,bootstrapdialog");

		setBody(new DisplayBody());
		getBody().addFeature(new PlusAsTabFeature(getBody()));

		addAttribute("style", "height:100%;width:100%;");

		SessionHelper.getServerPath();
		super.init();
	}

	/**
	 * Gets the local storage key from the system
	 */
	@SuppressWarnings("unchecked")
	public UUID getLocalStorageKey()
	{
		UUID uuid = super.getLocalStorageKey();
		GuiceContext.get(SessionProperties.class)
		            .setGuid(uuid.toString());
		return uuid;
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
					DisplayScreen<?> screen = GuiceContext.get(comp.getScreen());
					get(AjaxResponse.class).addComponent(screen);
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, "Unable to build screen?", e);
					Class<? extends DisplayScreen> ds = null;
					try
					{
						ds = (Class<? extends DisplayScreen>) Class.forName(page);
						DisplayScreen<?> screen = GuiceContext.get(ds);
						get(AjaxResponse.class).addComponent(screen);
					}
					catch (ClassNotFoundException e1)
					{
						DisplayPage.log.log(Level.SEVERE, "Unable to render page : " + page, e1);
					}
				}
			}
		}
	}

	private void updateVisitorInformation()
	{
		SessionProperties sp = get(SessionProperties.class);
		try
		{
			if (get(SessionProperties.class).getGuid() != null)
			{
				throw new NoConnectionInfoException("New something needed, going to say visitor.");
			}
			Optional<Visitors> returningVisitor = GuiceContext.get(VisitorsService.class)
			                                                  .findByUUID(UUID.fromString(sp.getGuid()));
			if (!returningVisitor.isPresent())
			{
				throw new NoConnectionInfoException("New visitor needed");
			}
			UUID uuid = UUID.fromString(returningVisitor.get()
			                                            .getLocalStorageKey());
			sp.setVisitor(uuid);

			Optional<SubscriberVisitors> svs = new SubscriberVisitors().builder()
			                                                           .findByVisitorID(returningVisitor.get())
			                                                           .inVisibleRange()
			                                                           .get();
			if (svs.isPresent())
			{
				SubscriberVisitors sv = svs.get();
				sp.setSubscriber(sv.getSubscriberID());
				if (sv.getSubscriberID()
				      .isRememberMe() && sv.getSubscriberID()
				                           .isLogInActive() && sv.getActiveFlag()
				                                                 .ordinal() >= ActiveFlag.Archived.ordinal())
				{
					sp.setLoggedIn(true);
				}

				ToastrFeature toastr = new ToastrFeature(ToastrType.Success, "Good to see you again,",
				                                         "Thanks for coming back! We'll update these messages for you to alert you of things you missed.");
				get(AjaxResponse.class).getFeatures()
				                       .add(toastr);
			}
			Visits.create(returningVisitor.get(), this);
			//getInstance(AjaxResponse.class).addComponent(new HomePage());
		}
		catch (NoConnectionInfoException nre)
		{
			Visitors newVisitor = GuiceContext.get(Visitors.class)
			                                  .createNew(sp.getGuid());
			sp.setVisitor(UUID.fromString(newVisitor.getLocalStorageKey()));
			try
			{
				Visits.create(newVisitor, this);
			}
			catch (Exception e)
			{
				DisplayPage.log.log(Level.SEVERE, "Unable to create visit log", e);
			}
			DisplayPage.log.info("Created a new visitor [" + newVisitor + "]");
			//New user Text
			AjaxResponse<?> response = get(AjaxResponse.class);
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
		catch (Exception e)
		{
			LogFactory.getLog("log")
			          .log(Level.SEVERE, "Message", e);
			e.printStackTrace();
		}
	}
}

