package io.handlingCSVData.CSVdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DataHandler {
    public static String header;

    static ArrayList<ArrayList<String>> entireSheet = ReadCSV.getEntireSheet();

    public static ArrayList<ArrayList<String>> sortEntireList(ArrayList<ArrayList<String>> sheet) {

        Collections.sort(sheet, new ListComp());

        return sheet;

    }

    public static int getSortIndex() {
        int position = 0;
        for (int i = 0; i < entireSheet.get(0).size(); i++) {
            String hdr = entireSheet.get(0).get(i);

            if (hdr.equals(header)) {
                position = i;
            }

        }
        return position;
    }

    public static HashMap<String, List<String>> createHash() {

        HashMap<String, List<String>> csvList = new HashMap<>();

        for (int col = 0; col < entireSheet.get(0).size(); col++) {

            ArrayList<String> column = returnOneColumn(col);

            csvList.put(column.get(0), column.subList(1, column.size()));
        }

        return csvList;
    }

    public static ArrayList<ArrayList<String>> rowsWithWrongValues() {
        ArrayList<String> dummy = new ArrayList<String>();
        dummy.add("dummy");
        ArrayList<ArrayList<String>> wrongRows = new ArrayList<ArrayList<String>>();
        wrongRows.add(dummy);
        ArrayList<ArrayList<String>> subList = new ArrayList<ArrayList<String>>(
                entireSheet.subList(1, entireSheet.size()));
        for (ArrayList<String> row : subList) {

            Double units = Double.parseDouble(row.get(6));
            Double unitCost = Double.parseDouble(row.get(7));
            Double totalCost = Double.parseDouble(row.get(8));
            Double totalCostCalc = units * unitCost;
            int tol = 1;
            if ((Math.abs(totalCostCalc - totalCost)) > tol) {
                wrongRows.add(row);

            }

        }
        return wrongRows;
    }

    public static ArrayList<String> returnOneColumn(int col) {
        ArrayList<String> column = new ArrayList<String>();

        for (int row = 0; row < entireSheet.size(); row++) {
            column.add(entireSheet.get(row).get(col));

        }

        return column;
    }

    public static ArrayList<String> oneColSorted(ArrayList<String> column) {
        Collections.sort(column);
        return column;

    }

}
