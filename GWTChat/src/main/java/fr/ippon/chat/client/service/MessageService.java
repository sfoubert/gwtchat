package fr.ippon.chat.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.ippon.chat.client.model.MessageModel;
import fr.ippon.chat.shared.MessageSZ;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("message")
public interface MessageService extends RemoteService {
	void addMessage(String message);

	MessageSZ getMessage(Long id);

	Integer countMessages();

	List<MessageSZ> findMessages();
	
	PagingLoadResult<MessageModel> findMessages(PagingLoadConfig loadConfig);
}
