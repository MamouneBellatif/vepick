package com.miage.vepick.service;

import java.util.List;
import java.util.Optional;

import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.Station;
import com.miage.vepick.repository.BornetteRepository;
import com.miage.vepick.repository.StationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.*;

@Service
public class BornetteService {
    @Autowired
    private BornetteRepository borRep;

    public Bornette saveBornette(Bornette bornette){
        return borRep.save(bornette);
    }

    public List<Bornette> getBornettesByStation(Station station){
        return borRep.findByStation(station);
    }
    
}
