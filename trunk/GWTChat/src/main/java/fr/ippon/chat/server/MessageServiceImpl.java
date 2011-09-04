package fr.ippon.chat.server;

import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.ippon.chat.client.model.MessageModel;
import fr.ippon.chat.client.service.MessageService;
import fr.ippon.chat.client.util.ModelHelper;
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

	public void addMessage(String firstName, String message) {
		messageDAO.addMessage(firstName, message);
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
	
	public PagingLoadResult<MessageModel> findMessages(PagingLoadConfig loadConfig) {
		
		Integer nbMessages = messageDAO.countMessages();
		
		List<Message> messageList = messageDAO.findMessages(
				loadConfig.getOffset(), loadConfig.getLimit());
		List<MessageSZ> messageSZList = SZHelper.convertToListSZ(messageList);
		return new BasePagingLoadResult<MessageModel>(ModelHelper.convertToListModel(messageSZList), loadConfig.getOffset() , nbMessages);
	}

}
