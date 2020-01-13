package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PackageReader implements Reader {

    private String fileToRead;
    private List<String> lines;
    private int index;

    public PackageReader() {
        fileToRead = "/var/lib/dpkg/status";
        index = 0;
    }

    public void readFile() {
        System.out.println("reading file: " + fileToRead);
        try {Scanner scanner = new Scanner(new File(fileToRead));
            lines = getStrings(scanner);
            index = 0;
        } catch (Exception e) {
            try {
                Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(fileToRead), "UTF-8");
                lines = getStrings(scanner);
                index = 0;
            } catch (Exception ex) {
                System.out.println("File " + fileToRead + " not found, using mock");
                fileToRead = "statusMock.txt";
                readFile();
            }
        }


    }
    
    private List<String> getStrings(Scanner scanner) {
        List<String> strings = new ArrayList<>();
        while (scanner.hasNextLine()) {
            strings.add(scanner.nextLine());
        }
        return strings;
    }

    public String nextLine() {
        if (lines == null || index == lines.size()) {
            return null;
        }
        index++;
        return lines.get(index - 1);
    }

    public String previousLine() {
        if (lines == null) {
            return "";
        }
        index--;
        return lines.get(index);
    }
}
