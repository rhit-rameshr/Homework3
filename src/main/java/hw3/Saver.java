package main.java.hw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * This is the text file Saver, used by InventorySaver, which is supposed 
 * to be the same for this or any other Savers used.
 */
public class Saver {

	BufferedWriter writer;
	
	public Saver(String dataFileName, String directoryName) {
		try { // opens the output file
			writer = new BufferedWriter(new FileWriter(directoryName+dataFileName));
		} catch (IOException e) {
			System.out.println(">>Error trying to create output file to write inventory.");
			e.printStackTrace();
		} 
	}
	
	public boolean writeNext(String data) {
		try {
            // Writes next line to the file, using default UTF-8 encoding
            writer.write(data); 
            writer.newLine(); // Add newline on end of line
            //System.out.println("Successfully wrote line to the file = "+data);
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred writing inventory to the output file.");
            return false;
        }
	}
	
	public boolean closeSaver() {
		try {
			writer.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
