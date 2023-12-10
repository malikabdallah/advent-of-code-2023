package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReaderUtil {

    public static List<String> read(String input) {
        ClassLoader classLoader = FileReaderUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(input);
        List<String> list = new ArrayList<>();
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {

                    list.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;


    }
}
