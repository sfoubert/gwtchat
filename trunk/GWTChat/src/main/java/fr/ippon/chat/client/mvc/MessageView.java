/*
 * Ext GWT 2.2.4 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.ippon.chat.client.mvc;

import java.util.List;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.ippon.chat.client.AppEvents;
import fr.ippon.chat.client.GWTChat;
import fr.ippon.chat.client.service.MessageServiceAsync;
import fr.ippon.chat.client.util.ModelHelper;
import fr.ippon.chat.client.widget.MessageListPanel;
import fr.ippon.chat.shared.MessageSZ;

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
		messageListPanel = new MessageListPanel();
		center.removeAll();
		center.add(messageListPanel);
		center.layout();
	}

	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
			fireEvent(AppEvents.LoadMessage);
		} else if (event.getType() == AppEvents.LoadMessage) {

			loadMessage();
		}
	}

	private void loadMessage() {
		
		messageService.findMessages(new AsyncCallback<List<MessageSZ>>() {

			public void onSuccess(List<MessageSZ> messageSZList) {
				messageListPanel.loadMessage(ModelHelper.convertToListModel(messageSZList));
			}

			public void onFailure(Throwable caught) {
				Dispatcher.forwardEvent(AppEvents.Error, caught);

			}
		});

	}
}
