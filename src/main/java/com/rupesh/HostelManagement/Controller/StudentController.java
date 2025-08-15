package com.rupesh.HostelManagement.Controller;

import com.rupesh.HostelManagement.DTO.StudentDTO;
import com.rupesh.HostelManagement.Repository.StudentRepository;
import com.rupesh.HostelManagement.Service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/getAll")
    public List<StudentDTO> GetAllStudent(){
        return studentService.getAllStudents();
    }

    @GetMapping(path ="/get/{id}")
    public StudentDTO GetStudent(@PathVariable("id") int id){
        return studentService.getStudentById(id);
    }

    @PostMapping(path = "/create")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO){
        return studentService.CreateNewStudent(studentDTO);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String DeleteStudent(@PathVariable("id")int id){
        return studentService.DeleteStudentById(id);
    }

    @PutMapping("/update{id}")
    public StudentDTO updateStudent(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudentById(id, studentDTO);
    }

}
