package fr.eni.ludoteque.bll;



import fr.eni.ludoteque.bo.Client;
import fr.eni.ludoteque.bo.Exemplaire;
import fr.eni.ludoteque.bo.Facture;
import fr.eni.ludoteque.bo.Location;
import fr.eni.ludoteque.dal.ExemplaireRepository;
import fr.eni.ludoteque.dal.FactureRepository;
import fr.eni.ludoteque.dal.JeuRepository;
import fr.eni.ludoteque.dal.LocationRepository;
import fr.eni.ludoteque.dto.LocationDto;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
	@NonNull
	final private LocationRepository locationRepository;

	@NonNull
	final private JeuRepository jeuRepository;

	@NonNull
	final private ExemplaireRepository exemplaireRepository;

	@NonNull
	final private FactureRepository factureRepository;


	@Override
	public Location ajouterLocation(LocationDto locationDto  ) {
		//Exemplaire exemplaire = exemplaireRepository.findByCodebarreWithJeu(locationDto.getCodebarre());
		Exemplaire exemplaire = exemplaireRepository.findExemplaireByCodeBarre(locationDto.getCodebarre());
		Client client = new Client();
		client.setNoClient(locationDto.getNoClient());
					
		Location location = new Location(LocalDateTime.now(),client, exemplaire );
		Float tarifJour = jeuRepository.findTarifJour(exemplaire.getJeu().getNoJeu());
		location.setTarifJour(tarifJour);
		Location newLoc = locationRepository.save(location);
		
		return newLoc;
	}

	@Override
	@Transactional
	public Facture retourExemplaires(List<String> codebarres) {
		Facture facture = new Facture();
		//facture
		Location location = null;
		float prix = 0;
		for(String codebarre : codebarres) {
			location = locationRepository.findLocationByCodebarreWithJeu(codebarre);
			location.setDateRetour(LocalDateTime.now());
			facture.addLocation(location);
			//TODO : save date retour 
			long nbJours = ChronoUnit.DAYS.between(location.getDateDebut(), location.getDateRetour()) +1;
			prix += (nbJours * location.getTarifJour());
		}
		facture.setPrix(prix);
        return factureRepository.save(facture);
	}

    @Override
    public Facture payerFacture( UUID noFacture){
		Facture facture = factureRepository.findById(noFacture).orElse(null);
		facture.setDatePaiement(LocalDateTime.now());
		Facture factureBD = factureRepository.save(facture);
		return factureBD;
	}

	@Override
	public void trouverLocationParExemplaireCodebarre(String codebarre) {
		// TODO Auto-generated method stub
		
	}

}
