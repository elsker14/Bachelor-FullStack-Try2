package com.example.licentaBackendSB;

import com.example.licentaBackendSB.objects.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}

	@GetMapping
	public List<Student> hello()
	{
		List<Student> studentsDB = new ArrayList<>();
		studentsDB = Student.hardcodeStudents();

		return studentsDB;
	}

}
