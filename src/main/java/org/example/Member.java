package org.example;

public class Member {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int apartmentNumber;
    private double area;

    public Member() {
    }

    public String getFirstName() {
        return firstName;
    }

    public Member setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Member setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Member setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public Member setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        return this;
    }

    public double getArea() {
        return area;
    }

    public Member setArea(double area) {
        this.area = area;
        return this;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", apartmentNumber=" + apartmentNumber +
                ", area=" + area +
                '}';
    }
}

