package com.example.demo2.repo;

import com.example.demo2.Model.Mark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepo extends CrudRepository<Mark, Long> {
}
