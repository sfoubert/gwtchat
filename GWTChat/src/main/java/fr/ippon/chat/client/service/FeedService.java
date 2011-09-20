package fr.ippon.chat.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.ippon.chat.shared.Feed;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("feed")
public interface FeedService extends RemoteService {
	
	public void saveFeed(Feed feed);
}
