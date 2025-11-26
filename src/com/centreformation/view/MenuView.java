package com.centreformation.view;

public class MenuView {

    public static void afficherMenuPrincipal() {
        System.out.println("===== CENTRE DE FORMATION =====");
        System.out.println("1. Gérer les formations");
        System.out.println("2. Gérer les sessions");
        System.out.println("3. Gérer les apprenants");
        System.out.println("4. Gérer les formateurs");
        System.out.println("5. Quitter");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuFormations() {
        System.out.println("===== GESTION DES FORMATIONS =====");
        System.out.println("1. Ajouter une formation");
        System.out.println("2. Afficher les formations");
        System.out.println("3. Rechercher une formation");
        System.out.println("4. Supprimer une formation");
        System.out.println("5. Retour");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuApprenants() {
        System.out.println("===== GESTION DES APPRENANTS =====");
        System.out.println("1. Ajouter un apprenant");
        System.out.println("2. Afficher les apprenants");
        System.out.println("3. Rechercher un apprenant");
        System.out.println("4. Supprimer un apprenant");
        System.out.println("5. Retour");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuFormateurs() {
        System.out.println("===== GESTION DES FORMATEURS =====");
        System.out.println("1. Ajouter un formateur");
        System.out.println("2. Afficher les formateurs");
        System.out.println("3. Rechercher un formateur");
        System.out.println("4. Supprimer un formateur");
        System.out.println("5. Retour");
        System.out.print("Votre choix : ");
    }

    public static void afficherMenuSessions() {
        System.out.println("===== GESTION DES SESSIONS =====");
        System.out.println("1. Ajouter une session");
        System.out.println("2. Afficher les sessions");
        System.out.println("3. Rechercher une session");
        System.out.println("4. Supprimer une session");
        System.out.println("5. Retour");
        System.out.print("Votre choix : ");
    }
}
