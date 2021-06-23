package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.*;
import com.example.licentaBackendSB.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<Camin> camineList = caminService.getCamine();
        model.addAttribute("listOfCamine", camineList);

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
}
