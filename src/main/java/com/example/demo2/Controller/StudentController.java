package com.example.demo2.Controller;

import com.example.demo2.Model.Mark;
import com.example.demo2.Model.Student;
import com.example.demo2.Model.Subject;
import com.example.demo2.repo.MarkRepo;
import com.example.demo2.repo.StudentRepo;
import com.example.demo2.repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    
    private final MarkRepo markRepo;

    private final SubjectRepo subjectRepo;

    private final StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo, SubjectRepo subjectRepo, MarkRepo markRepo) {
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
        this.markRepo = markRepo;
    }

    /**
     * STUDENT
     * Ниже этого комментария методы CRUD для объекта Student
     */

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> list = new ArrayList<>();
        for (Student student : studentRepo.findAll()) {
            list.add(student);
        }
        return ResponseEntity.ok(list);
    }


    @PostMapping("/add")
    public void createStudent(@RequestBody Student student) {
        studentRepo.save(student);
    }


    @PutMapping("/{id}/update")
    public void updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {
        student.setId(id);
        studentRepo.save(student);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentRepo.deleteById(id);
    }

    /**
     * SUBJECT
     * Ниже 3 метода для Удаления, обновления, добавления Subject.
     */

    @PutMapping("/{id}/update/subject/{s_id}")
    public void updateSubject(@RequestBody Subject subject, @PathVariable("id") Long id, @PathVariable("s_id") Long s_id) {

    }


    @PostMapping("/{id}/addSubject")
    public void addSubject(@RequestBody Subject subject, @PathVariable("id") Long id) {
        subject.setStudent(studentRepo.findById(id).get());
        subjectRepo.save(subject);
    }


    /**
     * MARK
     * Ниже 3 метода для Удаления, обновления, добавления Subject.
     */

    @PostMapping("/{id}/addMark")
    public void addMark(@RequestBody Mark mark, @PathVariable("id") Long id) {
        mark.setSubject(subjectRepo.findById(id).get());
        markRepo.save(mark);
    }
}
