package view;

import controller.AccountManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountView {
    Scanner scanner = new Scanner(System.in);
    AccountManagement accountManagement = new AccountManagement();

    public void start() {
        try {
            run();
        } catch (InputMismatchException e) {
            System.out.println("<<< Not a number! Please enter your choice again! >>>");
            scanner.nextLine();
            start();
        }
    }

    private void run() {
        int choice;
        do {
            accountMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    accountManagement.display();
                    break;
                }
                case 2: {
                    accountManagement.add();
                    break;
                }
                case 3: {
                    accountManagement.edit();
                    break;
                }
                case 4: {
                    accountManagement.remove();
                    break;
                }
                case 5: {
                    accountManagement.recharge();
                    break;
                }
                case 0: {
                    return;
                }
                default: {
                    System.out.println("<<< Invalid choice! Please enter your choice again! >>>");
                }
            }
        } while (choice != 0);
    }

    private void accountMenu() {
        System.out.println("\n------- ACCOUNT MANAGEMENT --------");
        System.out.println("1. Display list of accounts");
        System.out.println("2. Add new account into list");
        System.out.println("3. Edit account's information");
        System.out.println("4. Remove account from list");
        System.out.println("5. Recharge for account");
        System.out.println("0. Back");
    }
}
