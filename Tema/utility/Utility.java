package org.example.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utility {


    public static void createFolder(String folderPath) {
        Path path = Paths.get(folderPath);
        try {
            Files.createDirectories(path);

        } catch (IOException e) {

            System.err.println("Eroare la crearea folderului: " + e.getMessage());
        }
    }

    public static File createEmptyFile(String filePath) {
        File file = new File(filePath);

        String parent = file.getParent();
        String name = file.getName();

        String baseName;
        String extension;

        int dotIndex = name.lastIndexOf(".");
        if (dotIndex != -1) {
            baseName = name.substring(0, dotIndex);
            extension = name.substring(dotIndex);
        } else {
            baseName = name;
            extension = "";
        }

        int index = 1;
        while (file.exists()) {
            String newName = baseName + "_" + index + extension;
            file = new File(parent, newName);
            index++;
        }

        try {

            if (file.createNewFile()) {
//                System.out.println("Fișier creat: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("Eroare la crearea fișierului: " + e.getMessage());
        }

        return file;
    }

    public static void writeToFile(String content, BufferedWriter writer)  {

        try {
            writer.write(content+'\n');

        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișier: " + e.getMessage());
        }

    }

}

