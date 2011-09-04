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
import java.util.Map;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.ippon.chat.client.GWTChat;
import fr.ippon.chat.client.model.MessageModel;
import fr.ippon.chat.client.service.MessageServiceAsync;

public class MessageListPanel extends ContentPanel {
	
	private static final int PAGINATION = 10;
	private ColumnModel cm;
	ListStore<MessageModel> store;
	
	MessageServiceAsync messageService = (MessageServiceAsync) Registry
			.get(GWTChat.MESSAGE_SERVICE);
	
    RpcProxy<PagingLoadResult<MessageModel>> proxy = new RpcProxy<PagingLoadResult<MessageModel>>() {  
        @Override  
        public void load(Object loadConfig, AsyncCallback<PagingLoadResult<MessageModel>> callback) {  
        	messageService.findMessages((PagingLoadConfig) loadConfig, callback);  
        }  
      };
	
	public MessageListPanel() {
		super();
		initUI();
	}

	protected void initUI() {
		setLayout(new FitLayout());
		getAriaSupport().setPresentation(true);

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		ColumnConfig column = new ColumnConfig();

		XTemplate tpl = XTemplate.create("<p><b>Detail:</b> {message}</p>");
	    RowExpander expander = new RowExpander();
	    expander.setTemplate(tpl);
	    configs.add(expander);
		
		column = new ColumnConfig("firstName", "Prenom", 100);
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);
		
		column = new ColumnConfig("creationDate", "Date", 120);
		column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy HH:mm:ss"));
		column.setAlignment(HorizontalAlignment.CENTER);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setId("message");
		column.setHeader("Message");
		column.setToolTip("message");
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);
		
		// loader  
	    final PagingLoader<PagingLoadResult<ModelData>> loader = new BasePagingLoader<PagingLoadResult<ModelData>>(  
	        proxy);
	    loader.setRemoteSort(true);
		
	    store = new ListStore<MessageModel>(loader);
	    
	    final PagingToolBar toolBar = new PagingToolBar(PAGINATION);
	    toolBar.bind(loader);
	    
//		store = new ListStore<MessageModel>();
		// store.add(TestData.getMessageModels());

		cm = new ColumnModel(configs);
		
		setBodyBorder(false);
		setHeaderVisible(false);
		setButtonAlign(HorizontalAlignment.CENTER);
		setLayout(new FitLayout());

		final Grid<MessageModel> grid = new Grid<MessageModel>(store, cm);
	    grid.setStateId("pagingGridExample");
	    grid.addPlugin(expander); 
	    grid.setStateful(false);
	    grid.addListener(Events.Attach, new Listener<GridEvent<MessageModel>>() {
	      public void handleEvent(GridEvent<MessageModel> be) {
	        PagingLoadConfig config = new BasePagingLoadConfig();
	        config.setOffset(0);
	        config.setLimit(PAGINATION);
	        
	        Map<String, Object> state = grid.getState();
	        if (state.containsKey("offset")) {
	          int offset = (Integer)state.get("offset");
	          int limit = (Integer)state.get("limit");
	          config.setOffset(offset);
	          config.setLimit(limit);
	        }
	        if (state.containsKey("sortField")) {
	          config.setSortField((String)state.get("sortField"));
	          config.setSortDir(SortDir.valueOf((String)state.get("sortDir")));
	        }
	        loader.load(config);
	      }
	    });
	    grid.setLoadMask(true);
	    grid.setBorders(true);
	    grid.setAutoExpandColumn("message");
		
	    ContentPanel panel = new ContentPanel();
	    panel.setFrame(true);
	    panel.setCollapsible(true);
	    panel.setAnimCollapse(false);
	    panel.setHeading("Messages"); 
	    panel.setLayout(new FitLayout());
	    panel.add(grid);
	    panel.setBottomComponent(toolBar);
	    grid.getAriaSupport().setLabelledBy(panel.getId());
	    add(panel);

	}

	public void loadMessage() {
		store.getLoader().load();
	}
	
//	public void loadMessage(List<MessageModel> messageModelList) {
//	store.removeAll();
//	store.add(messageModelList);
//}
}