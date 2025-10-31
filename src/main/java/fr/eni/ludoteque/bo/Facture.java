package fr.eni.ludoteque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="FACTURES")
public class Facture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID noFacture;
	
	@Basic(optional = true)
	private LocalDateTime datePaiement;
	
	@OneToMany
	@JoinColumn(name="no_facture")
	private List<Location> locations=new ArrayList<Location>();
	
	
	//@Transient // attribut calculable  
	private float prix;
	
	public void addLocation(Location location) {
		this.locations.add(location);
	}
}
