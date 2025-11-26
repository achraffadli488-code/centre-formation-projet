package com.centreformation.model;

import java.time.LocalDate;

public class Inscription {

    private int idInscription;
    private LocalDate dateInscription;
    private StatutInscription statut;
    private Session session;
    private Apprenant apprenant;

    public Inscription(int idInscription, LocalDate dateInscription, Session session, Apprenant apprenant) {
        this.idInscription = idInscription;
        this.dateInscription = dateInscription;
        this.session = session;
        this.apprenant = apprenant;
        this.statut = StatutInscription.ATTENTE;
    }

    public int getIdInscription() {
        return idInscription;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public StatutInscription getStatut() {
        return statut;
    }

    public Session getSession() {
        return session;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void confirmer() {
        this.statut = StatutInscription.CONFIRMEE;
    }

    public void annuler() {
        this.statut = StatutInscription.ANNULEE;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + idInscription +
                ", date=" + dateInscription +
                ", statut=" + statut +
                ", session=" + (session != null ? session.getIdSession() : null) +
                ", apprenant=" + (apprenant != null ? apprenant.getNom() : null) +
                '}';
    }
}
