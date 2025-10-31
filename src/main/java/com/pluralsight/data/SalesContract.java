package com.pluralsight.data;

import com.pluralsight.Vehicle;

public class SalesContract extends Contract {
    private double salesTax, recordingFee, processingFee;
    private boolean isFinanced;

    public SalesContract(Vehicle vehicleSold, String contractDate, String customerName, String customerEmail,
                         double salesTax, double recordingFee, double processingFee, boolean isFinanced) {
        super(vehicleSold, contractDate, customerName, customerEmail);

        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0;
        }

        double totalPrice = getTotalPrice();

        int numberOfMonths;
        double annualInterestRate;

        if (totalPrice >= 10000) {

            numberOfMonths = 48;
            annualInterestRate = 0.0425;

        } else {
            numberOfMonths = 24;
            annualInterestRate = 0.0525;
        }
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyPayment = (totalPrice * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfMonths));

        return monthlyPayment;
    }
}
