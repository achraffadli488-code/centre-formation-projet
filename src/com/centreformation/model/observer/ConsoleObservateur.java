package com.centreformation.model.observer;

public class ConsoleObservateur implements Observateur {

    @Override
    public void notifier(String message) {
        System.out.println("[OBSERVATEUR] " + message);
    }
}
