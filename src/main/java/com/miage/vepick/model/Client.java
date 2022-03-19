/*@Author: Mamoune Bellatif*/
package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;
@Entity
@Table(name="client")
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name="typeClient")
// @DiscriminatorValue(value="nonAbonne")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column
	private String password;

	@OneToMany(mappedBy="locataire")
	private List<Location> locations;

	
	private CarteBanquaire cbUtilisee;

	@Column(nullable=false)
	@OneToOne(mappedBy="proprietaire")
	private CarteBanquaire cbPossedee;

	@ManyToOne
	private Station stationUtilisee;


}