/*@Author: Mamoune Bellatif*/
package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;

// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name="typeClient")
// @DiscriminatorValue(value="nonAbonne")
@Entity
@Table(name="client")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column
	private String password;

	@OneToMany(mappedBy="locataire")
	private List<Location> locations;

	@ManyToOne
	private CarteBanquaire cbUtilisee;

	// @Column(nullable=false)
	@OneToOne(mappedBy="proprietaire")
	private CarteBanquaire cbPossedee;

	@ManyToOne
	private Station stationUtilisee;

    private boolean abonne;

    @Column(nullable=true)
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private int dureeAbo;

}