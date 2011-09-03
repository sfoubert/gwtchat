/*
 * Ext GWT 2.2.4 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.ippon.chat.client.model;

import java.io.Serializable;
import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

public class MessageModel extends BaseTreeModel implements Serializable {

	public MessageModel() {

	}

	public MessageModel(Long id, String firstName, String lastName, Date creationDate, 
			String message) {
		set("id", id);
		set("firstName", firstName);
		set("lastName", lastName);
		set("creationDate", creationDate);
		set("message", message);
	}

	public Long getId() {
		return (Long) get("id");
	}

	public String getFirstName() {
		return (String) get("firstName");
	}

	public String getLastName() {
		return (String) get("lastName");
	}
	
	public Date getCreationDate() {
		return (Date) get("creationDate");
	}
	
	public String getMessage() {
		return (String) get("message");
	}

	public String toString() {
		return getId() + getFirstName();
	}

}
