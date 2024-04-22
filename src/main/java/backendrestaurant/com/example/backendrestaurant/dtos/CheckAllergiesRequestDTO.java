package backendrestaurant.com.example.backendrestaurant.dtos;

import java.util.List;

public class CheckAllergiesRequestDTO {
    private Long menuItemId;
    private List<String> allergens;

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }
}
