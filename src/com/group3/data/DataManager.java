package com.group3.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import com.group3.ui.ClassBox;
import com.group3.ui.ViewManager;

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
	 * TODO: Update data as it changes, including removing data if a Class Box is deleted.
	 * TODO: Add save and load abilities.
	 * TODO: Be able to hand undo's with a stack-like structure?
	 * 
	 */
	public DataManager() {
		index = 0;
		this.classBoxes = new TreeMap<Integer, ClassBoxData>();
		this.relationships = new TreeMap<Integer, RelationshipData>();
	}
	
	/**
	 * Add new Class Box Data to the Data Manager
	 * 
	 * TODO: Add label information.
	 * 
	 * @param classBox the Class Box this data belongs to
	 * @return id for this new Class Box
	 */
	public int addClassBoxData(ClassBox classBox) {
		ClassBoxData classBoxData = 
				new ClassBoxData(classBox.getLocation().x,
								 classBox.getLocation().y,
								 classBox.getWidth(),
								 classBox.getHeight(),
								 new String[0]);
		index++;
		this.classBoxes.put(index, classBoxData);
		
		return index;
	}
	
	/**
	 * Update Class Box Data corresponding to a given id.
	 * 
	 * If no data exists with that id, no changes are made.
	 * 
	 * @param id unique id of the Class Box
	 * @param classBox Class Box to pull data from
	 */
	public void updateClassBoxData(ClassBox classBox) {
		int id = classBox.getId();
		ClassBoxData classBoxData = this.classBoxes.get(id);
		if(classBoxData != null) {
			classBoxData = 
					new ClassBoxData(classBox.getLocation().x,
									 classBox.getLocation().y,
									 classBox.getWidth(),
									 classBox.getHeight(),
									 classBox.getArrayRepresentation());
			this.classBoxes.put(id, classBoxData);
		}
	}
	
	public void removeClassBoxData(int id) {
		this.classBoxes.remove(id);
	}
	
	/* TODO: Add relationship data option!!! */
	
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
	
	public String toString() {
		return this.classBoxes.toString() + 
			   "\n" +
			   this.relationships.toString();
	}
}
