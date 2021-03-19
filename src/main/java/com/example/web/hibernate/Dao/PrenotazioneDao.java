package com.example.web.hibernate.Dao;

import com.example.web.hibernate.entity.Auto;
import com.example.web.hibernate.entity.Prenotazione;
import com.example.web.hibernate.entity.User;
import com.example.web.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PrenotazioneDao {
    public void savePrenotazione(Prenotazione prenotazione) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            if(prenotazione.getId()==0) {
                session.save(prenotazione);
            }else{
                session.update(prenotazione);
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


    public List<Prenotazione> getPrenotazioni() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prenotazione ", Prenotazione.class).list();
        }
    }

    public List<Prenotazione> getPrenotazioniByUserId(int userId) {
        Transaction transaction = null;
        List<Prenotazione> prenotazioni=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            User user= session.get(User.class,userId);
            prenotazioni=user.getPrenotazioni();

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

    public List<Prenotazione> getPrenotazioniByTarga(Auto auto) {
        Transaction transaction = null;
        List<Prenotazione> prenotazioni=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            String hql = "FROM Prenotazione E WHERE E.auto = :autoId";
            Query query = session.createQuery(hql);
            query.setParameter("autoId",auto );
            prenotazioni = query.list();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return prenotazioni;
    }



    public void deletePrenotazione(Prenotazione prenotazione) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
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



            prenotazione= session.get(Prenotazione.class,Integer.parseInt(prenotazioneId));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return prenotazione;
    }

    public List<Prenotazione> getPrenotazioniByField(String key, String researchField) {
        Transaction transaction = null;
        List<Prenotazione> prenotazioni=null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method
            String hql;
            switch (researchField){
                case "Username":hql = "FROM Prenotazione E WHERE E.user.username LIKE :key";break;
                case "Targa":hql = "FROM Prenotazione E WHERE E.auto.targa LIKE :key";break;
                case "Data di inizio":hql = "FROM Prenotazione E WHERE E.dataDiInizio = :key";break;
                case "Data di fine":hql = "FROM Prenotazione E WHERE E.dataDiFine = :key";break;
                default:hql="FROM User E";
            }

            Query query = session.createQuery(hql);
            if(researchField.equals("Data di inizio")||researchField.equals("Data di fine")){
                SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
                Date date=data.parse(key);
                query.setParameter("key", date );
            }else{
                query.setParameter("key", "%" + key + "%");
            }
            prenotazioni = query.list();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return prenotazioni;
    }
}

