package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;

@Data //Lombok (dépendance spring boot pour getters/setters)
@Entity
@Table(name="station")
public class Station {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column
	private String adresse;

	// @Enumerated(EnumType.STRING)
	// private TrajetsCourants trajets;

	// @Enumerated(EnumType.STRING)
	// private TypeStation type;

	// @OneToMany(mappedBy="stationUtilisee")
	// private list<Client> utilisateurs;

	// @OneToMany(mappedBy="station")
	// private list<Bornette> bornettes;

}