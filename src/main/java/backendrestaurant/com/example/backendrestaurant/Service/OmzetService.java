package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Bon;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Omzet;
import backendrestaurant.com.example.backendrestaurant.Repository.BonRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.OmzetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class OmzetService {
    @Autowired
    private BonRepository bonRepository;

    @Autowired
    private OmzetRepository omzetRepository;



// Bovenstaande imports zijn toegevoegd om de LocalDateTime en LocalDate klassen te gebruiken.

    public void berekenEnSlaOp() {
        List<Bon> bonnen = bonRepository.findAll();
        double omzet = 0.0;
        for (Bon bon : bonnen) {
            omzet += bon.getTotalPrijs().doubleValue(); // Gebruik getTotalPrijs() om de prijs op te halen
        }

        // Voeg de huidige datum en tijd toe aan de Omzet
        LocalDateTime now = LocalDateTime.now();
        Omzet omzetRecord = new Omzet(omzet, now);

        omzetRepository.save(omzetRecord);
    }
    public double getTotaalOmzet() {
        Omzet omzet = omzetRepository.findById(1L).orElse(null); // Stel dat de omzet wordt opgeslagen met een ID van 1
        if (omzet != null) {
            return omzet.getOmzetBedrag();
        }
        return 0.0; // Als er geen omzetrecord is gevonden, retourneer dan 0.0
    }
}
