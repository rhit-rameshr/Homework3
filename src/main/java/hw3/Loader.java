package main.java.hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Loader implements DataLoader {

    private boolean hasNextLine = false;
    private BufferedReader reader = null;
    String nextLine;

    public Loader(String dataFileName, String directoryName) throws IOException {
        reader = new BufferedReader(new FileReader(directoryName + dataFileName));
        getNextLine();
    }

    @Override
    public boolean hasNext() {
        return this.hasNextLine;
    }

    @Override
    public String next() {
        String lineToReturn = nextLine;
        getNextLine();
        return lineToReturn;
    }


    public boolean getNextLine() {
        boolean notGot = true;
        while (notGot) {
            try {
                nextLine = reader.readLine();
                if (nextLine == null) { // end of file
                    this.hasNextLine = false;
                    notGot = false;
                } else if (nextLine.length() > 0) {
                    this.hasNextLine = true;
                    notGot = false;
                }

            } catch (IOException e) {
                System.out.println(">>Error reading file.");
                this.hasNextLine = false;
                return false;
            }
        }
        return true;
    }
}
