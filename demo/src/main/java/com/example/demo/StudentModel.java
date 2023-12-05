package com.example.demo;

public class StudentModel {

    private String ID;

    private String FirstName;
    private String LastName;
    private String Gender;
    private double GPA;

    private int Level;

    private  String Address;

    public void setLevel(int level) {
        Level = level;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getLevel() {
        return Level;
    }

    public String getAddress() {
        return Address;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getGender() {
        return Gender;
    }

    public double getGPA() {
        return GPA;
    }


    public StudentModel(String ID, String firstName, String lastName, String gender, double GPA, int level, String address) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        this.GPA = GPA;
        Level = level;
        Address = address;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "ID='" + ID + '\'' +
                ", Firstname='" + FirstName + '\'' +
                ", Lastname='" + LastName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", GPA=" + GPA +
                ", Level=" + Level +
                ", Address='" + Address + '\'' +
                '}';
    }



}
