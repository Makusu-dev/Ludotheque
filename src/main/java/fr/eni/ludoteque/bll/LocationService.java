package fr.eni.ludoteque.bll;



import fr.eni.ludoteque.bo.Facture;
import fr.eni.ludoteque.bo.Location;
import fr.eni.ludoteque.dto.LocationDto;

import java.util.List;
import java.util.UUID;

public interface LocationService {
	
	Location ajouterLocation(LocationDto locationDto);
	
	Facture retourExemplaires(List<String> codebarres);

	Facture payerFacture( UUID noFacture);

	void trouverLocationParExemplaireCodebarre(String codebarre);
}
