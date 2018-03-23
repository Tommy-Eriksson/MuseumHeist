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
	void addKey_shouldAddKeyCodeOfPressedKeyToArrayListKeysOnlyIfKeyNotAlreadyActivated_addedKeyCodeExpected() {
	// Arrange
		InputHandler input = new InputHandler();
		
		String keyCode = "*";
		
		ArrayList<String> expectedArray = new ArrayList<String>();
		expectedArray.add("+");
		expectedArray.add("E");
		expectedArray.add("-");
		expectedArray.add("T");
		expectedArray.add(keyCode);
		
		ArrayList<String> resultArray = input.getKeys(); 
		resultArray.add("+");
		resultArray.add("E");
		resultArray.add("-");
		resultArray.add("T");

	// Act	
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
	void keyActivated_shouldIfKeyCodeInListReturnTrue_TrueExpected() {
		// Arrange
			InputHandler input = new InputHandler();
			ArrayList<String> resultArray = input.getKeys(); 
			resultArray.add("E");
			resultArray.add("+");
			resultArray.add("-");
			resultArray.add("T");
		// Act/Assert
			String keyCode="+";
		assertTrue (input.keyActivated(keyCode));
	}
	
	@Test
	void keyActivated_shouldIfKeyCodeNotInListReturnFalse_FalseExpected() {
		// Arrange
			InputHandler input = new InputHandler();
			ArrayList<String> resultArray = input.getKeys(); 
			resultArray.add("E");
			resultArray.add("+");
			resultArray.add("-");
			resultArray.add("T");
		// Act/Assert
			String keyCode="*";
		assertFalse (input.keyActivated(keyCode));
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
