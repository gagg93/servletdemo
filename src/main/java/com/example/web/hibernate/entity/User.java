package com.example.web.hibernate.entity;

import com.example.web.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.*;


@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private String data_di_nascita;
    @Column
    boolean admin;
    @OneToMany
    @JoinColumn(name = "prenotazioni")
    Set<Prenotazione> prenotazioni;



    public Set<Prenotazione> getPrenotazioni() {
       return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(String data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public User() {
    }

    public User(String username, String password, String nome, String cognome, String data_di_nascita,boolean admin) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.data_di_nascita = data_di_nascita;
        this.admin=admin;
    }
}
