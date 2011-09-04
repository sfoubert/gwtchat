/*
 * Ext GWT 2.2.4 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.ippon.chat.client.mvc;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.ui.RootPanel;

import fr.ippon.chat.client.AppEvents;
import fr.ippon.chat.client.widget.LoginDialog;
import fr.ippon.chat.client.widget.MessageFormPanel;
import fr.ippon.chat.client.widget.MessageListPanel;

public class AppView extends View {

	public static final String VIEWPORT = "viewport";
	
	public static final String NORTH_PANEL = "north";
	public static final String WEST_PANEL = "west";
	public static final String CENTER_PANEL = "center";
	public static final String SOUTH_PANEL = "south";

	private Viewport viewport;
	private ContentPanel northPanel;
	private ContentPanel westPanel;
	private ContentPanel centerPanel;
	private ContentPanel southPanel;

	public AppView(Controller controller) {
		super(controller);
	}

	protected void initialize() {
		LoginDialog dialog = new LoginDialog();
		dialog.setClosable(false);
		dialog.addListener(Events.Hide, new Listener<WindowEvent>() {
			public void handleEvent(WindowEvent be) {
				Dispatcher.forwardEvent(AppEvents.Init);
			}
		});
		dialog.show();
	}

	private void initUI() {
		viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
//		viewport.setStyleAttribute("padding", "10px");
		viewport.setStyleAttribute("background-color", "white");
		
		createNorth();
		createWest();
		createCenter();
		createSouth();

		// registry serves as a global context
		Registry.register(VIEWPORT, viewport);
		Registry.register(NORTH_PANEL, northPanel);
		Registry.register(WEST_PANEL, westPanel);
		Registry.register(CENTER_PANEL, centerPanel);
		Registry.register(SOUTH_PANEL, southPanel);
		
		RootPanel.get().add(viewport);
	}

	private void createNorth() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='demo-header' class='x-small-editor'><div id='demo-theme'></div><div id=demo-title>GWT Chat</div></div>");
		HtmlContainer htmlContainer = new HtmlContainer(sb.toString());
		htmlContainer.setStateful(false);
		northPanel = new ContentPanel();
		northPanel.add(htmlContainer);
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH,
				100);
		northData.setCollapsible(true);
		northData.setFloatable(true);
		northData.setHideCollapseTool(true);
		northData.setSplit(true);
		northData.setMargins(new Margins(0, 20, 5, 0));

		viewport.add(northPanel, northData);
	}

	private void createWest() {
		westPanel = new ContentPanel();
		westPanel.setBodyBorder(true);
		westPanel.setLayout(new AccordionLayout());
		westPanel.setLayoutOnChange(true);
		westPanel.setHeading("GXT Chat");

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 150);
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setMargins(new Margins(0, 5, 0, 0));

		viewport.add(westPanel, westData);
	}

	private void createCenter() {
		centerPanel = new MessageListPanel();

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
		data.setMargins(new Margins(0, 20, 0, 0));

		viewport.add(centerPanel, data);

	}
	
	private void createSouth() {
		southPanel = new MessageFormPanel();

	    BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 150);
	    southData.setSplit(true);
	    southData.setCollapsible(true);
	    southData.setFloatable(true);
	    southData.setMargins(new Margins(5, 20, 0, 0));

		viewport.add(southPanel, southData);
	}

	protected void handleEvent(AppEvent event) {
		if (event.getType() == AppEvents.Init) {
			initUI();
		}
	}


}
