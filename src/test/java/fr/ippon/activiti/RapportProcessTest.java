package fr.ippon.activiti;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.ippon.activiti.personnel.da.ListePersonnesDA;
import fr.ippon.activiti.personnel.model.ListePersonnes;
import fr.ippon.activiti.personnel.service.ListePersonnesService;
import fr.ippon.activiti.rapport.service.RapportService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class RapportProcessTest {
	
	@Autowired
	RapportService rapportService;
	
	@Autowired
	ListePersonnesDA listePersonnesDA;
	
		@Test
		public void testMiseEnValidation() {
			
			ListePersonnes listePersonnes = new ListePersonnes();
			listePersonnes.setNom("MaListe");
			listePersonnes.getPersonnes().add("Personne 1");
			listePersonnes.getPersonnes().add("Personne 2");

			listePersonnesDA.save(listePersonnes);
			
			rapportService.initialiserRapportsPourService("MaListe");
			
		}
	
		
		
		
		
		

}
