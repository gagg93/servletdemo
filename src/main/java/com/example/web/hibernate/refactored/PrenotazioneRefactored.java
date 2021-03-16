package com.example.web.hibernate.refactored;


import com.example.web.hibernate.entity.Prenotazione;

public class PrenotazioneRefactored {
    private int id;
    private Boolean approvata;
    private String data_di_inizio;
    private String data_di_fine;
    private String targa;
    private String username;

    public PrenotazioneRefactored(@org.jetbrains.annotations.NotNull Prenotazione prenotazione){
        this.id = prenotazione.getId();
        this.approvata = prenotazione.getApprovata();
        this.data_di_inizio = prenotazione.getData_di_inizio();
        this.data_di_fine = prenotazione.getData_di_fine();
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
