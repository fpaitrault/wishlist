package org.fpaitrault.dao;

import org.fpaitrault.dao.GenericDAO;
import org.fpaitrault.mdl.Session;
import org.fpaitrault.mdl.User;
import org.hibernate.Transaction;

public class SessionDAO extends GenericDAO<Session> {
    public SessionDAO() {
    	super(Session.class);
    }

    public final void deleteSessionByUUID(final String uuid) {
        synchronized (getSession()) {
            Session session = null;
            Transaction transact = getSession().beginTransaction();
            session = (Session) getSession().createQuery("from "+getType().getName()+" where uuid=:uuid")
                    .setString("uuid", uuid)
                    .uniqueResult();
            getSession().delete(session);
            transact.commit();
        }
    }

    public final User readUserByUUID(final String uuid) {
        synchronized (getSession()) {
            Session session = null;
            session = (Session) getSession().createQuery("from "+getType().getName()+" where uuid=:uuid")
                    .setString("uuid", uuid)
                    .uniqueResult();
            if (session != null) {
                return session.getUser();
            } else {
                return null;
            }
        }
    }
}
