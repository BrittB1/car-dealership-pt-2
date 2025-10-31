package com.pluralsight.FileManagers;

import com.pluralsight.Vehicle;
import com.pluralsight.data.Contract;
import com.pluralsight.data.LeaseContract;
import com.pluralsight.data.SalesContract;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public void saveContract(Contract contract) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/contracts.csv", true))) {

            if (contract instanceof SalesContract) {

                // casting necessary to acces sales-specific methods
                SalesContract sale = (SalesContract) contract;

                Vehicle vehicle = sale.getVehicleSold();

                String line = "SALE|" +
                        sale.getContractDate() + "|" +
                        sale.getCustomerName() + "|" +
                        sale.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "|" +
                        sale.getSalesTax() + "|" +
                        sale.getRecordingFee() + "|" +
                        sale.getProcessingFee() + "|" +
                        sale.getTotalPrice() + "|" +
                        (sale.isFinanced() ? "YES" : "NO") + "|" +
                        sale.getMonthlyPayment();

                bw.write(line);
                bw.newLine();

            } else if (contract instanceof LeaseContract) {

                LeaseContract lease = (LeaseContract) contract;

                Vehicle vehicle = lease.getVehicleSold();

                String line = "LEASE|" +
                        lease.getContractDate() + "|" +
                        lease.getCustomerName() + "|" +
                        lease.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "|" +
                        lease.getExpectedEndingValue() + "|" +
                        lease.getLeaseFee() + "|" +
                        lease.getTotalPrice() + "|" +
                        lease.getMonthlyPayment();

                bw.write(line);
                bw.newLine();

            }

        } catch (IOException e) {
            System.out.println("Error! Could not print to file" + e.getMessage());
            e.printStackTrace();
        }


    }

}
