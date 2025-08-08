import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Notes Manager ===");
            System.out.println("1. Write Notes");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            switch (choice) {
                case 1:
                    writeNotes();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);
    }

    // Write notes to file (append mode)
    public static void writeNotes() {
        System.out.println("Enter your note (type 'exit' to stop):");

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // append mode
            while (true) {
                String note = scanner.nextLine();
                if (note.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.write(note + "\n");
            }
            System.out.println("Notes saved successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read notes from file
    public static void readNotes() {
        System.out.println("\n--- Your Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isEmpty = true;
            while ((line = reader.readLine()) != null) {
                isEmpty = false;
                System.out.println(line);
            }
            if (isEmpty) {
                System.out.println("No notes found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Notes file not found. Start writing first.");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
