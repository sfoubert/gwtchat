package fr.ippon.chat.client;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import fr.ippon.chat.client.mvc.AppController;
import fr.ippon.chat.client.service.MessageService;
import fr.ippon.chat.client.service.MessageServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTChat implements EntryPoint {

	public static final String MESSAGE_SERVICE = "messageService";
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// GXT.setDefaultTheme(Theme.GRAY, true);
		// MessageBox.info("Message", "Hello World!!", null);

	    MessageServiceAsync messageService = (MessageServiceAsync) GWT.create(MessageService.class);
	    Registry.register(MESSAGE_SERVICE, messageService);
	    
		Dispatcher dispatcher = Dispatcher.get();
		dispatcher.addController(new AppController());
		// dispatcher.addController(new MailController());
		// dispatcher.addController(new TaskController());
		// dispatcher.addController(new ContactController());

		dispatcher.dispatch(AppEvents.Login);

		GXT.hideLoadingPanel("loading");

	}

}
