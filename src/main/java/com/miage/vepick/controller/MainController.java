package com.miage.vepick.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.Station;
import com.miage.vepick.repository.StationRepository;
import com.miage.vepick.service.BornetteService;
import com.miage.vepick.service.StationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.*;
@Controller
public class MainController{

    // @Autowired
    // private StationRepository stationRep;
    @Autowired
    private StationService stationService;

    @Autowired
    private BornetteService bornetteService;
    private static final String[] ADRESSES = new String[]{"grenoble","lyon","marrakech"};

    @ResponseBody
    @RequestMapping("/")
    public String home(){
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllStations'>Show All Stations</a></li>";
        html += " <li><a href='/deleteAllStations'>Delete All Stations</a></li>";
        html += "</ul>";
        return html;
    }

    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert(){
        int random = new Random().nextInt(3);
        String adresse=ADRESSES[random];

        Station station = new Station();
        Bornette bornette = new Bornette(station);
        
        station.setBornettes(new ArrayList<Bornette>());

        station.setAdresse(adresse);
        bornette.setStation(station);
        bornette.setLibre(true);

        // this.stationRep.save(station);

        
        this.stationService.saveStation(station);
        this.bornetteService.saveBornette(bornette);

        
        return "insertion: "+adresse;//test
    }

    @ResponseBody
    @RequestMapping("/showAllStations")
    public String showAllStations(){
        // Iterable<Station> stations = this.stationRep.findAll();
        Iterable<Station> stations = this.stationService.getStations();
        String html = "";
        for (Station station : stations) {
            html += station +"<br>";
            List<Bornette> bornettes = this.bornetteService.getBornettesByStation(station);
            for (Bornette bornette : bornettes) {
                html += bornette + "(bornette) <br>";
            }
        }
        return html;
    }

    
    @ResponseBody
    @RequestMapping("/deleteAllStations")
    public String deleteAllEmployee() {

        // this.stationRep.deleteAll();
        this.stationService.deleteAllStations();
        return "stations supprimés!";
    }

    @ResponseBody
    @RequestMapping("/louer-velo")
    public String rentBike(){
        long id=1;//test
        System.out.println("Conntectez vous a une station");
        Iterable<Station> stations = this.stationService.getStations();
        String html = "";
        for (Station station : stations) {
            html += "n"+station.getId()+": "+ station +"<br>";
        }
        Optional<Station> station = stationService.getStationById(id);
        return null;
    }

    @ResponseBody
    @RequestMapping("/showLocationForm")
    public String showForm(){
        return null;
    }
}