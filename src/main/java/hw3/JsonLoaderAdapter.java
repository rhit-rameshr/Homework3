package hw3;
// Rishi
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// Rishi
/**
 * Adapter class that allows JSON files to be read by the InventoryLoader
 * by implementing the DataLoader interface. This adapter uses JsonInventoryReader
 * to read JSON guitar inventory files and converts them to individual string
 * fields that can be consumed by InventoryLoader one field at a time.
 */
public class JsonLoaderAdapter implements DataLoader {

    private List<String> guitarFields;
    private int currentIndex;

    /**
     * Constructor that reads the JSON file and converts it to a list of strings
     * @param jsonFileName The name of the JSON file to read
     * @param directoryName The directory containing the JSON file
     */
    public JsonLoaderAdapter(String jsonFileName, String directoryName) {
        this.guitarFields = new ArrayList<>();
        this.currentIndex = 0;

        try {
            loadJsonFile(jsonFileName, directoryName);
        } catch (IOException e) {
            System.out.println(">>Error reading JSON file: " + jsonFileName);
            e.printStackTrace();
        }
    }

    /**
     * Reads the JSON file using JsonInventoryReader and converts all guitar
     * records into a flat list of string fields in the order expected by InventoryLoader:
     * serialNumber, price, builder, model, type, numStrings, backWood, topWood
     */
    private void loadJsonFile(String jsonFileName, String directoryName) throws IOException {
        // Use JsonInventoryReader to read the JSON file
        JsonInventoryReader reader = new JsonInventoryReader(jsonFileName, directoryName);

        // Process each guitar record from the reader
        while (reader.hasNext()) {
            JsonGuitarRecord record = reader.nextGuitar();

            // Convert the record to individual string fields in the order expected by InventoryLoader
            // Order: serialNumber, price, builder, model, type, numStrings, backWood, topWood
            guitarFields.add(record.serialNumber);
            guitarFields.add(String.valueOf(record.price));
            guitarFields.add(record.builder);
            guitarFields.add(record.model);
            guitarFields.add(record.type);
            guitarFields.add(String.valueOf(record.numStrings));
            guitarFields.add(record.backWood);
            guitarFields.add(record.topWood);
        }
    }

    /**
     * Checks if there are more fields to read
     * @return true if there are more fields, false otherwise
     */
    @Override
    public boolean hasNext() {
        return currentIndex < guitarFields.size();
    }

    /**
     * Returns the next field value as a String
     * @return the next field value, or null if no more fields exist
     */
    @Override
    public String next() {
        if (hasNext()) {
            return guitarFields.get(currentIndex++);
        }
        return null;
    }
}