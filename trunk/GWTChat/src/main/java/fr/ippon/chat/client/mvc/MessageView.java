/*
 * Ext GWT 2.2.4 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.ippon.chat.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;

import fr.ippon.chat.client.AppEvents;
import fr.ippon.chat.client.GWTChat;
import fr.ippon.chat.client.service.MessageServiceAsync;
import fr.ippon.chat.client.widget.MessageListPanel;

public class MessageView extends View {

	private MessageServiceAsync messageService;

	MessageListPanel messageListPanel;


	public MessageView(Controller controller) {
		super(controller);
	}

	protected void initialize() {
		messageService = (MessageServiceAsync) Registry
				.get(GWTChat.MESSAGE_SERVICE);
	}

	protected void initUI() {
		ContentPanel center = (ContentPanel) Registry.get(AppView.CENTER_PANEL);
		messageListPanel = (MessageListPanel)center;
//		messageListPanel = new MessageListPanel();
//		center.removeAll();
//		center.add(messageListPanel);
//		center.layout();
	}

	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
//			fireEvent(AppEvents.LoadMessage);
		} else if (event.getType() == AppEvents.LoadMessage) {
			loadMessage();
		}
	}
	
	private void loadMessage() {
		messageListPanel.loadMessage();
	}
}
