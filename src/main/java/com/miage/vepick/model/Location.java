package com.miage.vepick.model;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;



@Data
@Entity
@Table(name="location")
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column
	private Date date;

	@Column
	private Date dateFin;

	@ManyToOne
	private Client locataire;

    private String password;

    private boolean enCours;
 
	@ManyToOne
	private Velo velo;

	@ManyToOne
	private Bornette lieuLocation;

	public Location(){
		super();
	}

	public Location(Velo velo, Bornette lieuLocation, String password){
		this.velo=velo;
		this.enCours=true;
		this.date=new Date(System.currentTimeMillis());
		this.password=password;
	}

	public String toString(){
		return "id location"+this.id+"date: "+date;
	}

	public void setEnCours(boolean enCours){
		System.out.println("setEnCours");
		this.enCours=enCours;
	}

	
}