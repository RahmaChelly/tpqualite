package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	private long clientId;
	@Column(nullable=false)
	private String nom;
	@Column(nullable=false)
	private String prenom;
	@Column(nullable=false)
	private String adresse;
	@Column(nullable=false)
	private long numTel;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
	@Fetch(value=FetchMode.JOIN)
	List<Reservation> reservations = new ArrayList<Reservation>();
	
	public Client() {

	}
	
	public Client(String nom, String prenom, String adresse, long numTel) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numTel = numTel;
	}

	public String toString()
	{
		return nom + " " + prenom + " - " + numTel + " - " + adresse;
	}
	
	public List<Reservation> getReservations()
	{
		return reservations;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public long getNumTel() {
		return numTel;
	}

	public void setNumTel(long numTel) {
		this.numTel = numTel;
	}
	
	public void ajouterResevation(Reservation r)
	{
		this.reservations.add(r);
	}

}
