package com.centreformation.model;

import java.util.ArrayList;
import java.util.List;

public class Formateur {

    private int idFormateur;
    private String nom;
    private String prenom;
    private List<String> specialites = new ArrayList<>();

    public Formateur(int idFormateur, String nom, String prenom) {
        this.idFormateur = idFormateur;
        this.nom = nom;
        this.prenom = prenom;
    }

    public void ajouterSpecialite(String s) {
        if (s != null && !s.isBlank() && !specialites.contains(s)) {
            specialites.add(s);
        }
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public List<String> getSpecialites() {
        return specialites;
    }
}
