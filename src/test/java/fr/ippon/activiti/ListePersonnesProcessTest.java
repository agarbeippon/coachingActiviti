package fr.ippon.activiti;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.ippon.activiti.personnel.model.ListePersonnes;
import fr.ippon.activiti.personnel.service.ListePersonnesService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class ListePersonnesProcessTest {
	
	@Autowired
	ListePersonnesService listePersonnesService;
	
		@Test
		public void testDemarrageProcessListePersonnes() {
			
			ListePersonnes listePersonnes = new ListePersonnes();
			listePersonnes.setNom("MaListe");
			listePersonnesService.initialiserNouvelleListePersonnes(listePersonnes);
		}
		
		
		@Test
		public void testMiseEnValidation() {
			
			
			String validateur = "monValidateur" ;
			
			ListePersonnes listePersonnes = new ListePersonnes();
			listePersonnes.setNom("MaListe");
			

			listePersonnesService.initialiserNouvelleListePersonnes(listePersonnes);
			
			listePersonnesService.mettreEnValidation(listePersonnes, validateur);
			
			String nomValidateurListe = listePersonnesService.getNomValidateur(listePersonnes);
			
			listePersonnes = listePersonnesService.validerListePersonne(listePersonnes);
			
			Assert.assertEquals(validateur, nomValidateurListe);
			Assert.assertTrue(listePersonnes.isValide());
			
		}
	
		
		
		
		
		

}
