package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.Camin;
import com.example.licentaBackendSB.repositories.CaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CaminService {
    //Fields
    private final CaminRepository caminRepository;

    //Constructor
    @Autowired
    public CaminService(CaminRepository caminRepository) {
        this.caminRepository = caminRepository;
    }

    //Methods
    /* Get all Camine */
    public List<Camin> getCamine() {
        return caminRepository.findAll();
    }

    /* Get Id of Camin to update Fields */
    public Camin editCamin(Long caminId) {
        return caminRepository.findById(caminId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid camin Id:" + caminId));
    }

    /* Update Camin Fields */
    @Transactional
    public void updateCamin(Long caminId, Camin newCamin) {

        caminRepository.findById(caminId)
                .map(foundCamin -> {
                    //Validari si Verificari

                    /** update capacitate*/
                    if(newCamin.getCapacitate() > 0
                            && !foundCamin.getCapacitate().equals(newCamin.getCapacitate()))
                    {
                        foundCamin.setCapacitate(newCamin.getCapacitate());
                    }

                    /** update nr camere total*/
                    if(newCamin.getNrCamereTotal() > 0
                            && !foundCamin.getNrCamereTotal().equals(newCamin.getNrCamereTotal()))
                    {
                        foundCamin.setNrCamereTotal(newCamin.getNrCamereTotal());
                    }

                    /** update nr camere cu un student*/
                    if(newCamin.getNrCamereUnStudent() > 0
                            && !foundCamin.getNrCamereUnStudent().equals(newCamin.getNrCamereUnStudent()))
                    {
                        foundCamin.setNrCamereUnStudent(newCamin.getNrCamereUnStudent());
                    }

                    /** update nr camere cu doi student*/
                    if(newCamin.getNrCamereDoiStudenti() > 0
                            && !foundCamin.getNrCamereDoiStudenti().equals(newCamin.getNrCamereDoiStudenti()))
                    {
                        foundCamin.setNrCamereDoiStudenti(newCamin.getNrCamereDoiStudenti());
                    }

                    /** update nr camere cu trei student*/
                    if(newCamin.getNrCamereTreiStudenti() > 0
                            && !foundCamin.getNrCamereTreiStudenti().equals(newCamin.getNrCamereTreiStudenti()))
                    {
                        foundCamin.setNrCamereTreiStudenti(newCamin.getNrCamereTreiStudenti());
                    }

                    /** update nr camere cu patru student*/
                    if(newCamin.getNrCamerePatruStudenti() > 0
                            && !foundCamin.getNrCamerePatruStudenti().equals(newCamin.getNrCamerePatruStudenti()))
                    {
                        foundCamin.setNrCamerePatruStudenti(newCamin.getNrCamerePatruStudenti());
                    }

                    return caminRepository.save(foundCamin);
                }).
                orElseThrow(
                        () -> new IllegalStateException("Camin with id " + caminId + " does not exist")
                );
    }

    /* Clear Camin Fields and update them with 0 */
    @Transactional
    public void clearCamin(Long caminId, Camin newCamin) {
        caminRepository.findById(caminId)
                .map(foundCamin -> {
                    //Validari si Verificari

                    /** update capacitate*/
                    if(!foundCamin.getCapacitate().equals(newCamin.getCapacitate()))
                    {
                        foundCamin.setCapacitate(newCamin.getCapacitate());
                    }

                    /** update nr camere total*/
                    if(!foundCamin.getNrCamereTotal().equals(newCamin.getNrCamereTotal()))
                    {
                        foundCamin.setNrCamereTotal(newCamin.getNrCamereTotal());
                    }

                    /** update nr camere cu un student*/
                    if(!foundCamin.getNrCamereUnStudent().equals(newCamin.getNrCamereUnStudent()))
                    {
                        foundCamin.setNrCamereUnStudent(newCamin.getNrCamereUnStudent());
                    }

                    /** update nr camere cu doi student*/
                    if(!foundCamin.getNrCamereDoiStudenti().equals(newCamin.getNrCamereDoiStudenti()))
                    {
                        foundCamin.setNrCamereDoiStudenti(newCamin.getNrCamereDoiStudenti());
                    }

                    /** update nr camere cu trei student*/
                    if(!foundCamin.getNrCamereTreiStudenti().equals(newCamin.getNrCamereTreiStudenti()))
                    {
                        foundCamin.setNrCamereTreiStudenti(newCamin.getNrCamereTreiStudenti());
                    }

                    /** update nr camere cu patru student*/
                    if(!foundCamin.getNrCamerePatruStudenti().equals(newCamin.getNrCamerePatruStudenti()))
                    {
                        foundCamin.setNrCamerePatruStudenti(newCamin.getNrCamerePatruStudenti());
                    }

                    return caminRepository.save(foundCamin);
                }).
                orElseThrow(
                        () -> new IllegalStateException("Camin with id " + caminId + " does not exist")
                );
    }
}
