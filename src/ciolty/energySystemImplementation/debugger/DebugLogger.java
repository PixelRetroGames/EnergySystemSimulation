package ciolty.energySystemImplementation.debugger;

import java.io.PrintStream;

public final class DebugLogger {
    private boolean active = false;
    private PrintStream out = null;
    private static DebugLogger instance = null;

    private DebugLogger() {
    }

    private static DebugLogger getInstance() {
        if (instance == null) {
            instance = new DebugLogger();
        }
        return instance;
    }

    /**
     * Activate logging
     */
    public static void activate() {
        getInstance().active = true;
    }

    /**
     * Deactivate logging
     */
    public static void deactivate() {
        getInstance().active = false;
    }

    /**
     * @param out
     */
    public static void setPrintStream(final PrintStream out) {
        getInstance().out = out;
    }

    /**
     * @param message
     */
    public static void log(final String message) {
        if (getInstance().active && getInstance().out != null) {
            getInstance().out.println(message);
        }
    }
}
