package io.handlingCSVData.CSVdata;

import java.util.ArrayList;

public class JSONFrmt {

    public static String returnOneJSONCol(String header, ArrayList<String> columnArr) {

        String JSONCOltoSend = " { \"" + header + "\": \"" + columnArr.toString() + "\"} ";

        return JSONCOltoSend;
    }

    public static String returJSON(ArrayList<ArrayList<String>> entireSheet) {

        String JSONtoSend = "  {\"Order List\": [";
        for (int i = 1; i < entireSheet.size(); i++) {
            ArrayList<String> row;
            row = entireSheet.get(i);
            String pattern = "{ \"Row\":\"%s\",\"Order Date\":\"%s\", \"Region\":\"%s\", \"Representant 1\": \"%s\", \"Representant 2\": \"%s\", \"Item\": \"%s\", \"Units\": \"%s\", \"Unit Cost\": \"%s\", \"Total Cost\": \"%s\"}";

            JSONtoSend += String.format(pattern, row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5),
                    row.get(6), row.get(7), row.get(8));

            if (i < entireSheet.size() - 1) {

                JSONtoSend += ",";
            }

        }
        JSONtoSend += "]}";
        // System.out.println(JSONtoSend);
        return JSONtoSend;

    }

    public static void main(String[] args) {

        System.out.println(ReadCSV.getEntireSheet().get(0));

    }

}
