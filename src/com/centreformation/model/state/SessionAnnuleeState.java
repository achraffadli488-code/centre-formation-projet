package com.centreformation.model.state;

import com.centreformation.model.Session;

public class SessionAnnuleeState implements SessionState {

    @Override
    public void ouvrir(Session session) {
        // impossible
    }

    @Override
    public void clore(Session session) {
        // impossible
    }

    @Override
    public void annuler(Session session) {
        // déjà annulée
    }
}
