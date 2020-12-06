import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Valchev
 *
 */
public class CustomCustoms {
	public static void main(String[] args) throws IOException {

		part1();
		part2();
	}

	public static void part1() throws IOException {

		BufferedReader reader;
		int sum = 0;

		reader = new BufferedReader(new FileReader("input.txt"));
		ArrayList<Character> groupLetters = new ArrayList<Character>();

		String line = reader.readLine();
		while (line != null) {
			String nextLine = reader.readLine();
			char[] letters = line.toCharArray();
			for (char l : letters) {
				groupLetters.add(l);
			}

			if (line.isBlank() || nextLine == null) {
				int commonAnswers = 1;
				Collections.sort(groupLetters);
				for (int i = 1; i < groupLetters.size(); i++) {
					if (groupLetters.get(i) != groupLetters.get(i - 1)) {
						commonAnswers++;
					}
				}
				sum += commonAnswers;
				groupLetters.clear();
			}
			line = nextLine;
		}
		System.out.println(sum);
		reader.close();
	}

	public static void part2() throws IOException {

		BufferedReader reader;
		int sum = 0;

		boolean firstInGroup = true;
		reader = new BufferedReader(new FileReader("input.txt"));

		ArrayList<Character> groupLetters = new ArrayList<>();
		String line = reader.readLine();
		while (line != null) {

			String nextLine = reader.readLine();
			char[] letters = line.toCharArray();

			if (line.isBlank() || nextLine == null) {
				sum += groupLetters.size();
				groupLetters.clear();
				firstInGroup = true;
			} else {
				if (firstInGroup) {
					for (char l : letters) {
						groupLetters.add(l);
					}
				} else {
					for (int i = groupLetters.size()-1;i>=0;i--) {
						char c = groupLetters.get(i);
						boolean check = false;
						for (Character l : letters) {
							if(c==l) {
								check = true;
								break;
							}
						}
						if(!check) {
							groupLetters.remove(i);
						}
					}
				}

				firstInGroup = false;
			}
			line = nextLine;
		}

		System.out.println(sum);
		reader.close();
	}
}
