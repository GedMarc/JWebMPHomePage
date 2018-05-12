package com.jwebmp.examples.demos.homepage.display;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Singleton;
import com.jwebmp.Page;
import com.jwebmp.SessionHelper;
import com.jwebmp.base.ajax.*;
import com.jwebmp.base.html.CSSLink;
import com.jwebmp.base.html.Meta;
import com.jwebmp.base.servlets.SessionStorageProperties;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.DisplayScreen;
import com.jwebmp.examples.demos.homepage.display.home.HomePage;
import com.jwebmp.examples.demos.homepage.display.menu.West;
import com.jwebmp.examples.demos.homepage.display.rightbar.RightBar;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.Visits;
import com.jwebmp.examples.demos.homepage.enumerations.DisplayScreens;
import com.jwebmp.plugins.plusastab.PlusAsTabFeature;
import com.jwebmp.utilities.RegularExpressionsDTO;
import com.jwebmp.utilities.StaticStrings;
import za.co.mmagon.entityassist.enumerations.ActiveFlag;
import za.co.mmagon.guiceinjection.GuiceContext;
import za.co.mmagon.logger.LogFactory;

import javax.persistence.NoResultException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static za.co.mmagon.guiceinjection.GuiceContext.getInstance;


/**
 * @author Marc Magon
 * @since 30 Jul 2017
 */
@Singleton
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
		getPageFields().setTitle("JWebMP Application");

		getHead().add(new CSSLink("canonical", null, "https://www.jwebswing.com/"));

		getHead().add(new CSSLink<>(null, "icon",
		                            "https://scontent.fjnb6-1.fna.fbcdn.net/v/t1.0-9/30262102_618084625202203_3933231896061804544_n.png?_nc_cat=0&oh=6455cb346ca429e61fa4dc3b8b494bd0&oe=5B5838A9").addAttribute(
				"sizes", "192x192"));
		getHead().add(new CSSLink<>(null, "apple-touch-icon-precomposed",
		                            "https://scontent.fjnb6-1.fna.fbcdn.net/v/t1.0-9/30262102_618084625202203_3933231896061804544_n.png?_nc_cat=0&oh=6455cb346ca429e61fa4dc3b8b494bd0&oe=5B5838A9").addAttribute(
				"sizes", "180x180"));
		getHead().add(new CSSLink<>(null, "msapplication-TileImage",
		                            "https://scontent.fjnb6-1.fna.fbcdn.net/v/t1.0-9/30262102_618084625202203_3933231896061804544_n.png?_nc_cat=0&oh=6455cb346ca429e61fa4dc3b8b494bd0&oe=5B5838A9").addAttribute(
				"sizes", "270x270"));

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
		getPageFields().setDescription("JWebSwing Home and Demo Application!");
		getPageFields().setFavIcon(
				"https://scontent.fjnb6-1.fna.fbcdn.net/v/t1.0-9/30262102_618084625202203_3933231896061804544_n.png?_nc_cat=0&oh=6455cb346ca429e61fa4dc3b8b494bd0&oe=5B5838A9");
		getPageFields().setKeywords("Rapid Application Development,jwebswing,jwebmp, java,jweb, web,development,framework,ui,rad,urad,bootstrap,jqueryui,jquery,bootstrapdialog");

		setBody(new DisplayBody());
		getBody().addFeature(new PlusAsTabFeature(getBody()));
		getOptions().setLocalStorage(true);

		addAttribute("style", "height:100%;width:100%;");

		SessionHelper.getServerPath();
	}

	@Override
	public AjaxResponse onConnect(AjaxCall call, AjaxResponse response)
	{
		getLocalStorageKey();
		updateVisitorInformation();
		response.addDto("regex", new RegularExpressionsDTO().addDefaults()
		                                                    .addPasswordRegex(6, false, true, true, false));

		response.addComponent(GuiceContext.getInstance(West.class));
		response.addComponent(GuiceContext.getInstance(RightBar.class));
		response.addComponent(GuiceContext.getInstance(TopBar.class));
		response.addComponent(GuiceContext.getInstance(HomePage.class));

		deeplinkScreen(call);

		return response;
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
			log.info("Created a new visitor [" + newVisitor + "]");
			//New user Text
			getInstance(AjaxResponse.class).addReaction(new AjaxResponseReaction("First Use",
			                                                                     " We think this is the first time you've visited this site on this device and browser combination<br/><br/>" + "" + ".<br/></br>" + "You should only see this " + "message" + " once for each " + "browser.<br/> " + "This " + "allows " + "for " + "life-time " + "logins " + "on " + "any " + "" + "" + "application" + "<br/><br/>" + "This is made possible through Local Storage.",
			                                                                     ReactionType.DialogDisplay, AjaxResponseType.Primary));
		}
		catch (JsonProcessingException e)
		{
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
					log.log(Level.SEVERE, "Unable to render page : " + page, e);
				}
			}
		}
	}


}

