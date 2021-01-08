package com.example.demo2.repo;

import com.example.demo2.Model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepo extends JpaRepository<Mark, Long> {
}
