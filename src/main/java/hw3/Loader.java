package main.java.hw3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/*
 * This is the text file Loader, used by InventoryLoader, which is supposed 
 * to be the same for this or any other Loaders used.
 */
public class Loader {
    private boolean hasNextLine = false;
    private BufferedReader reader = null;
    String nextLine;

    public Loader(String dataFileName, String directoryName) throws IOException {
    	reader = new BufferedReader(new FileReader(directoryName+dataFileName)); 
    	// Read first line, if any:
    	getNextLine();
    }

    public boolean hasNext() {
        return this.hasNextLine;
    }

    public String next() {
  		String lineToReturn = nextLine; // Read one-ahead to preset "hasNextLine" value.
  		//System.out.println("Delivering next line to Inventory Loader = "+lineToReturn);
  		getNextLine();
        return lineToReturn;
    }

    // Common read-next-line routine - detects end of file,
    // skips blank lines, returns true for non-file-errors
    public boolean getNextLine() {
    	boolean notGot = true;
    	while (notGot) {
	    	try { 
				nextLine = reader.readLine();
				if (nextLine == null) { // end of file
					this.hasNextLine = false;
					notGot = false;
				} else if (nextLine.length()>0) {
					this.hasNextLine = true;
					notGot = false;
				} // else skip reading blank lines such as those inserted for human reading
			} catch (IOException e) {
	       	 	System.out.println(">>Error reading file.");
				this.hasNextLine = false;
				return false;
			}
    	}
    	return true;
    }

}
