package com.miage.vepick.model;
import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.*;
import java.sql.Date;

@Data
@Entity
@Table(name="velo")
public class Velo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private ModelVelo model;

	@Column
	private Date dateService;

	@Column
	private boolean enMaintenance;

	@Enumerated(EnumType.STRING)
	private EtatEnum etat;

	@OneToOne
	private Bornette bornette;

	@OneToMany(mappedBy="velo",cascade = CascadeType.ALL)
	private List<Location> locations;

	// public void setBornette(Bornette bornette){
	// 	this.bornette=bornette;
	// 	bornette.setLibre(false);
	// }
	public Velo(){
		super();
	}

	public Velo(ModelVelo model){
		this.model=model;
		this.dateService=new Date(System.currentTimeMillis());
		this.enMaintenance=false;
		this.etat=EtatEnum.OK;
		this.locations=new ArrayList<>();
	}

	public String toString(){
		return "velo n"+this.id+" model: "+this.model;
	}

	public void addLocation(Location location){
		this.locations.add(location);
	}
}