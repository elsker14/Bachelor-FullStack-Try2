package com.example.licentaBackendSB.entities;

import javax.persistence.*;

@Entity
@Table
public class CaminLeuC {

    @Id
    @SequenceGenerator(
            name = "caminLeuC_sequence",
            sequenceName = "caminLeuC_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "caminLeuC_sequence"
    )

    //Fields
    private Long id;
    private String nume;
    private String prenume;
    private String cnp;
    private Double medie;
    private Integer an;
    private String myToken;
    private String friendToken;

    //Constructor
    public CaminLeuC(Long id,
                     String nume,
                     String prenume,
                     String cnp,
                     Double medie,
                     Integer an,
                     String myToken,
                     String friendToken) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.medie = medie;
        this.an = an;
        this.myToken = myToken;
        this.friendToken = friendToken;
    }

    public CaminLeuC() {}

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Double getMedie() {
        return medie;
    }

    public void setMedie(Double medie) {
        this.medie = medie;
    }

    public Integer getAn() {
        return an;
    }

    public void setAn(Integer an) {
        this.an = an;
    }

    public String getMyToken() {
        return myToken;
    }

    public void setMyToken(String myToken) {
        this.myToken = myToken;
    }

    public String getFriendToken() {
        return friendToken;
    }

    public void setFriendToken(String friendToken) {
        this.friendToken = friendToken;
    }

    //Others methods
    public static CaminLeuC convertStudentToCaminLeuC(Student tmp)
    {
        CaminLeuC result = new CaminLeuC();

        result.setId(tmp.getId());
        result.setNume(tmp.getNume());
        result.setPrenume(tmp.getPrenume());
        result.setCnp(tmp.getCnp());
        result.setMedie(tmp.getMedie());
        result.setAn(tmp.getAn());
        result.setFriendToken(tmp.getFriendToken());
        result.setMyToken(tmp.getMyToken());

        return result;
    }
}
