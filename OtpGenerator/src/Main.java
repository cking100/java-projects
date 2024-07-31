import java.util.Random;
import java.util.Scanner;

public class Main {

    public static String generateOTP(int length, String characters) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            otp.append(characters.charAt(index));
        }

        return otp.toString();
    }

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Enhanced OTP Generator!");
            System.out.println("Choose the type of OTP:");
            System.out.println("1. Numeric");
            System.out.println("2. Alphanumeric");
            System.out.println("3. Alphanumeric with Special Characters");

            int choice;
            while (true) {
                System.out.print("Enter your choice (1-3): ");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

            System.out.print("Enter the length of the OTP: ");
            int length = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the number of OTPs to generate: ");
            int numOtps = scanner.nextInt();
            scanner.nextLine();

            String characters;
            switch (choice) {
                case 1:
                    characters = "0123456789";
                    break;
                case 2:
                    characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                    break;
                case 3:
                    characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{}|;:',.<>?/";
                    break;
                default:
                     throw new IllegalArgumentException("Invalid choice.");
            }

            for (int i = 0; i < numOtps; i++) {
                String otp = generateOTP(length, characters);
                System.out.println("Generated OTP: " + otp);
            }

            while (true) {
                System.out.print("Do you want to generate more OTPs? (yes/no): ");
                String resetChoice = scanner.nextLine().trim().toLowerCase();
                if (resetChoice.equals("yes")) {
                    break;
                } else if (resetChoice.equals("no")) {
                    scanner.close();
                    System.out.println("Goodbye!");
                    return;
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }
        }
    }

    public static void main(String[] args) {
        displayMenu();
    }
}
