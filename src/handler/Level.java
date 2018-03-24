package handler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.io.FileReader;

public class Level {

	private StringBuilder builder;
	private BufferedReader reader;

	private Object[][] level = new Cell[25][25];

	public Object[][] getLevel(String name) throws IOException {
		name = name.toLowerCase();

		builder = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(new File("src/assets/level/" + name + ".mhl")));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Cant find level with name: " + name);
		}

		try {
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			throw new IOException("No valid level data in file");
		}

		return parseLevelData(builder.toString());
	}

	private Object[][] parseLevelData(String s) {
		if (s.startsWith("museumHeist")) {
			String[] row = s.split("row");

			for (int y = 1; y < row.length; y++) {
				String[] cell = row[y].split("cell");
				for (int x = 1; x < cell.length; x++) {
					String[] levelData = cell[x].split(":");
					int type = 0;
					int[] data = null;
					for (int d = 0; d < levelData.length; d++) {
						if (levelData[d].equals("type")) {
							type = stringToInt(levelData[d + 1]);
						} else if (levelData[d].equals("data")) {
							String[] tempData = levelData[d + 1].split(",");
							data = new int[tempData.length];
							for (int t = 0; t < tempData.length; t++) {
								data[t] = stringToInt(tempData[t]);
							}
						}

					}
					level[y - 1][x - 1] = new Cell(type, data);
				}
			}
		}
		return level;
	}

	private static int stringToInt(String s) {
		try {
			return Integer.valueOf(s);
		} catch (Exception e) {
			throw new NumberFormatException("Invalid level data");
		}
	}

	private class Cell {
		int type;
		int[] data;

		Cell(int type, int[] data) {
			this.type = type;
			this.data = data;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int[] getData() {
			return data;
		}

		public void setData(int[] data) {
			this.data = data;
		}
	}
}
