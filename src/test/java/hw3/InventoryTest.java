package hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import hw3.*;

/* A sample unit test for the following guitar found in JSON data file:
{
    "serialNumber": "122784",
    "price": 5495.95,
    "builder": "MARTIN",
    "model": "D-18",
    "guitarType": "ACOUSTIC",
    "numStrings": 6,
    "backWood": "MAHOGANY",
    "topWood": "ADIRONDACK"
}

*/

public class InventoryTest {
    private Inventory inventory;
    
    @BeforeEach
    public void setUp() throws IOException {

        String textDataFileName = "guitar_inventory.txt";
        String directoryName = "resources/";
        inventory = new Inventory();
        Loader textBasedLoader = new Loader(textDataFileName, directoryName);
        InventoryLoader.loadInventory(inventory, textBasedLoader); 
    }
    
    @Test
    public void testGetGuitar_WithValidSerialNumber() {
        // Act
        Guitar guitar = inventory.getGuitar("122784");
        System.err.println(guitar);
        
        // Assert
        assertNotNull(guitar, "Guitar should be found");
    
        assertEquals(
            "(122784,5495.95,(Martin,D-18,acoustic,6,Mahogany,Adirondack))", 
            guitar.toString());
    }
    
    @Test
    public void testGetGuitar_WithInvalidSerialNumber() {
        // Act
        Guitar guitar = inventory.getGuitar("999999");
        
        // Assert
        assertNull(guitar, "Guitar should not be found");
    }
}