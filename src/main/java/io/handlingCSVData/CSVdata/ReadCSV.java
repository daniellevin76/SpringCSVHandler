package io.handlingCSVData.CSVdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;

public class ReadCSV {

    private static String COMMA_DELIMITER = ",";

    private static ArrayList<ArrayList<String>> entireSheet = new ArrayList<>();

    public static ArrayList<ArrayList<String>> getEntireSheet() {

        if (entireSheet.isEmpty()) {

            loadCSV();
        }
        return entireSheet;
    }

    private static void loadCSV() {

        var csvFile = new ClassPathResource("sample.csv");

        try (Scanner sc = new Scanner(csvFile.getFile())) {

            int i = -1;
            while (sc.hasNextLine()) {
                i++;
                entireSheet.add(getRows(sc.nextLine(), i));
            }

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    private static ArrayList<String> getRows(String nextLine, int i) {

        ArrayList<String> rowValues = new ArrayList<String>();

        try (Scanner rowScanner = new Scanner(nextLine)) {

            rowScanner.useDelimiter(COMMA_DELIMITER);

            rowValues.add(String.valueOf(i));
            while (rowScanner.hasNext()) {

                rowValues.add(rowScanner.next());

            }
        }
        return rowValues;
    }

    public static void printCSV() {
        for (ArrayList<String> row : entireSheet) {
            System.out.println(row);
        }
    }

}