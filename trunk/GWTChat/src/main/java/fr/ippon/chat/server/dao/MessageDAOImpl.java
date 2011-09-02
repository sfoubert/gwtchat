package fr.ippon.chat.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;

import fr.ippon.chat.server.EMF;
import fr.ippon.chat.server.entity.Message;

/**
 * The DAO implementation of Message.
 */
public class MessageDAOImpl implements MessageDAO {

	   private EntityManager getSession() {
		      return EMF.get().createEntityManager();
		   }
	
	public void addMessage(String message) {
		System.out.println("addMessage " + message);
		try {
			Message m = new Message();
			m.setMessage("Coucou");
			m.setFirstName("Sebastien");
			getSession().persist(m);
		} finally {
			getSession().close();
		}
	}

	public Message getMessage(Key key) {
		System.out.println("getMessage " + key);
		EntityManager em = EMF.get().createEntityManager();
		return getSession().find(Message.class, key);
	}

	public Integer countMessages() {
		System.out.println("select count(m.key) from Message m ");
		EntityManager em = EMF.get().createEntityManager();
		Query query = getSession().createQuery("select count(m.key) from Message m ");
		return (Integer) query.getSingleResult();
	}

	public List<Message> findMessages() {
		System.out.println("findMessages");
		EntityManager em = EMF.get().createEntityManager();
		Query query = getSession().createQuery("select m.* from Message m ");
		return query.getResultList();

	}

}
