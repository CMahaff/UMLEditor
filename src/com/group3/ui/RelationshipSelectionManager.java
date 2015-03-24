package com.group3.ui;

import java.awt.Color;


public class RelationshipSelectionManager {
	
	private ViewManager viewRef;
	private ClassBox[] selectedClassBoxes;
	private int index, relationshipType;
	
	public RelationshipSelectionManager(ViewManager viewRef, int relationshipType) {
		this.viewRef = viewRef;
		this.selectedClassBoxes = new ClassBox[2];
		this.index = 0;
		this.relationshipType = relationshipType;
	}
	
	/**
	 * Keeps track of which Class Boxes we are linking with a relationship.
	 * 
	 * If the same class box is chosen twice, it will not link with itself.
	 * 
	 * After the 2nd class box is chosen, the relationship is added and the selection
	 * event is stopped.
	 * 
	 * @param classBox the class box to add
	 */
	public void addSelection(ClassBox classBox) {
		if(index == 1 && this.selectedClassBoxes[0] == classBox) {
			return;
		}
		
		this.selectedClassBoxes[index] = classBox;
		++index;
		if(index == 2) {
			this.viewRef.getDataManager().addRelationshipData(this.selectedClassBoxes[0].getId(),
															  this.selectedClassBoxes[1].getId(), 
															  this.relationshipType);
			this.viewRef.endRelationshipSelection();
			this.selectedClassBoxes[0].setBorderColor(Color.BLACK);
			this.selectedClassBoxes[1].setBorderColor(Color.BLACK);
			this.viewRef.repaintUML();
		}
	}

}
