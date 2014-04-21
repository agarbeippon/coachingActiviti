import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.ippon.activiti.personnel.model.ListePersonnes;
import fr.ippon.activiti.personnel.service.ListePersonnesService;


public class Application {

	public static void main(String[] args) {
		
		ApplicationContext ap = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ListePersonnesService listePersonnesService = ap.getBean(ListePersonnesService.class);
		
		String validateur = "monValidateur" ;

		String preValidateur1 = "user 1" ;

		String preValidateur2 = "user 2" ;
		
		ListePersonnes listePersonnes = new ListePersonnes();
		listePersonnes.setNom("MaListe");
		

		listePersonnesService.initialiserNouvelleListePersonnes(listePersonnes);
		
		listePersonnesService.mettreEnPreValidation(listePersonnes);
		
		String nomPreValidateur1 = listePersonnesService.getNomPreValidateur1(listePersonnes);
		
		String nomPreValidateur2 = listePersonnesService.getNomPreValidateur2(listePersonnes);
		
		listePersonnesService.mettreEnValidation(listePersonnes, validateur);
		
		String nomValidateurListe = listePersonnesService.getNomValidateur(listePersonnes);
		
		//listePersonnes = listePersonnesService.validerListePersonne(listePersonnes);
	}

}
