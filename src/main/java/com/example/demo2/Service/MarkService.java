package com.example.demo2.Service;

import com.example.demo2.Model.Mark;
import com.example.demo2.repo.MarkRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarkService {
    private final MarkRepo markRepo;

    public MarkService(MarkRepo markRepo) {
        this.markRepo = markRepo;
    }

    public Mark add(Mark mark){
        return markRepo.save(mark);
    }

    public Mark update(Mark mark){
        return markRepo.save(mark);
    }

    public void delete(Long id){
        markRepo.deleteById(id);
    }
}
