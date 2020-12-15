package ciolty.energySystemImplementation.debugger;

public final class DebuggingVariables {
    private static boolean debuggingActive = false;

    private DebuggingVariables() {
    }

    public static boolean isDebuggingActive() {
        return debuggingActive;
    }

    public static void setDebuggingActive(final boolean debuggingActive) {
        DebuggingVariables.debuggingActive = debuggingActive;
    }
}
