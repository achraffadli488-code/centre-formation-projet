package com.centreformation.service;

import com.centreformation.factory.InscriptionFactory;
import com.centreformation.factory.SessionFactory;
import com.centreformation.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CentreFormationManager {

    // ==================== SINGLETON ====================
    private static final CentreFormationManager INSTANCE = new CentreFormationManager();

    private final List<Formation> formations = new ArrayList<>();
    private final List<Apprenant> apprenants = new ArrayList<>();
    private final List<Formateur> formateurs = new ArrayList<>();
    private int compteurInscription = 1;

    private CentreFormationManager() {}

    public static CentreFormationManager getInstance() {
        return INSTANCE;
    }

    // ==================== FORMATIONS ====================

    public void ajouterFormation(int id, String titre, int duree, String categorieStr) {
        CategorieFormation categorie;
        try {
            categorie = CategorieFormation.valueOf(categorieStr);
        } catch (IllegalArgumentException e) {
            categorie = CategorieFormation.AUTRE;
        }

        Formation f = new Formation(id, titre, duree, categorie);
        formations.add(f);
        System.out.println("✅ Formation ajoutée : " + f);
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void afficherFormations() {
        if (formations.isEmpty()) {
            System.out.println("Aucune formation.");
            return;
        }
        formations.forEach(System.out::println);
    }

    public Formation rechercherFormation(int id) {
        return formations.stream()
                .filter(f -> f.getIdFormation() == id)
                .findFirst()
                .orElse(null);
    }

    public void afficherFormationDetail(int id) {
        Formation f = rechercherFormation(id);
        if (f == null) {
            System.out.println("Formation introuvable.");
            return;
        }

        System.out.println(f);
        if (f.getSessions().isEmpty()) {
            System.out.println("  Aucune session pour cette formation.");
        } else {
            System.out.println("  Sessions :");
            for (Session s : f.getSessions()) {
                System.out.println("   - " + s);
            }
        }
    }

    public void supprimerFormation(int id) {
        boolean removed = formations.removeIf(f -> f.getIdFormation() == id);
        System.out.println(removed ? "Formation supprimée." : "Formation introuvable.");
    }

    // ==================== FORMATEURS ====================

    public void ajouterFormateur(int id, String nom, String prenom) {
        Formateur f = new Formateur(id, nom, prenom);
        formateurs.add(f);
        System.out.println("✅ Formateur ajouté : " + f);
    }

    public List<Formateur> getFormateurs() {
        return formateurs;
    }

    public void afficherFormateurs() {
        if (formateurs.isEmpty()) {
            System.out.println("Aucun formateur.");
            return;
        }
        formateurs.forEach(System.out::println);
    }

    public Formateur rechercherFormateur(int id) {
        return formateurs.stream()
                .filter(f -> f.getIdFormateur() == id)
                .findFirst()
                .orElse(null);
    }

    // ==================== APPRENANTS ====================

    public void ajouterApprenant(int id, String nom, String prenom, String email) {
        Apprenant a = new Apprenant(id, nom, prenom, email);
        apprenants.add(a);
        System.out.println("✅ Apprenant ajouté : " + a);
    }

    public List<Apprenant> getApprenants() {
        return apprenants;
    }

    public void afficherApprenants() {
        if (apprenants.isEmpty()) {
            System.out.println("Aucun apprenant.");
            return;
        }
        apprenants.forEach(System.out::println);
    }

    public Apprenant rechercherApprenant(int id) {
        return apprenants.stream()
                .filter(a -> a.getIdApprenant() == id)
                .findFirst()
                .orElse(null);
    }

    // ==================== SESSIONS ====================

    public void ajouterSession(
            int idSession,
            int idFormation,
            int idFormateur,
            LocalDate debut,
            LocalDate fin,
            int nbPlacesMax
    ) {
        Formation formation = rechercherFormation(idFormation);
        if (formation == null) {
            System.out.println("❌ Formation introuvable.");
            return;
        }

        Formateur formateur = rechercherFormateur(idFormateur);
        if (formateur == null) {
            System.out.println("❌ Formateur introuvable.");
            return;
        }

        Session session = SessionFactory.creerSession(
                idSession,
                debut,
                fin,
                nbPlacesMax,
                formation,
                formateur
        );

        formation.ajouterSession(session);
        System.out.println("✅ Session ajoutée : " + session);
    }

    public List<Session> getSessions() {
        List<Session> all = new ArrayList<>();
        for (Formation f : formations) {
            all.addAll(f.getSessions());
        }
        return all;
    }

    public void afficherSessions() {
        boolean found = false;
        for (Formation f : formations) {
            for (Session s : f.getSessions()) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Aucune session.");
        }
    }

    public Session rechercherSession(int idSession) {
        for (Formation f : formations) {
            Optional<Session> s = f.getSessions()
                    .stream()
                    .filter(sess -> sess.getIdSession() == idSession)
                    .findFirst();
            if (s.isPresent()) return s.get();
        }
        return null;
    }

    public void supprimerSession(int idSession) {
        boolean removed = false;
        for (Formation f : formations) {
            if (f.getSessions().removeIf(s -> s.getIdSession() == idSession)) {
                removed = true;
            }
        }
        System.out.println(removed ? "Session supprimée." : "Session introuvable.");
    }

    public void ouvrirSession(int idSession) {
        Session s = rechercherSession(idSession);
        if (s != null) s.ouvrir();
    }

    public void cloreSession(int idSession) {
        Session s = rechercherSession(idSession);
        if (s != null) s.clore();
    }

    public void annulerSession(int idSession) {
        Session s = rechercherSession(idSession);
        if (s != null) s.annuler();
    }

    // ==================== INSCRIPTIONS ====================

    public void inscrireApprenant(int idApprenant, int idSession) {
        Apprenant apprenant = rechercherApprenant(idApprenant);
        if (apprenant == null) {
            System.out.println("❌ Apprenant introuvable.");
            return;
        }

        Session session = rechercherSession(idSession);
        if (session == null) {
            System.out.println("❌ Session introuvable.");
            return;
        }

        if (!session.verifierPlaces()) {
            System.out.println("❌ Plus de places disponibles.");
            return;
        }

        Inscription inscription = InscriptionFactory.creerInscription(
                compteurInscription++,
                session,
                apprenant
        );

        session.ajouterInscription(inscription);
        inscription.confirmer();
        apprenant.ajouterFormation(session.getFormation());

        System.out.println("✅ Inscription confirmée : " + inscription);
    }
}
