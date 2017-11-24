package entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

@Entity
public class Chambre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	private long chambreId;
	@Column(nullable=false)
	private String typeChambre;
	@Column(nullable=false)
	private String etat;
	@Column(nullable=false,precision=20, scale=3)
	private BigDecimal prix;
	
	@SuppressWarnings("serial")
	@OneToMany(mappedBy="chambre",cascade = CascadeType.ALL)
	@Fetch(value=FetchMode.JOIN)
	private List<Reservation> reservations = new ArrayList<Reservation>() {
	};
	
	public String toString()
	{
		return "Prix Reservation: "+prix +"type de la chambre :" +typeChambre +"etat de la chambre"+ etat;
	}
	
	public Chambre() {
		// TODO Auto-generated constructor stub
	}
	
	public Chambre(String typeChambre, BigDecimal prix)
	{
		this.typeChambre=typeChambre;
		this.prix=prix;
		this.etat="libre";
		
	}
	
	
	public void reserver()
	{
		this.etat="reservee";
	}
	
	public void annulerReservation()
	{
		this.etat="libre";
	}
	
	public void ajouterResevation(Reservation r)
	{
		this.reservations.add(r);
	}
	
	public long getChambreId() {
		return chambreId;
	}


	public void setChambreId(long id) {
		this.chambreId = id;
	}
	


	public String getTypeChambre() {
		return this.typeChambre;
	}


	public void setTypeChambre(String typeChambre) {
		this.typeChambre = typeChambre;
	}
	
	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	
	public String getEtat() {
		return this.etat;
	}
	
	public void setEtat(String etat) {
		this.etat=etat;
	}

}
