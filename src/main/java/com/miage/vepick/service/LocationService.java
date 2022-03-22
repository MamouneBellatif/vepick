package com.miage.vepick.service;

import com.miage.vepick.model.Location;
import com.miage.vepick.model.Station;
import com.miage.vepick.repository.LocationRepository;
import com.miage.vepick.repository.StationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.*;

@Data
@Service
public class LocationService {

    @Autowired
    private StationRepository stationRep;

    @Autowired
    private LocationRepository locationRep;

   public Location saveLocation(Location location){
        Location savedLocation =locationRep.save(location);
        return location;
   }
}

