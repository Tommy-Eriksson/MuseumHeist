package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import handler.InputHandler;


/**
 * The Class InputTests holds the unit tests on which the class InputHandler is built.
 * 
 * @author Malin Albinsson
 * @version 1.0
 */
class InputTests {

	/**
	 * AddKey should add key code of pressed key to array list keys: key code string added last in array list keys expected.
	 */
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
	
	/**
	 * AddKey should add key code of pressed key to array list keys only if key not already activated: added key code expected.
	 */
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
	
	/**
	 * AddKey should add key code of pressed key to array list keys only if key not already activated: no add of key code expected.
	 */
	@Test
	void addKey_shouldAddKeyCodeOfPressedKeyToArrayListKeysOnlyIfKeyNotAlreadyActivated_NoAddOfKeyCodeExpected() {
	// Arrange
		InputHandler input = new InputHandler();
		
		String keyCode = "*";
		
		ArrayList<String> expectedArray = new ArrayList<String>();
		expectedArray.add("+");
		expectedArray.add("E");
		expectedArray.add("*");
		expectedArray.add("T");
		
		ArrayList<String> resultArray = input.getKeys(); 
		resultArray.add("+");
		resultArray.add("E");
		resultArray.add("*");
		resultArray.add("T");

	// Act	
		resultArray = input.addKey(keyCode);
	
	// Assert
		assertEquals(expectedArray, resultArray);
	}
	
	/**
	 * RemoveKey should remove key code from array list keys: key code string removed from array list keys expected.
	 */
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
	
	
	/**
	 * KeyActivated should if key code is in list return true: true expected.
	 */
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
	
	/**
	 * keyActivated should if key code not in list return false: false expected.
	 */
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
		

}
