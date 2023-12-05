package com.example.demo;

public class StudentDto {
    private String firstName;
    private String lastName;
    private double gpa;
    private int level;
    private String address;
    private String gender;

    // getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                ", Firstname='" + firstName + '\'' +
                ", Lastname='" + lastName + '\'' +
                ", Gender='" + gender + '\'' +
                ", GPA=" + gpa +
                ", Level=" + level +
                ", Address='" + address + '\'' +
                '}';
    }


}
