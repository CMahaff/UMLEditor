package com.group3.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * @author Connor Mahaffey
 *         Brady Landis
 *         David Mengel
 */
public class DataManager {
	
	private int index;
	private TreeMap<Integer, ClassBoxData> classBoxes;
	private TreeMap<Integer, RelationshipData> relationships;
	
	/**
	 * Manages all data for saving, reloading, etc. using a system
	 * of unique integer id's.
	 */
	public DataManager() {
		index = 0;
		this.classBoxes = new TreeMap<Integer, ClassBoxData>();
		this.relationships = new TreeMap<Integer, RelationshipData>();
	}
	
	/**
	 * Add new Class Box Data to the Data Manager
	 * 
	 * @param locX the x location of the Class Box
	 * @param locY the y location of the Class Box
	 * @param width the width of the Class Box
	 * @param height the height of the Class Box
	 * @param data the data inside the Class Box sections
	 * 
	 * @return id for this new Class Box
	 */
	public int addClassBoxData(int locX, int locY, int width, int height, String[] data) {
		ClassBoxData classBoxData = new ClassBoxData(locX, locY,
													 width, height, data);
		index++;
		this.classBoxes.put(index, classBoxData);
		
		return index;
	}
	
	/**
	 * Add new Class Box Data to the Data Manager
	 * 
	 * @param locX the x location of the Class Box
	 * @param locY the y location of the Class Box
	 * @param width the width of the Class Box
	 * @param height the height of the Class Box
	 * @param title the title of the new Class Box
	 * 
	 * @return id for this new Class Box
	 */
	public int addClassBoxData(int locX, int locY, int width, int height, String title) {
		ClassBoxData classBoxData = new ClassBoxData(locX, locY,
													 width, height,
													 new String[]{ title, "" });
		index++;
		this.classBoxes.put(index, classBoxData);
		
		return index;
	}
	
	/**
	 * Update Class Box Data corresponding to a given id.
	 * 
	 * If no data exists with that id, no changes are made.
	 * 
	 * @param id the unique id of the Class Box
	 * @param locX the updated x location of the Class Box
	 * @param locY the updated y location of the Class Box
	 * @param width the updated width of the Class Box
	 * @param height the updated height of the Class Box
	 * @param data the updated String content of the Class Box
	 */
	public void updateClassBoxData(int id, int locX, int locY, int width, int height, String[] data) {
		ClassBoxData classBoxData = this.classBoxes.get(id);
		if(classBoxData != null) {
			classBoxData.setX(locX);
			classBoxData.setY(locY);
			classBoxData.setWidth(width);
			classBoxData.setHeight(height);
			classBoxData.setBoxes(data);
		}
	}
	
	/**
	 * Update Class Box Data corresponding to a given id.
	 * 
	 * This is a performance method. As we drag text boxes across the screen
	 * we do not want to be creating new text representation arrays for each
	 * pixel we move.
	 * 
	 * @param id the unique id of the Class Box
	 * @param locX the updated x location of the Class Box
	 * @param locY the updated y location of the Class Box
	 * @param width the updated width of the Class Box
	 * @param height the updated height of the Class Box
	 */
	public void updateClassBoxDataWindow(int id, int locX, int locY, int width, int height) {
		ClassBoxData classBoxData = this.classBoxes.get(id);
		if(classBoxData != null) {
			classBoxData.setX(locX);
			classBoxData.setY(locY);
			classBoxData.setWidth(width);
			classBoxData.setHeight(height);
		}
	}
	
	/**
	 * Remove a Class Box with a given id.
	 * 
	 * This will also remove any relationships associated with this class box.
	 * 
	 * @param id the id of the Class Box to remove
	 */
	public void removeClassBoxData(int id) {
		ClassBoxData classBox = this.classBoxes.remove(id);
		
		ArrayList<Integer> keysToRemove = new ArrayList<Integer>();
		
		for(Entry<Integer, RelationshipData> entry : this.relationships.entrySet()) {
			if(entry.getValue().getSourceClassBox() == classBox ||
			   entry.getValue().getDestinationClassBox() == classBox) {
				//cannot remove the key directly here, causes an error 
				//since the entry set is modified while being traversed
				keysToRemove.add(entry.getKey());
			}
		}
		
		for(int key : keysToRemove){
			this.relationships.remove(key);
		}
	}
	
	/**
	 * Add a new relationship data object
	 * @param sourceId the id of the source class box
	 * @param destinationId the id of the destination class box
	 * @param type the type of UML association
	 * @return the unique identifier for this relationship
	 */
	public int addRelationshipData(int sourceId, int destinationId, int type) {
		ClassBoxData source = this.classBoxes.get(sourceId);
		ClassBoxData destination = this.classBoxes.get(destinationId);
		
		if(source != null && destination != null) {
			RelationshipData relationshipData = new RelationshipData(source, destination, type);
			index++;
			this.relationships.put(index, relationshipData);
		}
		
		return index;
	}
	
	/**
	 * Removes all relationships between two 
	 * @param sourceId the id of the source class box
	 * @param destinationId the id of the destination class box
	 */
	
	public void removeRelationshipData(int sourceId, int destinationId) {
		ClassBoxData source = this.classBoxes.get(sourceId);
		ClassBoxData destination = this.classBoxes.get(destinationId);
		
		ArrayList<Integer> keysToRemove = new ArrayList<Integer>();
		
		if(source != null && destination != null) {
			for(Entry<Integer, RelationshipData> entry : this.relationships.entrySet()) {
				if((entry.getValue().getSourceClassBox() == source ||
					entry.getValue().getSourceClassBox() == destination ) && 
				   (entry.getValue().getDestinationClassBox() == source ||
					entry.getValue().getDestinationClassBox() == destination )) {
					
					keysToRemove.add(entry.getKey());

				}
				
	
				}
			for(int key : keysToRemove){
				this.relationships.remove(key);
			
			}
		}
	}
	
	/**
	 * removes all relationships between two classboxes through the classboxdata
	 * @param source classboxdata of the source box
	 * @param destination classboxdata of the destination box
	 */
	public void removeRelationshipData(ClassBoxData source, ClassBoxData destination) {

		
		ArrayList<Integer> keysToRemove = new ArrayList<Integer>();
		
		if(source != null && destination != null) {
			for(Entry<Integer, RelationshipData> entry : this.relationships.entrySet()) {
				if((entry.getValue().getSourceClassBox() == source ||
					entry.getValue().getSourceClassBox() == destination ) && 
				   (entry.getValue().getDestinationClassBox() == source ||
					entry.getValue().getDestinationClassBox() == destination )) {
					
					keysToRemove.add(entry.getKey());

				}
				
	
				}
			for(int key : keysToRemove){
				this.relationships.remove(key);
			
			}
		}
	}
	/**
	 * @return the collection of Class Box data
	 */
	public TreeMap<Integer, ClassBoxData> getClassBoxData() {
		return this.classBoxes;
	}
	
	/**
	 * @param id id of the Relationship Data object
	 * @return the object if found, otherwise null
	 */
	public TreeMap<Integer, RelationshipData> getRelationshipData() {
		return this.relationships;
	}
	
	/**
	 * Loads a save file into the data manager
	 * @param file UML file to load
	 */
	public Element loadModel(File file) {
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document doc = builder.parse(file);
			
			return doc.getDocumentElement();
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Could not load file from file system.");
			System.exit(1);
			
			return null;
		}
	}
	
	/**
	 * Writes save data to file
	 * @param file file to save to
	 */
	public void saveModel(File file){
		
		HashMap<ClassBoxData, Integer> conversion = new HashMap<ClassBoxData, Integer>();
		
		int newIndex = 1;
		for(ClassBoxData c : this.classBoxes.values()) {
			conversion.put(c, newIndex);
			newIndex++;
		}
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document doc = builder.newDocument();
			
			Element root = doc.createElement("UMLEditorSave");
			Element cbElement = doc.createElement("ClassBoxes");
			
			for(ClassBoxData c : this.classBoxes.values()) {
				Element entry = doc.createElement("ClassBox");
				Element x = createElement(doc, "x", c.getX() + "");
				entry.appendChild(x);
				Element y = createElement(doc, "y", c.getY() + "");
				entry.appendChild(y);
				Element width = createElement(doc, "width", c.getWidth() + "");
				entry.appendChild(width);
				Element height = createElement(doc, "height", c.getHeight() + "");
				entry.appendChild(height);
				for(String s : c.getBoxes()) {
					Element line = createElement(doc, "box", s);
					entry.appendChild(line);
				}
				
				cbElement.appendChild(entry);
			}
			
			root.appendChild(cbElement);
			
			Element relElement = doc.createElement("Relationships");
			
			for(RelationshipData r : this.relationships.values()) {
				Element entry = doc.createElement("Relationship");
				Element type = createElement(doc, "type", r.getType() + "");
				entry.appendChild(type);
				
				int sourceId = conversion.get(r.getSourceClassBox());
				int destId = conversion.get(r.getDestinationClassBox());
				
				Element source = createElement(doc, "source", sourceId + "");
				entry.appendChild(source);
				Element dest = createElement(doc, "dest", destId + "");
				entry.appendChild(dest);
				
				Element sourceAmount = createElement(doc, "amountSource", r.getAmountSource());
				entry.appendChild(sourceAmount);
				Element destAmount = createElement(doc, "amountDest", r.getAmountDestination());
				entry.appendChild(destAmount);
				
				relElement.appendChild(entry);
			}
			
			root.appendChild(relElement);
			
			doc.appendChild(root);
			
			try {
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				
				transformer.transform(new DOMSource(doc), 
									  new StreamResult(new FileOutputStream(file)));
			
			} catch (TransformerException e) {
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Could not write save file to disk!");
				System.exit(1);
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates an XML element with the given name and string value
	 * 
	 * @param doc the XML document reference
	 * @param name the name of the element
	 * @param value the string value of the element
	 * @return the generated xml element
	 */
	private Element createElement(Document doc, String name, String value) {
		Element element = doc.createElement(name);
		element.appendChild(doc.createTextNode(value));
		
		return element;
	}
	
	/**
	 * Removes all data stored in the Data Manager
	 */
	public void clearData() {
		this.index = 0;
		this.classBoxes.clear();
		this.relationships.clear();
	}
	
	/**
	 * Helpful tool for debugging. Prints the contents of the Class Boxes
	 * and Relationships
	 */
	public String toString() {
		return this.classBoxes.toString() + 
			   "\n" +
			   this.relationships.toString();
	}
}
