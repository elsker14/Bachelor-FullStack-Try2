package com.example.licentaBackendSB.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class StudentAccount {
    @Id
    @SequenceGenerator(
            name = "studentAcc_sequence",
            sequenceName = "studentAcc_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "studentAcc_sequence"
    )
    //needed for Statistics && Main sources

    //Fields -----------------------------------------------------------------------------------------------------------
    private Long id;
    private String nume;
    private String prenume;
    private String zi_de_nastere;
    private String cnp;
    private String username;
    private String password;
    private String encodedPassword;

    //Constructor ------------------------------------------------------------------------------------------------------

    public StudentAccount(Long id, String nume, String prenume, String zi_de_nastere, String username, String password, String encodedPassword, String cnp) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.zi_de_nastere = zi_de_nastere;
        this.username = username;
        this.password = password;
        this.encodedPassword = encodedPassword;
        this.cnp = cnp;
    }

    public StudentAccount() {}

    //GETTERs && SETTERs -----------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getZi_de_nastere() {
        return zi_de_nastere;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    //toString ---------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "StudentAccountsDB{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", zi_de_nastere='" + zi_de_nastere + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", encodedPassword='" + encodedPassword + '\'' +
                '}';
    }

    //Methods ----------------------------------------------------------------------------------------------------------
    public static List<StudentAccount> hardcodeStudentsAccountsDB(List<Student> tmp) {
        List<StudentAccount> studentAccounts = new ArrayList<>();

        for(long i = Student.startIndexing - 1L; i < Student.endIndexing; i++)
        {
            studentAccounts.add(new StudentAccount(
                    (i + 1),
                    tmp.get((int) i).getNume(),
                    tmp.get((int) i).getPrenume(),
                    "null",
                    "null",
                    "null",
                    "null",
                    "null"
            ));
        }


        return studentAccounts;
    }
}