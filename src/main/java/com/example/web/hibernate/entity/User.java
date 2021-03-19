package com.example.web.hibernate.entity;

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
    @Temporal(TemporalType.DATE)
    private Date dataDiNascita;
    @Column
    boolean admin;
    @OneToMany(mappedBy="user",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    List<Prenotazione> prenotazioni;



    public List<Prenotazione> getPrenotazioni() {
       return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
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

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date data_di_nascita) {
        this.dataDiNascita = data_di_nascita;
    }

    public User() {
    }

    public User(String username, String password, String nome, String cognome, Date dataDiNascita, boolean admin) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.admin=admin;
    }
}
