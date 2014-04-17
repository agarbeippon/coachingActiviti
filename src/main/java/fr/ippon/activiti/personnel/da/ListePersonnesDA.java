package fr.ippon.activiti.personnel.da;

import fr.ippon.activiti.personnel.model.ListePersonnes;

public interface ListePersonnesDA {
	ListePersonnes save(
			ListePersonnes listePersonnes);

	ListePersonnes findByNom(String nom);
	
}
