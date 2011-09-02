package fr.ippon.chat.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("message")
public interface MessageService extends RemoteService {
  void addMessage(String message);
  Integer countMessages();
}
