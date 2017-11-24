package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	private long reservationId;
	@Column(nullable=false)
	private long numero;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="chambreId")
	private Chambre chambre;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="clientId")
	private Client client;

	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	
	public Reservation(long n) {

		this.numero=n;
	}

	public Chambre getChambre()
	{
		return chambre;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

}
