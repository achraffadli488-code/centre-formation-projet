package com.centreformation.factory;


import java.time.LocalDate;

import com.centreformation.model.Apprenant;
import com.centreformation.model.Inscription;
import com.centreformation.model.Session;

public final class InscriptionFactory {

    private InscriptionFactory() {}

    public static Inscription creerInscription(
            int idInscription,
            Session session,
            Apprenant apprenant
    ) {
        return new Inscription(
                idInscription,
                LocalDate.now(),
                session,
                apprenant
        );
    }
}
