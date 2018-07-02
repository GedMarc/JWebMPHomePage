package com.jwebmp.examples.demos.homepage;

import com.google.inject.Inject;
import com.jwebmp.base.ajax.AjaxResponse;
import com.jwebmp.plugins.atmosphere.AtmosphereWSReceiver;
import com.jwebmp.plugins.atmosphere.abstraction.AtmosphereAdapter;
import com.jwebmp.plugins.toastr.ToastrFeature;
import com.jwebmp.plugins.toastr.ToastrType;
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
		//Use a response object
		AjaxResponse<?> response = new AjaxResponse();
		//Send the connected only to the resource
		ToastrFeature toastr = new ToastrFeature(ToastrType.Success, "Youre Connected", "You have been connected to the Everyone group");
		toastr.getOptions()
		      .setProgressBar(true)
		      .setEscapeHtml(true)
		      .setPreventDuplicates(true);
		response.getFeatures()
		        .add(toastr);
		r.write(response.toString());

		//Send out a notice to everyone that a new user connected
		Broadcaster broadcaster = broadcasterFactory.lookup("Everyone");
		if (broadcaster == null)
		{
			broadcaster = broadcasterFactory.get("Everyone");
		}
		response = new AjaxResponse();
		//Send the connected only to the resource
		toastr = new ToastrFeature(ToastrType.Success, "User Joined", "Another user has connected to the site!");
		toastr.getOptions()
		      .setProgressBar(true)
		      .setEscapeHtml(true)
		      .setPreventDuplicates(true);
		response.getFeatures()
		        .add(toastr);
		broadcaster.broadcast(response.toString());

		broadcaster.addAtmosphereResource(r);
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

