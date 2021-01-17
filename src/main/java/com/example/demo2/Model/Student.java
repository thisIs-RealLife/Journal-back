package com.example.demo2.Model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please fill the name")
    @Length(max = 200, message = "name too long")
    private String name;

    @NotBlank(message = "Please fill the surname")
    @Length(max = 200, message = "surname too long")
    private String surname;

    @Range(max = 150, min = 16, message = "Error in the \"age\" field")
    private int age;

    @NotBlank(message = "Please fill the password")
    @Length(max = 200, message = "password too long")
    private String password;

    @OneToMany( cascade = {CascadeType.REMOVE, CascadeType.DETACH}, mappedBy = "student", fetch = FetchType.EAGER)
    private List<Subject> subjects;

    public Student() {
    }

    public Student(Long id, String name, String surName, int age, String password) {
        this.id = id;
        this.name = name;
        this.surname = surName;
        this.age = age;
        this.password = password;
    }

    public Student(@NotBlank(message = "Please fill the name") @Length(max = 200, message = "Message too long") String name, @NotBlank(message = "Please fill the name") @Length(max = 200, message = "Message too long") String surName, int age, @NotBlank(message = "Please fill the name") @Length(max = 200, message = "Message too long") String password) {
        this.name = name;
        this.surname = surName;
        this.age = age;
        this.password = password;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException();
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surName) {
        this.surname = surName;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        if (subjects != null)
            subjects.forEach(subject -> subject.setStudent(this));
        this.subjects = subjects;
    }

    public void setSubject(Subject subject) {
        if (subject != null)
        subjects.add(subject);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
