package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;

@Data
@Entity
@Table(name="velo")
public class Velo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Model model;

	@Column
	private Date dateService;

	@Column
	private boolean enMaintenance;

	@Enumerated(EnumType.STRING)
	private EtatEnum etat;

	@OneToOne
	private Bornette bornette;

	@OneToOne
	private Location location;

	// public void setBornette(Bornette bornette){
	// 	this.bornette=bornette;
	// 	bornette.setLibre(false);
	// }

	public String toStrig(){
		return "velo n"+this.id;
	}
}