package io.handlingCSVData.CSVdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller implements ErrorController {
    ArrayList<ArrayList<String>> sheet = ReadCSV.getEntireSheet();

    @RequestMapping(value = "/show-csv", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public void showCSV(HttpServletResponse response) throws IOException {

        var csvFile = new ClassPathResource("/resources/sample.csv");

        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        StreamUtils.copy(csvFile.getInputStream(), response.getOutputStream());

    }

    @RequestMapping(value = "/row-list-json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String showJSON() {
        return JSONFrmt.returJSON(ReadCSV.getEntireSheet());

    }

    @RequestMapping(value = "/one-column-json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String showOneColumnJSON(String header) {
        List<String> column = DataHandler.createHash().get(header);
        ArrayList<String> columnArr = new ArrayList<String>(column);
        return JSONFrmt.returnOneJSONCol(header, columnArr);

    }

    @RequestMapping(value = "/one-column-sorted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String showOneColumnSorted(String header) {
        List<String> column = DataHandler.createHash().get(header);
        ArrayList<String> columnArr = new ArrayList<String>(column);
        DataHandler.oneColSorted(columnArr);
        return JSONFrmt.returnOneJSONCol(header, columnArr);

    }

    @RequestMapping(method = RequestMethod.GET, value = "erroneous-rows", produces = MediaType.APPLICATION_JSON_VALUE)
    public String displayRowsWithWrongValues() {

        return JSONFrmt.returJSON(DataHandler.rowsWithWrongValues());

    }

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public String error() {
        return "no function like that, try this: " + start();
    }

    @Override
    public String getErrorPath() {

        return PATH;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/start", produces = MediaType.TEXT_HTML_VALUE)
    public String start() {

        String index = "";
        index += "<html> <head> <title>Working with CSV-files</title> </head> ";
        index += " </body>";
        index += " <h4>Methods for this API</h4>";
        index += " <ul>";
        index += " <li><a href = \"/start\">Start</a></li> ";
        index += " <li><a href = \"/one-column-json\">Display one column</a></li> ";
        index += " <li><a href = \"/row-list-json\">Show list row-wise</a></li> ";
        index += " <li><a href = \"/one-column-sorted\">one-column-sorted</a></li> ";
        index += " <li><a href = \"/erroneous-rows\">Rows with wrong values</a></li> ";
        index += " </ul>";
        index += "</body></html> ";

        return index;
    }

}
