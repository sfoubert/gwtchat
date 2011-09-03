/*
 * Ext GWT 2.2.4 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.ippon.chat.client.mvc;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

import fr.ippon.chat.client.AppEvents;

public class MessageController extends Controller {

	private MessageView messageView;

	public MessageController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.LoadMessage);
	}

	@Override
	public void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.Init) {
			forwardToView(messageView, event);
		} else if (type == AppEvents.LoadMessage) {
			onLoadMessage(event);
		}
	}

	private void onLoadMessage(final AppEvent event) {
		forwardToView(messageView, event);
	}

	public void initialize() {
		messageView = new MessageView(this);
	}

}
