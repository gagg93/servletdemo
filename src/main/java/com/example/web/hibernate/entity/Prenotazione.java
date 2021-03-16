package com.example.web.hibernate.entity;


import javax.persistence.*;

@Entity
@Table
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    Boolean approvata;
    @Column
    String data_di_inizio;
    @Column
    String data_di_fine;
    @ManyToOne(targetEntity = Auto.class)
    Auto auto;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    User user;


    public Prenotazione(String data_di_inizio, String data_di_fine, Auto auto, User user) {
        this.data_di_inizio = data_di_inizio;
        this.data_di_fine = data_di_fine;
        this.auto = auto;
        this.user = user;
    }

    public Prenotazione() {

    }


    public Boolean getApprovata() {

        return approvata;
    }

    public void setApprovata(Boolean approvata) {
        this.approvata = approvata;
    }

    public String getData_di_inizio() {
        return data_di_inizio;
    }

    public void setData_di_inizio(String data_di_inizio) {
        this.data_di_inizio = data_di_inizio;
    }

    public String getData_di_fine() {
        return data_di_fine;
    }

    public void setData_di_fine(String data_di_fine) {
        this.data_di_fine = data_di_fine;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}