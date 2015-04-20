package com.group3.data;

/**
 * @author Connor Mahaffey
 */
public class RelationshipData {
		
	public static final int BASIC = 0,
							DEPENDENCY = 1,
							AGGREGATION = 2,
							COMPOSITION = 3,
							GENERALIZATION = 4;
	
	private int type;
	private ClassBoxData source, destination;
	private String amountSource, amountDestination, comment;
	
	/**
	 * Hold Connector Data for saving, reloading, etc.
	 * 
	 * @param id unique id representing this object
	 * @param source source ClassBox that was selected
	 * @param destination destination ClassBox that was selected
	 * @param type relationship type, specified as one of the static constants in the class
	 */
	public RelationshipData(ClassBoxData source, 
							ClassBoxData destination, 
							int type) {
		this.source = source;
		this.destination = destination;
		this.type = type;
		this.amountSource = "";
		this.amountDestination = "";
		this.comment = "";
	}
	
	/**
	 * @return String notation representing how many things this refers to
	 */
	public String getAmountDestination() {
		return this.amountDestination;
	}
	
	/**
	 * @return String notation representing how many things this refers to
	 */
	public String getAmountSource() {
		return this.amountSource;
	}
	
	/**
	 * @return comment on the relationship line
	 */
	public String getComment() {
		return this.comment;
	}
	
	/**
	 * @return relationship type as defined by the class constants
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * @return destination ClassBox selected (the data member of it)
	 */
	public ClassBoxData getDestinationClassBox() {
		return this.destination;
	}
	
	/**
	 * @return source ClassBox selected (the data member of it)
	 */
	public ClassBoxData getSourceClassBox() {
		return this.source;
	}
	
	/**
	 * @param text text representing how many things this refers to
	 */
	public void setAmountDestination(String text) {
		this.amountDestination = text;
	}
	
	/**
	 * @param text text representing how many things this refers to
	 */
	public void setAmountSource(String text) {
		this.amountSource = text;
	}
	
	/**
	 * @param comment text that appears on the relationship line
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * @param type relationship type defined by the class constants
	 */
	public void setType(int type) {
		this.type = type;
	}
}
