package fr.ippon.chat.client.util;

import java.util.ArrayList;
import java.util.List;

import fr.ippon.chat.client.model.MessageModel;
import fr.ippon.chat.shared.MessageSZ;

public class ModelHelper {

	public static MessageModel convertToModel(MessageSZ messageSZ){
		return new MessageModel(messageSZ.getId(), messageSZ.getFirstName(), messageSZ.getLastName(), messageSZ.getMessage());
	}
	
	public static List<MessageModel> convertToListModel(List<MessageSZ> messageSZList){
		List<MessageModel> messageModelList = new ArrayList<MessageModel>();
		for(MessageSZ messageSZ : messageSZList){
			messageModelList.add(convertToModel(messageSZ));
		}
		return messageModelList;
	}
}
