package com.miage.vepick.repository;
import java.util.Optional;

import com.miage.vepick.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Interface qui implemente crudRepository pour l'acces au données
@Repository
public interface VeloRepository extends CrudRepository<Velo, Integer>{
    public Optional<Velo> findByBornette(Bornette bornette);
    public Optional<Velo> findByLocations(Location location);

}
