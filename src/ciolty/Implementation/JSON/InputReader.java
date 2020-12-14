package ciolty.Implementation.JSON;

import ciolty.Implementation.entities.InputData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputReader {
    String path;
    public InputReader(String path) {
        this.path = path;
    }

    public Object read() throws FileNotFoundException {
        System.out.println("path = " + path);
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = new FileInputStream(path);
        System.out.println("inputStream = " + inputStream);
        InputData input = null;
        try {
            input = objectMapper.readValue(inputStream, InputData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
