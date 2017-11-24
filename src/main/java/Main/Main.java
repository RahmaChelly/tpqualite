package Main;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import PortailClients.GestionReservation;
import entities.*;
import GenericRepository.*;

public class Main {

	
	public Main() {
		
	}

	public static void main(String[] args) {
		
		GestionReservation gr = new GestionReservation();
		
		System.out.println("*****Begin Main*****");
		
		//creation d'une chambre 
		//gr.createChambre();
		
		//creation d'un client
		gr.createClient();
		
		//get all clients
		for(Client c : gr.ListAllClients())
			System.out.println(c);
		
		// get All chambres
		for(Chambre ch : gr.ListAllChambres())
		System.out.println(ch);
		
		//reserver une chambre pour un client 
		//long idClient = 3;
		//long idChambre = 1;
		//gr.reserverChambre(idClient,idChambre);
		
		//annuler une reservation
		//long idReservation =5;
		//gr.annulerReservation(idReservation);
		
		//annuler reservation d'une chambre
		long chambreId =1;
		gr.annulerReservationChambre(chambreId);
		
		
		

	}
	
	

}
