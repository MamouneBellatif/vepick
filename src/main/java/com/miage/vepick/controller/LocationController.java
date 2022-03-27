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

import lombok.*;

import java.sql.Date;

@Controller
public class LocationController {
    
    @Autowired
    private StationService stationService;

    @Autowired
    private BornetteService bornetteService;

    @Autowired
    private VeloService veloService;

    @Autowired
    private LocationService locationService;


    /*Creer une nouvelle location si la station possède le modèle souhaité t que le velo est ok
    puis génère un mot de passe et l'envoie*/
    @GetMapping("/station/{id}/new-location/{nomModel}")
    public String newLocation(@PathVariable("id") int id, @PathVariable("nomModel") String nomModel, Model model){
        Optional<Station> stationOpt = stationService.getStationById(id);
        if(stationOpt.isPresent()){ //si la station existe, on extrait les brnttes
            Station station = stationOpt.get();
            List<Bornette> bornettes = this.bornetteService.getBornettesByStation(station);
            int i=0;
            boolean found=false;
            while(i<bornettes.size() && !found){
                Optional<Velo> veloOpt = this.veloService.getVeloByBornette(bornettes.get(i));
                if(veloOpt.isPresent()){
                    Velo velo = veloOpt.get();
                    if(velo.getModel().getNom().equals(nomModel) && velo.getEtat()==EtatEnum.OK){
                        found=true;
                        String password = generatePassword();
                        Location location = new Location(velo, bornettes.get(i), password);
                        // velo.setLocation(location);
                        velo.addLocation(location);
                        bornettes.get(i).setLibre(true);
                        velo.setBornette(null);
                        this.bornetteService.saveBornette(bornettes.get(i));
                        this.veloService.saveVelo(velo);        
                        this.locationService.saveLocation(location);                
                        this.stationService.saveStation(station);
                        model.addAttribute("location", location);
                        model.addAttribute("bornette", bornettes.get(i));
                        model.addAttribute("velo", velo);
                    }
                }
                i++;
            }
            model.addAttribute("station", station);
            model.addAttribute("success",found);
        }
        return "location";
    }

    @GetMapping("/station/{id}/fin-location/{idLocation}")
    public String finLocationForm(@PathVariable("id") int id, @PathVariable("idLocation") int idLocation, Model model){
        Optional<Station> stationOpt = stationService.getStationById(id);
        Optional<Location> locationOpt = locationService.getLocationById(idLocation);
        if(stationOpt.isPresent() && locationOpt.isPresent()){ //si la station et la location existent, on extrait les brnttes
            Location location = locationOpt.get();
            Station station = stationOpt.get();
            List<Bornette> bornettes = this.bornetteService.getBornettesByStation(station);
            int i=0;
            boolean found=false;
            Bornette bornette;
            while(i<bornettes.size() && !found){
                bornette=bornettes.get(i);
                if(bornette.isLibre()==true){
                    Optional<Velo> veloOpt = veloService.getVeloByLocation(location);
                    found=true;
                    if(veloOpt.isPresent()){
                        Velo velo = veloOpt.get();
                        model.addAttribute("bornette", bornette);
                        model.addAttribute("location", location);
                        model.addAttribute("station", station);

                    }
                }
                i++;
            }
            model.addAttribute("station", station);
            model.addAttribute("success",found);
        }
        return "fin-location";
    }

    @RequestMapping(value="/station/{id}/fin-location/{idLocation}/validate", method = RequestMethod.POST)
    public String finLocation(@RequestBody String password, @PathVariable("id") int id, @PathVariable("idLocation") int idLocation, Model model){
        System.out.println("mdp: "+password+"\n idstation: "+id+"\n idlocation: "+idLocation);
        Optional<Station> stationOpt = stationService.getStationById(id);
        Optional<Location> locationOpt = locationService.getLocationById(idLocation);
        if(stationOpt.isPresent() && locationOpt.isPresent()){ //si la station et la location existent, on extrait les brnttes
            System.out.println("station et location ok");
            Location location = locationOpt.get();
            Station station = stationOpt.get();
            List<Bornette> bornettes = this.bornetteService.getBornettesByStation(station);
            int i=0;
            boolean found=false;
            boolean passwordOk=false;
            Bornette bornette;
            while(i<bornettes.size() && !found){
                bornette=bornettes.get(i);
                if(bornette.isLibre()==true){
                    System.out.println("bornette libre ok");
                    found=true;
                    Velo velo = location.getVelo();
                    if(password.equals("password="+location.getPassword())){//on test le password envoyé par le client   
                        passwordOk=true;
                        location.setEnCours(false);
                        location.setDateFin(new Date(System.currentTimeMillis()));
                        System.out.println("durée: "+((location.getDateFin().getTime()-location.getDate().getTime())/10000));
                        System.out.println("durée debut: "+location.getDate().getTime());
                        System.out.println("durée fin: "+location.getDateFin().getTime());
                        bornette.setVeloStationnee(velo);
                        bornette.setLibre(false);
                        velo.setBornette(bornette);
                        this.veloService.saveVelo(velo);
                        this.bornetteService.saveBornette(bornette);
                        this.locationService.saveLocation(location);
                        model.addAttribute("location", location);
                        model.addAttribute("bornette", bornette);
                        model.addAttribute("velo", velo);
                        model.addAttribute("station", station);
                        System.out.println("done");
                    }
                }
                i++;
            }
            model.addAttribute("passwordOk", passwordOk);
            model.addAttribute("stationId", station.getId());
            System.out.println("station= "+station.getId());
            model.addAttribute("success",found);
        }
        return "fin-location-result";
    }

    private String generatePassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
}
