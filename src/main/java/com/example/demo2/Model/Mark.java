package com.example.demo2.Model;

import javax.persistence.*;

@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int mark;

    @OneToOne()
    private Subject subject;

    public Mark() {
    }

    public Mark(int mark) {
        this.mark = mark;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }
}
