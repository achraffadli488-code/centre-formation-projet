package com.centreformation.factory;

import java.time.LocalDate;

import com.centreformation.model.Formation;
import com.centreformation.model.Formateur;
import com.centreformation.model.Session;

public final class SessionFactory {

    // Empêche l'instanciation
    private SessionFactory() {}

    public static Session creerSession(
            int idSession,
            LocalDate dateDebut,
            LocalDate dateFin,
            int nbPlacesMax,
            Formation formation,
            Formateur formateur
    ) {
        return new Session(
                idSession,
                dateDebut,
                dateFin,
                nbPlacesMax,
                formation,
                formateur
        );
    }
}
