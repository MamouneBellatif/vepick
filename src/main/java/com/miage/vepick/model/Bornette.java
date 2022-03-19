package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;

@Data //Lombok (dépendance spring boot pour getters/setters)//
@Entity
@Table(name="bornette")
public class Bornette {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

    @Setter @Getter private boolean libre; //velo present ou pas

	@Setter @Getter private String adresse;

    @Enumerated(EnumType.STRING)
    private EtatEnum etat;

	@OneToOne(mappedBy="bornette")
	private Velo veloStationnee;

	@OneToMany(mappedBy="lieuLocation")
	private List<Location> locations;

	@ManyToOne
	private Station station;
	

}