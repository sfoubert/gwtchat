/*
 * Ext GWT 2.2.4 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.ippon.chat.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.ippon.chat.client.AppEvents;
import fr.ippon.chat.client.GWTChat;
import fr.ippon.chat.client.service.MessageServiceAsync;

public class AppController extends Controller {

	private AppView appView;

	 private MessageServiceAsync messageService;

	public AppController() {
		registerEventTypes(AppEvents.Init);
		registerEventTypes(AppEvents.Login);
		registerEventTypes(AppEvents.Error);
		registerEventTypes(AppEvents.AddMessage);
	}

	public void handleEvent(AppEvent event) {
		EventType type = event.getType();
		if (type == AppEvents.Init) {
			onInit(event);
		} else if (type == AppEvents.Login) {
			onLogin(event);
		} else if (type == AppEvents.Error) {
			onError(event);
		} else if (type == AppEvents.AddMessage) {
			onAddMessage(event);
		}
	}

	public void initialize() {
		appView = new AppView(this);
	}

	protected void onError(AppEvent ae) {
		System.out.println("error: " + ae.<Object> getData());
	}

	private void onInit(AppEvent event) {
		forwardToView(appView, event);
		messageService = (MessageServiceAsync) Registry.get(GWTChat.MESSAGE_SERVICE);

	}

	private void onLogin(AppEvent event) {
		forwardToView(appView, event);
	}

	private void onAddMessage(AppEvent event) {
		forwardToView(appView, event);
		messageService = (MessageServiceAsync) Registry.get(GWTChat.MESSAGE_SERVICE);
		
		String message = event.getData();
		messageService.addMessage(message, new AsyncCallback<Void>() {
			
			public void onSuccess(Void result) {
				MessageBox.info("Message", "Message ajoute", null);
			}
			
			public void onFailure(Throwable caught) {
				Dispatcher.forwardEvent(AppEvents.Error, caught);
				
			}
		});
	}

}
