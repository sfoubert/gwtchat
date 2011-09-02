/*
 * Ext GWT 2.2.4 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.ippon.chat.client.widget;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

import fr.ippon.chat.client.AppEvents;

public class MessageFormPanel extends FormPanel {

	protected TextArea messageArea;
	protected Button submit;

	public MessageFormPanel() {
		FormLayout layout = new FormLayout();
		layout.setLabelWidth(90);
		setLayout(layout);

		setButtonAlign(HorizontalAlignment.RIGHT);
		setIcon(IconHelper.createStyle("user"));
		setBodyBorder(true);

		messageArea = new TextArea();
		messageArea.setPreventScrollbars(true);
		messageArea.setAllowBlank(false);
		messageArea.setFieldLabel("Message");
		add(messageArea, new FormData("100%"));

		submit = new Button("Envoyer");

		submit.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				onSubmit();
			}
		});

		addButton(submit);

	}

	private void onSubmit() {
		EventType event = AppEvents.AddMessage;
		Dispatcher.forwardEvent(event, messageArea.getValue());

	}

}
