package com.example.web.hibernate.Dao;

import com.example.web.hibernate.entity.User;
import com.example.web.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDao {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            if (user.getId()==0){
                session.save(user);
            }else{
                session.update(user);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<User> getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    public User getUserByUsername(String username) {
        Transaction transaction = null;
        User user=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method

            String hql = "FROM User E WHERE E.username = :user_id";
            Query query = session.createQuery(hql);
            query.setParameter("user_id",username);
            List results = query.list();
            if(!results.isEmpty()) {
                user = (User) results.get(0);
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }


    public void deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.delete(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public User getUser(String userId) {
            Transaction transaction = null;
            User user=null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // start a transaction
                transaction = session.beginTransaction();

                user=(User) session.get(User.class,Integer.parseInt(userId));
                System.out.println(user.getUsername());
                System.out.println(user.getDataDiNascita());
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return user;
        }

    public List<User> getUserByField(String key, String researchField) {
        Transaction transaction = null;
        List<User> users=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql;
            switch (researchField){
                case "Username":hql = "FROM User E WHERE E.username LIKE :key";break;
                case "Nome":hql = "FROM User E WHERE E.nome LIKE :key";break;
                case "Cognome":hql = "FROM User E WHERE E.cognome LIKE :key";break;
                case "Data di nascita":{

                    hql = "FROM User E WHERE E.dataDiNascita = :key";
                };break;
                default:hql="FROM User E";
            }

            Query query = session.createQuery(hql);
            if(researchField.equals("Data di nascita")){
                SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
                Date date=data.parse(key);
                query.setParameter("key", date );
            }else{
                query.setParameter("key", "%" + key + "%");
            }
            users = query.list();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

}
