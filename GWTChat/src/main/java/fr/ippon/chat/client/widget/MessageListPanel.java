/*
 * Ext GWT 2.2.4 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.ippon.chat.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

import fr.ippon.chat.client.model.MessageModel;

public class MessageListPanel extends ContentPanel {
	private ColumnModel cm;
	ListStore<MessageModel> store;

	public MessageListPanel() {
		super();
		initUI();
	}

	protected void initUI() {
		setLayout(new FitLayout());
		getAriaSupport().setPresentation(true);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("id");
		column.setHeader("Id");
		column.setWidth(20);
		column.setRowHeader(true);
		configs.add(column);

		column = new ColumnConfig("firstName", "Prenom", 100);
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setId("message");
		column.setHeader("Message");
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);

		store = new ListStore<MessageModel>();
		// store.add(TestData.getMessageModels());

		cm = new ColumnModel(configs);

		
		setBodyBorder(true);
		setHeaderVisible(false);
		setButtonAlign(HorizontalAlignment.CENTER);
		setLayout(new FitLayout());

		final Grid<MessageModel> grid = new Grid<MessageModel>(store, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setAutoExpandColumn("message");
		grid.setBorders(false);
		grid.setStripeRows(true);
		grid.setColumnLines(true);
		grid.setColumnReordering(true);
		grid.getAriaSupport().setLabelledBy(getHeader().getId() + "-label");
		add(grid);

	}

	public void loadMessage(List<MessageModel> messageModelList) {
		store.removeAll();
		store.add(messageModelList);
	}
}