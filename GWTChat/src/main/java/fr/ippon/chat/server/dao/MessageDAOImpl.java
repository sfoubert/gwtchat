package fr.ippon.chat.server.dao;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.ippon.chat.server.EMF;
import fr.ippon.chat.server.entity.Message;

/**
 * The DAO implementation of Message.
 */
public class MessageDAOImpl extends AbstractDAO implements MessageDAO {
	
	private static final Logger log = Logger.getLogger(MessageDAOImpl.class.getName());

	
	public void addMessage(String firstName, String message) {
		log.info("addMessage " + message);
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Message m = new Message();
			m.setFirstName(firstName);
			m.setLastName("F");
			m.setCreationDate(Calendar.getInstance().getTime());
			m.setMessage(message);
			em.persist(m);
			tx.commit();
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
		Query query = getSession().createQuery("select m from Message m order by m.creationDate desc");
		return query.getResultList();
	}
	
	public List<Message> findMessages(int offset, int limit) {
		log.info("findMessages " + offset + " / " + limit);
		Query query = getSession().createQuery("select m from Message m order by m.creationDate desc");
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();

	}

}
