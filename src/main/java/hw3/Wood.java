package main.java.hw3;

public enum Wood {

    INDIAN_ROSEWOOD("Indian Rosewood"),
    BRAZILIAN_ROSEWOOD("Brazilian Rosewood"),
    MAHOGANY("Mahogany"),
    MAPLE("Maple"),
    COCOBOLO("Cocobolo"),
    CEDAR("Cedar"),
    ADIRONDACK("Adirondack"),
    ALDER("Alder"),
    SITKA("Sitka"),
    ANY("Unspecified");

    private final String printName;

    private Wood(String printName) {
        this.printName = printName;
    }

    @Override
    public String toString() {
        return printName; 
    }

    public static Wood fromString(String text) {
        for (Wood w : Wood.values()) {
            if (w.name().equalsIgnoreCase(text)) {
                return w;
            }
        }
        return ANY;
    }
}
