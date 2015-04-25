package com.group3.ui;

import java.awt.Color;


public class RelationshipSelectionManager {
	
	private ViewManager viewRef;
	private ClassBox[] selectedClassBoxes;
	private int index, relationshipType;
	
	/**
	 * Helps keep track of which class boxes are being linked in a relationship
	 * @param viewRef a reference to the View Manager
	 * @param relationshipType the type of relationship this selection will create
	 */
	public RelationshipSelectionManager(ViewManager viewRef, int relationshipType) {
		this.viewRef = viewRef;
		this.selectedClassBoxes = new ClassBox[4];
		this.index = 0;
		this.relationshipType = relationshipType;
	}

	

	public RelationshipSelectionManager(ViewManager viewRef) {
		this.viewRef = viewRef;
		this.selectedClassBoxes = new ClassBox[4];
		this.index = 0;
	}



	/**
	 * Serves two purposes:
	 * 1.
	 * Keeps track of which Class Boxes we are linking with a relationship.
	 * 
	 * If the same class box is chosen twice, it will not link with itself.
	 * 
	 * After the 2nd class box is chosen, the relationship is added and the selection
	 * event is stopped.
	 * 
	 * 2.
	 * Keeps track of which Class Boxes we are deleting relationships between.
	 * 
	 * If two class boxes are selected and do not share a relationship, nothing will occur.
	 * 
	 *  After the 2nd class box is chosen, the relationships are deleted and the selection
	 *  event is stopped.
	 * 
	 * @param classBox the class box to add
	 */
	public void addSelection(ClassBox classBox) {
		if(index == 1 && this.selectedClassBoxes[0] == classBox) {
			return;
		}
		
		this.selectedClassBoxes[index] = classBox;
		++index;
		System.out.println(index);
		if(index == 2) {
			
			if(this.viewRef.getRemoving() == false) {
			this.viewRef.getDataManager().addRelationshipData(this.selectedClassBoxes[0].getId(),
															  this.selectedClassBoxes[1].getId(), 
															  this.relationshipType);
			}
			
			if(this.viewRef.getRemoving()){
				System.out.println("test");
				this.viewRef.getDataManager().removeRelationshipData(this.selectedClassBoxes[0].getId(),
																	 this.selectedClassBoxes[1].getId());
			}
			
			
			
			this.viewRef.endRelationshipSelection();
			this.selectedClassBoxes[0].setBorderColor(Color.BLACK);
			this.selectedClassBoxes[1].setBorderColor(Color.BLACK);
			
			this.viewRef.repaintUML();
			index = 0;
			
		}
	}


}
