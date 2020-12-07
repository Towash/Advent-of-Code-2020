import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Daniel Valchev
 *
 */
public class HandyHaversacks {
	public static HashMap<String, List<Item>> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		part1();
	}

	public static void part1() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Input.txt"));
		String line;
		int count = 0;

		while ((line = reader.readLine()) != null) {

			List<Item> bags = new ArrayList<>();
			String[] split = line.substring(0, line.length() - 1).split("bags contain");
			String outer = split[0].trim();
			String[] inners = split[1].split(",");
			for (String bag : inners) {

				bag = bag.replace("bags", "").replace("bag", "").trim();
				int i = bag.trim().indexOf(" ");
				String checkForNo = bag.substring(0, i);

				if (!checkForNo.equals("no")) {

					Item item = new Item();
					item.amount = Integer.parseInt(checkForNo);
					item.name = bag.substring(i + 1);
					bags.add(item);
				}
			}
			map.put(outer, bags);
		}

		for (String bag : map.keySet()) {
			if (hasGold(bag)) {
				count++;
			}
		}
		System.out.println(count);
		System.out.println(part2("shiny gold") - 1);

		reader.close();
	}

	private static boolean hasGold(String bag) {
		for (Item item : map.get(bag)) {
			if (item.name.equals("shiny gold")) {
				return true;
			}
		}
		for (Item item : map.get(bag)) {
			if (hasGold(item.name)) {
				return true;
			}
		}
		return false;
	}

	private static int part2(String bag) {
		List<Item> items = map.get(bag);
		int count = 1;
		for (Item item : items) {
			count += (item.amount * part2(item.name));
		}

		return count;
	}

	public static class Item {
		public int amount;
		public String name;
	}
}
