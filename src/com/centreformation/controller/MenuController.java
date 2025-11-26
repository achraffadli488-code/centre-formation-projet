package com.centreformation.controller;

import com.centreformation.service.CentreFormationManager;
import com.centreformation.view.MenuView;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuController {

    private final Scanner scanner = new Scanner(System.in);
    private final CentreFormationManager manager = CentreFormationManager.getInstance();

    public void run() {
        int choix;
        do {
            MenuView.afficherMenuPrincipal();
            choix = lireInt();
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

    // ========================= FORMATIONS =========================

    private void menuFormations() {
        int choix;
        do {
            MenuView.afficherMenuFormations();
            choix = lireInt();
            switch (choix) {
                case 1 -> ajouterFormation();
                case 2 -> manager.afficherFormations();
                case 3 -> rechercherFormation();
                case 4 -> supprimerFormation();
                case 5 -> System.out.println("Retour au menu principal.");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 5);
    }

    private void ajouterFormation() {
        System.out.print("ID formation : ");
        int id = lireInt();

        System.out.print("Titre : ");
        String titre = scanner.nextLine();

        System.out.print("Durée (en heures) : ");
        int duree = lireInt();

        System.out.print("Catégorie (INFORMATIQUE, MANAGEMENT, LANGUES, AUTRE) : ");
        String cat = scanner.nextLine().toUpperCase();

        manager.ajouterFormation(id, titre, duree, cat);
    }

    private void rechercherFormation() {
        System.out.print("ID formation : ");
        int id = lireInt();
        manager.afficherFormationDetail(id);
    }

    private void supprimerFormation() {
        System.out.print("ID formation à supprimer : ");
        int id = lireInt();
        manager.supprimerFormation(id);
    }

    // ========================= SESSIONS =========================

    private void menuSessions() {
        int choix;
        do {
            MenuView.afficherMenuSessions();
            choix = lireInt();
            switch (choix) {
                case 1 -> ajouterSession();
                case 2 -> manager.afficherSessions();
                case 3 -> changerEtatSession();
                case 4 -> supprimerSession();
                case 5 -> System.out.println("Retour au menu principal.");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 5);
    }

    private void ajouterSession() {
        System.out.print("ID session : ");
        int idSession = lireInt();

        System.out.print("ID formation associée : ");
        int idFormation = lireInt();

        System.out.print("ID formateur : ");
        int idFormateur = lireInt();

        System.out.print("Date début (AAAA-MM-JJ) : ");
        LocalDate debut = LocalDate.parse(scanner.nextLine());

        System.out.print("Date fin (AAAA-MM-JJ) : ");
        LocalDate fin = LocalDate.parse(scanner.nextLine());

        System.out.print("Nombre de places max : ");
        int nbPlaces = lireInt();

        manager.ajouterSession(idSession, idFormation, idFormateur, debut, fin, nbPlaces);
    }

    private void changerEtatSession() {
        System.out.print("ID session : ");
        int idSession = lireInt();

        MenuView.afficherMenuEtatSession();
        int choix = lireInt();

        switch (choix) {
            case 1 -> manager.ouvrirSession(idSession);
            case 2 -> manager.cloreSession(idSession);
            case 3 -> manager.annulerSession(idSession);
            default -> System.out.println("Choix invalide !");
        }
    }

    private void supprimerSession() {
        System.out.print("ID session à supprimer : ");
        int idSession = lireInt();
        manager.supprimerSession(idSession);
    }

    // ========================= APPRENANTS =========================

    private void menuApprenants() {
        int choix;
        do {
            MenuView.afficherMenuApprenants();
            choix = lireInt();
            switch (choix) {
                case 1 -> ajouterApprenant();
                case 2 -> manager.afficherApprenants();
                case 3 -> inscrireApprenant();
                case 4 -> System.out.println("Retour au menu principal.");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 4);
    }

    private void ajouterApprenant() {
        System.out.print("ID apprenant : ");
        int id = lireInt();

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        manager.ajouterApprenant(id, nom, prenom, email);
    }

    private void inscrireApprenant() {
        System.out.print("ID apprenant : ");
        int idApprenant = lireInt();

        System.out.print("ID session : ");
        int idSession = lireInt();

        manager.inscrireApprenant(idApprenant, idSession);
    }

    // ========================= FORMATEURS =========================

    private void menuFormateurs() {
        int choix;
        do {
            MenuView.afficherMenuFormateurs();
            choix = lireInt();
            switch (choix) {
                case 1 -> ajouterFormateur();
                case 2 -> manager.afficherFormateurs();
                case 3 -> System.out.println("Retour au menu principal.");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 3);
    }

    private void ajouterFormateur() {
        System.out.print("ID formateur : ");
        int id = lireInt();

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        manager.ajouterFormateur(id, nom, prenom);
    }

    // ========================= UTIL =========================

    private int lireInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Veuillez saisir un nombre : ");
            scanner.nextLine();
        }
        int valeur = scanner.nextInt();
        scanner.nextLine(); // consommer le retour à la ligne
        return valeur;
    }
}
