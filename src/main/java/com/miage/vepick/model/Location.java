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

	private Client locataire;
 
	@OneToOne(mappedBy="location")
	private Velo velo;

	@ManyToOne
	private Bornette lieuLocation;


}