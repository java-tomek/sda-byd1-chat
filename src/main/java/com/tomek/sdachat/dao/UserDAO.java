package com.tomek.sdachat.dao;

import com.tomek.sdachat.model.User;
import com.tomek.sdachat.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class UserDAO extends GenericDAO<User> {
    public UserDAO() {
        type = User.class;
    }

    public User getOneUser(String nick, String password) {
        Session session = HibernateUtility.getHibernateSession();
        User user = null;

        try {
            session.beginTransaction();

            String hql = "from User u where upper(u.nick) = upper(:nick) and u.password = :password";
            List users = session.createQuery(hql)
                    .setParameter("nick", nick)
                    .setParameter("password", password)
                    .list();

            if (users.size() > 0)
                user = (User) users.get(0);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.printStackTrace();
        }
        return user;
    }

    public User findOneUserByNick(String nick) {
        Session session = HibernateUtility.getHibernateSession();
        User user = null;

        try {
            session.beginTransaction();

            String hql = "from User u where upper(u.nick) = upper(:nick)";
            List users = session.createQuery(hql)
                    .setParameter("nick", nick)
                    .list();

            if (users.size() > 0)
                user = (User) users.get(0);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.printStackTrace();
        }
        return user;
    }

    public User findOneUserByUuid(String uuid) {
        Session session = HibernateUtility.getHibernateSession();
        User user = null;

        try {
            session.beginTransaction();

            String hql = "from User u where u.uuid = :uuid";
            List users = session.createQuery(hql)
                    .setParameter("uuid", uuid)
                    .list();

            if (users.size() > 0)
                user = (User) users.get(0);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.printStackTrace();
        }
        return user;
    }

    public Boolean isNickTaken(String nick) {
        Session session = HibernateUtility.getHibernateSession();
        Boolean result = false;

        try {
            session.beginTransaction();

            String hql = "from User u where upper(u.nick) = upper(:nick)";
            List users = session.createQuery(hql)
                    .setParameter("nick", nick)
                    .list();

            if (users.size() > 0)
                result = true;

            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }

            e.printStackTrace();
        }
        return result;
    }
}
