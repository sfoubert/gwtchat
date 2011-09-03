package fr.ippon.chat.server.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.ippon.chat.server.EMF;
import fr.ippon.chat.server.entity.Message;

/**
 * The DAO implementation of Message.
 */
public class MessageDAOImpl extends AbstractDAO implements MessageDAO {
	
	private static final Logger log = Logger.getLogger(MessageDAOImpl.class.getName());

	
	public void addMessage(String message) {
		log.info("addMessage " + message);
		EntityManager em = EMF.get().createEntityManager();
		try {
			Message m = new Message();
			m.setFirstName("Sebastien");
			m.setLastName("F");
			m.setMessage(message);
			em.persist(m);
		} finally {
			em.close();
		}
	}

	public Message getMessage(Long id) {
		log.info("getMessage " + id);
		return getSession().find(Message.class, id);
	}

	public Integer countMessages() {
		log.info("select count(m.id) from Message m ");
		Query query = getSession().createQuery("select count(m.key) from Message m ");
		return (Integer) query.getSingleResult();
	}

	public List<Message> findMessages() {
		log.info("findMessages");
		Query query = getSession().createQuery("select m from Message m ");
		return query.getResultList();

	}

}
