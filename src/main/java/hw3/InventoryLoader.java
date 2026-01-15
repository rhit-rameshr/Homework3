package main.java.hw3;

import java.util.List;
import java.util.ArrayList;

public class InventoryLoader {
    public static void loadInventory(Inventory inventory, Loader loader) {
        // Notes: 
        // 1) Your work to create and use the adapter pattern should result
        //    in zero changes to the following executable code
        // 2) You might need to modify the method signature in order to use
        //    your adapter

        int k = 0;
        List<String> guitarDataValues = new ArrayList<String>();
        inventory.clear();

        while (loader.hasNext()) {
            // Note:
            // See the README.md in the resources folder for the format of the text data file
        	// or just look at the guitar_inventory.txt file itself.
            final int guitarFieldMax = 8;
            k = 0;
            guitarDataValues.clear();
            while ((k < guitarFieldMax) && loader.hasNext()) {
                guitarDataValues.add(loader.next());
                k++;
            }
            if (k == guitarFieldMax) {
                // All guitar data values were successfully read in.  In the conversions below,
            	// the Enum ones have error handling built into their "fromString" methods called here.
            	// Error handling for the raw numbers is done here:
            	String serialNumber = guitarDataValues.get(0);
            	Integer numStrings = 0;
            	try {
            		numStrings = Integer.parseInt(guitarDataValues.get(5));
            	} catch (NumberFormatException e) {
            		System.out.println(">>Error - found guitar serial number "+serialNumber+" with non-numeric number of strings - replaced with 0");		
            	}
            	Double price = 0.00;
            	try {
            		price = Double.parseDouble(guitarDataValues.get(1));
            	} catch (NumberFormatException e) {
            		System.out.println(">>Error - found guitar serial number "+serialNumber+" with non-numeric price - replaced with 0.00");		
            	}
                GuitarSpec guitarSpec = new GuitarSpec(
                        Builder.fromString(guitarDataValues.get(2)),   // builder
                        guitarDataValues.get(3),                       // model
                        Type.fromString(guitarDataValues.get(4)),      // type
                        numStrings,								       // numStrings
                        Wood.fromString(guitarDataValues.get(6)),      // backWood
                        Wood.fromString(guitarDataValues.get(7))       // topWood
                        );
                
                inventory.addGuitar(
                        serialNumber,    			                   // serialNumber   
                        price,   									   // price
                        guitarSpec                                     // GuitarSpec
                    );
            }
        }
    } 
}
