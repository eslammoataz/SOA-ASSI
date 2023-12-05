package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class TSevice {

    DataBase db;

    public TSevice(DataBase db) {
        this.db = db;
    }

    public List<StudentModel> getData() {
        return db.loadData();
    }

    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public String addStudent(StudentModel student) {
        List<StudentModel> students = db.loadAllStudents();

        if (student.getID() == null || student.getFirstName() == null || student.getLastName() == null ||
                student.getGender() == null || student.getGPA() == 0.0 || student.getLevel() == 0 || student.getAddress() == null) {
            return ("Some input values are not provided");
        }

        String id = student.getID();
        for (var s : students) {
            if (s.getID().equals(id)) {
                return "ID is not unique";
            }
        }
        if (!isAlpha(student.getFirstName()) || !isAlpha(student.getLastName())) {
            return "Firstname and Lastname must be characters only";
        }

        if (student.getGPA() < 0 || student.getGPA() > 4) {
            return "Gpa must be between 0 - 4";
        }

        students.add(student);
        db.appendStudentsToXml(students);
        return "Added Succesfully";
    }


    public List<StudentModel> searchByFirstName(String name) {
        return db.searchByFirstName(name);
    }


    public List<StudentModel> searchByGPA(double gpa) {
        return db.searchByGpa(gpa);
    }

    public boolean deleteByID(String id) {
        return db.deleteById(id);
    }


    public List<StudentModel> search(String searchField, String searchValue) {
        List<StudentModel> students = db.loadData();
        List<StudentModel> response = new ArrayList<>();

        if ("null".equalsIgnoreCase(searchValue)) {
            return getData();
        }

        for (var student : students) {
            boolean match = false;

            switch (searchField.toLowerCase()) {
                case "firstname":
                    match = student.getFirstName().equalsIgnoreCase(searchValue);
                    break;
                case "lastname":
                    match = student.getLastName().equalsIgnoreCase(searchValue);
                    break;
                case "gender":
                    match = student.getGender().equalsIgnoreCase(searchValue);
                    break;
                case "gpa":
                    match = String.valueOf(student.getGPA()).equals(searchValue);
                    break;
                case "level":
                    match = String.valueOf(student.getLevel()).equals(searchValue);
                    break;
                case "address":
                    match = student.getAddress().equalsIgnoreCase(searchValue);
                    break;
                default:

            }

            if (match) {
                response.add(student);
            }
        }
        return response;
    }

    public String updateByID(String id, StudentDto studentDto) {
        List<StudentModel> students = db.loadData();

        StudentModel student = null;

        for (var s : students) {
            if (s.getID().equals(id)) {
                student = s;
                break;
            }
        }

        if (student == null) {
            return "ID not Found in the databse";
        }

        System.out.println("Student Found with id " + student.getID());

        if (studentDto.getFirstName() != null) {
            student.setFirstName(studentDto.getFirstName());
        }

        if (studentDto.getLastName() != null) {
            student.setLastName(studentDto.getLastName());
        }

        if (studentDto.getGpa() != 0.0) {
            student.setGPA(studentDto.getGpa());
        }

        if (studentDto.getLevel() != 0) {
            student.setLevel(studentDto.getLevel());
        }

        if (studentDto.getAddress() != null) {
            student.setAddress(studentDto.getAddress());
        }

        if (studentDto.getGender() != null) {
            student.setGender(studentDto.getGender());
        }


        db.appendStudentsToXml(students);
        return "Updated Successfully";
    }


    public String sort(String kind, String sortField) {
        List<StudentModel> students = db.loadData();
        System.out.println(sortField);


        Comparator<StudentModel> comparator = getComparator(sortField);

        if ("desc".equalsIgnoreCase(kind)) {
            Collections.sort(students, comparator.reversed());
        } else {
            Collections.sort(students, comparator);
        }

        db.appendStudentsToXml(students);

        return "Sorted Successfully";
    }

    private Comparator<StudentModel> getComparator(String sortField) {
        switch (sortField.toLowerCase()) {
            case "firstname":
                return Comparator.comparing(StudentModel::getFirstName);
            case "lastname":
                return Comparator.comparing(StudentModel::getLastName);
            case "gender":
                return Comparator.comparing(StudentModel::getGender);
            case "gpa":
                return Comparator.comparingDouble(StudentModel::getGPA);
            case "level":
                return Comparator.comparingInt(StudentModel::getLevel);
            case "address":
                return Comparator.comparing(StudentModel::getAddress);
            default:
                return Comparator.comparing(StudentModel::getID);
        }
    }
}
