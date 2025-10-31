package com.pluralsight.data;

import com.pluralsight.Vehicle;

public class LeaseContract extends  Contract {

    private double expectedEndingValue,leaseFee;

    public LeaseContract(Vehicle vehicleSold, String contractDate, String customerName, String customerEmail,
                         double expectedEndingValue, double leaseFee) {
        super(vehicleSold, contractDate, customerName, customerEmail);

        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        double originalPrice = getVehicleSold().getPrice();

        return  (originalPrice - expectedEndingValue) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();
        int numOfMonths = 36;
        double annualInterestRate = 0.04;

        double monthlyInterestRate = annualInterestRate / 12;

        return (totalPrice * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numOfMonths));
    }
}
