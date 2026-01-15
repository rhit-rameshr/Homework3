package main.java.hw3;

public enum Builder {

    FENDER("Fender"),
    MARTIN("Martin"),
    GIBSON("Gibson"),
    COLLINGS("Collings"),
    OLSON("Olson"),
    RYAN("Ryan"),
    PRS("PRS"),
    ANY("Unspecified");

    private final String printName;

    private Builder(String printName) {
        this.printName = printName;
    }

    @Override
    public String toString() {
        return printName;
    }

    public static Builder fromString(String text) {
        for (Builder b : Builder.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return ANY;
    }
}
