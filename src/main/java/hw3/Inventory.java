package main.java.hw3;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Inventory {
  private Map<String, Guitar> guitars;
  private Iterator<Map.Entry<String, Guitar>> iterator; // used for saving inventory

  public Inventory() {
        this.guitars = new HashMap<>();
  }
  public void clear() {
      this.guitars = new HashMap<>();
  }

  public void addGuitar(String serialNumber, double price,
                        GuitarSpec spec) {
    Guitar guitar = new Guitar(serialNumber, price, spec);
    this.guitars.put(serialNumber, guitar);
  }
  
  public Guitar removeGuitar(String serialNumber) {
	  return this.guitars.remove(serialNumber); // returns null if nonexistent
  }

  public Guitar getGuitar(String serialNumber) {
    return this.guitars.getOrDefault(serialNumber, null); // returns null if nonexistent
  }

  public Set<Guitar> search(GuitarSpec searchSpec) {
      Set<Guitar> matchingGuitars = new HashSet<>();
      for (Guitar g : this.guitars.values()) {
          if (g.getSpec().matches(searchSpec)) {
              matchingGuitars.add(g);
          }
      }
      return matchingGuitars;
  }

  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();
      int index = 0;
      for (Guitar g : this.guitars.values()) {
          sb.append(g);
          index++;
          if (index < guitars.size()) {
        	  sb.append("\n");
          }
      }
      return "{" + sb.toString() + "}";
    }
  
  public int getSize() {
	  return guitars.size();
  }
  
  // For saving inventory to file - need to initialize for each save by InventorySaver
  public void initializeIterator() {
	  this.iterator = guitars.entrySet().iterator();
  }
  
  // This method then delivers to InventorySaver the next guitar record to be saved
  public Guitar getNextGuitar() {
	  if (iterator.hasNext()) {
		  Map.Entry<String, Guitar> entry = iterator.next();
		  return entry.getValue();
	  } else {
		  return null; // to flag that we're at the end
	  }
  }
}
