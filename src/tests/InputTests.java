package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class InputTests {

	@Test
	void addKey_shouldAddKeyCodeToArrayListKeys_keyCodeStringAddedLastInArrayListKeys() {
	// Arrange
	Input input = new Input();
	
	ArrayList<String> expectedArray = new ArrayList<String>();
	String keyCode="+";
	expectedArray.add(keyCode);
	
	// Act
	ArrayList<String> resultArray = input.keys; 
	resultArray = input.addKey(keyCode);
	
	// Assert
			assertEquals(expectedArray, resultArray);
	}
	
	@Test 
	// TODO How should ArrayList keys work? Can the same keycode be found in more than one position in the list?
	void removeKey_shouldRemoveKeyCodeFromArrayListKeys_keyCodeStringRemovedFromArrayListKeys() {
	// Arrange
		fail("Not yet implemented");
	// Arrange
	
	// Act
	
	// Assert
	 
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
