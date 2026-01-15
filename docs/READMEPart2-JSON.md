### JSON Implementation of Your Adapter

### First - Advice from Dr Hollingsworth:

#### JSON Data File
* The src/main/resources/README.md describes the format of the .json data file
* The data file itself is located in the src/main/resources folder

#### JSON Dependency:
* FYI: So that you can import JSON related modules, you will need to have a Maven project with dependencies added to that project's pom.xml.  An example pom.xml file is included with this project.
* These Maven dependencies support the following import statement that you will need to utilize to use a package that reads and writes Json files:
`import jakarta.json.*;`

#### JSON Documentation
* [Jakarta EE Tutorial](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/web/jsonp/jsonp.html)
* [jakarta.json-api](https://javadoc.io/doc/jakarta.json/jakarta.json-api/latest/help-doc.html)

### Second - Advice from Matteo Calviello, our TA.  You can also ask Matteo for additional advice if you get stuck doing this.  He did it similarly to Dr Holly, above:

#### This is meant to give some guidance on what we thought might be a hard part of the homework: finding and using a tool to process Json files.

#### 1. What You Are Supposed To Do
Your task is to extend the current inventory system to read and write JSON files without changing the core execution logic in InventoryLoader.java and InventorySaver.java.

##### The InventoryLoader expects data for each guitar to be delivered as 8 individual strings (Serial Number, Price, Builder, Model, Type, Number of Strings, Back Wood, and Top Wood). Your adapter must be able to "flatten" a JSON object into this specific sequence of strings.

#### 2. Package & Dependency Suggestions
You can choose any tool you want to do this, one suggestions is using the Jakarta JSON-P library.

##### Required Dependencies: Add both the API and the Provider implementation to your pom.xml.

##### jakarta.json-api provides the interfaces like JsonObject and JsonArray.

##### org.glassfish:jakarta.json is the "Provider" that handles the actual JSON processing.