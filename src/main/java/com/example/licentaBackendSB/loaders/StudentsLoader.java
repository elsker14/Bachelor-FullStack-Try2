package com.example.licentaBackendSB.loaders;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class StudentsLoader implements CommandLineRunner{

    private final Logger logger = LoggerFactory.getLogger(StudentsLoader.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading data from StudentLoader...");

        List <Student> studentsDB = Student.hardcodedStudents;

        studentRepository.saveAll(studentsDB);
    }
}
