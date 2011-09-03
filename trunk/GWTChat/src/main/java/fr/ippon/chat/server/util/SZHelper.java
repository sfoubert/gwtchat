package fr.ippon.chat.server.util;

import java.util.ArrayList;
import java.util.List;

import fr.ippon.chat.server.entity.Message;
import fr.ippon.chat.shared.MessageSZ;

public class SZHelper {

	public static MessageSZ convertToSZ(Message message){
		MessageSZ messageSZ = new MessageSZ();
		messageSZ.setId(message.getKey().getId());
		messageSZ.setFirstName(message.getFirstName());
		messageSZ.setLastName(message.getLastName());
		messageSZ.setMessage(message.getMessage());
		return messageSZ;
	}
	
	public static Message convertToEntity(MessageSZ messageSZ){
		Message message = new Message();
		message.setFirstName(messageSZ.getFirstName());
		message.setLastName(messageSZ.getLastName());
		message.setMessage(messageSZ.getMessage());
		return message;
	}
	
	public static List<MessageSZ> convertToListSZ(List<Message> messageList){
		List<MessageSZ> messageSZList = new ArrayList<MessageSZ>();
		for(Message message : messageList){
			messageSZList.add(convertToSZ(message));
		}
		return messageSZList;
	}
}
