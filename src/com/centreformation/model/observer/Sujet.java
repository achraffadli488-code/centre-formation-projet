package com.centreformation.model.observer;

import java.util.ArrayList;
import java.util.List;

public class Sujet {

    private final List<Observateur> observateurs = new ArrayList<>();

    public void ajouterObservateur(Observateur o) {
        if (o != null && !observateurs.contains(o)) {
            observateurs.add(o);
        }
    }

    public void retirerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    protected void notifierObservateurs(String message) {
        for (Observateur o : observateurs) {
            o.notifier(message);
        }
    }
}
