package com.miage.vepick.service;

import com.miage.vepick.model.Station;
import com.miage.vepick.repository.StationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.*;

@Data
@Service
public class StationService {

    @Autowired
    private StationRepository stationRep;

    public Station saveStation(Station station){
        Station savedStation = stationRep.save(station);
        return savedStation;
    }

    public Iterable<Station> getStations(){
        return stationRep.findAll();
    }

    public void deleteStation(Station station){
        stationRep.delete(station);
    }

    public void deleteAllStations(){
        stationRep.deleteAll();
    }
}
