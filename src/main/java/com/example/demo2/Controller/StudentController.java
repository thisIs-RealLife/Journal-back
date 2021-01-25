package com.example.demo2.Controller;

import com.example.demo2.Model.Student;
import com.example.demo2.Service.StudentService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }


    @PostMapping("/add")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else
            return ResponseEntity.ok(studentService.add(student));
    }


    @PutMapping("/{id}/update")
    public ResponseEntity updateStudent(@Valid @RequestBody Student student, @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        student.setId(id);
        studentService.update(student);

        return ResponseEntity.ok("The student has been updated");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {

        try {
            studentService.delete(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok("Ok");
    }

}
