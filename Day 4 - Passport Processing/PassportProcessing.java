
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daniel Valchev
 *
 */
public class PassportProcessing {

	public static void main(String[] args) {
		System.out.println(part1());
		System.out.println(part2());
	}

	public static int part1() {
		int valid = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Input.txt"));
			String line;
			ArrayList<String> passport = new ArrayList<String>();

			while ((line = reader.readLine()) != null) {
				passport.add(line);
			}

			// ----
			String[] keys = new String[] { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };
			Map<String, String> keyValues = new HashMap<>();
			for (String s : passport) {

				if (s.isEmpty()) {
					boolean isvalid = true;
					for (String key : keys) {
						if (!keyValues.containsKey(key)) {
							isvalid = false;
							break;
						}
					}
					if (isvalid) {
						valid++;
					}
					keyValues.clear();

				} else {
					String[] kPart = s.split(" ");
					for (String part : kPart) {
						String[] keyVal = part.split(":");
						keyValues.put(keyVal[0], keyVal[1]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return valid + 1;
	}

	public static int part2() {
		int valid = 0;
		BufferedReader reader;
		ArrayList<String> requierments = new ArrayList<String>();

		try {
			reader = new BufferedReader(new FileReader("Input.txt"));
			String line;
			ArrayList<String> passport = new ArrayList<String>();

			while ((line = reader.readLine()) != null) {
				passport.add(line);
			}

			// ---
			String[] keys = new String[] { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };
			Map<String, String> keyValues = new HashMap<>();
			for (String s : passport) {
				if (s.isEmpty()) {
					boolean isvalid = true;
					if (keyValues.containsKey("byr")) {
						int year = Integer.parseInt(keyValues.get("byr"));
						if (year < 1920 || year > 2002)
							isvalid = false;
					} else {
						isvalid = false;
					}

					if (keyValues.containsKey("iyr")) {
						int year = Integer.parseInt(keyValues.get("iyr"));
						if (year < 2010 || year > 2020)
							isvalid = false;
					} else {
						isvalid = false;
					}

					if (keyValues.containsKey("eyr")) {
						int year = Integer.parseInt(keyValues.get("eyr"));
						if (year < 2020 || year > 2030)
							isvalid = false;
					} else {
						isvalid = false;
					}

					if (keyValues.containsKey("hgt")) {
						String height = keyValues.get("hgt");
						if (height.endsWith("in")) {
							int Inches = Integer.parseInt(height.replace("in", ""));
							if (Inches <= 60 || Inches >= 75)
								isvalid = false;
						} else if (height.endsWith("cm")) {
							int Centimeters = Integer.parseInt(height.replace("cm", ""));
							if (Centimeters < 150 || Centimeters > 193)
								isvalid = false;
						} else {
							isvalid = false;
						}
					} else {
						isvalid = false;
					}

					if (keyValues.containsKey("hcl")) {
						//Hate regex btw - shoutout to TurkeyDev for helping with this one
						if (!keyValues.get("hcl").matches("#[a-f0-9]{6}"))
							isvalid = false;
					} else {
						isvalid = false;
					}

					if (keyValues.containsKey("ecl")) {

						String color = keyValues.get("ecl");
						if (!color.equals("amb") && !color.equals("blu") && !color.equals("brn") && !color.equals("gry")
								&& !color.equals("grn") && !color.equals("hzl") && !color.equals("oth"))
							isvalid = false;
					} else {
						isvalid = false;
					}

					if (keyValues.containsKey("pid")) {
						if (!keyValues.get("pid").matches("[0-9]{9}"))
							isvalid = false;
					} else {
						isvalid = false;
					}

					if (isvalid) {
						valid++;
					}
					keyValues.clear();
				} else {
					String[] kPart = s.split(" ");
					for (String part : kPart) {
						String[] keyVal = part.split(":");
						keyValues.put(keyVal[0], keyVal[1]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return valid;
	}
}
