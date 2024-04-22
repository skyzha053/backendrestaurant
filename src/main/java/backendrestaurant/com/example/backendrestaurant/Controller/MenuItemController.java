package backendrestaurant.com.example.backendrestaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import backendrestaurant.com.example.backendrestaurant.dtos.MenuItemDTO;
import backendrestaurant.com.example.backendrestaurant.dtos.MenuItemResponseDTO;
import backendrestaurant.com.example.backendrestaurant.dtos.CheckAllergiesRequestDTO;
import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Entiteit.MenuItem;
import backendrestaurant.com.example.backendrestaurant.Service.AllergieService;
import backendrestaurant.com.example.backendrestaurant.Service.MenuItemService;

@RestController
@RequestMapping("/menuItems/all")
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
    public ResponseEntity<MenuItemResponseDTO> createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        MenuItem menuItem = mapMenuItemDTOToEntity(menuItemDTO);
        Set<Allergie> existingAllergens = new HashSet<>();
        for (String allergen : menuItemDTO.getAllergenen()) {
            Allergie existingAllergie = allergieService.getByName(allergen);
            if (existingAllergie != null) {
                existingAllergens.add(existingAllergie);
            }
        }
        menuItem.setAllergenen(existingAllergens);
        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
        if (createdMenuItem != null) {
            MenuItemResponseDTO responseDTO = mapMenuItemToResponseDTO(createdMenuItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponseDTO> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDTO updatedMenuItemDTO) {
        MenuItem updatedMenuItem = mapMenuItemDTOToEntity(updatedMenuItemDTO);
        MenuItem menuItem = menuItemService.updateMenuItem(id, updatedMenuItem);
        MenuItemResponseDTO responseDTO = mapMenuItemToResponseDTO(menuItem);
        return ResponseEntity.ok().body(responseDTO);
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

    @PutMapping("/{id}/unblock")
    public ResponseEntity<String> unblockMenuItem(@PathVariable Long id) {
        String result = menuItemService.unblockMenuItem(id);
        if (result != null) {
            if (result.equals("Menu item is now available.")) {
                return ResponseEntity.ok().body(result);
            } else if (result.equals("Menu item is already available.")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred.");
        }
    }

    @GetMapping("/checkAllergies")
    public ResponseEntity<Map<String, Boolean>> checkAllergiesInMenu(@RequestBody CheckAllergiesRequestDTO requestDTO) {
        Long menuItemId = requestDTO.getMenuItemId();
        List<String> allergens = requestDTO.getAllergens();
        Optional<MenuItem> optionalMenuItem = menuItemService.getMenuItemById(menuItemId);
        if (optionalMenuItem.isPresent()) {
            MenuItem menuItem = optionalMenuItem.get();
            Set<Allergie> allergensInMenuItem = menuItem.getAllergenen();
            Map<String, Boolean> allergenPresenceMap = new HashMap<>();
            for (String allergen : allergens) {
                boolean isPresent = allergensInMenuItem.stream()
                        .anyMatch(a -> a.getNaam().equals(allergen));
                allergenPresenceMap.put(allergen, isPresent);
            }
            return ResponseEntity.ok(allergenPresenceMap);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyMap());
        }
    }

    // Hulpmethode om een MenuItemDTO naar een MenuItem entiteit te mappen
    private MenuItem mapMenuItemDTOToEntity(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDTO.getName());
        menuItem.setDescription(menuItemDTO.getDescription());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setAvailable(menuItemDTO.isAvailable());
        return menuItem;
    }

    // Hulpmethode om een MenuItem naar een MenuItemResponseDTO te mappen
    private MenuItemResponseDTO mapMenuItemToResponseDTO(MenuItem menuItem) {
        MenuItemResponseDTO responseDTO = new MenuItemResponseDTO();
        responseDTO.setId(menuItem.getId());
        responseDTO.setName(menuItem.getName());
        responseDTO.setDescription(menuItem.getDescription());
        responseDTO.setPrice(menuItem.getPrice().doubleValue());
        responseDTO.setAvailable(menuItem.isAvailable());
        return responseDTO;
    }
}
