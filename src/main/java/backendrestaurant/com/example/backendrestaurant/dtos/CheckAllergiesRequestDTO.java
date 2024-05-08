package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public class CheckAllergiesRequestDTO {
    @NotNull(message = "MenuItem ID mag niet leeg zijn")
    @Positive(message = "MenuItem ID moet een positief getal zijn")
    private Long menuItemId;

    @NotEmpty(message = "Allergenenlijst mag niet leeg zijn")
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
