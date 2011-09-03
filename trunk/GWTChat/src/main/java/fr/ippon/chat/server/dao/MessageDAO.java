package fr.ippon.chat.server.dao;

import java.util.List;

import fr.ippon.chat.server.entity.Message;

public interface MessageDAO {
	
	void addMessage(String message);

	Message getMessage(Long id);

	Integer countMessages();

	List<Message> findMessages();
	
	List<Message> findMessages(int offset, int limit);
}
