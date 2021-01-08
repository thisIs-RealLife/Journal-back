package com.example.demo2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties("student")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSubject;
    private String professor;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "subject")
    private Mark marks;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},  fetch = FetchType.LAZY)
    private Student student;

    public Subject() {
    }

    public Subject(String nameSubject, String professor) {
        this.nameSubject = nameSubject;
        this.professor = professor;
    }

    public Subject(String nameSubject, String professor, Mark marks) {
        this.nameSubject = nameSubject;
        this.professor = professor;
        this.marks = marks;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String subject) {
        this.nameSubject = subject;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Mark getMarks() {
        return marks;
    }

    public void setMarks(Mark marks) {
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
