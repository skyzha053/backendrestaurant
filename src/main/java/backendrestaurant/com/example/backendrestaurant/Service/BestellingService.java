package backendrestaurant.com.example.backendrestaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;  // Import BigDecimal
import java.util.List;
import java.util.Optional;

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
    }

    private BesteldItem createBesteldItem(Tafel tafel, MenuItem menuItem, int hoeveelheid) {
        // Implementeer de logica om de prijs in te stellen op basis van menuItem en hoeveelheid
        BigDecimal prijs = menuItem.getPrice().multiply(BigDecimal.valueOf(hoeveelheid));

        BesteldItem besteldItem = new BesteldItem();
        besteldItem.setTafel(tafel);
        besteldItem.setMenuItem(menuItem);
        besteldItem.setHoeveelheid(hoeveelheid);
        besteldItem.setPrijs(prijs);
        besteldItem.setItemNaam(menuItem.getName()); // Zet de itemNaam op basis van menuItem

        return besteldItem;
    }

    private BesteldItem createBesteldItem(Tafel tafel, Drank drank, int hoeveelheid) {
        // Implementeer de logica om de prijs in te stellen op basis van drank en hoeveelheid
        BigDecimal prijs = drank.getPrijs().multiply(BigDecimal.valueOf(hoeveelheid));

        BesteldItem besteldItem = new BesteldItem();
        besteldItem.setTafel(tafel);
        besteldItem.setDrank(drank);
        besteldItem.setHoeveelheid(hoeveelheid);
        besteldItem.setPrijs(prijs);
        besteldItem.setItemNaam(drank.getNaam()); // Zet de itemNaam op basis van drank

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

}
