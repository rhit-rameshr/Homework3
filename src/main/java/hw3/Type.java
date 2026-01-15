package main.java.hw3;

public enum Type {
    ACOUSTIC("acoustic"),
    ELECTRIC("electric"),
    ANY("Unspecified");

    private final String printName;

    private Type(String printName) {
        this.printName = printName;
    }

    @Override
    public String toString() {
        return printName;
    }

    public static Type fromString(String text) {
        for (Type t : Type.values()) {
            if (t.name().equalsIgnoreCase(text)) {
                return t;
            }
        }
        return ANY;
    }
}

