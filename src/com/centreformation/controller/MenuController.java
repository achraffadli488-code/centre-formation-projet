package com.centreformation.controller;

import com.centreformation.service.CentreFormationManager;
import com.centreformation.view.MenuView;
import java.util.Scanner;

public class MenuController {

    private final CentreFormationManager manager = new CentreFormationManager();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {

        int choix;

        do {
            MenuView.afficherMenuPrincipal();
            choix = scanner.nextInt();
            scanner.nextLine(); // consommer le retour à la ligne

            switch (choix) {
                case 1 -> menuFormations();
                case 2 -> menuSessions();
                case 3 -> menuApprenants();
                case 4 -> menuFormateurs();
                case 5 -> System.out.println("Au revoir.");
                default -> System.out.println("Choix invalide !");
            }

        } while (choix != 5);
    }

    // ================================
    //          FORMATIONS
    // ================================
    private void menuFormations() {
        int choix;

        do {
            MenuView.afficherMenuFormations();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterFormation();
                case 2 -> manager.afficherFormations();
                case 3 -> rechercherFormation();
                case 4 -> supprimerFormation();
                case 5 -> System.out.println("Retour...");
                default -> System.out.println("Choix invalide !");
            }

        } while (choix != 5);
    }

    private void ajouterFormation() {
        System.out.print("ID formation : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Titre : ");
        String titre = scanner.nextLine();

        System.out.print("Durée : ");
        int duree = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Catégorie (INFORMATIQUE, MANAGEMENT, LANGUES, AUTRE) : ");
        String categorie = scanner.nextLine();

        manager.ajouterFormation(id, titre, duree, categorie);
    }

    private void rechercherFormation() {
        System.out.print("ID formation : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.rechercherFormation(id);
    }

    private void supprimerFormation() {
        System.out.print("ID formation : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.supprimerFormation(id);
    }

    // ================================
    //          APPRENANTS
    // ================================
    private void menuApprenants() {
        int choix;

        do {
            MenuView.afficherMenuApprenants();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterApprenant();
                case 2 -> manager.afficherApprenants();
                case 3 -> rechercherApprenant();
                case 4 -> supprimerApprenant();
                case 5 -> System.out.println("Retour...");
                default -> System.out.println("Choix invalide !");
            }

        } while (choix != 5);
    }

    private void ajouterApprenant() {
        System.out.print("ID apprenant : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        manager.ajouterApprenant(id, nom, prenom);
    }

    private void rechercherApprenant() {
        System.out.print("ID apprenant : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.rechercherApprenant(id);
    }

    private void supprimerApprenant() {
        System.out.print("ID apprenant : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.supprimerApprenant(id);
    }

    // ================================
    //          FORMATEURS
    // ================================
    private void menuFormateurs() {
        int choix;

        do {
            MenuView.afficherMenuFormateurs();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterFormateur();
                case 2 -> manager.afficherFormateurs();
                case 3 -> rechercherFormateur();
                case 4 -> supprimerFormateur();
                case 5 -> System.out.println("Retour...");
                default -> System.out.println("Choix invalide !");
            }

        } while (choix != 5);
    }

    private void ajouterFormateur() {
        System.out.print("ID formateur : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        manager.ajouterFormateur(id, nom, prenom);
    }

    private void rechercherFormateur() {
        System.out.print("ID formateur : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.rechercherFormateur(id);
    }

    private void supprimerFormateur() {
        System.out.print("ID formateur : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.supprimerFormateur(id);
    }

    // ================================
    //          SESSIONS
    // ================================
    private void menuSessions() {
        int choix;

        do {
            MenuView.afficherMenuSessions();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterSession();
                case 2 -> manager.afficherSessions();
                case 3 -> rechercherSession();
                case 4 -> supprimerSession();
                case 5 -> System.out.println("Retour...");
                default -> System.out.println("Choix invalide !");
            }

        } while (choix != 5);
    }

    private void ajouterSession() {
        System.out.print("ID session : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID formation associée : ");
        int idFormation = scanner.nextInt();
        scanner.nextLine();

        manager.ajouterSession(id, idFormation);
    }

    private void rechercherSession() {
        System.out.print("ID session : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.rechercherSession(id);
    }

    private void supprimerSession() {
        System.out.print("ID session : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.supprimerSession(id);
    }
}
