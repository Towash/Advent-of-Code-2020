package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel Valchev
 *
 */
public class HandheldHalting {

	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}

	public static void part1() throws IOException {
		int acc = 0;
		BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
		String line;

		ArrayList<String> command = new ArrayList<>();
		ArrayList<Integer> arg = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(" ");
			command.add(parts[0]);
			arg.add(Integer.parseInt(parts[1]));
		}

		String[] commands = command.toArray(new String[0]);
		Integer[] args = arg.toArray(new Integer[0]);

		boolean[] visited = new boolean[commands.length];
		Arrays.fill(visited, false);

		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				System.out.println(acc);
				break;
			} else if (commands[i].equals("acc")) {
				acc += args[i];
				visited[i] = true;
			} else if (commands[i].equals("jmp")) {
				visited[i] = true;
				i += args[i] - 1;
			} else if (commands[i].equals("nop")) {
				visited[i] = true;
			}
		}
		reader.close();
	}

	public static void part2() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
		String line;

		ArrayList<String> command = new ArrayList<>();
		ArrayList<Integer> arg = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(" ");
			command.add(parts[0]);
			arg.add(Integer.parseInt(parts[1]));
		}
		String[] commands = command.toArray(new String[0]);
		Integer[] args = arg.toArray(new Integer[0]);
		boolean[] visited = new boolean[commands.length];
		Arrays.fill(visited, false);

		int index = 0;
		String old = "";
		do {
			boolean infinite = false;
			int acc = 0;
			if (commands[index].equals("nop")) {
				old = "nop";
				commands[index] = "jmp";
			} else if (commands[index].equals("jmp")) {
				old = "jmp";
				commands[index] = "nop";
			}

			for (int i = 0; i < visited.length; i++) {
				if (visited[i]) {
					infinite = true;
					break;
				} else if (commands[i].equals("acc")) {
					acc += args[i];
					visited[i] = true;
				} else if (commands[i].equals("jmp")) {

					visited[i] = true;
					i += args[i] - 1;
				} else if (commands[i].equals("nop")) {
					visited[i] = true;
				}
			}
			Arrays.fill(visited, false);
			if (!infinite) {
				System.out.println(acc);
				break;
			}
			if (commands[index].equals("nop") || commands[index].equals("jmp"))
				commands[index] = old;
			index++;
		} while (index < commands.length);

		reader.close();
	}
}
