package com.centreformation.model;

import java.util.ArrayList;
import java.util.List;

public class Apprenant {

    private int idApprenant;
    private String nom;
    private String prenom;
    private String email;
    private List<Formation> historique = new ArrayList<>();

    public Apprenant(int idApprenant, String nom, String prenom, String email) {
        this.idApprenant = idApprenant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getIdApprenant() {
        return idApprenant;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public List<Formation> getHistorique() {
        return historique;
    }

    public void ajouterFormation(Formation f) {
        if (f != null && !historique.contains(f)) {
            historique.add(f);
        }
    }

    @Override
    public String toString() {
        return "Apprenant{" +
                "id=" + idApprenant +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
