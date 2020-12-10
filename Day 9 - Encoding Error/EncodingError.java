import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Daniel Valchev
 *
 */

public class EncodingError {

	public static void main(String[] args) throws IOException {
		solve();
	}

	public static void solve() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Input.txt"));
		String line;
		long min, max;
		int invalidIndex = 0;
		long invalidNum = 0;

		ArrayList<Long> list = new ArrayList<>();
		ArrayList<Long> last25 = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			list.add(Long.parseLong(line));
		}

		for (int i = 0; i < 25; i++) {
			last25.add(list.get(i));
		}

		for (int i = 25; i < list.size(); i++) {

			boolean isSum = false;

			for (int j = 0; j < last25.size(); j++) {
				if (isSum) {
					break;
				}
				for (int k = j + 1; k < last25.size(); k++) {
					if (last25.get(j) + last25.get(k) == list.get(i)) {
						isSum = true;
						break;
					}
				}
			}
			last25.remove(0);
			last25.add(list.get(i));
			if (!isSum) {
				System.out.println(list.get(i));
				invalidNum = list.get(i);
				invalidIndex = i;
			}
		}

		for (int i = 0; i < invalidIndex - 2; i++) {
			for (int j = i + 1; j < invalidIndex; j++) {
				long sum = 0;
				min = Long.MAX_VALUE;
				max = Long.MIN_VALUE;
				for (int k = i; k <= j; k++) {
					sum += list.get(k);
					if (list.get(k) > max) {
						max = list.get(k);
					}
					if (list.get(k) < min) {
						min = list.get(k);
					}
				}
				if (sum == invalidNum) {
					System.out.println(min + max);
				}
			}
		}
		reader.close();
	}
}
