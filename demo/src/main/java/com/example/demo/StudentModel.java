package com.example.demo;

public class StudentModel {

    private String ID;

    private String Firstname;
    private String Lastname;
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

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
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

    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getGender() {
        return Gender;
    }

    public double getGPA() {
        return GPA;
    }


    public StudentModel(String ID, String firstname, String lastname, String gender, double GPA, int level, String address) {
        this.ID = ID;
        Firstname = firstname;
        Lastname = lastname;
        Gender = gender;
        this.GPA = GPA;
        Level = level;
        Address = address;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "ID='" + ID + '\'' +
                ", Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", Gender='" + Gender + '\'' +
                ", GPA=" + GPA +
                ", Level=" + Level +
                ", Address='" + Address + '\'' +
                '}';
    }



}
