package com.example.demo2.repo;

import com.example.demo2.Model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends CrudRepository<Subject,Long> {
}
