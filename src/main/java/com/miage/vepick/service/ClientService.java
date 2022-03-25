package com.miage.vepick.service;

import java.util.Optional;

import com.miage.vepick.model.Bornette;
import com.miage.vepick.model.Station;
import com.miage.vepick.model.Client;
import com.miage.vepick.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    // public Client getClientByBornette(Bornette bornette){
    //     return clientRepository.findByBornette(bornette);
    // }
}