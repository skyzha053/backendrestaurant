package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Bon;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BesteldItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import backendrestaurant.com.example.backendrestaurant.Repository.BesteldItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.BonRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.TafelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class BonService {

    @Autowired
    private BesteldItemRepository besteldItemRepository;

    @Autowired
    private TafelRepository tafelRepository;

    @Autowired
    private BonRepository bonRepository;


    @Transactional
    public void saveBon(Long tafelId, boolean isPaid) {
        // Create a new Bon instance
        Bon bon = new Bon();
        bon.setTafelId(tafelId);
        bon.setPaid(isPaid);

        // Save the bon in the database
        bonRepository.save(bon);
    }
    @Transactional
    public void setPaidStatus(Long tafelId, boolean paid) {
        Tafel tafel = tafelRepository.findById(tafelId).orElse(null);
        if (tafel != null) {
            tafel.setPaid(paid); // Update betaalstatus in de Tafel entiteit
            tafelRepository.save(tafel); // Bewaar de wijzigingen aan de Tafel entiteit

            // Bereken totale prijs van items voor de tafel
            BigDecimal totalPrijs = calculateTotalPrijs(tafelId);

            // Haal Bon entry op via tafelId
            Optional<Bon> optionalBon = bonRepository.findByTafelId(tafelId);
            if (optionalBon.isPresent()) {
                Bon bon = optionalBon.get();
                bon.setTafelId(tafelId); // Stel het tafelId in
                bon.setPaid(paid);
                bon.setTotalPrijs(totalPrijs); // Stel de totale prijs in
                bonRepository.save(bon);
            } else {
                Bon bon = new Bon();
                bon.setTafelId(tafelId); // Stel het tafelId in
                bon.setPaid(paid);
                bon.setTotalPrijs(totalPrijs); // Stel de totale prijs in
                bonRepository.save(bon);
            }
        }
    }


    // Method to calculate the total price of items for a table
    private BigDecimal calculateTotalPrijs(Long tafelId) {
        List<BesteldItem> besteldItems = besteldItemRepository.findByTafel_Id(tafelId);
        return besteldItems.stream()
                .map(BesteldItem::getPrijs)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isPaid(Long tafelId) {
        Tafel tafel = tafelRepository.findById(tafelId).orElse(null);
        return tafel != null && tafel.isPaid();
    }

    public String generateBon(Long tafelId) {
        // Fetch bestelde items for the given tafel_id
        List<BesteldItem> besteldItems = besteldItemRepository.findByTafel_Id(tafelId);

        // Check if there are any items for the tafel
        if (besteldItems.isEmpty()) {
            return "Geen bestellingen gevonden voor tafel met ID: " + tafelId;
        }

        // Group bestelde items by item_naam
        Map<String, List<BesteldItem>> groupedItems = besteldItems.stream()
                .collect(Collectors.groupingBy(BesteldItem::getItemNaam));

        // Get the corresponding tafel
        Tafel tafel = tafelRepository.findById(tafelId).orElse(null);

        if (tafel == null) {
            return "Tafel niet gevonden met ID: " + tafelId;
        }

        // Generate bon header
        StringBuilder bon = new StringBuilder();
        bon.append("Bon voor Tafel: ").append(tafel.getNaam()).append("\n\n");

        // Generate bon items
        for (Map.Entry<String, List<BesteldItem>> entry : groupedItems.entrySet()) {
            String itemNaam = entry.getKey();
            List<BesteldItem> items = entry.getValue();

            int totalHoeveelheid = items.stream().mapToInt(BesteldItem::getHoeveelheid).sum();
            BigDecimal totalPrice = items.stream().map(BesteldItem::getPrijs).reduce(BigDecimal.ZERO, BigDecimal::add);

            bon.append("Item: ").append(itemNaam)
                    .append(", Hoeveelheid: ").append(totalHoeveelheid)
                    .append(", Prijs: ").append(totalPrice)
                    .append("\n");
        }

        // Generate bon footer with totaal prijs
        BigDecimal totaalPrijs = besteldItems.stream().map(BesteldItem::getPrijs).reduce(BigDecimal.ZERO, BigDecimal::add);
        bon.append("\nTotaal Prijs: ").append(totaalPrijs);

        return bon.toString();
    }
}
