package com.training;

import com.training.dto.UserRoleDto;
import com.training.entity.Login;
import com.training.entity.User;
import com.training.exception.UserNotFoundException;
import com.training.service.*;
import com.training.exception.AuthenticationException;
import com.training.exception.UnauthorizedException;

import java.util.Scanner;

public class MainApp {

    public static void print(UserService userService) throws UnauthorizedException {
        System.out.println("User Id, Name, Roles");
        userService.getAllUsers().forEach(u ->
                {
                    System.out.print(u.getUser().getId() + " " + u.getUser().getFirstName() + " " + u.getUser().getLastName() + " ");
                    u.getCurrentRoles().forEach(r-> System.out.print(r+", "));
                    System.out.println();

                }
        );

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserServiceImpl();
        LoginService loginService = new LoginServiceImpl();
        UserRoleService userRoleService = new UserRoleServiceImpl();

        System.out.println("=== Welcome to JDBC Login ===");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        UserRoleDto user;
        try {
            user = loginService.authenticate(username, password);
            System.out.println("Login successful! Roles: " + user.getCurrentRoles());
        } catch (AuthenticationException | UserNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Get All Users");
            System.out.println("4. Delete User");
            System.out.println("5. Create Login for User");
            System.out.println("6. Assign Role");
            System.out.println("7. Exit");
            System.out.print("Select a choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        if (!user.getCurrentRoles().contains("ADMIN"))
                            throw new UnauthorizedException("Only ADMIN can add users.");
                        System.out.print("Enter first name:");
                        String fn = sc.nextLine();
                        System.out.print("Enter last name:");
                        String ln = sc.nextLine();
                        userService.addUser(new User(0, fn, ln));
                        System.out.println("User Added!");
                        break;

                    case 2:
                        int uid = user.getUser().getId();
                        if (user.getCurrentRoles().contains("ADMIN")) {
                            print(userService);
                            System.out.println("Enter user id to update:");
                            uid = sc.nextInt();
                            sc.nextLine();
                        }
                        System.out.print("Enter first name:");
                        fn = sc.nextLine();
                        System.out.print("Enter last name:");
                        ln = sc.nextLine();
                        userService.updateUser(new User(uid, fn, ln));
                        System.out.println("User Updated!");
                        break;

                    case 3:
                        if (!user.getCurrentRoles().contains("ADMIN"))
                            throw new UnauthorizedException("Only ADMIN can view all users.");
                        print(userService);

                        break;

                    case 4:
                        if (!user.getCurrentRoles().contains("ADMIN"))
                            throw new UnauthorizedException("Only ADMIN can delete users.");
                        print(userService);

                        System.out.print("Enter user id to delete:");
                        uid = sc.nextInt();
                        userService.deleteUser(uid);
                        System.out.println("User Deleted!");
                        break;
                    case 5:
                        if (!user.getCurrentRoles().contains("ADMIN"))
                            throw new UnauthorizedException("Only ADMIN can create login.");
                        print(userService);

                        System.out.print("Enter user id to add Login:");
                        uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter username:");
                        username = sc.nextLine();
                        System.out.print("Enter password:");
                        password = sc.nextLine();
                        loginService.createLogin(new Login(0, username, password, uid));
                        System.out.print("Enter Role for the user [ADMIN/USER]:");
                        String role = sc.nextLine();
                        userRoleService.assignRole(uid, role);
                        break;

                    case 6:
                        if (!user.getCurrentRoles().contains("ADMIN"))
                            throw new UnauthorizedException("Only ADMIN can assign role.");
                        print(userService);

                        System.out.print("Enter user id to assign Role:");
                        uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Role for the user [ADMIN/USER]:");
                        role = sc.nextLine();
                        userRoleService.assignRole(uid, role);
                        break;
                    case 7:
                        System.out.println("Goodbye!");
                        return;
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
