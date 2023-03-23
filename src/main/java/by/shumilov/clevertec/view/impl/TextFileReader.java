package by.shumilov.clevertec.view.impl;

import by.shumilov.clevertec.view.Reader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Class uses to create String from txt file.
 */
@Component
public class TextFileReader implements Reader {

    /**
     * The method is used to read txt file and make String value from it.
     *
     * @return String value
     */
    @Override
    public String read(String filename) {
        File file = new File(filename);
        StringBuilder result = new StringBuilder();
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            result.append(line).append("\n");
        }
        return result.toString().trim();
    }

    /**
     * The method is used to convert file name into File object.
     *
     * @param fileName - name of file.
     * @return - File object.
     */
    public File getFileFromResource(final String fileName)
            throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) {
            return new File(resource.toURI());
        } else {
            throw new URISyntaxException(fileName, ": couldn't be parsed.");
        }
    }
}
