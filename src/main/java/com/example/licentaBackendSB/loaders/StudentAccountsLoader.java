package com.example.licentaBackendSB.loaders;

import com.example.licentaBackendSB.entities.StudentAccount;
import com.example.licentaBackendSB.repositories.StudentAccountsDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class StudentAccountsLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(StudentsLoader.class);

    @Autowired
    private StudentAccountsDBRepository studentAccountsDBRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading data from StudentAccountsLoader...");

        //List<StudentAccountsDB> studentsAccountsDB = StudentAccountsDB.hardcodeStudentsAccountsDB(studentsDB);
        //studentAccountsDBRepository.saveAll(studentsAccountsDB);

        studentAccountsDBRepository.save(
                new StudentAccount(
                        1L,
                        "Iancu",
                        "Jianu",
                        "14.03.1998",
                        "14031998",
                        "1980314170059",
                        "parola codata"
                )
        );
    }
}
