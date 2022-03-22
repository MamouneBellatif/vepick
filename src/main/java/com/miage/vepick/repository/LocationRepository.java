package com.miage.vepick.repository;
import com.miage.vepick.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    
}
