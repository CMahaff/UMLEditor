package com.group3.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JOptionPane;

/**
 * @author Connor Mahaffey
 *         Brady Landis
 */
public class DataManager {
	
	private int index;
	private TreeMap<Integer, ClassBoxData> classBoxes;
	private TreeMap<Integer, RelationshipData> relationships;
	
	/**
	 * Manages all data for saving, reloading, etc. using a system
	 * of unique integer id's.
	 * 
	 * TODO: Be able to hand undo's with a stack-like structure?
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
	 */
	public void addRelationshipData(int sourceId, int destinationId, int type) {
		ClassBoxData source = this.classBoxes.get(sourceId);
		ClassBoxData destination = this.classBoxes.get(destinationId);
		
		if(source != null && destination != null) {
			RelationshipData relationshipData = new RelationshipData(source, destination, type);
			index++;
			this.relationships.put(index, relationshipData);
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
	public String[] loadModel(File file) {
		//TODO: checks for invalid data
		//clear existing data
		this.index = 0;
		this.classBoxes.clear();
		this.relationships.clear();
		
		//load text
		String content = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String s = "";
			do {
				content += s + "\n";
				s = reader.readLine();
			} while(s != null);
			reader.close();
			content = content.substring(1);
		} catch (FileNotFoundException e) {
			// TODO: Improve error handling
			JOptionPane.showMessageDialog(null, 
										  "Could not read from file system!", 
										  "Error!",
										  JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO: Improve error handling
			JOptionPane.showMessageDialog(null, 
										  "Problem reading from file system!", 
										  "Error!",
										  JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
	
		return content.split("\n;;;\n");
	}
	
	/**
	 * Writes save data to file
	 * @param file file to save to
	 */
	public void saveModel(File file){
		String str = "";
		for (Entry<Integer, ClassBoxData> entry : this.classBoxes.entrySet()){
			str += entry.getValue().toSave();
		}
		
		try {
			PrintWriter saveFile = new PrintWriter(file);
			saveFile.println(str);
			saveFile.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, 
										  "Could not save to file system!", 
										  "Error!",
										  JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}		
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
