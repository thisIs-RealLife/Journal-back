package com.example.demo2.repo;

import com.example.demo2.Model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Student, Long> {

}
