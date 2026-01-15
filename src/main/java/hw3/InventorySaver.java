package main.java.hw3;

public class InventorySaver {
	public static void saveInventory(Inventory inventory, Saver saver) {
		// Notes: 
        // 1) Your work to create and use the adapter pattern should result
        //    in zero changes to the following executable code
        // 2) You might need to modify the method signature in order to use
        //    your adapter
		
		inventory.initializeIterator();  // To get the list of guitars in inventory
		
		while (true) {
			Guitar nextGuitar = inventory.getNextGuitar(); // use the iterator
			if (nextGuitar == null) break; // end of the inventory of guitars
			// else write out the 8 lines of data for the next guitar in inventory
			saver.writeNext(nextGuitar.getSerialNumber());
			saver.writeNext(String.valueOf(nextGuitar.getPrice()));
			GuitarSpec nextSpec = nextGuitar.getSpec();
			saver.writeNext(nextSpec.getBuilder().toString());
			saver.writeNext(nextSpec.getModel());
			saver.writeNext(nextSpec.getType().toString());
			saver.writeNext(String.valueOf(nextSpec.getNumStrings()));
			saver.writeNext(nextSpec.getBackWood().toString());
			saver.writeNext(nextSpec.getTopWood().toString());
		}
		saver.closeSaver();
	}
}
