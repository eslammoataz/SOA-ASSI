package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public void addStudent(List<StudentModel> students) {
        students.addAll(db.loadAllStudents());
        db.appendStudentsToXml(students);
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


    public List<StudentModel> search(Double gpa, String fname) {
        List<StudentModel> students = db.loadData();
        List<StudentModel> response = new ArrayList<>();

        if (fname == null && gpa == null) {
            return getData();
        }
        for (var student : students) {
            boolean matchFirstName = fname == null || student.getFirstname().equals(fname);
            boolean matchGPA = gpa == null || student.getGPA() == gpa;

            if (matchFirstName && matchGPA) {
                response.add(student);
            }
        }
        return response;
    }


}
