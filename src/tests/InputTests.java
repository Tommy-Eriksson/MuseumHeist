package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import handler.InputHandler;

class InputTests {

	@Test
	void addKey_shouldAddKeyCodeOfPressedKeyToArrayListKeys_keyCodeStringAddedLastInArrayListKeys() {
	// Arrange
	InputHandler input = new InputHandler();
	
	ArrayList<String> expectedArray = new ArrayList<String>();
	String keyCode="+";
	expectedArray.add(keyCode);
	
	// Act
	ArrayList<String> resultArray = input.getKeys(); 
	resultArray = input.addKey(keyCode);
	
	// Assert
			assertEquals(expectedArray, resultArray);
	}
	
	@Test 
	void removeKey_shouldRemoveKeyCodeFromArrayListKeys_keyCodeStringRemovedFromArrayListKeys() {
	// Arrange
		InputHandler input = new InputHandler();
		
		ArrayList<String> expectedArray = new ArrayList<String>();
		expectedArray.add("E");
		expectedArray.add("-");
		expectedArray.add("T");
		
		ArrayList<String> resultArray = input.getKeys(); 
		resultArray.add("E");
		resultArray.add("+");
		resultArray.add("-");
		resultArray.add("T");
	
	// Act
		String keyCode="+";
		resultArray = input.removeKey(keyCode);
	// Assert
		assertEquals(expectedArray, resultArray);
	}
	
	@Test
	void getKeys_shouldGetKeyCodeFromArrayListKeys_keyCodesFromArrayListKeysReturned() {
		fail("Not yet implemented");
	// Arrange
	
	// Act
	
	// Assert
	 
	}
		
	/*
	@Test
	void metodnamn_informationomtest_förväntatresultat() {
		fail("Not yet implemented");
	// Arrange
	
	// Act
	
	// Assert
	 
	}
*/
}
