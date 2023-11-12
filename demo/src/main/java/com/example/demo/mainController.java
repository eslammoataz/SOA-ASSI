package com.example.demo;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@RestController
public class mainController {

    TSevice Service;
    public mainController(TSevice t) {
        this.Service = t;
    }


    @GetMapping("/getStudents")
    public List<StudentModel> createFile(){
        List<StudentModel> studentsData  =Service.getData();

        return studentsData;
    }

    @PostMapping("/addStudents")
    public String addStudents(@RequestBody List<StudentModel> students){
        Service.addStudent(students);
        return "Added Successfully";
    }

    @GetMapping("/getbyname/{firstname}")
    public List<StudentModel> searchByFirstName(@PathVariable  String firstname){
        return Service.searchByFirstName(firstname);
    }

    @GetMapping("/getbygpa/{gpa}")
    public List<StudentModel> searchByFirstName(@PathVariable  double gpa){
        return Service.searchByGPA(gpa);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable  String id){
        if(Service.deleteByID(id))
            return "deleted successfully";

        return "Not Found";
    }




}
