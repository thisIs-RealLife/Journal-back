package com.example.demo2.Controller;

import com.example.demo2.Model.Subject;
import com.example.demo2.Service.StudentService;
import com.example.demo2.Service.SubjectService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "http://localhost:4200")
public class SubjectController {

    private final SubjectService subjectService;

    private final StudentService studentService;


    public SubjectController(SubjectService subjectService, StudentService studentService) {

        this.subjectService = subjectService;
        this.studentService = studentService;
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<Subject> addSubject(@Valid @RequestBody Subject subject,
                                              @PathVariable("id") Long id,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getFieldError().
                    getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            subject.setStudent(studentService.findById(id));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        if (subject.getMark() != null)
            subject.getMark().setSubject(subject);

        subjectService.add(subject);
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Subject> updateSubject(@Valid @RequestBody Subject subject,
                                                 @PathVariable("id") Long id,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getFieldError().
                    getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            Subject subject1 = subjectService.findById(id);
            subject1.setNameSubject(subject.getNameSubject());
            subject1.setProfessor(subject.getProfessor());
            subject1.setMark(subject.getMark());
            return ResponseEntity.ok(subjectService.update(subject1));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteSubject(@PathVariable("id") Long id) {
        try {
            subjectService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
