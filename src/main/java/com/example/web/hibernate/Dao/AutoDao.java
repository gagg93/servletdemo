package com.example.web.hibernate.Dao;

import com.example.web.hibernate.entity.Auto;

import com.example.web.hibernate.entity.User;
import com.example.web.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AutoDao {
    public void saveAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(auto);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<Auto> getAutos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Auto", Auto.class).list();
        }
    }

    public Auto getAutoByTarga(String targa) {
        Transaction transaction = null;
        Auto auto=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method

            String hql = "FROM Auto E WHERE E.targa = :targa";
            Query query = session.createQuery(hql);
            query.setParameter("targa",targa);
            List results = query.list();
            if(!results.isEmpty()) {
                auto=(Auto) results.get(0);
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return auto;
    }

    public void updateAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(auto);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAuto(Auto auto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(auto);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public Auto getAuto(String autoId) {
        Transaction transaction = null;
        Auto auto=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();



            // Obtain an entity using byId() method
            auto=(Auto) session.get(Auto.class,Integer.parseInt(autoId));
            //auto = session.byId(Auto.class).getReference(Integer.parseInt(autoId));
            System.out.println(auto);
            // commit transaction
            transaction.commit();
            return auto;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
            e.printStackTrace();
            return null;
        }

    }
}