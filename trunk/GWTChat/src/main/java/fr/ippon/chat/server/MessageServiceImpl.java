package fr.ippon.chat.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.ippon.chat.client.service.MessageService;
import fr.ippon.chat.server.dao.MessageDAO;
import fr.ippon.chat.server.dao.MessageDAOImpl;
import fr.ippon.chat.server.entity.Message;
import fr.ippon.chat.server.util.SZHelper;
import fr.ippon.chat.shared.MessageSZ;

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

	public MessageSZ getMessage(Long id) {
		Message message = messageDAO.getMessage(id);
		return SZHelper.convertToSZ(message);
	}

	public Integer countMessages() {
		return messageDAO.countMessages();
	}

	public List<MessageSZ> findMessages() {
		List<Message> messageList = messageDAO.findMessages();
		return SZHelper.convertToListSZ(messageList);
	}

}
