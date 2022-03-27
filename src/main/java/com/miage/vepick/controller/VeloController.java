package com.miage.vepick.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.miage.vepick.model.Station;
import com.miage.vepick.model.Velo;
import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.EtatEnum;
import com.miage.vepick.model.Location;
import com.miage.vepick.model.ModelVelo;
import com.miage.vepick.repository.StationRepository;
import com.miage.vepick.service.BornetteService;
import com.miage.vepick.service.LocationService;
import com.miage.vepick.service.ModelService;
import com.miage.vepick.service.StationService;
import com.miage.vepick.service.VeloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.*;

import java.sql.Date;

@Controller
public class VeloController {
    
    @Autowired
    private StationService stationService;


    @Autowired
    private VeloService veloService;




    /*Signal panne de velo*/
    @GetMapping("/station/{id}/velo/{veloId}/panne")
    public String panneVelo(@PathVariable("id") int stationId, @PathVariable("veloId") int veloId, Model model){
        Optional<Station> stationOpt = stationService.getStationById(stationId);
        if(stationOpt.isPresent()){ //si la station existe, on extrait les brnttes
            Station station = stationOpt.get();
            Optional<Velo> veloOpt = veloService.getVeloById(veloId);
            if(veloOpt.isPresent()){
                Velo velo = veloOpt.get();
                velo.setEtat(EtatEnum.HS);
                veloService.saveVelo(velo);
            }
        }
        // model.addAttribute("station", station);")
        return "redirect:/station/"+stationId;
    }
    
}
