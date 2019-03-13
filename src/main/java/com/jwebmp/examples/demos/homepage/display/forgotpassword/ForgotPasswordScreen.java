package com.jwebmp.examples.demos.homepage.display.forgotpassword;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestParameters;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.core.base.html.HorizontalRule;
import com.jwebmp.core.base.html.Link;
import com.jwebmp.examples.demos.homepage.SessionProperties;
import com.jwebmp.examples.demos.homepage.components.display.DisplayScreen;
import com.jwebmp.examples.demos.homepage.db.dao.VisitorsService;
import com.jwebmp.examples.demos.homepage.display.OuterLayout;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.guicedinjection.GuiceContext;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumb;
import com.jwebmp.plugins.bootstrap4.breadcrumbs.BSBreadCrumbItem;
import com.jwebmp.plugins.bootstrap4.buttons.BSButton;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonOptions;
import com.jwebmp.plugins.bootstrap4.buttons.BSButtonSizeOptions;
import com.jwebmp.plugins.bootstrap4.containers.BSContainer;
import com.jwebmp.plugins.bootstrap4.forms.BSForm;
import com.jwebmp.plugins.fontawesome5.FontAwesome;
import com.jwebmp.plugins.fontawesome5.icons.FontAwesomeIcons;
import com.jwebmp.plugins.jqlayout.events.JQLayoutCloseLayoutDivFeature;
import com.jwebmp.plugins.softhistorychange.SoftHistoryChangeFeature;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.jwebmp.guicedinjection.GuiceContext.*;

public class ForgotPasswordScreen
		extends DisplayScreen<ForgotPasswordScreen>
{
	//Another way of getting a hold of the method parameters
	@Inject
	@RequestParameters
	Map<String, String[]> params;

	BSContainer block = new BSContainer();

	public ForgotPasswordScreen()
	{
		super("Forgot Password");

	}

	@Override
	public BSContainer<?> getContentContainer()
	{
		perform();
		return block;
	}

	@Override
	public BSBreadCrumb<?> getTitleBreadcrumbs()
	{
		BSBreadCrumb crumbs = new BSBreadCrumb();
		crumbs.addBreadCrumb(new BSBreadCrumbItem().setActive(true)
		                                           .setCrumbLink(new Link<>("#").setText("JWebMP")));
		crumbs.addBreadCrumb(new BSBreadCrumbItem<>().setActive(false)
		                                             .setText("Forgot Password"));
		return crumbs;
	}

	public void perform()
	{
		//Check if the link contains the key, if the key is right, and the key is for an unconfirmed user
		if (params != null)
		{
			if (params.containsKey("key"))
			{
				//never show confirms or fails for key based operations, don't log in
				String key = params.get("key")[0];
				confirmUser(key);
			}
			else
			{
				AjaxCall call = GuiceContext.get(AjaxCall.class);
				if (!call.getParameters()
				         .isEmpty())
				{
					if (call.getParameters()
					        .get("key") != null)
					{
						confirmUser(call.getParameters()
						                .get("key")
						                .toString());
					}
				}
			}
			AjaxResponse response = GuiceContext.get(AjaxResponse.class);
			response.getFeatures()
			        .add(new SoftHistoryChangeFeature().setDocumentTitle("JWebMP")
			                                           .setQueryParameters("p=HomePageScreen"));
		}
	}

	public void confirmUser(String confirmKey)
	{
		Optional<Subscribers> subs = new Subscribers().builder()
		                                              .findByConfirmationKey(confirmKey)
		                                              .withUnconfirmedKey()
		                                              .get();
		if (subs.isPresent())
		{
			Subscribers s = subs.get();
			//Key expired, but never let them know anything about that xD
			if (!s.getWarehouseLastUpdatedTimestamp()
			      .plusHours(2L)
			      .isBefore(LocalDateTime.now()))
			{
				SessionProperties properties = GuiceContext.get(SessionProperties.class);

				s.setConfirmed(true);
				s.setWarehouseLastUpdatedTimestamp(LocalDateTime.now());
				s.setConfirmationKey(UUID.randomUUID()
				                         .toString());
				s.setLogInActive(true);
				s.update();
				s.builder()
				 .getEntityManager()
				 .flush();

				properties.setSubscriber(s);
				properties.setLoggedIn(true);

				Visitors v = get(VisitorsService.class).findByUUID(properties.getVisitor())
				                                       .get();
				if (!new SubscriberVisitors().builder()
				                             .findBySubscriberAndVisitorID(s, v)
				                             .getAll()
				                             .isEmpty())
				{
					SubscriberVisitors.create(s, v);
				}

				BSForm<?> form = new BSForm<>();
				form.createPasswordInput("subscriber.password", "New Password", true)
				    .prepend(FontAwesome.icon(FontAwesomeIcons.key));
				form.createPasswordInput("subscriber.confirmPassword", "Confirm Password", true)
				    .prepend(FontAwesome.icon(FontAwesomeIcons.key));
				BSButton submit = form.createSubmitButton(BSButtonOptions.Btn_Outline_Primary, BSButtonSizeOptions.Btn_Lg)
				                      .addClass("mt-2");
				submit.setText("Reset Password");
				submit.addEvent(new SetPasswordEvent(submit));

				block.add(form);

				block.add(HorizontalRule.getInstance());

				OuterLayout layout = GuiceContext.get(OuterLayout.class);
				block.addFeature(new JQLayoutCloseLayoutDivFeature(layout.getWest()));
			}
			else
			{
				block.add(new H3<>("This key has expired. Please request another confirmation email on the JWeb MicroProfile Home Page<br/>"));
				block.add(HorizontalRule.getInstance());
			}
		}
	}
}
