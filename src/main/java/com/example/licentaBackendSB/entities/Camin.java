package com.example.licentaBackendSB.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Camin {

    @Transient
    public static List<Camin> hardcodedCamine = hardcodeCamine();

    @Id
    @SequenceGenerator(
            name = "camin_sequence",
            sequenceName = "camin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "camin_sequence"
    )

    //Fields
    private Long id;
    private String numeCamin;
    private Integer capacitate;
    private Integer nrCamereTotal;
    private Integer nrCamereUnStudent;
    private Integer nrCamereDoiStudenti;
    private Integer nrCamereTreiStudenti;
    private Integer nrCamerePatruStudenti;

    //Constructor


    public Camin(Long id,
                 String numeCamin,
                 Integer capacitate,
                 Integer nrCamereTotal,
                 Integer nrCamereUnStudent,
                 Integer nrCamereDoiStudenti,
                 Integer nrCamereTreiStudenti,
                 Integer nrCamerePatruStudenti)
    {
        this.id = id;
        this.numeCamin = numeCamin;
        this.capacitate = capacitate;
        this.nrCamereTotal = nrCamereTotal;
        this.nrCamereUnStudent = nrCamereUnStudent;
        this.nrCamereDoiStudenti = nrCamereDoiStudenti;
        this.nrCamereTreiStudenti = nrCamereTreiStudenti;
        this.nrCamerePatruStudenti = nrCamerePatruStudenti;
    }

    public Camin() {}

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeCamin() {
        return numeCamin;
    }

    public void setNumeCamin(String numeCamin) {
        this.numeCamin = numeCamin;
    }

    public Integer getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(Integer capacitate) {
        this.capacitate = capacitate;
    }

    public Integer getNrCamereTotal() {
        return nrCamereTotal;
    }

    public void setNrCamereTotal(Integer nrCamereTotal) {
        this.nrCamereTotal = nrCamereTotal;
    }

    public Integer getNrCamereUnStudent() {
        return nrCamereUnStudent;
    }

    public void setNrCamereUnStudent(Integer nrCamereUnStudent) {
        this.nrCamereUnStudent = nrCamereUnStudent;
    }

    public Integer getNrCamereDoiStudenti() {
        return nrCamereDoiStudenti;
    }

    public void setNrCamereDoiStudenti(Integer nrCamereDoiStudenti) {
        this.nrCamereDoiStudenti = nrCamereDoiStudenti;
    }

    public Integer getNrCamereTreiStudenti() {
        return nrCamereTreiStudenti;
    }

    public void setNrCamereTreiStudenti(Integer nrCamereTreiStudenti) {
        this.nrCamereTreiStudenti = nrCamereTreiStudenti;
    }

    public Integer getNrCamerePatruStudenti() {
        return nrCamerePatruStudenti;
    }

    public void setNrCamerePatruStudenti(Integer nrCamerePatruStudenti) {
        this.nrCamerePatruStudenti = nrCamerePatruStudenti;
    }

    //Methods
    private static List<Camin> hardcodeCamine() {
        List <Camin> hardcodedListOfCamine = new ArrayList<>();

        hardcodedListOfCamine.add(new Camin(
                1L,
                "Leu A",
                0,
                0,
                0,
                0,
                0,
                0)
        );

        hardcodedListOfCamine.add(new Camin(
                2L,
                "Leu C",
                0,
                0,
                0,
                0,
                0,
                0)
        );

        hardcodedListOfCamine.add(new Camin(
                3L,
                "P20",
                0,
                0,
                0,
                0,
                0,
                0)
        );

        hardcodedListOfCamine.add(new Camin(
                4L,
                "P23",
                0,
                0,
                0,
                0,
                0,
                0)
        );

        return hardcodedListOfCamine;
    }
}
