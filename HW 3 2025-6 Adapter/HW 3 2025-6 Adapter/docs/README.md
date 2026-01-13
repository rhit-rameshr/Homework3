## HW3 - Data Input Files
This resources folder contains two different versions of Rick's Guitar Inventory. 

1. guitar_inventory.txt
2. guitar_inventory.json

In the current design of Rick's Guitar app, each guitar has 8 pieces data: serialNumber, price, builder, model, guitarType, numStrings,backWood, topWood,

The actual guitar data in these two inventories are identical to each other. The inventories were created from the initialization code found in FindGuitarTest.java. This code came from HF OO Design & Analysis's Chapter 1 and the data values were hard-coded-in-source in one of FindGuitarTest's methods.

### About: guitar_inventory.txt
The data in this file stores one piece of guitar data per line as follows:

1. serialNumber
2. price
3. builder
4. model
5. guitarType
6. numStrings
7. backWood
8. topWood

Here is an example of one guitar's data:
V95693
1499.95
FENDER
Stratocastor
ELECTRIC
6
ALDER
ALDER


### About: guitar_inventory.json
The guitar data in this file is stored in json format.

The root object holds a json array under the name "inventory". The array holds json guitar objects, each member of a guitar object is a key-value pair. The keys represent the different types of guitar data.

Here's an example of one guitar object:

    {
      "serialNumber": "V95693",
      "price": 1499.95,
      "builder": "FENDER",
      "model": "Stratocastor",
      "guitarType": "ELECTRIC",
      "numStrings": 6,
      "backWood": "ALDER",
      "topWood": "ALDER"
    }

test