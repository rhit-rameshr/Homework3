package hw3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class GuitarInventoryTester {

    // Here we pretend a user named Erin is inputting what she wants in a guitar.
    // We search for it and show her the results in the console.  And then let her
    // select just some of the search criteria for a wider search, if desired.
    GuitarSpec whatErinLikes;

    private void runTest(Inventory inventory) {
        String userName = "Erin";
        Builder erinBuilder = Builder.FENDER;
        String erinModel = "Stratocastor";
        Type erinType = Type.ELECTRIC;
        int erinNumStrings = 6;
        Wood erinBackWood = Wood.ALDER;
        Wood erinTopWood = Wood.ALDER;
        whatErinLikes =  // saved as an attribute to add a guitar to inventory later
                new GuitarSpec(erinBuilder, erinModel,
                        erinType, erinNumStrings, erinBackWood, erinTopWood);
        showMatchingGuitars(userName, inventory, whatErinLikes);
        System.out.print("Would you like us to show partial matches to your search (y or n)? ");
        Scanner scanner = new Scanner(System.in);
        String inputValue = scanner.nextLine();
        if (inputValue.equals("y")) {
            System.out.println("What specs you like to retain in the search? Type any or all of these, separated by spaces --");
            System.out.print("1 = Builder, 2 = Model, 3 = Type, 4 = Back Wood, 5 = Top Wood ");
            inputValue = scanner.nextLine();
            ArrayList<Integer> retainList = new ArrayList<Integer>();
            Builder erinBuilder1 = Builder.FENDER;
            String erinModel1 = "Stratocastor";
            Type erinType1 = Type.ELECTRIC;
            int erinNumStrings1 = 6;
            Wood erinBackWood1 = Wood.ALDER;
            Wood erinTopWood1 = Wood.ALDER;
            if (!inputValue.contains("1")) {
                erinBuilder1 = Builder.ANY;
            }
            if (!inputValue.contains("2")) {
                erinModel1 = "ANY";
            }
            if (!inputValue.contains("3")) {
                erinType1 = Type.ANY;
            }
            if (!inputValue.contains("4")) {
                erinBackWood1 = Wood.ANY;
            }
            if (!inputValue.contains("5")) {
                erinTopWood1 = Wood.ANY;
            }
            System.out.println("Sending spec with "+erinBuilder1+", "+erinModel1+", "+erinType1+
                    ", "+erinNumStrings1+", "+erinBackWood1+","+erinTopWood1);
            GuitarSpec whatErinLikes1 =
                    new GuitarSpec(erinBuilder1, erinModel1,
                            erinType1, erinNumStrings1, erinBackWood1, erinTopWood1);
            showMatchingGuitars(userName, inventory, whatErinLikes1);
        }
    }

    // This method reads the guitar inventory file using the appropriate loader
    private void initializeInventory(Inventory inventory, String dataFileName,
                                     String directoryName, boolean useJson) {
        DataLoader loader = null;
        try {
            if (useJson) {
                // Use JSON loader adapter
                loader = new JsonLoaderAdapter(dataFileName, directoryName);
                System.out.println("Using JSON file format...");
            } else {
                // Use text-based loader
                loader = new Loader(dataFileName, directoryName);
                System.out.println("Using text file format...");
            }
        } catch (IOException e) {
            System.out.println(">>Error opening guitar inventory file.");
            e.printStackTrace();
        }

        InventoryLoader.loadInventory(inventory, loader);
        System.out.println("Loaded guitar inventory, size = "+inventory.getSize());
    }

    // This test app reads-in the guitar inventory file, displays the results and then
    // runs the test for a single user, above.  It then deletes one guitar, from inventory,
    // creates one new guitar and adds it to inventory, and writes out the results to
    // a new file (either text or JSON format based on user choice).
    private void runApp() {
        Scanner scanner = new Scanner(System.in);

        // Ask user which format to use for INPUT
        System.out.println("=== Guitar Inventory System ===");
        System.out.print("Choose INPUT file format - (1) Text or (2) JSON: ");
        String inputChoice = scanner.nextLine().trim();
        boolean useJsonInput = inputChoice.equals("2");

        // Set input file name based on choice
        String inputFileName;
        if (useJsonInput) {
            inputFileName = "guitar_inventory.json";
        } else {
            inputFileName = "guitar_inventory.txt";
        }

        String directoryName = "resources/";
        Inventory inventory = new Inventory();

        // Load the inventory using the chosen format
        this.initializeInventory(inventory, inputFileName, directoryName, useJsonInput);

        System.out.println("\nAll guitars in inventory are:");
        System.out.println(inventory);

        // Run the user test search
        this.runTest(inventory);

        // Try maintenance on the inventory in memory:
        inventory.removeGuitar("77023");
        inventory.addGuitar("12345", 1699.95, whatErinLikes); // guitar spec saved from test search
        System.out.println("\nAfter removing 77023 and adding 12345, all guitars in inventory are:");
        System.out.println(inventory);

        // Ask user which format to use for OUTPUT
        System.out.print("\nChoose OUTPUT file format - (1) Text or (2) JSON: ");
        String outputChoice = scanner.nextLine().trim();
        boolean useJsonOutput = outputChoice.equals("2");

        // Set output file name and create appropriate saver
        String outputFileName;
        DataSaver saver;

        if (useJsonOutput) {
            outputFileName = "guitar_inventory_output.json";
            saver = new JsonSaverAdapter(outputFileName, directoryName);
            System.out.println("Using JSON output format...");
        } else {
            outputFileName = "guitar_inventory_output.txt";
            saver = new Saver(outputFileName, directoryName);
            System.out.println("Using text output format...");
        }

        // Save the inventory using the chosen format
        InventorySaver.saveInventory(inventory, saver);
        System.out.println("Revised inventory written to "+outputFileName);

        scanner.close();
    }

    public static void main(String[] args) {
        GuitarInventoryTester app = new GuitarInventoryTester();
        app.runApp();
    }

    // Common code called twice in user test, above:
    private void showMatchingGuitars(String userName, Inventory inventory, GuitarSpec whatCustomerLikes) {
        Set<Guitar> matchingGuitars = inventory.search(whatCustomerLikes);
        if (!matchingGuitars.isEmpty()) {
            System.out.println(userName+", you might like any of these "+matchingGuitars.size()+" guitars:");
            for (Guitar g : matchingGuitars) {
                GuitarSpec spec = g.getSpec();
                System.out.println("  We have a " +
                        spec.getBuilder() + " " + spec.getModel() + " " +
                        spec.getType() + " guitar:\n     " +
                        spec.getBackWood() + " back and sides,\n     " +
                        spec.getTopWood() + " top.\n  You can have it for only $" +
                        g.getPrice() + "!\n  ----");
            }
        } else {
            System.out.println("Sorry, "+userName+", we have nothing for you that matches these choices.");
        }
    }
}