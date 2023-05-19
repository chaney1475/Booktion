package com.project.Booktion.model;

public class Address {
    private String zipcode;
    private String address1;
    private String address;

    public Address() {
    }

    public Address(String zipcode, String address1, String address) {
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
