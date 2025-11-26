package com.centreformation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.centreformation.model.observer.Sujet;
import com.centreformation.model.state.EtatSession;
import com.centreformation.model.state.SessionAnnuleeState;
import com.centreformation.model.state.SessionCompleteState;
import com.centreformation.model.state.SessionOuverteState;
import com.centreformation.model.state.SessionState;
import com.centreformation.model.state.SessionTermineeState;

public class Session extends Sujet {

    private int idSession;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nbPlacesMax;

    private EtatSession etat;
    private SessionState state;

    private Formation formation;
    private Formateur formateur;
    private List<Inscription> inscriptions = new ArrayList<>();

    public Session(int idSession,
                   LocalDate dateDebut,
                   LocalDate dateFin,
                   int nbPlacesMax,
                   Formation formation,
                   Formateur formateur) {

        this.idSession = idSession;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbPlacesMax = nbPlacesMax;
        this.formation = formation;
        this.formateur = formateur;

        this.etat = EtatSession.OUVERTE;
        this.state = new SessionOuverteState();

        if (formation != null) {
            formation.ajouterSession(this);
        }
    }

    // ====== Méthodes métier ======

    public void ajouterInscription(Inscription i) {
        if (!verifierPlaces()) {
            System.out.println("Plus de places disponibles pour la session " + idSession);
            return;
        }

        if (i != null && !inscriptions.contains(i)) {
            inscriptions.add(i);

            if (getNbPlacesRestantes() == 0) {
                this.etat = EtatSession.COMPLETE;
                this.state = new SessionCompleteState();
                notifierObservateurs("La session " + idSession + " est maintenant complète.");
            }
        }
    }

    public boolean verifierPlaces() {
        return getNbPlacesRestantes() > 0;
    }

    public int getNbPlacesRestantes() {
        long nbInscrits = inscriptions.stream()
                .filter(i -> i.getStatut() == StatutInscription.CONFIRMEE
                          || i.getStatut() == StatutInscription.ATTENTE)
                .count();

        return nbPlacesMax - (int) nbInscrits;
    }

    // Intégration du pattern State

    public void ouvrir() {
        state.ouvrir(this);
    }

    public void clore() {
        state.clore(this);
    }

    public void annuler() {
        state.annuler(this);
    }

    // ====== Getters / Setters ======

    public int getIdSession() {
        return idSession;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public int getNbPlacesMax() {
        return nbPlacesMax;
    }

    public EtatSession getEtat() {
        return etat;
    }

    public SessionState getState() {
        return state;
    }

    public Formation getFormation() {
        return formation;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setEtat(EtatSession etat) {
        this.etat = etat;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    // Méthodes utilitaires utilisées par les State

    public void passerEnTerminee() {
        this.etat = EtatSession.TERMINEE;
        this.state = new SessionTermineeState();
    }

    public void passerEnAnnulee() {
        this.etat = EtatSession.ANNULEE;
        this.state = new SessionAnnuleeState();
    }

    public void passerEnComplete() {
        this.etat = EtatSession.COMPLETE;
        this.state = new SessionCompleteState();
    }

    @Override
    public String toString() {
        return "Session " + idSession + " (" + dateDebut + " -> " + dateFin + "), places max : " +
                nbPlacesMax + ", état : " + etat;
    }
}
