package hw3;

public class JsonGuitarRecord {
    String serialNumber;
    double price;
    String builder;
    String model;
    String type;
    int numStrings;
    String backWood;
    String topWood;

    private int fieldIndex = 0;

    public boolean add(String value) {
        switch (fieldIndex++) {
            case 0 -> serialNumber = value;
            case 1 -> price = Double.parseDouble(value);
            case 2 -> builder = value;
            case 3 -> model = value;
            case 4 -> type = value;
            case 5 -> numStrings = Integer.parseInt(value);
            case 6 -> backWood = value;
            case 7 -> {topWood = value; return true;}
            default -> { return true; }
        }
        return false;
    }

    public void clear(){
        serialNumber = null;
        price = 0;
        builder = null;
        model = null;
        type = null;
        numStrings = 0;
        backWood = null;
        topWood = null;
        fieldIndex = 0;
    }
}
