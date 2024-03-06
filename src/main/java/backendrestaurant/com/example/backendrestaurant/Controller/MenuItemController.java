package backendrestaurant.com.example.backendrestaurant.Controller;

import backendrestaurant.com.example.backendrestaurant.MenuItem;
import backendrestaurant.com.example.backendrestaurant.Service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuItems")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuItem(@PathVariable Long id) {
        MenuItem menuItem = menuItemService.getMenuItemById(id);

        if (menuItem != null) {
            if (!menuItem.isAvailable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu item is now unavailable.");
            } else {
                return ResponseEntity.ok().body(menuItem);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menu item not found.");
        }
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItem);
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
}
