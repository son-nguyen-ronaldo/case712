package view;

import controller.LoginManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {
    Scanner scanner = new Scanner(System.in);
    LoginManagement loginManagement = new LoginManagement();
    ComputerView computerView = new ComputerView();

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
            loginMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    loginManagement.login();
                    computerView.start();
                    break;
                }
                case 0: {
                    System.exit(0);
                }
                default: {
                    System.out.println("<<< Invalid choice! Please enter your choice again! >>>");
                }
            }
        } while (choice != 0);
    }

    private void loginMenu() {
        System.out.println("----- CYBER GAMING MANAGEMENT -----");
        System.out.println("1. Login");
        System.out.println("0. Exit");
        System.out.println("-----------------------------------");
    }
}
