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
                user=(User) session.get(User.class,Integer.parseInt(userId));
                //user = session.byId(User.class).getReference(Integer.valueOf(userId));
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

    public List<User> getUserByNome(String key) {
        Transaction transaction = null;
        List<User> users=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method

            String hql = "FROM User E WHERE E.nome = :nome";
            Query query = session.createQuery(hql);
            query.setParameter("nome",key);
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

    public List<User> getUserByCognome(String key) {
        Transaction transaction = null;
        List<User> users=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method

            String hql = "FROM User E WHERE E.cognome = :cognome";
            Query query = session.createQuery(hql);
            query.setParameter("cognome",key);
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

    public List<User> getUserByData(String key) {
        Transaction transaction = null;
        List<User> users=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method

            String hql = "FROM User E WHERE E.data_di_nascita = :Data";
            Query query = session.createQuery(hql);
            query.setParameter("Data",key);
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
