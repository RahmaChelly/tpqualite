package PortailClients;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import entities.Chambre;
import entities.*;
import GenericRepository.*;

public class GestionReservation {

	IGenericRepository<Chambre,Long> chambreRepository;
	IGenericRepository<Client,Long> clientRepository;
	IGenericRepository<Reservation,Long> reservationRepository;
	
	public GestionReservation() {
		// TODO Auto-generated constructor stub
		
		chambreRepository = new GenericRepositoryImplementation<Chambre,Long> (Chambre.class);
		clientRepository = new GenericRepositoryImplementation<Client,Long> (Client.class);
		reservationRepository = new GenericRepositoryImplementation<Reservation,Long> (Reservation.class);

	}
	
	public boolean reserverChambre(long clientId, long chambreId)
	{
		return clientRepository.reserver(clientId,chambreId);
	}
	
	public void annulerReservation(long reservationId)
	{
		Chambre chambre = reservationRepository.findByID(reservationId).getChambre();
		chambre.annulerReservation();
		chambreRepository.update(chambre);
		Reservation r = reservationRepository.findByID(reservationId);
		r.setChambre(null);
		r.setClient(null);
		reservationRepository.update(r);
		reservationRepository.delete(reservationId);
	}
	
	public List<Reservation> getClientReservations(long idClient)
	{
		Client c =clientRepository.findByID(idClient);
		
		return c.getReservations();
	}

	public void annulerReservationChambre(long chambreId) {
		
		chambreRepository.delete(chambreId);
		
	}
	
	public void createChambre()
	{
		String typeChambre="Single";
		BigDecimal prix= new BigDecimal(589.500);
		
		Chambre chambre=new Chambre(typeChambre,prix);
		chambreRepository.create(chambre);
		System.out.println("chambre Created successfully!");
	}
	
	public void createClient()
	{
		Client c = new Client("Bouhlel","Ali", "Rades",58181884);
		clientRepository.create(c);
	}
	
	
	public List<Client> ListAllClients()
	{
		return clientRepository.findAll();
	}
	
	public List<Chambre> ListAllChambres()
	{
		return chambreRepository.findAll();
	}
	


}
