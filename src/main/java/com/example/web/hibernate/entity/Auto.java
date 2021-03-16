package com.example.web.hibernate.entity;

import javax.persistence.*;

@Entity
@Table
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String casaCostruttrice;
    @Column
    String modello;
    @Column
    int annoImmatricolazione;
    @Column
    String targa;
    @Column
    String tipologia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCasaCostruttrice() {
        return casaCostruttrice;
    }

    public void setCasaCostruttrice(String casaCostruttrice) {
        this.casaCostruttrice = casaCostruttrice;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public void setAnnoImmatricolazione(int annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Auto() {
    }

    public Auto(String casaCostruttrice, String modello, int annoImmatricolazione, String targa, String tipologia) {
        this.casaCostruttrice = casaCostruttrice;
        this.modello = modello;
        this.annoImmatricolazione = annoImmatricolazione;
        this.targa = targa;
        this.tipologia = tipologia;
    }
}
