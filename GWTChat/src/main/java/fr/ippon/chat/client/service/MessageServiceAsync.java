package fr.ippon.chat.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MessageServiceAsync {
	void addMessage(String message, AsyncCallback<Void> callback);

	void countMessages(AsyncCallback<Integer> callback);
}
