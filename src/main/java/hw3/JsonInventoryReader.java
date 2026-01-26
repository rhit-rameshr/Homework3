package hw3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;

/**
 * JsonInventoryReader reads guitar inventory data from JSON files.
 * This class is the counterpart to JsonInventoryWriter and provides
 * methods to read guitar records from a JSON array.
 */
public class JsonInventoryReader {

    private List<JsonGuitarRecord> guitarRecords;
    private int currentIndex;

    /**
     * Constructor that opens and reads the JSON file
     * @param jsonFileName The name of the JSON file to read
     * @param directoryFileName The directory containing the JSON file
     * @throws IOException if there's an error reading the file
     */
    public JsonInventoryReader(String jsonFileName, String directoryFileName) throws IOException {
        this.guitarRecords = new ArrayList<>();
        this.currentIndex = 0;

        File file = new File(directoryFileName, jsonFileName);
        readJsonFile(file);
    }

    /**
     * Reads the JSON file and parses it into JsonGuitarRecord objects
     * Handles two formats:
     * 1. Direct array: [ {guitar}, {guitar}, ... ]
     * 2. Object wrapper: { "inventory": [ {guitar}, {guitar}, ... ] }
     *
     * @param file The file to read
     * @throws IOException if there's an error reading the file
     */
    private void readJsonFile(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file);
             JsonReader jsonReader = Json.createReader(fileReader)) {

            JsonArray guitarArray;

            // Read the JSON structure - could be array or object
            JsonStructure structure = jsonReader.read();

            if (structure.getValueType() == JsonValue.ValueType.ARRAY) {
                // Format 1: Direct array
                guitarArray = (JsonArray) structure;
            } else if (structure.getValueType() == JsonValue.ValueType.OBJECT) {
                // Format 2: Object with "inventory" key
                JsonObject rootObject = (JsonObject) structure;
                if (rootObject.containsKey("inventory")) {
                    guitarArray = rootObject.getJsonArray("inventory");
                } else {
                    throw new IOException("JSON object does not contain 'inventory' key");
                }
            } else {
                throw new IOException("Invalid JSON format: expected array or object");
            }

            // Parse each guitar object and convert to JsonGuitarRecord
            for (int i = 0; i < guitarArray.size(); i++) {
                JsonObject guitarObj = guitarArray.getJsonObject(i);
                JsonGuitarRecord record = parseGuitarObject(guitarObj);
                guitarRecords.add(record);
            }
        }
    }

    /**
     * Parses a JSON object into a JsonGuitarRecord
     * @param guitarObj The JSON object representing a guitar
     * @return A JsonGuitarRecord containing the guitar data
     */
    private JsonGuitarRecord parseGuitarObject(JsonObject guitarObj) {
        JsonGuitarRecord record = new JsonGuitarRecord();

        // Populate the record with data from JSON
        record.serialNumber = guitarObj.getString("serialNumber");
        record.price = guitarObj.getJsonNumber("price").doubleValue();
        record.builder = guitarObj.getString("builder");
        record.model = guitarObj.getString("model");
        record.type = guitarObj.getString("guitarType");
        record.numStrings = guitarObj.getInt("numStrings");
        record.backWood = guitarObj.getString("backWood");
        record.topWood = guitarObj.getString("topWood");

        return record;
    }

    /**
     * Checks if there are more guitar records to read
     * @return true if there are more records, false otherwise
     */
    public boolean hasNext() {
        return currentIndex < guitarRecords.size();
    }

    /**
     * Returns the next guitar record
     * @return The next JsonGuitarRecord, or null if no more records exist
     */
    public JsonGuitarRecord nextGuitar() {
        if (hasNext()) {
            return guitarRecords.get(currentIndex++);
        }
        return null;
    }

    /**
     * Returns all guitar records as a list
     * @return List of all JsonGuitarRecord objects
     */
    public List<JsonGuitarRecord> getAllGuitars() {
        return new ArrayList<>(guitarRecords);
    }

    /**
     * Returns the total number of guitar records
     * @return The number of guitars in the inventory
     */
    public int getSize() {
        return guitarRecords.size();
    }

    /**
     * Resets the reader to start reading from the beginning again
     */
    public void reset() {
        currentIndex = 0;
    }
}