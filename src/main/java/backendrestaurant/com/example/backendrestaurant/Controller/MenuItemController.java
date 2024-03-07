package backendrestaurant.com.example.backendrestaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import java.util.Optional;
import java.util.HashMap;

import java.util.Arrays;

import java.util.stream.Collectors;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Entiteit.MenuItem;
import backendrestaurant.com.example.backendrestaurant.Service.AllergieService;
import backendrestaurant.com.example.backendrestaurant.Service.MenuItemService;

@RestController
@RequestMapping("/menuItems")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private AllergieService allergieService;

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuItem(@PathVariable Long id) {
        Optional<MenuItem> optionalMenuItem = menuItemService.getMenuItemById(id);

        if (optionalMenuItem.isPresent()) {
            MenuItem menuItem = optionalMenuItem.get();
            if (!menuItem.isAvailable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu item is now unavailable.");
            } else {
                return ResponseEntity.ok().body(menuItem);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu item not found.");
        }
    }


    @PostMapping("/create")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        menuItem.setId(null);

        // Iterate through the received allergens and check if they exist in the database
        Set<Allergie> existingAllergens = new HashSet<>();
        for (Allergie allergie : menuItem.getAllergenen()) {
            Allergie existingAllergie = allergieService.getByName(allergie.getNaam());

            // Only add the allergen if it already exists in the database
            if (existingAllergie != null) {
                existingAllergens.add(existingAllergie);
            }
        }

        // Set the existing allergens to the menu item
        menuItem.setAllergenen(existingAllergens);

        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);

        if (createdMenuItem != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItem);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem updatedMenuItem) {
        MenuItem menuItem = menuItemService.updateMenuItem(id, updatedMenuItem);
        return ResponseEntity.ok().body(menuItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<String> blockMenuItem(@PathVariable Long id) {
        String result = menuItemService.blockMenuItem(id);

        if (result != null) {
            if (result.equals("Menu item is now unavailable.")) {
                return ResponseEntity.ok().body(result);
            } else if (result.equals("Menu item is already unavailable.")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred.");
        }
    }

    @GetMapping("/checkAllergies")
    public ResponseEntity<Map<String, Boolean>> checkAllergiesInMenu(@RequestBody Map<String, Object> requestBody) {
        Object menuItemIdObject = requestBody.get("menuItemId");

        if (menuItemIdObject == null) {
            return ResponseEntity.badRequest().body(Collections.emptyMap());
        }

        try {
            Long menuItemId = Long.parseLong(menuItemIdObject.toString());
            List<String> allergenen = (List<String>) requestBody.get("allergenen");

            Optional<MenuItem> optionalMenuItem = menuItemService.getMenuItemById(menuItemId);

            if (optionalMenuItem.isPresent()) {
                MenuItem menuItem = optionalMenuItem.get();
                Set<Allergie> allergenenInMenuItem = menuItem.getAllergenen();

                Map<String, Boolean> allergenPresenceMap = new HashMap<>();

                for (String allergen : allergenen) {
                    boolean isPresent = allergenenInMenuItem.stream()
                            .anyMatch(a -> a.getNaam().equals(allergen));

                    allergenPresenceMap.put(allergen, isPresent);
                }

                return ResponseEntity.ok(allergenPresenceMap);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyMap());
            }
        } catch (NumberFormatException | ClassCastException e) {
            return ResponseEntity.badRequest().body(Collections.emptyMap());
        }
    }

    // DTO class for checkAllergies endpoint
    public static class CheckAllergiesRequest {
        private Long menuItemId;
        private List<String> allergenen;

        // Getters and setters

        public Long getMenuItemId() {
            return menuItemId;
        }

        public void setMenuItemId(Long menuItemId) {
            this.menuItemId = menuItemId;
        }

        public List<String> getAllergenen() {
            return allergenen;
        }

        public void setAllergenen(List<String> allergenen) {
            this.allergenen = allergenen;
        }
    }
}