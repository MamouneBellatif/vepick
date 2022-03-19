package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;

@Data //Lombok (dépendance spring boot pour getters/setters)
@Entity
@Table(name="station")
public class Station {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column
	@Setter @Getter private String adresse;

	@Enumerated(EnumType.STRING)
	private TrajetsCourants trajets;

	@Enumerated(EnumType.STRING)
	private TypeStation type;

	@OneToMany(mappedBy="stationUtilisee")
	private List<Client> utilisateurs;

	@OneToMany(mappedBy="station")
	private List<Bornette> bornettes;

}