package fr.ippon.chat.server.dao;

import javax.persistence.EntityManager;

import fr.ippon.chat.server.EMF;

public abstract class AbstractDAO {

	private EntityManager em;

	protected EntityManager getSession() {
		if (em == null) {
			em = EMF.get().createEntityManager();
		}
		return em;

	}
}
