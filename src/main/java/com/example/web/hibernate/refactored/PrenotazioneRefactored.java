package com.example.web.hibernate.refactored;


import com.example.web.hibernate.entity.Prenotazione;

import java.util.Date;

public class PrenotazioneRefactored {
    private int id;
    private Boolean approvata;
    private Date dataDiInizio;
    private Date dataDiFine;
    private String targa;
    private String username;

    public PrenotazioneRefactored(@org.jetbrains.annotations.NotNull Prenotazione prenotazione){
        this.id = prenotazione.getId();
        this.approvata = prenotazione.getApprovata();
        this.dataDiInizio =prenotazione.getDataDiInizio();
        this.dataDiFine = prenotazione.getDataDiFine();
        this.targa= prenotazione.getAuto().getTarga();
        this.username=prenotazione.getUser().getUsername();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getApprovata() {
        return approvata;
    }

    public void setApprovata(Boolean approvata) {
        this.approvata = approvata;
    }

    public Date getDataDiInizio() {
        return dataDiInizio;
    }

    public void setDataDiInizio(Date dataDiInizio) {
        this.dataDiInizio = dataDiInizio;
    }

    public Date getDataDiFine() {
        return dataDiFine;
    }

    public void setDataDiFine(Date dataDiFine) {
        this.dataDiFine = dataDiFine;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
