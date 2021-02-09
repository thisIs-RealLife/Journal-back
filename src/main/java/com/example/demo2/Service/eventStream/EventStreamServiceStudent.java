package com.example.demo2.Service.eventStream;

import com.example.demo2.Model.Student;
import com.example.demo2.Service.ServiceRepo.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class EventStreamServiceStudent {

    public final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    private final StudentService studentService;

    public EventStreamServiceStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    public Student eventSteamAddStudent(Student s){
        Student student = studentService.add(s);

        for (SseEmitter emitter : emitters){
            try {
                emitter.send(SseEmitter.event().name("message").data(student));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
        return student;
    }
}
