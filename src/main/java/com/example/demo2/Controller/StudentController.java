package com.example.demo2.Controller;

import com.example.demo2.Model.Mark;
import com.example.demo2.Model.Student;
import com.example.demo2.Model.Subject;
import com.example.demo2.repo.MarkRepo;
import com.example.demo2.repo.StudentRepo;
import com.example.demo2.repo.SubjectRepo;
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

    private final MarkRepo markRepo;

    private final SubjectRepo subjectRepo;

    private final StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo, SubjectRepo subjectRepo, MarkRepo markRepo) {
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
        this.markRepo = markRepo;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentRepo.findAllByOrderByNameAsc());
    }


    @PostMapping("/add")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else
            return ResponseEntity.ok(studentRepo.save(student));
    }


    @PutMapping("/{id}/update")
    public ResponseEntity updateStudent(@Valid @RequestBody Student student, @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        student.setId(id);
        studentRepo.save(student);

        return ResponseEntity.ok("The student has been updated");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {

        try {
            studentRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok("Ok");
    }



    @PutMapping("/{id}/update/subject/{s_id}")
    public void updateSubject(@RequestBody Subject subject, @PathVariable("id") Long id, @PathVariable("s_id") Long s_id) {
        Subject subject1 = subjectRepo.findById(s_id).get();
        subject1.setNameSubject(subject.getNameSubject());
        subject1.setProfessor(subject.getProfessor());
        System.out.println(subject.getMark().getMark());
        subjectRepo.save(subject1);
    }


    @PostMapping("/{id}/addSubject")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject, @PathVariable("id") Long id) {
        subject.setStudent(studentRepo.findById(id).get());
        subject.getMark().setSubject(subject);
        subjectRepo.save(subject);
        return ResponseEntity.ok(subject);
    }

    @DeleteMapping("/{id}/deleteSubject/{s_id}")
    public void deleteSubject(@PathVariable("id") Long id, @PathVariable("s_id") Long s_id) {
        subjectRepo.deleteById(s_id);
    }

    /**
     * Mark
     * Ниже 3 метода для Удаления, обновления, добавления Mark.
     */
    @PostMapping("/{id}/addMark")
    public void addMark(@RequestBody Mark mark, @PathVariable("id") Long id) {
        Subject subject = subjectRepo.findById(id).get();
        System.out.println(mark.getMark());
        subject.setMark(mark);
        mark.setSubject(subject);
        subjectRepo.save(subject);
        markRepo.save(mark);

    }

    @PutMapping("/{id}/updateMark")
    public void editMark(@RequestBody Mark mark, @PathVariable("id") Long id) {
        Subject subject = subjectRepo.findById(id).get();
        subject.setMark(mark);
        subjectRepo.save(subject);
    }


    @DeleteMapping("/{id}/deleteMark")
    public void deleteMark(@RequestBody Mark mark, @PathVariable("id") Long id) {
        markRepo.deleteById(id);
    }
}
