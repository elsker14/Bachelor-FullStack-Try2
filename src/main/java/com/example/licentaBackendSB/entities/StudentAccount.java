package com.example.licentaBackendSB.entities;

import com.example.licentaBackendSB.others.randomizers.DoBandCNPandGenderRandomizer;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class StudentAccount {

    @Transient
    public static List<StudentAccount> studentAccountsList = hardcodeStudentsAccountsDB(Student.hardcodedStudentsList);

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
    private String autoritate;

    //Constructor ------------------------------------------------------------------------------------------------------

    public StudentAccount(Long id, String nume, String prenume, String zi_de_nastere, String username, String password, String cnp, String autoritate) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.zi_de_nastere = zi_de_nastere;
        this.username = username;
        this.password = password;
        this.cnp = cnp;
        this.autoritate = autoritate;
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

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAutoritate() {
        return autoritate;
    }

    public void setAutoritate(String autoritate) {
        this.autoritate = autoritate;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setZi_de_nastere(String zi_de_nastere) {
        this.zi_de_nastere = zi_de_nastere;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //toString ---------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "StudentAccount{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", zi_de_nastere='" + zi_de_nastere + '\'' +
                ", cnp='" + cnp + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", autoritate='" + autoritate + '\'' +
                '}';
    }

    //Methods ----------------------------------------------------------------------------------------------------------
    public static List<StudentAccount> hardcodeStudentsAccountsDB(List<Student> tmp) {
        List<StudentAccount> studentAccounts = new ArrayList<>();

        for(long i = Student.startIndexing - 1L; i < Student.endIndexing; i++)
        {
            String username = tmp.get((int) i).getCnp();
            String password = DoBandCNPandGenderRandomizer.splitDoBbyDot(tmp.get((int) i).getZi_de_nastere());

            studentAccounts.add(new StudentAccount(
                    (i + 1),
                    tmp.get((int) i).getNume(),
                    tmp.get((int) i).getPrenume(),
                    tmp.get((int) i).getZi_de_nastere(),
                    username,
                    password,
                    tmp.get((int) i).getCnp(),
                    "STUDENT"
            ));
        }

        return studentAccounts;
    }
}
