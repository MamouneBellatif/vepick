package com.miage.vepick.model;

import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;


@Data
@Entity
@Table(name="model")
public class ModelVelo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(unique=true)
	private String nom;

	@Column
	private double prix;

	public ModelVelo(){
		super();
	}

	public ModelVelo(String nom, double prix){
		this.nom=nom;
		this.prix=prix;
	}

	public String toString(){
		return nom;
	}
}