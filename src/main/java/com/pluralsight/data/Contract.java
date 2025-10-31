package com.pluralsight.data;

import com.pluralsight.Vehicle;

public abstract class Contract {

    private Vehicle vehicleSold;
    private String contractDate,
            customerName,customerEmail;

    public Contract(Vehicle vehicleSold, String contractDate, String customerName, String customerEmail) {
        this.vehicleSold = vehicleSold;
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
