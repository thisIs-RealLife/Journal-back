package com.example.demo2.Service;

import com.example.demo2.Model.Student;
import com.example.demo2.Model.Subject;
import com.example.demo2.repo.SubjectRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectService {

    private final SubjectRepo subjectRepo;

    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public Subject findById(Long id){
        return subjectRepo.findById(id).get();
    }

    public Subject add(Subject subject){
      return subjectRepo.save(subject);
    }

    public Subject update(Subject subject){
        return subjectRepo.save(subject);
    }

    public void delete(Long id){
        subjectRepo.deleteById(id);
    }

}
