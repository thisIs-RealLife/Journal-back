package com.example.demo2.Service.ServiceRepo;

import com.example.demo2.Model.Student;
import com.example.demo2.repo.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAll(){
        return studentRepo.findAllByOrderByNameAsc();
    }

    public Student findById(Long id){
        return studentRepo.findById(id).get();
    }

    public Student add(Student student){
        return studentRepo.save(student);
    }

    public Student update(Student student){
        return studentRepo.save(student);
    }

    public void delete(Long id){
        studentRepo.deleteById(id);
    }


}
