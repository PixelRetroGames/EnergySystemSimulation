import ciolty.Implementation.JSON.InputReader;
import ciolty.Implementation.JSON.JsonOutputConverter;
import ciolty.Implementation.entities.InputData;
import ciolty.Implementation.server.ImplementedServer;
import ciolty.engine.JSON.JsonConverter;
import ciolty.engine.JSON.Writer;
import ciolty.engine.server.Server;

import java.lang.reflect.WildcardType;

public class Main {
    static int nrTest = 0;
    public static void main(String[] args) throws Exception {
        System.out.println(args[0]);
        InputReader reader = new InputReader(args[0]);
        InputData input = (InputData) reader.read();

        Server server = new ImplementedServer(input);
        server.runAllActions();
        System.out.println(server.getOutput());

        Writer writer = new Writer(args[1]);
        JsonConverter converter = new JsonOutputConverter();
        writer.write(converter.convert(server.getOutput()));
        writer.close();

        nrTest++;
        writer = new Writer("myResults/" + "test_" + nrTest + ".json");
        writer.write(converter.convert(server.getOutput()));
        writer.close();

        System.out.println(args[1]);
    }
}
