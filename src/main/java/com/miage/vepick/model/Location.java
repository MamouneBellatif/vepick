package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;



@Entity
@Table(name="location")
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column
	private Date date;

	@ManyToOne
	private Client locataire;

    private String password;

    private boolean enCours;
 
	@OneToOne(mappedBy="location")
	private Velo velo;

	@ManyToOne
	private Bornette lieuLocation;


}