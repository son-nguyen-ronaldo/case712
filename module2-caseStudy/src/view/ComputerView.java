package view;

import controller.BillManagement;
import controller.ComputerManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ComputerView {
    Scanner scanner = new Scanner(System.in);
    ComputerManagement computerManagement = new ComputerManagement();
    BillManagement billManagement = new BillManagement();
    AccountView accountView = new AccountView();

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
            computerMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    computerManagement.display();
                    break;
                }
                case 2: {
                    computerManagement.add();
                    break;
                }
                case 3: {
                    computerManagement.edit();
                    break;
                }
                case 4: {
                    computerManagement.remove();
                    break;
                }
                case 5: {
                    computerManagement.startPlaying();
                    break;
                }
                case 6: {
                    computerManagement.addService();
                    break;
                }
                case 7: {
                    computerManagement.endPlaying();
                    break;
                }
                case 8: {
                    accountView.start();
                    break;
                }
                case 9: {
                    billManagement.checkRevenue();
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

    private void computerMenu() {
        System.out.println("\n------- COMPUTER MANAGEMENT -------");
        System.out.println("1. Display list of computers");
        System.out.println("2. Add new computer into list");
        System.out.println("3. Edit computer's information");
        System.out.println("4. Remove computer from list");
        System.out.println("5. Start playing");
        System.out.println("6. Add new service");
        System.out.println("7. End playing and make bill");
        System.out.println("8. Manage registered account");
        System.out.println("9. Show total revenue");
        System.out.println("0. Exit");
        System.out.println("-----------------------------------");
    }
}
