package handler;

import java.util.ArrayList;

public class InputHandler {
	
	private ArrayList<String> keys;
	
	public InputHandler() {
		setKeys(new ArrayList<String>());
	}

	/**
	 * @return the keys
	 */
	public ArrayList<String> getKeys() {
		return keys;
	}

	/**
	 * @param keys keys the ArrayList to set
	 */
	public void setKeys(ArrayList<String> keys) {
		this.keys = keys;
	}

	public ArrayList<String> addKey(String keyCode) {
		if(keyActivated(keyCode))
			throw new IllegalArgumentException("Key already activated");
		else if(!keyActivated(keyCode))
			keys.add(keyCode);
		return keys;
	}

	public ArrayList<String> removeKey(String keyCode) {
		keys.remove(keyCode);
		return keys;
	}

	public boolean keyActivated(String keyCode) {
		if(keys.contains(keyCode)) {
		return true;
		}
		return false;
	}

}
