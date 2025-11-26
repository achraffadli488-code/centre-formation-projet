package com.centreformation.model.state;

import com.centreformation.model.Session;

public class SessionCompleteState implements SessionState {

    @Override
    public void ouvrir(Session session) {
        // impossible
    }

    @Override
    public void clore(Session session) {
        session.setEtat(EtatSession.TERMINEE);
        session.setState(new SessionTermineeState());
        session.notifierEtat("La session " + session.getIdSession() + " est clôturée.");
    }

    @Override
    public void annuler(Session session) {
        session.setEtat(EtatSession.ANNULEE);
        session.setState(new SessionAnnuleeState());
        session.notifierEtat("La session " + session.getIdSession() + " est annulée.");
    }
}
