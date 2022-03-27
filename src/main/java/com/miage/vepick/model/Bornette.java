package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.*;

@Data //Lombok (dépendance spring boot pour getters/setters)//
@Entity
@Table(name="bornette")
public class Bornette {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    private boolean libre; //velo present ou pas

    
    @Enumerated(EnumType.STRING)
    private EtatEnum etat;

	@OneToOne(mappedBy="bornette")
	private Velo veloStationnee;

	@OneToMany(mappedBy="lieuLocation")
	private List<Location> locations;

	// @JsonIgnore //pour eviter stackoverflow lors d'un toString
	@ManyToOne
	private Station station;

	public Bornette(){//nécessaire en cas d'autres construceurs
		super();
	}

    public Bornette(Station station){
        this.etat=EtatEnum.OK;
        this.station=station;
    }


	

	@Override
	public String toString() {
		return "Bornette id: "+this.id +"|bornette.velo.id"+this.veloStationnee.getId();
	}

}