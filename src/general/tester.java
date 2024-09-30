package general;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import general.p1;
import org.junit.jupiter.api.Test;

class tester {

	@Test
	void test() {
		int rows = 0;
		int columns = 0;
		int levels = 0;
		int it = 0;
		int[] start = new int[2];
		String line = "";
		try {
		File file = new File("easyMap.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext()) {
			line = scanner.next();
			if(it == 0) {
				rows = Integer.parseInt(line);
			}
			if(it == 1) {
				columns = Integer.parseInt(line);
			}
			if(it == 2) {
				levels = Integer.parseInt(line);
				break;
			}
			it++;
		}
		String[][] arr2 = new String[rows][columns];
		for(int r = 0; r < rows; r++) {
			line = scanner.next();
			for(int c = 0; c < columns; c++) {
				arr2[r][c] = line.substring(c, c+1);
				if(arr2[r][c].equals("W")) {
					start[0] = c;
					start[1] = r;
				}
			}
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
