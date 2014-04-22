package fr.ippon.activiti.rapport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ippon.activiti.personnel.da.ListePersonnesDA;
import fr.ippon.activiti.personnel.model.ListePersonnes;

@Service
public class RapportServiceImpl implements RapportService {

	private static final String CLE_PROCESS_RAPPORT = "rapport" ; 

	@Autowired
	ListePersonnesDA listePersonnesDA;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	public void initialiserRapportsPourService (
			String nomListePersonne) {
		

		ListePersonnes listePersonnes = listePersonnesDA.findByNom(nomListePersonne);

		Map<String,Object> parametres = new HashMap<String, Object>();
		
		parametres.put("membresDuPersonnelEvalues", new ArrayList<String>(listePersonnes.getPersonnes()));
		
		runtimeService.startProcessInstanceByKey(CLE_PROCESS_RAPPORT,
				"Process_rapport_"+listePersonnes.getNom(),parametres);
		
		
	}
	 
}
