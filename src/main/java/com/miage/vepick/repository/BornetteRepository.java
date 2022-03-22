package com.miage.vepick.repository;
import java.util.List;

import com.miage.vepick.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Interface qui implemente crudRepository pour l'acces au données
@Repository
public interface BornetteRepository extends CrudRepository<Bornette, Long>{
    public List<Bornette> findByStation(Station station);
}
