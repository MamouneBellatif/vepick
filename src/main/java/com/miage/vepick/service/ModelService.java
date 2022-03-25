package com.miage.vepick.service;

import java.util.Optional;

import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.Station;
import com.miage.vepick.model.ModelVelo;
import com.miage.vepick.repository.ModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.*;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;
    
    public ModelVelo saveModel(ModelVelo model){
        return modelRepository.save(model);
    }

}