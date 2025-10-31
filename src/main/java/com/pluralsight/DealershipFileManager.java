package com.pluralsight;

import java.io.*;


public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"))) {

            String dealershipInfo = reader.readLine();
            String[] parts = dealershipInfo.split("\\|");

            String dealershipName = parts[0].trim();
            String address = parts[1];
            String phoneNum = parts[2];

            dealership = new Dealership(dealershipName, address, phoneNum);

            String line;

            while ((line = reader.readLine()) != null) {
                String[] vehicleInfo = line.split("\\|");

                if (vehicleInfo.length == 8) {

                    int vin = Integer.parseInt(vehicleInfo[0]);
                    int year = Integer.parseInt(vehicleInfo[1]);
                    String make = vehicleInfo[2];
                    String model = vehicleInfo[3];
                    String vehicleType = vehicleInfo[4];
                    String color = vehicleInfo[5];
                    int odometer = Integer.parseInt(vehicleInfo[6]);
                    double price = Double.parseDouble(vehicleInfo[7]);

                    Vehicle vehicle = new Vehicle(vin, year, odometer, make, model, color, vehicleType, price);
                    dealership.addVehicle(vehicle);
                }

            }
        } catch (IOException e) {
            System.out.println("Error reading file!" + e.getMessage());
            e.printStackTrace();
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership){
        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/inventory.csv"))){

            String dealershipLine = dealership.getDealershipName() + "|" +
                    dealership.getAddress() + "|" + dealership.getPhoneNum();

            bufWriter.write(dealershipLine);
            bufWriter.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                String vehicleLine = vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice();

                bufWriter.write(vehicleLine);
                bufWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();

        }
    }
}