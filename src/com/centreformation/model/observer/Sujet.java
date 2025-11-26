package com.centreformation.model.observer;

import java.util.ArrayList;
import java.util.List;

public class Sujet {

    private List<Observateur> observateurs = new ArrayList<>();

    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    public void notifierObservateurs(String message) {
        observateurs.forEach(o -> o.notifier(message));
    }
}
