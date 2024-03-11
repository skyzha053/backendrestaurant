package backendrestaurant.com.example.backendrestaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;  // Import BigDecimal
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import backendrestaurant.com.example.backendrestaurant.Entiteit.BesteldItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Drank;
import backendrestaurant.com.example.backendrestaurant.Entiteit.MenuItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import backendrestaurant.com.example.backendrestaurant.Repository.BesteldItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.DrankRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.MenuItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.TafelRepository;

@Service
public class BestellingService {

    @Autowired
    private TafelRepository tafelRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private DrankRepository drankRepository;

    @Autowired
    private BesteldItemRepository besteldItemRepository;

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

    public void processMenuItems(List<BesteldItem> besteldeMenuItems, Tafel tafel) {
        for (BesteldItem besteldItem : besteldeMenuItems) {
            Optional<MenuItem> optionalMenuItem = menuItemRepository.findByName(besteldItem.getItemNaam());
            if (optionalMenuItem.isPresent()) {
                MenuItem menuItem = optionalMenuItem.get();
                // Create and save BesteldItem based on menuItem
                BesteldItem besteldMenuItem = createBesteldItem(tafel, menuItem, besteldItem.getHoeveelheid());
                besteldItemRepository.save(besteldMenuItem);
            }
        }
        updateTotalePrijs(tafel);
    }

    public void processDranken(List<BesteldItem> besteldeDranken, Tafel tafel) {
        for (BesteldItem besteldItem : besteldeDranken) {
            Optional<Drank> optionalDrank = drankRepository.findByNaam(besteldItem.getItemNaam());
            if (optionalDrank.isPresent()) {
                Drank drank = optionalDrank.get();
                // Create and save BesteldItem based on drank
                BesteldItem besteldDrank = createBesteldItem(tafel, drank, besteldItem.getHoeveelheid());
                besteldItemRepository.save(besteldDrank);
            }
        }
        updateTotalePrijs(tafel);
    }

    private void updateTotalePrijs(Tafel tafel) {
        List<BesteldItem> besteldeItems = besteldItemRepository.findByTafel(tafel);
        BigDecimal totalePrijs = BigDecimal.ZERO;

        for (BesteldItem besteldItem : besteldeItems) {
            totalePrijs = totalePrijs.add(besteldItem.getPrijs());
        }

        tafel.setTotalePrijs(totalePrijs);
        tafelRepository.save(tafel);
    }

    private BesteldItem createBesteldItem(Tafel tafel, MenuItem menuItem, int hoeveelheid) {
        // Implementeer de logica om de prijs in te stellen op basis van menuItem en hoeveelheid
        BigDecimal prijs = menuItem.getPrice().multiply(BigDecimal.valueOf(hoeveelheid));

        BesteldItem besteldItem = new BesteldItem();
        besteldItem.setTafel(tafel);
        besteldItem.setMenuItem(menuItem);
        besteldItem.setHoeveelheid(hoeveelheid);
        besteldItem.setPrijs(prijs);
        besteldItem.setItemNaam(menuItem.getName());

        return besteldItem;
    }

    private BesteldItem createBesteldItem(Tafel tafel, Drank drank, int hoeveelheid) {

        BigDecimal prijs = drank.getPrijs().multiply(BigDecimal.valueOf(hoeveelheid));

        BesteldItem besteldItem = new BesteldItem();
        besteldItem.setTafel(tafel);
        besteldItem.setDrank(drank);
        besteldItem.setHoeveelheid(hoeveelheid);
        besteldItem.setPrijs(prijs);
        besteldItem.setItemNaam(drank.getNaam());

        return besteldItem;
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
