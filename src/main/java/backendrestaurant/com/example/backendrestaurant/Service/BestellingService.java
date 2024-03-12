package backendrestaurant.com.example.backendrestaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BestellingRequest;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BesteldItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Drank;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Factuur;
import backendrestaurant.com.example.backendrestaurant.Entiteit.MenuItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import backendrestaurant.com.example.backendrestaurant.Repository.BesteldItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.DrankRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.FactuurRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.MenuItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.TafelRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BestellingService {

    @Autowired
    private TafelRepository tafelRepository;

    @Autowired
    private DrankRepository drankRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private BesteldItemRepository besteldItemRepository;

    @Autowired
    private FactuurRepository factuurRepository;


    public void processMenuItems(List<BesteldItem> besteldeMenuItems, Tafel tafel) {
        processItems(besteldeMenuItems, tafel, false);
    }

    public void processDranken(List<BesteldItem> besteldeDranken, Tafel tafel) {
        processItems(besteldeDranken, tafel, true);
    }
    public ResponseEntity<String> plaatsBestelling(BestellingRequest bestellingRequest) {
        String tafelNaam = bestellingRequest.getTafelNaam();
        List<BesteldItem> besteldeMenuItems = bestellingRequest.getBesteldeMenuItems();
        List<BesteldItem> besteldeDranken = bestellingRequest.getBesteldeDranken();

        Tafel tafel = createOrUpdateTafel(tafelNaam);

        // Verwerk zowel menu-items als dranken
        processItems(besteldeMenuItems, tafel, false);
        processItems(besteldeDranken, tafel, true);

        return new ResponseEntity<>("Bestelling geplaatst voor tafel " + tafelNaam, HttpStatus.OK);
    }



    public Tafel createOrUpdateTafel(String tafelNaam) {
        Optional<Tafel> optionalTafel = tafelRepository.findByNaam(tafelNaam);
        Tafel tafel;
        if (optionalTafel.isPresent()) {
            tafel = optionalTafel.get();
        } else {
            tafel = new Tafel();
            tafel.setNaam(tafelNaam);
            tafelRepository.save(tafel);
        }
        return tafel;
    }

    public void processItems(List<BesteldItem> besteldeItems, Tafel tafel, boolean isDrank) {
        for (BesteldItem besteldItem : besteldeItems) {
            Optional<? extends Object> optionalItem = isDrank ?
                    drankRepository.findByNaam(besteldItem.getItemNaam()) :
                    menuItemRepository.findByName(besteldItem.getItemNaam());

            if (optionalItem.isPresent()) {
                Object item = optionalItem.get();
                BesteldItem besteldItemEntity = createBesteldItem(tafel, item, besteldItem.getHoeveelheid(), isDrank);
                besteldItemRepository.save(besteldItemEntity);
            }
        }
        updateTotalePrijs(tafel);
    }

    private BesteldItem createBesteldItem(Tafel tafel, Object item, int hoeveelheid, boolean isDrank) {
        BigDecimal prijs = isDrank ?
                ((Drank) item).getPrijs().multiply(BigDecimal.valueOf(hoeveelheid)) :
                ((MenuItem) item).getPrice().multiply(BigDecimal.valueOf(hoeveelheid));

        BesteldItem besteldItem = new BesteldItem();
        besteldItem.setTafel(tafel);
        besteldItem.setHoeveelheid(hoeveelheid);
        besteldItem.setPrijs(prijs);
        besteldItem.setItemNaam(isDrank ? ((Drank) item).getNaam() : ((MenuItem) item).getName());

        if (isDrank) {
            besteldItem.setDrank((Drank) item);
        } else {
            besteldItem.setMenuItem((MenuItem) item);
        }

        return besteldItem;
    }


    private void updateTotalePrijs(Tafel tafel) {
        // Check if there is an existing Factuur for the given tafel
        Factuur existingFactuur = factuurRepository.findByTafel(tafel);

        if (existingFactuur == null) {
            // Create a new Factuur instance if none exists
            existingFactuur = new Factuur();
            existingFactuur.setTafel(tafel);
        } else {
            // Remove existing BesteldItems from the factuur
            existingFactuur.getBesteldeItems().clear();
        }

        List<BesteldItem> besteldeItems = besteldItemRepository.findByTafel(tafel);
        BigDecimal totalePrijs = BigDecimal.ZERO;

        // Calculate totale prijs and associate items with the factuur
        for (BesteldItem besteldItem : besteldeItems) {
            totalePrijs = totalePrijs.add(besteldItem.getPrijs());
            besteldItem.setFactuur(existingFactuur);
            existingFactuur.getBesteldeItems().add(besteldItem);
        }

        // Set the totale prijs for the factuur en tafel
        existingFactuur.setTotalePrijs(totalePrijs);
        tafel.setTotalePrijs(totalePrijs);

        // Save the factuur (and cascade to besteldeItems) and update tafel
        factuurRepository.save(existingFactuur);
        tafelRepository.save(tafel);

        // Note: Since besteldItemRepository.findByTafel(tafel) returns a new list, we need to fetch
        // the updated list after saving the factuur and tafel.
        besteldeItems = besteldItemRepository.findByTafel(tafel);

        // Update the factuur reference for each BesteldItem
        for (BesteldItem besteldItem : besteldeItems) {
            besteldItem.setFactuur(existingFactuur);
            besteldItemRepository.save(besteldItem);
        }
    }


    public Tafel updateTafel(String tafelNaam, String nieuweNaam) {
        Optional<Tafel> optionalTafel = tafelRepository.findByNaam(tafelNaam);

        if (optionalTafel.isPresent()) {
            Tafel tafel = optionalTafel.get();
            tafel.setNaam(nieuweNaam);
            tafelRepository.save(tafel);
            return tafel;
        } else {
            return null;
        }
    }

    public ResponseEntity<String> updateBestelling(String tafelNaam, String itemName, int hoeveelheid) {

        Optional<Tafel> optionalTafel = tafelRepository.findByNaam(tafelNaam);

        if (optionalTafel.isPresent()) {
            Tafel tafel = optionalTafel.get();

            Optional<MenuItem> optionalMenuItem = menuItemRepository.findByName(itemName);
            Optional<Drank> optionalDrank = drankRepository.findByNaam(itemName);

            if (optionalMenuItem.isPresent() || optionalDrank.isPresent()) {
                BigDecimal prijs;
                if (optionalMenuItem.isPresent()) {
                    prijs = optionalMenuItem.get().getPrice();
                } else {
                    prijs = optionalDrank.get().getPrijs();
                }


                Optional<BesteldItem> optionalBesteldItem = besteldItemRepository.findByTafelAndItemNaam(tafel, itemName);
                if (optionalBesteldItem.isPresent()) {

                    BesteldItem besteldItem = optionalBesteldItem.get();
                    besteldItem.setHoeveelheid(besteldItem.getHoeveelheid() + hoeveelheid);
                    besteldItem.setPrijs(prijs.multiply(BigDecimal.valueOf(besteldItem.getHoeveelheid())));
                    besteldItemRepository.save(besteldItem);
                } else {

                    BesteldItem besteldItem = new BesteldItem();
                    besteldItem.setTafel(tafel);
                    besteldItem.setItemNaam(itemName);
                    besteldItem.setHoeveelheid(hoeveelheid);
                    besteldItem.setPrijs(prijs.multiply(BigDecimal.valueOf(hoeveelheid)));
                    besteldItemRepository.save(besteldItem);
                }

                return new ResponseEntity<>("Bestelling bijgewerkt", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Item niet gevonden", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Tafel niet gevonden", HttpStatus.NOT_FOUND);
        }
    }


}