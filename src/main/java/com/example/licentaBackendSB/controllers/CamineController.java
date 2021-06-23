package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.*;
import com.example.licentaBackendSB.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/camine")
public class CamineController {

    //Fields
    private final CaminLeuAService caminLeuAService;
    private final CaminLeuCService caminLeuCService;
    private final CaminP20Service caminP20Service;
    private final CaminP23Service caminP23Service;
    private final CaminService caminService;

    //Constructor
    @Autowired
    public CamineController(CaminLeuAService caminLeuAService, CaminLeuCService caminLeuCService, CaminP20Service caminP20Service, CaminP23Service caminP23Service, CaminService caminService) {
        this.caminLeuAService = caminLeuAService;
        this.caminLeuCService = caminLeuCService;
        this.caminP20Service = caminP20Service;
        this.caminP23Service = caminP23Service;
        this.caminService = caminService;
    }

    /* ~~~~~~~~~~~ Get Camine View ~~~~~~~~~~~ */
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getCaminePage(Model model)
    {
        Camin leuA = new Camin();
        Camin leuC = new Camin();
        Camin p20 = new Camin();
        Camin p23 = new Camin();

        List<Camin> camineList = caminService.getCamine();

        for(Camin it: camineList)
        {
            switch(it.getNumeCamin())
            {
                case "Leu A":
                    leuA = it;
                    break;
                case "Leu C":
                    leuC = it;
                    break;
                case "P20":
                    p20 = it;
                    break;
                case "P23":
                    p23 = it;
                    break;
            }
        }

        model.addAttribute("listOfCamine", camineList);
        model.addAttribute("leuA", leuA);
        model.addAttribute("leuC", leuC);
        model.addAttribute("p20", p20);
        model.addAttribute("p23", p23);

        return "pages/layer 4/camine/camine_page";
    }

    /* ~~~~~~~~~~~ LeuA List ~~~~~~~~~~~ */
    @GetMapping("leuAlist")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getLeuAStudents(Model model)
    {
        List <CaminLeuA> caminLeuAList = caminLeuAService.getLeuAStudents();
        model.addAttribute("listOfLeuAStudents", caminLeuAList);

        return "pages/layer 4/camine/tables/leuAlist";
    }

    /* ~~~~~~~~~~~ LeuC List ~~~~~~~~~~~ */
    @GetMapping("leuClist")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getLeuCStudents(Model model)
    {
        List <CaminLeuC> caminLeuCList = caminLeuCService.getLeuCStudents();
        model.addAttribute("listOfLeuCStudents", caminLeuCList);

        return "pages/layer 4/camine/tables/leuClist";
    }

    /* ~~~~~~~~~~~ LeuC List ~~~~~~~~~~~ */
    @GetMapping("P20list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getP20Students(Model model)
    {
        List <CaminP20> caminP20List = caminP20Service.getP20Students();
        model.addAttribute("listOfP20Students", caminP20List);

        return "pages/layer 4/camine/tables/P20list";
    }

    /* ~~~~~~~~~~~ LeuA List ~~~~~~~~~~~ */
    @GetMapping("P23list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getP23Students(Model model)
    {
        List <CaminP23> caminP23List = caminP23Service.getP23Students();
        model.addAttribute("listOfP23Students", caminP23List);

        return "pages/layer 4/camine/tables/P23list";
    }

    /* ~~~~~~~~~~~ Get Camin knowing ID ~~~~~~~~~~~ */
    @GetMapping(path = "/edit/{caminId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String editCamin(
            @PathVariable("caminId") Long caminId,
            Model model)
    {

        Camin selectedCamin = caminService.editCamin(caminId);
        model.addAttribute("selectedCamintById", selectedCamin);

        return "pages/layer 4/camine/crud camine/update_info_camin";
    }

    /* ~~~~~~~~~~~ Update Camin and Redirect to Camine Page ~~~~~~~~~~~ */
    @PostMapping(path = "/update/{caminId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String updateCamin(
            @PathVariable("caminId") Long caminId,
            Camin newCamin)
    {
        caminService.updateCamin(caminId, newCamin);

        return "redirect:/admin/camine";
    }

    /* ~~~~~~~~~~~ Clear Fields and Update with 0 and Redirect to Camine Page ~~~~~~~~~~~ */
    @RequestMapping(path = "/clear/{caminId}")
    public String clearCamin(
            @PathVariable("caminId") Long caminId)
    {
        //Preluam caminul actual stiind Id-ul
        Camin selectedCamin = caminService.editCamin(caminId);

        selectedCamin.setCapacitate(0);
        selectedCamin.setNrCamereTotal(0);
        selectedCamin.setNrCamereUnStudent(0);
        selectedCamin.setNrCamereDoiStudenti(0);
        selectedCamin.setNrCamereTreiStudenti(0);
        selectedCamin.setNrCamerePatruStudenti(0);

        caminService.clearCamin(selectedCamin.getId(), selectedCamin);

        return "redirect:/admin/camine";
    }
}
