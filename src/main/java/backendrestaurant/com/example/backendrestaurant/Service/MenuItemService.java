package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Entiteit.MenuItem;
import backendrestaurant.com.example.backendrestaurant.Repository.MenuItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.AllergieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Collections;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private AllergieRepository allergieRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItemRepository.findById(id);
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        // Eerst allergieën opslaan
        for (Allergie allergie : menuItem.getAllergenen()) {
            allergieRepository.save(allergie);
        }

        // Nu backendrestaurant.com.example.backendrestaurant.Entiteit.MenuItem opslaan zonder allergenen
        menuItem.setAllergenen(Collections.emptySet()); // Allergenen leeg maken om oneindige loop te voorkomen
        MenuItem savedMenuItem = menuItemRepository.save(menuItem); // Zorg ervoor dat menuItem nu een ID heeft

        // Zet het toegewezen ID terug in het oorspronkelijke object
        menuItem.setId(savedMenuItem.getId());

        // Voeg allergenen weer toe en update het menu-item in de database
        menuItem.setAllergenen(savedMenuItem.getAllergenen());
        return menuItemRepository.save(menuItem);
    }


    public void removeMenuItemFromAllergies(Long menuItemId) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);

        if (optionalMenuItem.isPresent()) {
            MenuItem menuItem = optionalMenuItem.get();
            Set<Allergie> allergenenInMenuItem = menuItem.getAllergenen();

            for (Allergie allergie : allergenenInMenuItem) {
                allergie.getMenuItems().remove(menuItem);
            }

            menuItem.getAllergenen().clear();
            menuItemRepository.save(menuItem);
        }
    }

    public MenuItem updateMenuItem(Long id, MenuItem updatedMenuItem) {
        if (menuItemRepository.existsById(id)) {
            MenuItem menuItem = menuItemRepository.findById(id).orElse(null);

            if (menuItem != null) {
                // Allergieën van het bestaande menu-item verwijderen
                for (Allergie allergie : menuItem.getAllergenen()) {
                    allergie.getMenuItems().remove(menuItem);
                }

                // Allergieën van het bijgewerkte menu-item toevoegen
                for (Allergie allergie : updatedMenuItem.getAllergenen()) {
                    allergie.getMenuItems().add(updatedMenuItem);
                    allergieRepository.save(allergie);
                }

                // Overige bijwerkingen uitvoeren
                updatedMenuItem.setId(id);

                // Update het menu-item in de database
                return menuItemRepository.save(updatedMenuItem);
            }
        }
        return null;
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    public String blockMenuItem(Long id) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(id);

        if (optionalMenuItem.isPresent()) {
            MenuItem menuItem = optionalMenuItem.get();

            if (menuItem.isAvailable()) {
                menuItem.setAvailable(false);
                menuItemRepository.save(menuItem);
                return "Menu item is now unavailable.";
            } else {
                return "Menu item is already unavailable.";
            }
        } else {
            return "Menu item not found.";
        }
    }

    public String unblockMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);

        if (menuItem != null) {
            if (menuItem.isAvailable()) {
                return "Menu item is already available.";
            } else {
                menuItem.setAvailable(true);
                menuItemRepository.save(menuItem);
                return "Menu item is now available.";
            }
        } else {
            return "Menu item not found.";
        }
    }

    public boolean containsAllergenen(Long menuItemId, List<String> allergenen) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);

        return optionalMenuItem.map(menuItem -> {
            Set<Allergie> allergenenInMenuItem = menuItem.getAllergenen();
            return allergenenInMenuItem.stream().anyMatch(allergie -> allergenen.contains(allergie.getNaam()));
        }).orElse(false);
    }

    public List<Allergie> getAllAllergies() {
        return allergieRepository.findAll();
    }
}
