package ciolty.energySystemImplementation.debugger;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

public final class DebugLogger implements Observer {
    private boolean active = false;
    private PrintStream out = null;
    private static DebugLogger instance = null;

    private DebugLogger() {
    }

    /**
     * @return instance
     */
    public static DebugLogger getInstance() {
        if (instance == null) {
            instance = new DebugLogger();
        }
        return instance;
    }

    private static void setActive(boolean active) {
        getInstance().active = active;
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

    @Override
    public void update(Observable o, Object arg) {
        setActive((boolean) arg);
    }
}
