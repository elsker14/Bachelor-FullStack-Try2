package com.example.licentaBackendSB.entities;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.repositories.StudentAccountsDBRepository;
import com.example.licentaBackendSB.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class DatabaseStudentConfig implements CommandLineRunner{

    private final Logger logger = LoggerFactory.getLogger(DatabaseStudentConfig.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading data from StudentLoader...");

        List <Student> studentsDB = Student.hardcodeStudents();
        studentRepository.saveAll(studentsDB);
    }


//    List <Student> studentsDB2;
//
//    //vom avea un BEAN pe care il folosim sa populam baza de date cu obiecte de tipul Student
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentAccountsDBRepository studentAccountsDBRepository)
//    {
//        return args -> {
//            //hardcodam copii si ii bagam in baza de date
//            List <Student> studentsDB = Student.hardcodeStudents();
//            studentsDB2 = studentsDB;
//
//            //insereaza lista respectiva in tabel <3
//            studentRepository.saveAll(studentsDB);
//
////            //----------------------------------------------------------------------------------------------------------
////            //hardcodam copii si ii bagam in baza de date
////            List <StudentAccountsDB> studentsAccountsDB = StudentAccountsDB.hardcodeStudentsAccountsDB(studentsDB2);
////
////            //insereaza lista respectiva in tabel <3
////            studentAccountsDBRepository.saveAll(studentsAccountsDB);
//        };
//    }
}
