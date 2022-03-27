package com.miage.vepick.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
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

import lombok.*;

@Controller
public class StationController {
    
    @Autowired
    private StationService stationService;

    @Autowired
    private BornetteService bornetteService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private VeloService veloService;

    @Autowired
    private LocationService locationService;

    /*Cherche les bornettes de ma station et les envoie a la vue*/
    @GetMapping("/station/{id}")
    public String getBornettes(@PathVariable("id") int id, Model model){
    // public String getBornettes(@PathVariable("id") Long id, Model model){
        Iterable<Location> locations = this.locationService.getAllLocations();
        Optional<Station> stationOpt = stationService.getStationById(id);
        if(stationOpt.isPresent()){ //si la station existe, on extrait les brnttes
            Station station = stationOpt.get();
            List<Bornette> bornettes = this.bornetteService.getBornettesByStation(station);
            Iterable<ModelVelo> models = this.modelService.getAllModels();
            model.addAttribute("station", station);
            model.addAttribute("bornettes", bornettes);
            model.addAttribute("models", models);
            model.addAttribute("locations", locations);
            ArrayList<Location> liveLocations = new ArrayList<Location>();
            ArrayList<Location> oldLocations = new ArrayList<Location>();
            for(Location location : locations){
                if(location.isEnCours()){
                    liveLocations.add(location);
                }
                else{
                    oldLocations.add(location);
                }
            }
            model.addAttribute("liveLocations", liveLocations);
            model.addAttribute("oldLocations", oldLocations);
            
        }
        return "station";
    }


    // /*Creer une nouvelle location si la station possède le modèle souhaité t que le velo est ok
    // puis génère un mot de passe et l'envoie*/
    // @GetMapping("/station/{id}/new-location/{nomModel}")
    // public String newLocation(@PathVariable("id") int id, @PathVariable("nomModel") String nomModel, Model model){
    //     Optional<Station> stationOpt = stationService.getStationById(id);
    //     if(stationOpt.isPresent()){ //si la station existe, on extrait les brnttes
    //         Station station = stationOpt.get();
    //         List<Bornette> bornettes = this.bornetteService.getBornettesByStation(station);
    //         int i=0;
    //         boolean found=false;
    //         while(i<bornettes.size() && !found){
    //             Optional<Velo> veloOpt = this.veloService.getVeloByBornette(bornettes.get(i));
    //             if(veloOpt.isPresent()){
    //                 Velo velo = veloOpt.get();
    //                 if(velo.getModel().getNom().equals(nomModel) && velo.getEtat()==EtatEnum.OK){
    //                     found=true;
    //                     String password = generatePassword();
    //                     Location location = new Location(velo, bornettes.get(i), password);
    //                     velo.setLocation(location);
    //                     bornettes.get(i).setLibre(true);
    //                     velo.setBornette(null);
    //                     this.bornetteService.saveBornette(bornettes.get(i));
    //                     this.veloService.saveVelo(velo);        
    //                     this.locationService.saveLocation(location);                
    //                     this.stationService.saveStation(station);
    //                     model.addAttribute("location", location);
    //                     model.addAttribute("bornette", bornettes.get(i));
    //                     model.addAttribute("velo", velo);
    //                 }
    //             }
    //             i++;
    //         }
    //         model.addAttribute("station", station);
    //         model.addAttribute("success",found);
    //     }
    //     return "location";
    // }


    // private String generatePassword() {
    //     String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    //     StringBuilder salt = new StringBuilder();
    //     Random rnd = new Random();
    //     while (salt.length() < 18) { // length of the random string.
    //         int index = (int) (rnd.nextFloat() * SALTCHARS.length());
    //         salt.append(SALTCHARS.charAt(index));
    //     }
    //     String saltStr = salt.toString();
    //     return saltStr;

    // }
    
}
