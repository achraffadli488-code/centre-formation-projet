package com.centreformation.model;

public class Apprenant {

    private int idApprenant;
    private String nom;
    private String prenom;

    public Apprenant(int idApprenant, String nom, String prenom) {
        this.idApprenant = idApprenant;
        this.nom = nom;
        this.prenom = prenom;
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
}
