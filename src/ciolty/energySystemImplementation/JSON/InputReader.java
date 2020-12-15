package ciolty.energySystemImplementation.JSON;

import ciolty.energySystemImplementation.entities.InputData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class InputReader {
    private final String path;

    public InputReader(final String path) {
        this.path = path;
    }

    /**
     * @return input data
     */
    public InputData read() {
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = null;
        InputData input = null;

        try {
            inputStream = new FileInputStream(path);
            input = objectMapper.readValue(inputStream, InputData.class);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }
}
