package fr.ippon.chat.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.ippon.chat.shared.Feed;

/**
 * The async counterpart of <code>FeedService</code>.
 */
public interface FeedServiceAsync {
	
	public void saveFeed(Feed feed, AsyncCallback<Void> callback);
}
