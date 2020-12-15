import ciolty.energySystemImplementation.JSON.InputReader;
import ciolty.energySystemImplementation.JSON.JsonOutputConverter;
import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.debugger.DebuggingVariables;
import ciolty.energySystemImplementation.entities.InputData;
import ciolty.energySystemImplementation.server.EnergySystemServer;
import ciolty.engine.JSON.Writer;

public final class Main {
    private Main() {
    }

    static final int TEST_NUMBER_POSITION = 47;
    static final int FILE_EXTENSION_LENGTH = 5;

    private static String getTestNumber(final String path) {
        if (!DebuggingVariables.isDebuggingActive()) {
            return null;
        }
        return path.substring(TEST_NUMBER_POSITION,
                path.length() - FILE_EXTENSION_LENGTH);
    }

    private static String getResultPath(final String path) {
        return "myResults/" + "test_" + getTestNumber(path) + "_";
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        DebugLogger.deactivate();
        DebugLogger.setPrintStream(System.out);
        DebuggingVariables.setDebuggingActive(false);

        InputReader reader = new InputReader(inputFilePath);
        InputData input = reader.read();

        EnergySystemServer server = new EnergySystemServer(input, getResultPath(inputFilePath));

        server.runAllActions();

        Writer writer = new Writer(outputFilePath);
        writer.write(JsonOutputConverter.convert(server.getOutput()));
        writer.close();
    }
}
