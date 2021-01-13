package ciolty.energySystemImplementation.debugger;

import java.util.Observable;

public final class DebuggingVariables extends Observable {
    private boolean debuggingActive = false;
    private static DebuggingVariables instance;

    private DebuggingVariables() {
        addObserver(DebugLogger.getInstance());
    }

    /**
     * @return instance
     */
    public static DebuggingVariables getInstance() {
        if (instance == null) {
            instance = new DebuggingVariables();
        }
        return instance;
    }

    /**
     * @return debuggingActive
     */
    public static boolean isDebuggingActive() {
        return getInstance().debuggingActive;
    }

    /**
     * @param debuggingActive
     */
    public static void setDebuggingActive(final boolean debuggingActive) {
        getInstance().debuggingActive = debuggingActive;
        getInstance().setChanged();
        getInstance().notifyObservers(debuggingActive);
    }
}
