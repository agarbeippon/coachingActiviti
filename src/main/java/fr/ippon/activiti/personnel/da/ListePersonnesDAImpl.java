package fr.ippon.activiti.personnel.da;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.ippon.activiti.personnel.model.ListePersonnes;

@Repository
public class ListePersonnesDAImpl implements ListePersonnesDA {

	@PersistenceContext
	EntityManager em;

	public ListePersonnes save(ListePersonnes listePersonnes) {
		em.persist(listePersonnes);
		return listePersonnes;
	}

	public ListePersonnes findByNom(String nom) {
		return (ListePersonnes) em.createQuery("from ListePersonnes where nom=:nom")
				.setParameter("nom", nom).getSingleResult();
	}

}
