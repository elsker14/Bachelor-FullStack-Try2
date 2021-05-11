package com.example.licentaBackendSB.objects.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfig {

    //vom avea un BEAN
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository)
    {
        return args -> {
            //hardcodam copii si ii bagam in baza de date
            List <Student> studentsDB = Student.hardcodeStudents();
            Student.collectionSort(studentsDB);

            //insereaza lista respectiva in tabel <3
            studentRepository.saveAll(studentsDB);
        };
    }
}
