package com.miage.vepick.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.ModelVelo;
import com.miage.vepick.model.Station;
import com.miage.vepick.model.Velo;
import com.miage.vepick.repository.StationRepository;
import com.miage.vepick.service.BornetteService;
import com.miage.vepick.service.ClientService;
import com.miage.vepick.service.ModelService;
import com.miage.vepick.service.StationService;
import com.miage.vepick.service.VeloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.ui.Model;
import lombok.*;
@Controller
public class MainController{

    // @Autowired
    // private StationRepository stationRep;
    @Autowired
    private StationService stationService;

    @Autowired
    private BornetteService bornetteService;

    @Autowired
    private VeloService veloService;

    @Autowired
    private ModelService modelService;
    
    private static final String[] ADRESSES = new String[]{"grenoble","lyon","marrakech"};

    @Autowired
    private ClientService clientServce;
    
    @GetMapping("/")
    public String home(Model model){
        
        Iterable<Station> stations = this.stationService.getStations();
        model.addAttribute("stations", stations);
        return "home";
    }

}