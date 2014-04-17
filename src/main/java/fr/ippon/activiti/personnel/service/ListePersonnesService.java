package fr.ippon.activiti.personnel.service;

import fr.ippon.activiti.personnel.model.ListePersonnes;

public interface ListePersonnesService {

	ListePersonnes initialiserNouvelleListePersonnes(ListePersonnes listePersonnes);
	
	ListePersonnes mettreEnValidation(ListePersonnes listePersonnes,String validateur);
	
	String getNomValidateur(ListePersonnes listePersonnes);
	
	ListePersonnes validerListePersonne(ListePersonnes listePersonnes);

	ListePersonnes rechercherListePersonnesParNom(String nom);
	
}
