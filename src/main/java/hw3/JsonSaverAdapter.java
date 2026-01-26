package hw3;

import javax.xml.crypto.Data;
import java.io.IOException;

public class JsonSaverAdapter implements DataSaver {
    private JsonInventoryWriter writer;
    private JsonGuitarRecord current;

    public JsonSaverAdapter(String jsonFileName, String directoryFileName)  {
        try {
            this.writer = new JsonInventoryWriter(jsonFileName, directoryFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.current = new JsonGuitarRecord();
    }
    public boolean writeNext(String data) {
        boolean full = current.add(data);
        if (full) {
            try {
                this.writer.writeGuitar(current);
            } catch (Exception e) {
                this.current = new JsonGuitarRecord();
                e.printStackTrace();
                return false;
            }
            this.current = new JsonGuitarRecord();
            return true;
        }
        return true;
    }
    public boolean closeSaver() {
       writer.closeSaver();
       return false;
    }
}
