package persistence;

import model.*;
import org.json.*;

import java.io.*;

// represents a reader that reads the string data from data files stored in
// the data directory
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: returns the source of the file
    public String returnSource() {
        return source;
    }

    // REQUIRES: source to exist in data directory
    // EFFECTS: loads the file from the data directory and converts all the
    // text into a JSONObject with the same format as it was saved in
    public JSONObject loadData(String source) {
        // initializes a new empty string for jsonString
        String loadFileName = ("data/" + source + ".json");
        // initialize a StringBuilder object to copy text into JSONString
        // refer to string builder documentation https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
        StringBuilder jsonString = new StringBuilder();

        // uses a buffered reader to read through the file
        // Refer to documentation: https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
        try {
            BufferedReader reader = new BufferedReader(new FileReader(loadFileName));
            String line = "";
            // runs a while loop until there are no more lines left to read
            // from the file
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // returns the jsonData from the file
            JSONObject jsonData = new JSONObject(jsonString.toString());
            return jsonData;
            // catches an IOException if there is an error with the file
        } catch (IOException exception) {
            return null;
        }
    }
}
