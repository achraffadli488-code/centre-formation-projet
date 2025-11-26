package com.centreformation.model.state;

import com.centreformation.model.Session;

public class SessionTermineeState implements SessionState {

    @Override
    public void ouvrir(Session session) {
        // impossible
    }

    @Override
    public void clore(Session session) {
        // déjà terminée
    }

    @Override
    public void annuler(Session session) {
        // impossible
    }
}
