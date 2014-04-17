package fr.ippon.activiti.personnel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ListePersonnes {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nom;
	
	private boolean valide;

	@ElementCollection
	private List<String> personnes = new ArrayList<String>();

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	

	public List<String> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<String> personnes) {
		this.personnes = personnes;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
