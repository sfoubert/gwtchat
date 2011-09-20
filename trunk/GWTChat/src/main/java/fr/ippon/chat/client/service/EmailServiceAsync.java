package fr.ippon.chat.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>MessageService</code>.
 */
public interface EmailServiceAsync {
	void sendMail(AsyncCallback<Void> callback);

}
