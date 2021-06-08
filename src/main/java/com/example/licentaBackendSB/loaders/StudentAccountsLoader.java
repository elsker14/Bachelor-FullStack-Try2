package com.example.licentaBackendSB.loaders;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.entities.StudentAccount;
import com.example.licentaBackendSB.repositories.StudentAccountsDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class StudentAccountsLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(StudentsLoader.class);

    @Autowired
    private StudentAccountsDBRepository studentAccountsDBRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading data from StudentAccountsLoader...");

        List<Student> studentsDB = Student.hardcodedStudentsList;
        List<StudentAccount> studentAccountsDB = StudentAccount.hardcodeStudentsAccountsDB(studentsDB);

        studentAccountsDBRepository.saveAll(studentAccountsDB);
    }
}
