package com.example.demo2;

import com.example.demo2.Model.Mark;
import com.example.demo2.Model.Student;
import com.example.demo2.Model.Subject;
import com.example.demo2.repo.StudentRepo;
import com.example.demo2.repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner{


	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	}
}
