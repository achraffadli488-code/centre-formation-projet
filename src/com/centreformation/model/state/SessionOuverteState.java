package com.centreformation.model.state;

import com.centreformation.model.Session;

public class SessionOuverteState implements SessionState {

    @Override
    public void ouvrir(Session session) {}

    @Override
    public void clore(Session session) {
        session.setEtat(EtatSession.TERMINEE);
        session.setState(new SessionTermineeState());
        session.notifierObservateurs("La session " + session.getIdSession() + " est clôturée.");
    }

    @Override
    public void annuler(Session session) {
        session.setEtat(EtatSession.ANNULEE);
        session.setState(new SessionAnnuleeState());
        session.notifierObservateurs("La session " + session.getIdSession() + " est annulée.");
    }
}
