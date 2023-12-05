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
    public String addStudents(@RequestBody StudentModel student){

        return Service.addStudent(student);
    }

    @GetMapping("/getbyname/{firstname}")
    public List<StudentModel> searchByFirstName(@PathVariable  String firstname){
        return Service.searchByFirstName(firstname);
    }

    @GetMapping("/getbygpa/{gpa}")
    public List<StudentModel> searchByFirstName(@PathVariable  double gpa){
        return Service.searchByGPA(gpa);
    }

    @GetMapping("/search/{searchField}/{searchValue}")
    public List<StudentModel> search(@PathVariable String searchField ,@PathVariable String searchValue){
        return Service.search(searchField , searchValue);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable  String id){
        if(Service.deleteByID(id))
            return "deleted successfully";

        return "Not Found";
    }

    @PutMapping("/update/{id}")
    public String updateById(@PathVariable String id, @RequestBody StudentDto requestBody) {
        return Service.updateByID(id , requestBody);
    }

    @GetMapping("/sort/{kind}/{sortField}")
    public String sort(@PathVariable String sortField , @PathVariable String kind) {
        return Service.sort(kind,sortField);
    }



}