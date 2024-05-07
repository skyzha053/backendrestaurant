package backendrestaurant.com.example.backendrestaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BestellingRequest;
import backendrestaurant.com.example.backendrestaurant.Entiteit.BesteldItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Drank;
import backendrestaurant.com.example.backendrestaurant.Entiteit.MenuItem;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Tafel;
import backendrestaurant.com.example.backendrestaurant.Repository.BesteldItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.DrankRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.MenuItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.TafelRepository;

import java.math.BigDecimal;
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

    public void processMenuItems(List<BesteldItem> besteldeMenuItems, Tafel tafel) {
        processItems(besteldeMenuItems, tafel, false);
    }

    public void processDranken(List<BesteldItem> besteldeDranken, Tafel tafel) {
        processItems(besteldeDranken, tafel, true);
    }


    public List<BesteldItem> getAllBestellingen() {
        return besteldItemRepository.findAll();
    }

    public ResponseEntity<String> plaatsBestelling(BestellingRequest bestellingRequest) {
        String tafelNaam = bestellingRequest.getTafelNaam();
        List<BesteldItem> besteldeMenuItems = bestellingRequest.getBesteldeMenuItems();
        List<BesteldItem> besteldeDranken = bestellingRequest.getBesteldeDranken();

        Tafel tafel = createOrUpdateTafel(tafelNaam);


        if (!areMenuItemsAvailable(besteldeMenuItems)) {
            return new ResponseEntity<>("Niet alle menu-items zijn momenteel beschikbaar. Bestelling kan niet worden geplaatst.", HttpStatus.BAD_REQUEST);
        }


        processMenuItems(besteldeMenuItems, tafel);
        processDranken(besteldeDranken, tafel);

        return new ResponseEntity<>("Bestelling geplaatst voor tafel " + tafelNaam, HttpStatus.OK);
    }


    private boolean areMenuItemsAvailable(List<BesteldItem> besteldeMenuItems) {
        for (BesteldItem item : besteldeMenuItems) {
            Optional<MenuItem> optionalMenuItem = menuItemRepository.findByName(item.getItemNaam());
            if (optionalMenuItem.isPresent()) {
                MenuItem menuItem = optionalMenuItem.get();
                if (!menuItem.isAvailable()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
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
        List<BesteldItem> besteldeItems = besteldItemRepository.findByTafel(tafel);
        BigDecimal totalePrijs = BigDecimal.ZERO;

        // Calculate totale prijs
        for (BesteldItem besteldItem : besteldeItems) {
            totalePrijs = totalePrijs.add(besteldItem.getPrijs());
        }

        // Set the totale prijs for the tafel
        tafel.setTotalePrijs(totalePrijs);
        tafelRepository.save(tafel);
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