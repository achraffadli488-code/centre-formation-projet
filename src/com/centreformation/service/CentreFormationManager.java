package com.centreformation.service;

import com.centreformation.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CentreFormationManager {

    private final List<Formation> formations = new ArrayList<>();
    private final List<Apprenant> apprenants = new ArrayList<>();
    private final List<Formateur> formateurs = new ArrayList<>();
    private final List<Session> sessions = new ArrayList<>();

    // ==========================
    //       FORMATIONS
    // ==========================

    public void ajouterFormation(int id, String titre, int duree, String categorieStr) {
        CategorieFormation categorie;

        try {
            categorie = CategorieFormation.valueOf(categorieStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            categorie = CategorieFormation.AUTRE;
        }

        Formation f = new Formation(id, titre, duree, categorie);
        formations.add(f);
        System.out.println("Formation ajoutée : " + f);
    }

    public void afficherFormations() {
        if (formations.isEmpty()) {
            System.out.println("Aucune formation.");
            return;
        }
        System.out.println("=== Liste des formations ===");
        for (Formation f : formations) {
            System.out.println(f);
        }
    }

    public Formation rechercherFormation(int id) {
        return formations.stream()
                .filter(f -> f.getIdFormation() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Formation " + id + " introuvable.");
                    return null;
                });
    }

    public void supprimerFormation(int id) {
        formations.removeIf(f -> f.getIdFormation() == id);
        System.out.println("Si elle existait, la formation " + id + " a été supprimée.");
    }

    // ==========================
    //       APPRENANTS
    // ==========================

    public void ajouterApprenant(int id, String nom, String prenom) {
        Apprenant a = new Apprenant(id, nom, prenom);
        apprenants.add(a);
        System.out.println("Apprenant ajouté : " + a.getNom() + " " + a.getPrenom());
    }

    public void afficherApprenants() {
        if (apprenants.isEmpty()) {
            System.out.println("Aucun apprenant.");
            return;
        }
        System.out.println("=== Liste des apprenants ===");
        for (Apprenant a : apprenants) {
            System.out.println(a.getIdApprenant() + " - " + a.getNom() + " " + a.getPrenom());
        }
    }

    public Apprenant rechercherApprenant(int id) {
        return apprenants.stream()
                .filter(a -> a.getIdApprenant() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Apprenant " + id + " introuvable.");
                    return null;
                });
    }

    public void supprimerApprenant(int id) {
        apprenants.removeIf(a -> a.getIdApprenant() == id);
        System.out.println("Si il existait, l’apprenant " + id + aEtSupprime());
    }

    private String aEtSupprime() {
        return " a été supprimé.";
    }

    // ==========================
    //       FORMATEURS
    // ==========================

    public void ajouterFormateur(int id, String nom, String prenom) {
        Formateur f = new Formateur(id, nom, prenom);
        formateurs.add(f);
        System.out.println("Formateur ajouté : " + f.getNom() + " " + f.getPrenom());
    }

    public void afficherFormateurs() {
        if (formateurs.isEmpty()) {
            System.out.println("Aucun formateur.");
            return;
        }
        System.out.println("=== Liste des formateurs ===");
        for (Formateur f : formateurs) {
            System.out.println(f.getIdFormateur() + " - " + f.getNom() + " " + f.getPrenom());
        }
    }

    public Formateur rechercherFormateur(int id) {
        return formateurs.stream()
                .filter(f -> f.getIdFormateur() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Formateur " + id + " introuvable.");
                    return null;
                });
    }

    public void supprimerFormateur(int id) {
        formateurs.removeIf(f -> f.getIdFormateur() == id);
        System.out.println("Si il existait, le formateur " + id + aEtSupprime());
    }

    // ==========================
    //          SESSIONS
    // ==========================

    public void ajouterSession(int idSession, int idFormation) {
        Formation formation = rechercherFormation(idFormation);
        if (formation == null) {
            System.out.println("Impossible de créer la session : formation inexistante.");
            return;
        }

        // Pour simplifier : dates et nbPlaces par défaut, pas encore de formateur assigné
        Session s = new Session(
                idSession,
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                10,
                formation,
                null
        );
        sessions.add(s);

        System.out.println("Session ajoutée : " + s);
    }

    public void afficherSessions() {
        if (sessions.isEmpty()) {
            System.out.println("Aucune session.");
            return;
        }
        System.out.println("=== Liste des sessions ===");
        for (Session s : sessions) {
            System.out.println(s);
        }
    }

    public Session rechercherSession(int id) {
        return sessions.stream()
                .filter(s -> s.getIdSession() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Session " + id + " introuvable.");
                    return null;
                });
    }

    public void supprimerSession(int id) {
        sessions.removeIf(s -> s.getIdSession() == id);
        System.out.println("Si elle existait, la session " + id + " a été supprimée.");
    }
}
