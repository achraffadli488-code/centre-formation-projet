package com.centreformation.model;

import com.centreformation.model.observer.ConsoleObservateur;
import com.centreformation.model.observer.Sujet;
import com.centreformation.model.state.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        // observer console
        this.ajouterObservateur(new ConsoleObservateur());
    }

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

    // ==== API métier ====

    public boolean verifierPlaces() {
        long nbConfirmes = inscriptions.stream()
                .filter(i -> i.getStatut() == StatutInscription.CONFIRMEE)
                .count();
        return nbConfirmes < nbPlacesMax;
    }

    public void ajouterInscription(Inscription i) {
        if (i != null && !inscriptions.contains(i)) {
            inscriptions.add(i);
        }
    }

    public void ouvrir() {
        state.ouvrir(this);
    }

    public void clore() {
        state.clore(this);
    }

    public void annuler() {
        state.annuler(this);
    }

    // utilisé par les états pour notifier via Observer
    public void notifierEtat(String message) {
        notifierObservateurs(message);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + idSession +
                ", formation=" + (formation != null ? formation.getTitre() : "N/A") +
                ", formateur=" + (formateur != null ? formateur.getNom() : "N/A") +
                ", debut=" + dateDebut +
                ", fin=" + dateFin +
                ", nbPlacesMax=" + nbPlacesMax +
                ", etat=" + etat +
                '}';
    }
}
