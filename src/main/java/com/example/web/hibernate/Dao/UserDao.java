package com.example.web.hibernate.Dao;

import com.example.web.hibernate.entity.User;
import com.example.web.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDao {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            /*List<User> users = this.getUsers();
            List<String> usernames=null;
            if(users!=null) {
                for (User var :
                        users) {
                    usernames.add(var.getUsername());
                }
                if (!usernames.contains(user.getUsername())) {
                    session.save(user);
                }
            }*/
            session.save(user);
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

    public User getUserByUsername(String userName) {
        Transaction transaction = null;
        User user=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method

            String hql = "FROM User E WHERE E.username = :employee_id";
            Query query = session.createQuery(hql);
            query.setParameter("employee_id",userName);
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

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
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



                // Obtain an entity using byId() method
                user = session.byId(User.class).getReference(Integer.valueOf(userId));
                System.out.println(user.getUsername());
                System.out.println(user.getData_di_nascita());
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
}
