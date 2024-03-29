package fr.ippon.chat.client;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import fr.ippon.chat.client.mvc.AppController;
import fr.ippon.chat.client.mvc.MessageController;
import fr.ippon.chat.client.service.EmailService;
import fr.ippon.chat.client.service.EmailServiceAsync;
import fr.ippon.chat.client.service.MessageService;
import fr.ippon.chat.client.service.MessageServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTChat implements EntryPoint {

	public static final String MESSAGE_SERVICE = "messageService";
	public static final String EMAIL_SERVICE = "emailService";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// GXT.setDefaultTheme(Theme.GRAY, true);

		MessageServiceAsync messageService = (MessageServiceAsync) GWT
				.create(MessageService.class);
		EmailServiceAsync emailService = (EmailServiceAsync) GWT
				.create(EmailService.class);
		
		Registry.register(MESSAGE_SERVICE, messageService);
		Registry.register(EMAIL_SERVICE, emailService);

		Dispatcher dispatcher = Dispatcher.get();
		dispatcher.addController(new AppController());
		dispatcher.addController(new MessageController());

		dispatcher.dispatch(AppEvents.Login);

		GXT.hideLoadingPanel("loading");

	}

}
