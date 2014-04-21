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

			String preValidateur1 = "user 1" ;

			String preValidateur2 = "user 2" ;
			
			ListePersonnes listePersonnes = new ListePersonnes();
			listePersonnes.setNom("MaListe");
			

			listePersonnesService.initialiserNouvelleListePersonnes(listePersonnes);
			
			listePersonnesService.mettreEnPreValidation(listePersonnes);
			
			String nomPreValidateur1 = listePersonnesService.getNomPreValidateur1(listePersonnes);
		
			Assert.assertEquals(preValidateur1, nomPreValidateur1);
			
			String nomPreValidateur2 = listePersonnesService.getNomPreValidateur2(listePersonnes);
			
			Assert.assertEquals(preValidateur2, nomPreValidateur2);
			
			listePersonnesService.mettreEnValidation(listePersonnes, validateur);
			
			String nomValidateurListe = listePersonnesService.getNomValidateur(listePersonnes);
			
			listePersonnes = listePersonnesService.validerListePersonne(listePersonnes);
			
			Assert.assertEquals(validateur, nomValidateurListe);
			Assert.assertTrue(listePersonnes.isValide());
			
		}
	
		
		
		
		
		

}
