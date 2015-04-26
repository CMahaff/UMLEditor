package com.group3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.group3.data.DataManager;
import com.group3.data.RelationshipData;
import com.group3.data.ClassBoxData;

import java.util.TreeMap;

public class RelationshipsTest {

	@org.junit.Test
	public void addAssociation(){	
		System.out.println("Test: Adding an Association");
		DataManager dataManager = new DataManager();

		String before = dataManager.toString();
		
		int idOne = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		int idTwo = dataManager.addClassBoxData(10, 10, 10, 10, "Test2");
		dataManager.addRelationshipData(idOne, idTwo, 0);

		TreeMap<Integer, RelationshipData> relationshipTree = dataManager.getRelationshipData();
		RelationshipData relationshipData = relationshipTree.get(3);
		int type = relationshipData.getType();
		
		TreeMap<Integer, ClassBoxData> classBoxDataTree = dataManager.getClassBoxData();
		ClassBoxData classBoxOne = classBoxDataTree.get(1);
		ClassBoxData classBoxTwo = classBoxDataTree.get(2);
		int source = classBoxOne.getId();
		int destination = classBoxTwo.getId();
		
		assertFalse(before.equals(dataManager.toString()));	
		assertTrue(type == 0);
		assertTrue(source == idOne && destination == idTwo);
		System.out.println("Test Passed");
		System.out.println();
	}
	
	@org.junit.Test
	public void addDependency(){	
		System.out.println("Test: Adding a Dependency");
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int idOne = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		int idTwo = dataManager.addClassBoxData(10, 10, 10, 10, "Test2");
		dataManager.addRelationshipData(idOne, idTwo, 1);
		
		TreeMap<Integer, RelationshipData> relationshipTree = dataManager.getRelationshipData();
		RelationshipData relationshipData = relationshipTree.get(3);
		int type = relationshipData.getType();
		
		TreeMap<Integer, ClassBoxData> classBoxDataTree = dataManager.getClassBoxData();
		ClassBoxData classBoxOne = classBoxDataTree.get(1);
		ClassBoxData classBoxTwo = classBoxDataTree.get(2);
		int source = classBoxOne.getId();
		int destination = classBoxTwo.getId();
		
		assertFalse(before.equals(dataManager.toString()));
		assertTrue(type == 1);
		assertTrue(source == idOne && destination == idTwo);
		System.out.println("Test Passed");
		System.out.println();
	}
	
	@org.junit.Test
	public void addComposition(){	
		System.out.println("Test: Adding a Composition");
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int idOne = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		int idTwo = dataManager.addClassBoxData(10, 10, 10, 10, "Test2");
		dataManager.addRelationshipData(idOne, idTwo, 2);
		
		TreeMap<Integer, RelationshipData> relationshipTree = dataManager.getRelationshipData();
		RelationshipData relationshipData = relationshipTree.get(3);
		int type = relationshipData.getType();
		
		TreeMap<Integer, ClassBoxData> classBoxDataTree = dataManager.getClassBoxData();
		ClassBoxData classBoxOne = classBoxDataTree.get(1);
		ClassBoxData classBoxTwo = classBoxDataTree.get(2);
		int source = classBoxOne.getId();
		int destination = classBoxTwo.getId();
		
		assertFalse(before.equals(dataManager.toString()));	
		assertTrue(type == 2);
		assertTrue(source == idOne && destination == idTwo);
		System.out.println("Test Passed");
		System.out.println();
	}
	
	@org.junit.Test
	public void addGeneralization(){	
		System.out.println("Test: Adding a Generalization");
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int idOne = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		int idTwo = dataManager.addClassBoxData(10, 10, 10, 10, "Test2");
		dataManager.addRelationshipData(idOne, idTwo, 3);

		TreeMap<Integer, RelationshipData> relationshipTree = dataManager.getRelationshipData();
		RelationshipData relationshipData = relationshipTree.get(3);
		int type = relationshipData.getType();
		
		TreeMap<Integer, ClassBoxData> classBoxDataTree = dataManager.getClassBoxData();
		ClassBoxData classBoxOne = classBoxDataTree.get(1);
		ClassBoxData classBoxTwo = classBoxDataTree.get(2);
		int source = classBoxOne.getId();
		int destination = classBoxTwo.getId();
		
		assertFalse(before.equals(dataManager.toString()));
		assertTrue(type == 3);
		assertTrue(source == idOne && destination == idTwo);
		System.out.println("Test Passed");
		System.out.println();
	}
	
	@org.junit.Test
	public void addAggregation(){	
		System.out.println("Test: Adding an Aggregation");
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int idOne = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		int idTwo = dataManager.addClassBoxData(10, 10, 10, 10, "Test2");
		dataManager.addRelationshipData(idOne, idTwo, 4);

		TreeMap<Integer, RelationshipData> relationshipTree = dataManager.getRelationshipData();
		RelationshipData relationshipData = relationshipTree.get(3);
		int type = relationshipData.getType();
		
		TreeMap<Integer, ClassBoxData> classBoxDataTree = dataManager.getClassBoxData();
		ClassBoxData classBoxOne = classBoxDataTree.get(1);
		ClassBoxData classBoxTwo = classBoxDataTree.get(2);
		int source = classBoxOne.getId();
		int destination = classBoxTwo.getId();
		
		assertFalse(before.equals(dataManager.toString()));
		assertTrue(type == 4);
		assertTrue(source == idOne && destination == idTwo);
		System.out.println("Test Passed");
		System.out.println();
	}
	
	@org.junit.Test
	public void addRecursiveRelationship(){	
		System.out.println("Test: Adding an Aggregation");
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int idOne = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		dataManager.addRelationshipData(idOne, idOne, 4);

		TreeMap<Integer, RelationshipData> relationshipTree = dataManager.getRelationshipData();
		RelationshipData relationshipData = relationshipTree.get(2);
		int type = relationshipData.getType();
		
		TreeMap<Integer, ClassBoxData> classBoxDataTree = dataManager.getClassBoxData();
		ClassBoxData classBoxOne = classBoxDataTree.get(1);
		int source = classBoxOne.getId();
		int destination = classBoxOne.getId();
		
		assertFalse(before.equals(dataManager.toString()));
		assertTrue(type == 4);
		assertTrue(source == idOne && destination == idOne);
		System.out.println("Test Passed");
		System.out.println();
	}
	
	@org.junit.Test
	public void addMultipleRelationships(){	
		System.out.println("Test: Adding all relationships");
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int idOne = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		int idTwo = dataManager.addClassBoxData(10, 10, 10, 10, "Test2");
		dataManager.addRelationshipData(idOne, idTwo, 0);
		dataManager.addRelationshipData(idOne, idTwo, 1);
		dataManager.addRelationshipData(idOne, idTwo, 2);
		dataManager.addRelationshipData(idOne, idTwo, 3);
		dataManager.addRelationshipData(idOne, idTwo, 4);

		TreeMap<Integer, RelationshipData> relationshipTree = dataManager.getRelationshipData();
		RelationshipData relationshipDataOne = relationshipTree.get(3);
		RelationshipData relationshipDataTwo = relationshipTree.get(4);
		RelationshipData relationshipDataThree = relationshipTree.get(5);
		RelationshipData relationshipDataFour = relationshipTree.get(6);
		RelationshipData relationshipDataFive = relationshipTree.get(7);
		int typeOne = relationshipDataOne.getType();
		int typeTwo = relationshipDataTwo.getType();
		int typeThree = relationshipDataThree.getType();
		int typeFour = relationshipDataFour.getType();
		int typeFive = relationshipDataFive.getType();
		
		TreeMap<Integer, ClassBoxData> classBoxDataTree = dataManager.getClassBoxData();
		ClassBoxData classBoxOne = classBoxDataTree.get(1);
		ClassBoxData classBoxTwo = classBoxDataTree.get(2);
		int source = classBoxOne.getId();
		int destination = classBoxTwo.getId();
		
		assertFalse(before.equals(dataManager.toString()));
		assertTrue(typeOne == 0 && typeTwo == 1 && typeThree == 2 && typeFour == 3 && typeFive == 4);
		assertTrue(source == idOne && destination == idTwo);
		System.out.println("Test Passed");
		System.out.println();
	}
	
	@org.junit.Test
	public void addMultipleRelationshipsToMultipleClassBoxes(){	
		System.out.println("Test: Adding relationships to several boxes");
		DataManager dataManager = new DataManager();
		
		String before = dataManager.toString();
		
		int idOne = dataManager.addClassBoxData(0, 0, 0, 0, "Test1");
		int idTwo = dataManager.addClassBoxData(10, 10, 10, 10, "Test2");
		int idThree = dataManager.addClassBoxData(20, 20, 20, 20, "Test3");
		dataManager.addRelationshipData(idOne, idTwo, 0);
		dataManager.addRelationshipData(idOne, idThree, 1);
		dataManager.addRelationshipData(idTwo, idThree, 2);
		dataManager.addRelationshipData(idTwo, idOne, 3);
		dataManager.addRelationshipData(idThree, idTwo, 4);
		dataManager.addRelationshipData(idThree, idOne, 5);

		TreeMap<Integer, RelationshipData> relationshipTree = dataManager.getRelationshipData();
		RelationshipData relationshipDataOne = relationshipTree.get(4);
		RelationshipData relationshipDataTwo = relationshipTree.get(5);
		RelationshipData relationshipDataThree = relationshipTree.get(6);
		RelationshipData relationshipDataFour = relationshipTree.get(7);
		RelationshipData relationshipDataFive = relationshipTree.get(8);
		RelationshipData relationshipDataSix = relationshipTree.get(9);
		int typeOne = relationshipDataOne.getType();
		int typeTwo = relationshipDataTwo.getType();
		int typeThree = relationshipDataThree.getType();
		int typeFour = relationshipDataFour.getType();
		int typeFive = relationshipDataFive.getType();
		int typeSix = relationshipDataSix.getType();
		
		TreeMap<Integer, ClassBoxData> classBoxDataTree = dataManager.getClassBoxData();
		ClassBoxData classBoxOne = classBoxDataTree.get(1);
		ClassBoxData classBoxTwo = classBoxDataTree.get(2);
		ClassBoxData classBoxThree = classBoxDataTree.get(3);
		int valOne = classBoxOne.getId();
		int valTwo = classBoxTwo.getId();
		int valThree = classBoxThree.getId();
		
		assertFalse(before.equals(dataManager.toString()));
		assertTrue(typeOne == 0 && typeTwo == 1 && typeThree == 2 && typeFour == 3 && typeFive == 4 && typeSix == 5);
		assertTrue(valOne == idOne && valTwo == idTwo && valThree == idThree);
		System.out.println("Test Passed");
		System.out.println();
	}
	
}



