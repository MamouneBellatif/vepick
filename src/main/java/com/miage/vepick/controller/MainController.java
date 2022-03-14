package com.miage.vepick.controller;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.miage.vepick.model.Station;
import com.miage.vepick.repository.StationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.*;
@Controller
public class MainController{

    @Autowired
    private StationRepository stationRep;

    private static final String[] ADRESSES = new String[]{"grenoble","lyon","marrakech"};

    @ResponseBody
    @RequestMapping("/")
    public String home(){
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllStations'>Show All Employee</a></li>";
        html += " <li><a href='/deleteAllStations'>Delete All Employee</a></li>";
        html += "</ul>";
        return html;
    }

    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert(){
        int random = new Random().nextInt(3);
        String adresse=ADRESSES[random];
        Station station = new Station();
        station.setAdresse(adresse);
        this.stationRep.save(station);
        return "insertion: "+adresse;
    }

    @ResponseBody
    @RequestMapping("/showAllStations")
    public String showAllStations(){
        Iterable<Station> stations = this.stationRep.findAll();
        String html = "";
        for (Station station : stations) {
            html += station +"<br>";
        }
        return html;
    }

    @ResponseBody
    @RequestMapping("/deleteAllStations")
    public String deleteAllEmployee() {

        this.stationRep.deleteAll();
        return "stations supprimés!";
    }
}