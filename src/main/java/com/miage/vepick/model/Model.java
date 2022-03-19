package com.miage.vepick.model;

import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;

@Entity
@Table(name="model")
public class Model {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(unique=true)
	private String nom;

	@Column
	private double prix;
}