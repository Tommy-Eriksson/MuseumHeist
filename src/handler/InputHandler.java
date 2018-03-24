package handler;

import java.util.ArrayList;

/**
 * The Class InputHandler holds an ArrayList of keys activated and 
 * functionality related to that.
 *  
 * @author Malin Albinsson
 * @version 1.0
 */
public class InputHandler {
	
	/** The ArrayList keys. */
	private ArrayList<String> keys;
	
	/**
	 * Instantiates a new input handler.
	 */
	public InputHandler() {
		setKeys(new ArrayList<String>());
	}

	/**
	 * Gets the ArrayList keys.
	 *
	 * @return the keys
	 */
	public ArrayList<String> getKeys() {
		return keys;
	}

	/**
	 * Sets the ArrayList keys.
	 *
	 * @param keys keys the ArrayList to set
	 */
	public void setKeys(ArrayList<String> keys) {
		this.keys = keys;
	}

	/**
	 * Adds the key to the ArrayList keys.
	 *
	 * @param keyCode the keycode
	 * @return the array list
	 */
	public ArrayList<String> addKey(String keyCode) {
		if(!keyActivated(keyCode)) {
			keys.add(keyCode);
		return keys;
		} 
		else
		return keys;
	}

	/**
	 * Removes the key from the ArrayList keys.
	 *
	 * @param keyCode the keycode
	 * @return the array list
	 */
	public ArrayList<String> removeKey(String keyCode) {
		keys.remove(keyCode);
		return keys;
	}

	/**
	 * Checks if the key already is activated 
	 *
	 * @param keyCode the key code
	 * @return true, if successful
	 */
	public boolean keyActivated(String keyCode) {
		if(keys.contains(keyCode)) {
		return true;
		}
		return false;
	}

}
