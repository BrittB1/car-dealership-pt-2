package com.pluralsight;

import java.util.List;
import java.util.Scanner;

// Shows possible options and gets input from user; doesn't know anything about searching

public class UserInterface {

    private Dealership dealership;
    private Scanner keyboard = null;

    public UserInterface() {
        this.keyboard = new Scanner(System.in);
    }

    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        this.dealership = manager.getDealership();
    }

    public void showHomeScreen() {
        init();

        boolean running = true;
        keyboard = new Scanner(System.in);

        while (running) {

            System.out.println("""
                     ╔════════════════════════════════════╗
                     ║         🏠 HOME SCREEN             ║
                     ╠════════════════════════════════════╣
                     ║ Welcome! What do you want to do ?  ║
                     ║   Choose an option by number:      ║
                     ║                                    ║
                     ║        Find a vehicle by:          ║
                     ║                                    ║
                     ║  1. 📋 PRICE RANGE                 ║
                     ║  2. 💰 MAKE/MODEL                  ║
                     ║  3. 💸 YEAR RANGE                  ║
                     ║  4. 📈 COLOR                       ║
                     ║  5. 🏠 MILEAGE                     ║
                     ║  6. 💰 TYPE                        ║
                     ║                                    ║
                     ║       + Other Options +            ║
                     ║                                    ║
                     ║  7. 📋 SHOW ALL VEHICLES           ║
                     ║  8. 💸 Add a Vehicle               ║
                     ║  9. 📈 Remove a Vehicle            ║
                     ║  0. 🚪 EXIT                        ║
                     ╚════════════════════════════════════╝
                    \s""");

            int selection = keyboard.nextInt();
            keyboard.nextLine();

            switch (selection) {

                case 1:
                    processGetByPrice();
                    break;

                case 2:
                    processGetByMakeMode();
                    break;

                case 3:
                    processGetByYearRange();
                    break;

                case 4:
                    processGetColorByRequest();
                    break;
                case 5:
                    processGetByMileageRange();
                    break;
                case 6:
                    processGetByType();
                    break;
                case 7:
                    List<Vehicle> vehicles = dealership.getAllVehicles();
                    displayVehicles(vehicles);
                    break;
                case 8:
                    processAddVehicle();
                    break;
                case 9:
                    processRemoveVehicle();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("❌ Sorry invalid option. Please try again");

            }
        }
    }

    private void processAddVehicle() {

        System.out.print("Enter VIN: ");
        int vin = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Enter year: ");
        int year = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Enter make: ");
        String make = keyboard.nextLine();

        System.out.print("Enter model: ");
        String model = keyboard.nextLine();

        System.out.print("Enter vehicle type: ");
        String vehicleType = keyboard.nextLine();

        System.out.print("Enter color: ");
        String color = keyboard.nextLine();

        System.out.print("Enter odometer: ");
        int odometer = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Enter price: $");
        double price = keyboard.nextDouble();
        keyboard.nextLine();

        Vehicle newVehicle = new Vehicle(vin, year, odometer, make, model, color, vehicleType, price);

        dealership.addVehicle(newVehicle);

        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);

        System.out.println("New vehicle added successfully! ");
    }

    private void processRemoveVehicle() {
        System.out.println("Enter the VIN of vehicle to be removed: ");
        int vin = keyboard.nextInt();
        keyboard.nextLine();

        List<Vehicle> allVehicles = dealership.getAllVehicles();
        Vehicle vehicleToDelete = null;

        for (Vehicle vehicle : allVehicles) {
            if (vehicle.getVin() == vin) {
                vehicleToDelete = vehicle;
                break;
            }
        }
        if (vehicleToDelete != null) {
            dealership.removeVehicle(vehicleToDelete);

            DealershipFileManager manager = new DealershipFileManager();
            manager.saveDealership(dealership);

            System.out.println("Vehicle removed successfully!");

        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("❌ No vehicles found matching your criteria.");
            return;
        }

        System.out.println("\n" + "=".repeat(120));
        System.out.printf("%-10s %-6s %-12s %-12s %-15s %-10s %-10s %-10s%n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("=".repeat(120));

        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-10d %-6d %-12s %-12s %-15s %-10s %-10d $%-9.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }
        System.out.println("=".repeat(120) + "\n");
    }

    private void processGetByType() {
        System.out.println("Enter vehicle type (SUV, Truck, Van, etc):  ");
        String vehicleType = keyboard.nextLine();
        keyboard.nextLine();

        List<Vehicle> results = dealership.getVehiclesByType(vehicleType);

        displayVehicles(results);
    }

    private void processGetByMileageRange() {
        System.out.println("Enter minimum mileage:  ");
        int min = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Enter maximum mileage:  ");
        int max = keyboard.nextInt();
        keyboard.nextLine();

        List<Vehicle> results = dealership.getVehiclesByMileage(min, max);
        displayVehicles(results);

    }

    private void processGetByYearRange() {
        System.out.println("Enter a minimum year: ");
        int min = keyboard.nextInt();
        keyboard.nextLine();

        System.out.println("Enter a maximum year: ");
        int max = keyboard.nextInt();

        List<Vehicle> results = dealership.getVehiclesByYear(min, max);

        displayVehicles(results);
    }

    private void processGetByMakeMode() {

        System.out.println("Enter the vehicle make: ");
        String make = keyboard.nextLine();


        System.out.println("Enter a maximum model: ");
        String model = keyboard.nextLine();


        List<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);

        displayVehicles(results);
    }

    private void processGetByPrice() {

        System.out.println("Enter a minimum price: ");
        double min = keyboard.nextDouble();
        keyboard.nextLine();

        System.out.println("Enter a maximum price: ");
        double max = keyboard.nextDouble();
        keyboard.nextLine();

        List<Vehicle> results = dealership.getVehiclesByPrice(min, max);

        displayVehicles(results);
    }

    private void processGetColorByRequest() {

        System.out.println("Enter color: ");
        String color = keyboard.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);

        displayVehicles(vehicles);
    }

}