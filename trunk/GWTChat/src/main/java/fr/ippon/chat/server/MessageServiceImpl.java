package fr.ippon.chat.server;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.ippon.chat.client.service.MessageService;
import fr.ippon.chat.server.dao.MessageDAO;
import fr.ippon.chat.server.dao.MessageDAOImpl;
import fr.ippon.chat.server.entity.Message;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MessageServiceImpl extends RemoteServiceServlet implements
		MessageService {

	MessageDAO messageDAO;
	
	public MessageServiceImpl() {
		super();
		messageDAO = new MessageDAOImpl();
	}

	public void addMessage(String message) {
		messageDAO.addMessage(message);
	}

	public Message getMessage(Key key) {
		return messageDAO.getMessage(key);
	}

	public Integer countMessages() {
		return messageDAO.countMessages();
	}

	public List<Message> findMessages() {
		return messageDAO.findMessages();
		//TODO Serialize MessageSZ
	}

}
