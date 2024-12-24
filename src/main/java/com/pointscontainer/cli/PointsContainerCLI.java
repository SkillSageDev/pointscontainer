package com.pointscontainer.cli;

import com.pointscontainer.container.Point;
import com.pointscontainer.container.PointsContainer;
import com.pointscontainer.errors.PointNotFoundException;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class PointsContainerCLI {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Points Container CLI");
        PointsContainer container = initializeContainer();

        boolean running = true;
        while (running) {
            displayMenu();
            int option = getValidatedInteger("Choose an option:");

            switch (option) {
                case 1:
                    addPoint(container);
                    break;
                case 2:
                    deletePoint(container);
                    break;
                case 3:
                    searchPoint(container);
                    break;
                case 4:
                    getPointByIndex(container);
                    break;
                case 5:
                    sortPoints(container);
                    break;
                case 6:
                    displayPoints(container);
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting the CLI. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static PointsContainer initializeContainer() {
        System.out.println("Choose list type:");
        System.out.println("1. Unlimited list");
        System.out.println("2. Limited list (with capacity)");

        int choice = getValidatedInteger("Your choice:");

        PointsContainer container;
        if (choice == 1) {
            container = new PointsContainer();  // Unlimited list
            System.out.println("You have chosen an unlimited list.");
        } else if (choice == 2) {
            int capacity = getValidatedInteger("Enter the capacity of the list:");
            container = new PointsContainer(capacity);  // Limited list
            System.out.println("You have chosen a limited list with capacity: " + capacity);
        } else {
            System.out.println("Invalid choice. Exiting.");
            System.exit(1);
            return null;  // This won't actually be reached, but it's here to satisfy the compiler.
        }
        return container;
    }

    private static void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Add a point");
        System.out.println("2. Delete a point");
        System.out.println("3. Search for a point");
        System.out.println("4. Get a point by index");
        System.out.println("5. Sort points by magnitude");
        System.out.println("6. Display all points");
        System.out.println("7. Exit");
    }

    private static void addPoint(PointsContainer container) {
        double x = getValidatedDouble("Enter the X coordinate of the point:");
        double y = getValidatedDouble("Enter the Y coordinate of the point:");
        Point point = new Point(x, y);
        container.add(point);
        System.out.println("Point " + point + " added.");
    }

    private static void deletePoint(PointsContainer container) {
        double x = getValidatedDouble("Enter the X coordinate of the point to delete:");
        double y = getValidatedDouble("Enter the Y coordinate of the point to delete:");
        Point point = new Point(x, y);
        try {
            container.delete(point);
            System.out.println("Point " + point + " deleted.");
        } catch (PointNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchPoint(PointsContainer container) {
        double x = getValidatedDouble("Enter the X coordinate of the point to search:");
        double y = getValidatedDouble("Enter the Y coordinate of the point to search:");
        Point point = new Point(x, y);
        try {
            boolean found = container.search(point);
            System.out.println("Point " + point + " is in the container.");
        } catch (PointNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getPointByIndex(PointsContainer container) {
        int index = getValidatedInteger("Enter the index of the point:");
        try {
            Point point = container.get(index);
            System.out.println("Point at index " + index + " is: " + point);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index. Please try again.");
        }
    }

    private static void sortPoints(PointsContainer container) {
        try {
            container.sort();
            System.out.println("Points have been sorted by magnitude.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayPoints(PointsContainer container) {
        System.out.println("Displaying all points in the container:");
        Iterator<Point> it = container.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    // Input validation methods

    private static int getValidatedInteger(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();  // Clear the invalid input from the scanner
            }
        }
    }

    private static double getValidatedDouble(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                scanner.nextLine();  // Clear the invalid input from the scanner
            }
        }
    }
}
