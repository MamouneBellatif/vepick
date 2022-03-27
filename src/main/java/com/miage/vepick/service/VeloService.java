package com.miage.vepick.service;

import java.util.Optional;

import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.Location;
import com.miage.vepick.model.Station;
import com.miage.vepick.model.Velo;
import com.miage.vepick.repository.VeloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.*;

@Service
public class VeloService {

    @Autowired
    private VeloRepository veloRepository;

    public Optional<Velo> getVeloById(int id){
        return veloRepository.findById(id);
    }

    
    
    public Velo saveVelo(Velo velo){
        return veloRepository.save(velo);
    }

    public Optional<Velo> getVeloByBornette(Bornette bornette){
        return veloRepository.findByBornette(bornette);
    }

    public Optional<Velo> getVeloByLocation(Location location){
        return veloRepository.findByLocations(location);
    }

   
}