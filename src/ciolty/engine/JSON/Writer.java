package ciolty.engine.JSON;

import java.io.FileWriter;
import java.io.IOException;

public final class Writer {
    private final FileWriter file;

    /**
     * Opens file from path
     * @param path
     */
    public Writer(final String path) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.file = fileWriter;
    }

    /**
     * Writes string
     * @param str
     */
    public void write(final String str) {
        try {
            file.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close file
     */
    public void close() {
        try {
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
