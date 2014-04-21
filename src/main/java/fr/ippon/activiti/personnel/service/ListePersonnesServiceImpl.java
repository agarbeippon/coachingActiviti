package fr.ippon.activiti.personnel.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ippon.activiti.personnel.da.ListePersonnesDA;
import fr.ippon.activiti.personnel.model.ListePersonnes;

@Service
@Transactional
public class ListePersonnesServiceImpl implements ListePersonnesService {

	private static final String CLE_PROCESS_LISTE_PERSONNES = "listePersonnes";

	private static final String NOM_TACHE_EN_ATTENTE_MISE_EN_VALIDATION = "attenteMiseEnValidation";

	private static final String NOM_TACHE_VALIDATION = "validation";

	private static final String NOM_TACHE_PRE_VALIDATION1 = "preValidation1";

	private static final String NOM_TACHE_PRE_VALIDATION2 = "preValidation2";

	@Autowired
	ListePersonnesDA listePersonnesDA;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	public ListePersonnes initialiserNouvelleListePersonnes(
			ListePersonnes listePersonnes) {

		listePersonnes = listePersonnesDA.save(listePersonnes);

		runtimeService.startProcessInstanceByKey(CLE_PROCESS_LISTE_PERSONNES,
				listePersonnes.getNom());

		return listePersonnes;
	}
	
	public ListePersonnes mettreEnPreValidation(ListePersonnes listePersonnes) {

		Task attenteMiseEnValidation = taskService.createTaskQuery()
				.processDefinitionKey(CLE_PROCESS_LISTE_PERSONNES)
				.processInstanceBusinessKey(listePersonnes.getNom())
				.taskName(NOM_TACHE_EN_ATTENTE_MISE_EN_VALIDATION)
				.singleResult();


		Map<String, Object> parametres = new HashMap<String, Object>();

		parametres.put("preValidateur1", "user 1");
		parametres.put("preValidateur2", "user 2");
		
		taskService.complete(attenteMiseEnValidation.getId(),parametres);


		return listePersonnes;

	}


	public ListePersonnes mettreEnValidation(ListePersonnes listePersonnes,
			String validateur) {

		Task preValidation1 = taskService.createTaskQuery()
				.processDefinitionKey(CLE_PROCESS_LISTE_PERSONNES)
				.processInstanceBusinessKey(listePersonnes.getNom())
				.taskName(NOM_TACHE_PRE_VALIDATION1)
				.singleResult();

		taskService.complete(preValidation1.getId());
		

		Task preValidation2 = taskService.createTaskQuery()
				.processDefinitionKey(CLE_PROCESS_LISTE_PERSONNES)
				.processInstanceBusinessKey(listePersonnes.getNom())
				.taskName(NOM_TACHE_PRE_VALIDATION2)
				.singleResult();

		taskService.complete(preValidation2.getId());

		Task validation = taskService.createTaskQuery()
				.processDefinitionKey(CLE_PROCESS_LISTE_PERSONNES)
				.processInstanceBusinessKey(listePersonnes.getNom())
				.taskName(NOM_TACHE_VALIDATION).singleResult();

		taskService.setAssignee(validation.getId(), validateur);

		return listePersonnes;

	}

	public String getNomValidateur(ListePersonnes listePersonnes) {

		Task validation = taskService.createTaskQuery()
				.processDefinitionKey(CLE_PROCESS_LISTE_PERSONNES)
				.processInstanceBusinessKey(listePersonnes.getNom())
				.taskName(NOM_TACHE_VALIDATION).singleResult();

		return validation.getAssignee();

	}

	public ListePersonnes validerListePersonne(ListePersonnes listePersonnes) {
		
		Task validation = taskService.createTaskQuery()
				.processDefinitionKey(CLE_PROCESS_LISTE_PERSONNES)
				.processInstanceBusinessKey(listePersonnes.getNom())
				.taskName(NOM_TACHE_VALIDATION).singleResult();

		Map<String, Object> parametres = new HashMap<String, Object>();

		parametres.put("listePersonnes", listePersonnes);

		taskService.complete(validation.getId(), parametres);

		return listePersonnes;

	}
	
	public ListePersonnes rechercherListePersonnesParNom(String nom){
		
		return listePersonnesDA.findByNom(nom);
		
		
	}

	public String getNomPreValidateur1(ListePersonnes listePersonnes) {
		Task validation = taskService.createTaskQuery()
				.processDefinitionKey(CLE_PROCESS_LISTE_PERSONNES)
				.processInstanceBusinessKey(listePersonnes.getNom())
				.taskName(NOM_TACHE_PRE_VALIDATION1).singleResult();

		return validation.getAssignee();
	}

	public String getNomPreValidateur2(ListePersonnes listePersonnes) {
		Task validation = taskService.createTaskQuery()
				.processDefinitionKey(CLE_PROCESS_LISTE_PERSONNES)
				.processInstanceBusinessKey(listePersonnes.getNom())
				.taskName(NOM_TACHE_PRE_VALIDATION2).singleResult();

		return validation.getAssignee();
	}


}
