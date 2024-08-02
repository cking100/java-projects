import java.util.Scanner;

public class App {
    private String AccountNumber;
    private String AccountHolder;
    private long balance;
    private long withdraw;     

    Scanner scanner = new Scanner(System.in);
    public  void OpenAccount() {
        System.out.print("Enter Account No: ");  
        AccountNumber = scanner.next();  
        System.out.print("Enter Account type: ");  
       AccountHolder  = scanner.next();  
        System.out.print("Enter Balance: ");  
        balance = scanner.nextLong();  
    }
    public void show() {
        System.out.println("Your current account number is " + AccountNumber);
        System.out.println("The current account holder is" + AccountHolder);
        System.out.println("Your current balance is" + balance);
    }

    public void deposit() {  
        long amount;  
        System.out.println("Enter the amount you want to deposit: ");  
        amount = scanner.nextLong();  
        balance = balance + amount;  
    }  
    public void withdrawal() {  
        long amount;  
        System.out.println("Enter the amount you want to withdraw: ");  
        amount = scanner.nextLong();  
        if (balance >= amount) {  
            balance = balance - amount;  
            System.out.println("Balance after withdrawal: " + balance);  
        } else {  
            System.out.println("Your balance is less than " + amount + "\tTransaction failed...!!" );  
        }  
    }
    public void exit() {
        System.out.println("Thanks for visiting");
    }
    public static void main(String[] args) throws Exception {

        
        Scanner scanner = new Scanner(System.in);
        App account = new App();
        int choice;
        do { 
         System.err.println("1. open account");
         System.err.println("2.show account balance");
         System.out.println("3.deposit");
         System.out.println("4. withdrawl");
         System.out.println("5. exit");
         System.out.println("Enter your preffered choice");
         choice = scanner.nextInt();
         switch (choice) {
            case 1:
            account.OpenAccount();
            break;
            case 2:
            account.show();
            break;
            case 3:
            account.deposit();
            break;
            case 4:
            account.withdrawal();
            break;
            case 5:
            account.exit();
            break;
         }

        } while(choice !=5);
        scanner.close();   
}

}
