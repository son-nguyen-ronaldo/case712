package controller;

import java.util.Scanner;

import static model.Const.*;

public class LoginManagement {
    Scanner scanner = new Scanner(System.in);

    public void login() {
        System.out.print("Username: ");
        String userName = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (userName.equals(LOGIN_USERNAME) && password.equals(LOGIN_PASSWORD)) {
            System.out.println("<<< Login Success! >>>");
        } else {
            System.out.println("<<< Login Failed! Please try again! >>>");
            login();
        }
    }
}
