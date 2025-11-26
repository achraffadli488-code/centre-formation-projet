package com.centreformation.model;

import java.util.ArrayList;
import java.util.List;

public class Formation {

    private int idFormation;
    private String titre;
    private int duree;
    private CategorieFormation categorie;
    private List<Session> sessions = new ArrayList<>();

    public Formation(int idFormation, String titre, int duree, CategorieFormation categorie) {
        this.idFormation = idFormation;
        this.titre = titre;
        this.duree = duree;
        this.categorie = categorie;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public String getTitre() {
        return titre;
    }

    public int getDuree() {
        return duree;
    }

    public CategorieFormation getCategorie() {
        return categorie;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void ajouterSession(Session s) {
        if (s != null && !sessions.contains(s)) {
            sessions.add(s);
        }
    }

    public void supprimerSession(int idSession) {
        sessions.removeIf(s -> s.getIdSession() == idSession);
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + idFormation +
                ", titre='" + titre + '\'' +
                ", duree=" + duree +
                ", categorie=" + categorie +
                '}';
    }
}
