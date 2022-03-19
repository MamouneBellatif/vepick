package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;

/*Author: Mamoune Bellatif*/
@Entity
@Table(name="carteBanquaire")
public class CarteBanquaire {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(nullable=false)
	@OneToMany(mappedBy="cbUtilisee")
	private List<Client> utilisateurs;

	@Column(nullable=false)
	@OneToMany
	private Client proprietaire;
	

}