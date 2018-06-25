package com.jwebmp.examples.demos.homepage;

import com.google.inject.Inject;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.base.ajax.AjaxResponseReaction;
import com.jwebmp.base.ajax.ReactionType;
import com.jwebmp.plugins.atmosphere.AtmosphereWSReceiver;
import com.jwebmp.plugins.atmosphere.abstraction.AtmosphereAdapter;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;

@SuppressWarnings("unused")
public class AtmospherePushManager
		extends AtmosphereAdapter
{
	@Inject
	private BroadcasterFactory broadcasterFactory;

	@Override
	public AjaxResponse onReady(AtmosphereResource r)
	{
		Broadcaster broadcaster = broadcasterFactory.lookup("Everyone");
		if (broadcaster == null)
		{
			broadcaster = broadcasterFactory.get("Everyone");
		}
		broadcaster.addAtmosphereResource(r);
		r.write(
				new AjaxResponse<>().addReaction(
						new AjaxResponseReaction("You're Connected!", "You have been added to the Everyone chat group", ReactionType.DialogDisplay))
				                    .toString()
		       );
		return super.onReady(r);
	}

	@Override
	public void onDisconnect(AtmosphereResourceEvent event)
	{
		Broadcaster broadcaster = broadcasterFactory.lookup("Everyone");
		if (broadcaster == null)
		{
			broadcaster = broadcasterFactory.get("Everyone");
		}
		broadcaster.removeAtmosphereResource(event.getResource());
		super.onDisconnect(event);
	}

	@Override
	public AjaxResponse onMessage(AtmosphereWSReceiver message)
	{
		return super.onMessage(message);
	}

	@Override
	public AjaxResponse onResume(AtmosphereResourceEvent r)
	{
		return super.onResume(r);
	}
}

