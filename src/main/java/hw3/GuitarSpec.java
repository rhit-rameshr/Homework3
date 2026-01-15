package main.java.hw3;

public class GuitarSpec {
 
  private Builder builder; 
  private String model;
  private Type type;
  private int numStrings;
  private Wood backWood;
  private Wood topWood;

  public GuitarSpec(Builder builder, String model, Type type,
                    int numStrings, Wood backWood, Wood topWood) {
    this.builder = builder;
    this.model = model;
    this.type = type;
    this.numStrings = numStrings;
    this.backWood = backWood;
    this.topWood = topWood;
  }

  public Builder getBuilder() {
    return builder;
  }

  public String getModel() {
    return model;
  }

  public Type getType() {
    return type;
  }

  public int getNumStrings() {
    return numStrings;
  }

  public Wood getBackWood() {
    return backWood;
  }

  public Wood getTopWood() {
    return topWood;
  }

  public boolean matches(GuitarSpec otherSpec) {
      // The otherSpec at a minimum must specify builder and numStrings
      if (otherSpec.builder != null && otherSpec.builder != Builder.ANY && this.builder != otherSpec.builder)
          return false;
      if (this.numStrings != otherSpec.numStrings)
          return false;

      // The rest of the otherSpec can have null values which means "don't care"
      if ((otherSpec.model != null   && !otherSpec.model.equals("ANY")) && (!otherSpec.model.isEmpty()) &&
              (!this.model.equalsIgnoreCase(otherSpec.model)))
          return false;
      if (otherSpec.type != null && otherSpec.type != Type.ANY && this.type != otherSpec.type)
          return false;
      if (otherSpec.backWood != null && otherSpec.backWood != Wood.ANY && this.backWood != otherSpec.backWood)
          return false;
      return (otherSpec.topWood == null || otherSpec.topWood == Wood.ANY || this.topWood == otherSpec.topWood);
  }

  @Override
  public String toString() {
      return 
      "(" + 
      this.builder + ',' +
      this.model + ',' +
      this.type + ',' +
      this.numStrings + ',' +
      this.backWood + ',' +
      this.topWood + 
      ')';
  }

}
