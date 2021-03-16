package com.example.web.hibernate.Dao;

import com.example.web.hibernate.entity.Auto;
import com.example.web.hibernate.entity.Prenotazione;
import com.example.web.hibernate.entity.User;
import com.example.web.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.List;

public class PrenotazioneDao {
    public void savePrenotazione(Prenotazione prenotazione) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(prenotazione);
            /*TypedQuery<User> q = session.createQuery("SELECT i FROM User i JOIN FETCH i.prenotazioni where i.id=prenotazione.user_id", User.class);
            q.setFirstResult(0);
            q.setMaxResults(5);
            List<User> items = q.getResultList();*/
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public List<Prenotazione> getPrenotazioni() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prenotazione ", Prenotazione.class).list();
        }
    }

    public List<Prenotazione> getPrenotazioneByUserId(int userId) {
        Transaction transaction = null;
        List<Prenotazione> prenotazioni=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method

            String hql = "FROM Prenotazione E WHERE E.user = :employee_id";
            Query query = session.createQuery(hql);
            query.setParameter("employee_id",userId);
            List prenotazionis = query.list();
            for (Object var :
                    prenotazionis) {
                prenotazioni.add((Prenotazione) var);
            }


            // commit transaction
            transaction.commit();
            return prenotazioni;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return prenotazioni;
    }

    public void updatePrenotazione(Prenotazione prenotazione) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(prenotazione);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePrenotazione(Prenotazione prenotazione) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(prenotazione);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public Prenotazione getPrenotazione(String prenotazioneId) {
        Transaction transaction = null;
        Prenotazione prenotazione=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();



            // Obtain an entity using byId() method
            prenotazione=(Prenotazione) session.get(Prenotazione.class,Integer.parseInt(prenotazioneId));
            //prenotazione = session.byId(Prenotazione.class).getReference(Integer.valueOf(prenotazioneId));
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return prenotazione;
    }
}

