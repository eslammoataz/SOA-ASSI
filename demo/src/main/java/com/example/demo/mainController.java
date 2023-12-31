package com.example.demo;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @CrossOrigin(origins = "*")

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
        for(var x : students){
            System.out.println(x.getID());
        }
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

    @GetMapping("/search")
    public List<StudentModel> searchByFirstName(@RequestParam String gpa , @RequestParam String firstName){
        return Service.search(gpa, firstName);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable  String id){
        if(Service.deleteByID(id))
            return "deleted successfully";

        return "Not Found";
    }






}