package com.miage.vepick.repository;
import com.miage.vepick.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Interface qui implemente crudRepository pour l'acces au données
@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>{
    
}
