import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Daniel Valchev
 *
 */
public class PasswordPhilosophyPart2 {
	public static void main(String[] args) throws Exception {
		int passwordcount=0;
		
		
		// Convert input.txt to csv file.
		final Path path = Paths.get("");
		final Path txt = path.resolve("Input.txt");
		final Path csv = path.resolve("Inputs.csv");
		try (final Stream<String> lines = Files.lines(txt);
				final PrintWriter pw = new PrintWriter(Files.newBufferedWriter(csv, StandardOpenOption.CREATE))) {
			lines.map((line) -> line.split("-|:|\s")).map((line) -> Stream.of(line).collect(Collectors.joining(",")))
					.forEach(pw::println);

			// Read from line
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader("Inputs.csv"));
			while ((line = br.readLine()) != null) {
				
			//Split into lines and check the condition.
				String[] values = line.split(",");

				int a = Integer.parseInt(values[0]);
				int b = Integer.parseInt(values[1]);
				char c = values[2].charAt(0);
				char[] chararray = values[4].toCharArray();
				
				if((c == chararray[a-1] && c!= chararray[b-1]) || (c == chararray[b-1] && c!= chararray[a-1]) ) {
					passwordcount++;
				}
			}
			br.close();
			System.out.println(passwordcount);
		}
	}
}