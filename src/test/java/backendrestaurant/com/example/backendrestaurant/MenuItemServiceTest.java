package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Entiteit.MenuItem;
import backendrestaurant.com.example.backendrestaurant.Repository.MenuItemRepository;
import backendrestaurant.com.example.backendrestaurant.Repository.AllergieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private AllergieRepository allergieRepository;

    @InjectMocks
    private MenuItemService menuItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testUpdateMenuItem_Success() {
        Long menuItemId = 1L;
        MenuItem existingMenuItem = new MenuItem();
        existingMenuItem.setId(menuItemId);
        existingMenuItem.setAllergenen(new HashSet<>());
        MenuItem updatedMenuItem = new MenuItem();
        updatedMenuItem.setId(menuItemId);
        Set<Allergie> allergenen = new HashSet<>();
        allergenen.add(new Allergie("Lactose"));
        updatedMenuItem.setAllergenen(allergenen);

        when(menuItemRepository.existsById(menuItemId)).thenReturn(true);
        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(existingMenuItem));
        when(menuItemRepository.save(updatedMenuItem)).thenReturn(updatedMenuItem);
        when(allergieRepository.save(any(Allergie.class))).thenReturn(new Allergie("Lactose"));

        MenuItem result = menuItemService.updateMenuItem(menuItemId, updatedMenuItem);

        assertNotNull(result);
        assertEquals(updatedMenuItem.getId(), result.getId());
        assertEquals(updatedMenuItem.getAllergenen(), result.getAllergenen());
        verify(menuItemRepository, times(1)).existsById(menuItemId);
        verify(menuItemRepository, times(1)).findById(menuItemId);
        verify(allergieRepository, times(1)).save(any(Allergie.class));
        verify(menuItemRepository, times(1)).save(updatedMenuItem);
    }

    @Test
    public void testRemoveMenuItemFromAllergies_Success() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemId);
        Allergie allergie1 = new Allergie("Gluten");
        Allergie allergie2 = new Allergie("Lactose");
        Set<Allergie> allergenenInMenuItem = new HashSet<>();
        allergenenInMenuItem.add(allergie1);
        allergenenInMenuItem.add(allergie2);
        menuItem.setAllergenen(allergenenInMenuItem);

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(menuItem));

        menuItemService.removeMenuItemFromAllergies(menuItemId);

        assertTrue(menuItem.getAllergenen().isEmpty());
        verify(menuItemRepository, times(1)).findById(menuItemId);
        verify(menuItemRepository, times(1)).save(menuItem);
        verifyNoInteractions(allergieRepository); // We controleren of allergieRepository niet wordt aangeroepen omdat we allergieën niet opslaan
    }

    @Test
    public void testDeleteMenuItem_Success() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemId);

        menuItemService.deleteMenuItem(menuItemId);

        verify(menuItemRepository, times(1)).deleteById(menuItemId);
    }

    @Test
    public void testBlockMenuItem_MenuItemAlreadyBlocked_ReturnsAlreadyUnavailableMessage() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemId);
        menuItem.setAvailable(false);

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(menuItem));

        String result = menuItemService.blockMenuItem(menuItemId);

        assertEquals("Menu item is already unavailable.", result);
        verify(menuItemRepository, times(1)).findById(menuItemId);
        verify(menuItemRepository, never()).save(any(MenuItem.class));
    }

    @Test
    public void testUnblockMenuItem_MenuItemAlreadyAvailable_ReturnsAlreadyAvailableMessage() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemId);
        menuItem.setAvailable(true);

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(menuItem));

        String result = menuItemService.unblockMenuItem(menuItemId);

        assertEquals("Menu item is already available.", result);
        verify(menuItemRepository, times(1)).findById(menuItemId);
        verify(menuItemRepository, never()).save(any(MenuItem.class));
    }

    @Test
    public void testContainsAllergenen_MenuItemContainsAllergen_ReturnsTrue() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemId);
        Allergie allergie = new Allergie("Gluten");
        menuItem.getAllergenen().add(allergie);

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(menuItem));

        boolean result = menuItemService.containsAllergenen(menuItemId, Collections.singletonList("Gluten"));

        assertTrue(result);
        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    public void testContainsAllergenen_MenuItemDoesNotContainAllergen_ReturnsFalse() {
        Long menuItemId = 1L;
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemId);
        Allergie allergie = new Allergie("Lactose");
        menuItem.getAllergenen().add(allergie);

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(menuItem));

        boolean result = menuItemService.containsAllergenen(menuItemId, Collections.singletonList("Gluten"));

        assertFalse(result);
        verify(menuItemRepository, times(1)).findById(menuItemId);
    }

    @Test
    public void testGetAllAllergies() {
        List<Allergie> allergies = new ArrayList<>();
        allergies.add(new Allergie("Gluten"));
        allergies.add(new Allergie("Lactose"));

        when(allergieRepository.findAll()).thenReturn(allergies);

        List<Allergie> result = menuItemService.getAllAllergies();

        assertEquals(allergies.size(), result.size());
        assertEquals(allergies.get(0), result.get(0));
        assertEquals(allergies.get(1), result.get(1));
        verify(allergieRepository, times(1)).findAll();
    }
}
