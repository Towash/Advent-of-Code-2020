
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Daniel Valchev
 *
 */
public class TobogganTrajectory {
	

	public static void main(String[] args) throws IOException {

		System.out.println(part1(3));
		System.out.println((long)part1(1) * part1(3) * part1(5) * part1(7) * part2(1));

	}

	public static int part1(int x) throws IOException {
		int treecount = 0;
		int initialx = x;
		
		BufferedReader reader = new BufferedReader(new FileReader("Input.txt"));
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			char[] symbols = line.toCharArray();
			if (symbols[x] == '#') {
				treecount++;
			}

			x += initialx;
			if (x >= line.length()) {
				x -= line.length();
			}
		}
		reader.close();
		return treecount;
	}
	
	public static int part2(int x) throws IOException {
		int treecount = 0;
		int initialx = x;
		
		BufferedReader reader = new BufferedReader(new FileReader("Input.txt"));
		String line = reader.readLine();
		
		while ((line = reader.readLine()) != null) {
			line = reader.readLine();
			char[] symbols = line.toCharArray();
			if (symbols[x] == '#') {
				treecount++;
			}

			x += initialx;
			if (x >= line.length()) {
				x -= line.length();
			}
		}
		reader.close();
		return treecount;
	}
}
