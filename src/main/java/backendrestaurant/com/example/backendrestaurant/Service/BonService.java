package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.BesteldItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Bon;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
@Service
public class BonService {

    @Autowired
    private BesteldItemRepository besteldItemRepository;

    @Autowired
    private TafelRepository tafelRepository;

    @Autowired
    private BonRepository bonRepository;

    @Transactional
    public String getAllBonnen() {
        List<Tafel> tafels = tafelRepository.findAll();
        List<String> bonnen = new ArrayList<>();

        for (Tafel tafel : tafels) {
            Long tafelId = tafel.getId();
            String bonText = generateBon(tafelId);


            bonText += "\nIs Paid: " + isPaid(tafelId);

            bonnen.add(bonText);
        }


        return bonnen.stream().collect(Collectors.joining("\n\n", "", ""));
    }

    @Transactional
    public void saveBon(Long tafelId, boolean isPaid) {

        Optional<Bon> existingBonOptional = bonRepository.findByTafelId(tafelId);
        if (existingBonOptional.isPresent()) {

            Bon existingBon = existingBonOptional.get();
            existingBon.setPaid(isPaid);
            bonRepository.save(existingBon);
        } else {

            Bon bon = new Bon();
            bon.setTafelId(tafelId);
            bon.setPaid(isPaid);

            bonRepository.save(bon);
        }
    }

    @Transactional
    public void setPaidStatus(Long tafelId, boolean paid) {
        Tafel tafel = tafelRepository.findById(tafelId).orElse(null);
        if (tafel != null) {
            tafel.setPaid(paid);
            tafelRepository.save(tafel);


            BigDecimal totalPrijs = calculateTotalPrijs(tafelId);


            Optional<Bon> optionalBon = bonRepository.findByTafelId(tafelId);
            if (optionalBon.isPresent()) {
                Bon bon = optionalBon.get();
                bon.setTafelId(tafelId);
                bon.setPaid(paid);
                bon.setTotalPrijs(totalPrijs);
                bonRepository.save(bon);
            } else {
                Bon bon = new Bon();
                bon.setTafelId(tafelId);
                bon.setPaid(paid);
                bon.setTotalPrijs(totalPrijs);
                bonRepository.save(bon);
            }
        }
    }


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

    @Transactional
    public String generateBon(Long tafelId) {

        List<BesteldItem> besteldItems = besteldItemRepository.findByTafel_Id(tafelId);

        if (besteldItems.isEmpty()) {
            return "Geen bestellingen gevonden voor tafel met ID: " + tafelId;
        }


        Map<String, List<BesteldItem>> groupedItems = besteldItems.stream()
                .collect(Collectors.groupingBy(BesteldItem::getItemNaam));


        BigDecimal totalePrijs = BigDecimal.ZERO;
        for (BesteldItem item : besteldItems) {
            totalePrijs = totalePrijs.add(item.getPrijs());
        }


        Tafel tafel = tafelRepository.findById(tafelId).orElse(null);

        if (tafel == null) {
            return "Tafel niet gevonden met ID: " + tafelId;
        }


        Optional<Bon> optionalBon = bonRepository.findByTafelId(tafelId);
        if (optionalBon.isPresent()) {
            Bon bon = optionalBon.get();
            bon.setTotalPrijs(totalePrijs);
            bonRepository.save(bon);
        } else {

            Bon bon = new Bon();
            bon.setTafelId(tafelId);
            bon.setTotalPrijs(totalePrijs);
            bonRepository.save(bon);
        }


        StringBuilder bonBuilder = new StringBuilder();
        bonBuilder.append("Bon voor Tafel: ").append(tafel.getNaam()).append("\n\n");


        for (Map.Entry<String, List<BesteldItem>> entry : groupedItems.entrySet()) {
            String itemNaam = entry.getKey();
            List<BesteldItem> items = entry.getValue();

            int totalHoeveelheid = items.stream().mapToInt(BesteldItem::getHoeveelheid).sum();
            BigDecimal totalPrice = items.stream().map(BesteldItem::getPrijs).reduce(BigDecimal.ZERO, BigDecimal::add);

            bonBuilder.append("Item: ").append(itemNaam)
                    .append(", Hoeveelheid: ").append(totalHoeveelheid)
                    .append(", Prijs: ").append(totalPrice)
                    .append("\n");
        }


        bonBuilder.append("\nTotaal Prijs: ").append(totalePrijs);

        return bonBuilder.toString();
    }
}
