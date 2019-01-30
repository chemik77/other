package pl.chemik77.UserRegistrationSystem;

import java.io.*;
import java.util.Scanner;

public class UserRegistrationSystem {

	private File file = new File("C:/Java/database.txt");
	private File temp = new File("C:/Java/database_temp.txt");

	public void addUser(String name, int age) throws Exception {

		// Create file if doesn't exist
		if (!file.exists()) {
			file.createNewFile();
		}

		// Append content if file already stores data
		FileWriter fileWriter = new FileWriter(file, true);
		PrintWriter writer = new PrintWriter(fileWriter);

		writer.println(name + "\t" + age);
		writer.close();
	}

	public void deleteUser(String name) throws Exception {

		if (!file.exists()) {
			file.createNewFile();
		}

		// Create new temporary file that will later replace old one
		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new FileWriter(temp));

		String line;

		while ((line = reader.readLine()) != null) {
			String trimmedLine = line.replaceAll("[^A-Za-z]", "");
			if (trimmedLine.equalsIgnoreCase(name))
				continue;
			writer.println(line);

		}

		writer.close();
		reader.close();

		// Replace i rename file
		file.delete();
		temp.renameTo(file);

	}

	public static void main(String[] args) throws Exception {
		UserRegistrationSystem sys = new UserRegistrationSystem();

		Scanner scanner = new Scanner(System.in);

		String line;
		String name;
		int age;

		int choice;
		do {
			System.out.println("Select option: ");
			System.out.println("1) add User");
			System.out.println("2) delete User");
			System.out.println("3) exit");

			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				System.out.println("To add User enter name and age:");
				line = scanner.nextLine();
				name = line.replaceAll("[^A-Za-z]", "");
				age = Integer.parseInt(line.replaceAll("[^0-9]", ""));
				sys.addUser(name, age);
				break;
			case 2:
				System.out.println("To delete User enter name:");
				line = scanner.nextLine();
				name = line.replaceAll("[^A-Za-z]", "");
				sys.deleteUser(name);
				break;
			}

		} while (choice < 3);
		scanner.close();

	}
}
