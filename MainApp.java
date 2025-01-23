import javax.crypto.SecretKey;
import java.io.File;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Secure File Encryption System!");

        try {
            while (true) {
                System.out.println("\nOptions:");
                System.out.println("1. Encrypt a file");
                System.out.println("2. Decrypt a file");
                System.out.println("3. Generate file integrity hash");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 4) {
                    System.out.println("Exiting... Goodbye!");
                    break;
                }

                System.out.print("Enter the file path: ");
                String filePath = scanner.nextLine();
                File file = new File(filePath);

                if (!file.exists()) {
                    System.out.println("File not found!");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter a password for encryption: ");
                        String password = scanner.nextLine();
                        SecretKey key = FileEncryptor.generateKey(password);

                        System.out.print("Enter output file path: ");
                        String encryptedFilePath = scanner.nextLine();
                        File encryptedFile = new File(encryptedFilePath);

                        FileEncryptor.encryptFile(file, encryptedFile, key);
                        System.out.println("File encrypted successfully!");
                        break;

                    case 2:
                        System.out.print("Enter the password for decryption: ");
                        password = scanner.nextLine();
                        key = FileEncryptor.generateKey(password);

                        System.out.print("Enter output file path: ");
                        String decryptedFilePath = scanner.nextLine();
                        File decryptedFile = new File(decryptedFilePath);

                        FileEncryptor.decryptFile(file, decryptedFile, key);
                        System.out.println("File decrypted successfully!");
                        break;

                    case 3:
                        String hash = IntegrityChecker.generateHash(file);
                        System.out.println("File hash (SHA-256): " + hash);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
