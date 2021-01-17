package com.example.demo2.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties("student")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameSubject;
    private String professor;

    @OneToOne( cascade = CascadeType.ALL, mappedBy = "subject")
    private Mark mark;

    @ManyToOne(  fetch = FetchType.EAGER)
    private Student student;

    public Subject() {
    }

    public Subject(String nameSubject, String professor) {
        this.nameSubject = nameSubject;
        this.professor = professor;
    }

    public Subject(String nameSubject, String professor, Mark mark) {
        this.nameSubject = nameSubject;
        this.professor = professor;
        this.mark = mark;
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

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark marks) {
        this.mark = marks;
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
