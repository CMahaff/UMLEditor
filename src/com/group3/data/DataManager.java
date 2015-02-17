package com.group3.data;

import java.util.TreeMap;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import com.group3.ui.ClassBox;
import com.group3.ui.ViewManager;

/**
 * @author Connor Mahaffey
 *         Brady Landis
 */
public class DataManager {
	
	private ViewManager viewRef;
	
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
								 classBox.getHeight());
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
	public void updateClassBoxData(int id, ClassBox classBox) {
		ClassBoxData classBoxData = this.classBoxes.get(id);
		if(classBoxData != null) {
			classBoxData = 
					new ClassBoxData(classBox.getLocation().x,
									 classBox.getLocation().y,
									 classBox.getWidth(),
									 classBox.getHeight());
			this.classBoxes.put(id, classBoxData);
		}
	}
	
	/* TODO: Add relationship data option!!! */
	
	/**
	 * @param id id of the Class Box Data object
	 * @return the object if found, otherwise null
	 */
	public ClassBoxData getClassBoxData(int id) {
		return this.classBoxes.get(id);
	}
	
	/**
	 * @param id id of the Relationship Data object
	 * @return the object if found, otherwise null
	 */
	public RelationshipData getRelationshipData(int id) {
		return this.relationships.get(id);
	}
	
	/**
	 * @param viewManager reference to the View Manager
	 */
	public void setViewManager(ViewManager viewManager) {
		this.viewRef = viewManager;
	}
	/**
	 * creates a text file that can later be read
	 */
	public void saveModel(String Filename){
		ClassBoxData current;
		String str="";
		for (int i=0; i<index; i++){
			current = classBoxes.get(i);
			str+=current.toSave();
		}
		//this is just a test will be removed later
		System.out.printline("heres what should be writen:\n" + str);
		
		PrintWriter savefile = new PrintWriter(Filename);
		savefile.println(text);
		
		
	}
	
	public String toString() {
		return this.classBoxes.toString() + 
			   "\n" +
			   this.relationships.toString();
	}
}
