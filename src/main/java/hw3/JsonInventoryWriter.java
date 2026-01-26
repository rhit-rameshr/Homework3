package hw3;
// Mason
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;

public class JsonInventoryWriter {

    StringWriter sw;
    FileWriter fw;
    JsonArrayBuilder arrayBuilder;
    public JsonInventoryWriter(String jsonFileName, String directoryFileName) throws IOException {
        this.sw = new StringWriter();
        File file = new File(directoryFileName, jsonFileName);
        this.fw = new FileWriter(file);
        this.arrayBuilder = Json.createArrayBuilder();
    }

    public boolean writeGuitar(JsonGuitarRecord record) throws IOException {

        JsonObject json = Json.createObjectBuilder()
                .add("serialNumber", record.serialNumber)
                .add("price", record.price)
                .add("builder", record.builder)
                .add("model", record.model)
                .add("guitarType", record.type)
                .add("numStrings", record.numStrings)
                .add("backWood", record.backWood)
                .add("topWood", record.topWood)
                .build();
        arrayBuilder.add(json);
        return true;
    }

    public boolean closeSaver() {
        try (JsonWriter jw = Json.createWriter(fw)) {
            jw.writeArray(arrayBuilder.build());
            jw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
