package com.centreformation.view;

public class MenuView {

    public static void afficherMenuPrincipal() {
        System.out.println();
        System.out.println("===== CENTRE DE FORMATION =====");
        System.out.println("1. Gérer les formations");
        System.out.println("2. Gérer les sessions");
        System.out.println("3. Gérer les apprenants");
        System.out.println("4. Gérer les formateurs");
        System.out.println("5. Quitter");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuFormations() {
        System.out.println();
        System.out.println("=== MENU FORMATIONS ===");
        System.out.println("1. Ajouter une formation");
        System.out.println("2. Afficher toutes les formations");
        System.out.println("3. Rechercher une formation");
        System.out.println("4. Supprimer une formation");
        System.out.println("5. Retour");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuSessions() {
        System.out.println();
        System.out.println("=== MENU SESSIONS ===");
        System.out.println("1. Ajouter une session");
        System.out.println("2. Afficher toutes les sessions");
        System.out.println("3. Changer l'état d'une session");
        System.out.println("4. Supprimer une session");
        System.out.println("5. Retour");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuEtatSession() {
        System.out.println();
        System.out.println("=== CHANGER ETAT SESSION ===");
        System.out.println("1. Ouvrir");
        System.out.println("2. Clore");
        System.out.println("3. Annuler");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuApprenants() {
        System.out.println();
        System.out.println("=== MENU APPRENANTS ===");
        System.out.println("1. Ajouter un apprenant");
        System.out.println("2. Afficher les apprenants");
        System.out.println("3. Inscrire un apprenant à une session");
        System.out.println("4. Retour");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuFormateurs() {
        System.out.println();
        System.out.println("=== MENU FORMATEURS ===");
        System.out.println("1. Ajouter un formateur");
        System.out.println("2. Afficher les formateurs");
        System.out.println("3. Retour");
        System.out.print("Votre choix : ");
    }
}
