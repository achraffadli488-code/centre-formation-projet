package com.centreformation.model;

import java.time.LocalDate;

public class Inscription {

    private int idInscription;
    private LocalDate dateInscription;
    private StatutInscription statut;

    private Session session;
    private Apprenant apprenant;

    public Inscription(int idInscription,
                       LocalDate dateInscription,
                       Session session,
                       Apprenant apprenant) {

        this.idInscription = idInscription;
        this.dateInscription = dateInscription;
        this.session = session;
        this.apprenant = apprenant;
        this.statut = StatutInscription.ATTENTE;

        if (session != null) {
            session.ajouterInscription(this);
        }
    }

    public void confirmer() {
        this.statut = StatutInscription.CONFIRMEE;
        if (session != null) {
            session.notifierObservateurs(
                "Inscription " + idInscription + " confirmée pour " +
                apprenant.getNom() + " " + apprenant.getPrenom()
            );
        }
    }

    public void annuler() {
        this.statut = StatutInscription.ANNULEE;
        if (session != null) {
            session.notifierObservateurs(
                "Inscription " + idInscription + " annulée pour " +
                apprenant.getNom() + " " + apprenant.getPrenom()
            );
        }
    }

    // Getters

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
}
