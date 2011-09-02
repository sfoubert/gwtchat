package fr.ippon.chat.server.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;

import fr.ippon.chat.server.entity.Message;

public interface MessageDAO {
	
	void addMessage(String message);

	Message getMessage(Key key);

	Integer countMessages();

	List<Message> findMessages();
}
