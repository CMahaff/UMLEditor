package com.group3.ui;

public class KeyboardShortcuts {

	private int[] numbersArray = new int[10];
	private int[] charactersArray = new int[26];
	private int[] masksArray = new int[] {1, 2, 8};
	
	/**
	 * Instantiates the shortcut key values
	 */
	public KeyboardShortcuts(){
		fillArrays();
	}
	
	/**
	 * Finds the value of the key and returns it
	 * @param key english word/abreviation for the key
	 * @return the mask/value for the key
	 */
	public int checkKey(String key){
		key.toUpperCase();	
		int value = 0;

		if(key.equals("SHIFT"))
			value = masksArray[0];
		else if(key.equals("CTRL"))
			value = masksArray[1];
		else if(key.equals("ALT"))
			value = masksArray[2];
		else if(key.equals("0"))
			value = numbersArray[0];
		else if(key.equals("1"))
			value = numbersArray[1];
		else if(key.equals("2"))
			value = numbersArray[2];
		else if(key.equals("3"))
			value = numbersArray[3];
		else if(key.equals("4"))
			value = numbersArray[4];
		else if(key.equals("5"))
			value = numbersArray[5];
		else if(key.equals("6"))
			value = numbersArray[6];
		else if(key.equals("7"))
			value = numbersArray[7];
		else if(key.equals("8"))
			value = numbersArray[8];
		else if(key.equals("9"))
			value = numbersArray[9];
		else if(key.equals("A"))
			value = charactersArray[0];
		else if(key.equals("B"))
			value = charactersArray[1];
		else if(key.equals("C"))
			value = charactersArray[2];
		else if(key.equals("D"))
			value = charactersArray[3];
		else if(key.equals("E"))
			value = charactersArray[4];
		else if(key.equals("F"))
			value = charactersArray[5];
		else if(key.equals("G"))
			value = charactersArray[6];
		else if(key.equals("H"))
			value = charactersArray[7];
		else if(key.equals("I"))
			value = charactersArray[8];
		else if(key.equals("J"))
			value = charactersArray[9];
		else if(key.equals("K"))
			value = charactersArray[10];
		else if(key.equals("L"))
			value = charactersArray[11];
		else if(key.equals("M"))
			value = charactersArray[12];
		else if(key.equals("N"))
			value = charactersArray[13];
		else if(key.equals("O"))
			value = charactersArray[14];
		else if(key.equals("P"))
			value = charactersArray[15];
		else if(key.equals("Q"))
			value = charactersArray[16];
		else if(key.equals("R"))
			value = charactersArray[17];
		else if(key.equals("S"))
			value = charactersArray[18];
		else if(key.equals("T"))
			value = charactersArray[19];
		else if(key.equals("U"))
			value = charactersArray[20];
		else if(key.equals("V"))
			value = charactersArray[21];
		else if(key.equals("W"))
			value = charactersArray[22];
		else if(key.equals("X"))
			value = charactersArray[23];
		else if(key.equals("Y"))
			value = charactersArray[24];
		else if(key.equals("Z"))
			value = charactersArray[25];
	
		return value;
	}
	
	/**
	 * Fills an array holding the character and number masks,
	 * which are sequential in value
	 */
	private void fillArrays(){
		
		int numbersStart = 48;
		int charsStart = 65;
		
		for (int i = 0; i < numbersArray.length; i++){
			numbersArray[i] = numbersStart;
			numbersStart ++;
		}
		
		for (int i = 0; i < charactersArray.length; i++){
			charactersArray[i] = charsStart;
			charsStart ++;
		}
				
	}
}
