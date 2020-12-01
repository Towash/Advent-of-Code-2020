import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Daniel Valchev
 *
 */
public class Expenses {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("Expenses.txt"));

		// Makes the size of the array equal to the number of inputs.
		int ints = 0;
		while (scanner.hasNextInt()) {
			ints++;
			scanner.nextInt();
		}
		//Brings the scanner back to the start of the file
		scanner = new Scanner(new File("Expenses.txt"));
		//Initialises the array of ints.
		int[] a = new int[ints];
		int num = 0;
		//Puts the values from the file in the array.
		while (scanner.hasNextInt()) {
			a[num++] = scanner.nextInt();
		}
		
		//Computes the answers
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if ((a[i] + a[j]) == 2020) {
					System.out.println(a[i] * a[j]);
				}
				for (int k = j + 1; k < a.length; k++) {
					if ((a[i] + a[j] + a[k]) == 2020) {
						System.out.println(a[i] * a[j] * a[k]);
					}
				}
			}
		}
	}

}
