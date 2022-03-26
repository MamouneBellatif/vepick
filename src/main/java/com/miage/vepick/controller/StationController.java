package com.miage.vepick.controller;

import java.util.List;
import java.util.Optional;

import com.miage.vepick.model.Station;
import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.ModelVelo;
import com.miage.vepick.repository.StationRepository;
import com.miage.vepick.service.BornetteService;
import com.miage.vepick.service.ModelService;
import com.miage.vepick.service.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.*;

@Controller
public class StationController {
    
    @Autowired
    private StationService stationService;

    @Autowired
    private BornetteService bornetteService;

    @Autowired
    private ModelService modelService;

    @GetMapping("/station/{id}")
    public String getBornettes(@PathVariable("id") int id, Model model){
    // public String getBornettes(@PathVariable("id") Long id, Model model){
        Optional<Station> stationOpt = stationService.getStationById(id);
        if(stationOpt.isPresent()){ //si la station existe, on extrait les brnttes
            Station station = stationOpt.get();
            List<Bornette> bornettes = this.bornetteService.getBornettesByStation(station);
            Iterable<ModelVelo> models = this.modelService.getAllModels();
            model.addAttribute("station", station);
            model.addAttribute("bornettes", bornettes);
            model.addAttribute("models", models);
        }
        return "station";
    }
}
