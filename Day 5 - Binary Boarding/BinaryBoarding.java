import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 
 *  @author Daniel Valchev
 * 
 */
public class BinaryBoarding {

	public static void main(String[] args) throws IOException {
		solve();
	}

	public static void solve() throws IOException {

		List<Integer> ids = new ArrayList();
		int maxid = 0;
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("input.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			int id;
			int row = 0;
			int col = 0;
			int rowpow = 6;
			int colpow = 2;

			char[] letters = line.toCharArray();
			for (char c : letters) {
				if (c == 'B') {
					row += Math.pow(2, rowpow);
					rowpow--;
				} else if (c == 'F') {
					rowpow--;
				} else if (c == 'L') {
					colpow--;
				} else if (c == 'R') {
					col += Math.pow(2, colpow);
					colpow--;
				}
			}

			id = (row * 8) + col;
			ids.add(id);
			if (maxid < id) {
				maxid = id;
			}
		}

		System.out.println(maxid + " ");
		reader.close();
		
		// Part 2 below
		ids.sort(Integer::compareTo);
		int lastId = -1;
		for (int currentid : ids) {
			if (lastId != 1 && currentid - lastId == 2) {
				System.out.println(currentid - 1);
			}
			lastId = currentid;
		}

	}

}
