package com.example.ergasia1;

public class FsCustomer {
    private int customerId;
    private String customerName;
    private int zip;

    public FsCustomer() {
    }
    public FsCustomer(int customerId, String customerName, int zip) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.zip = zip;
    }



    //cid
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    //cname
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    //zip
    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
