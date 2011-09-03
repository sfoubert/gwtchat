package fr.ippon.chat.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.ippon.chat.shared.MessageSZ;

/**
 * The async counterpart of <code>MessageService</code>.
 */
public interface MessageServiceAsync {
	void addMessage(String message, AsyncCallback<Void> callback);

	void getMessage(Long id, AsyncCallback<MessageSZ> callback);

	void countMessages(AsyncCallback<Integer> callback);

	void findMessages(AsyncCallback<List<MessageSZ>> callback);
}
