package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Repository.AllergieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.never;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AllergieServiceTest {

    @Mock
    private AllergieRepository allergieRepository;

    @InjectMocks
    private AllergieService allergieService;

    @Test
    public void testGetAllAllergieen() {
        List<Allergie> allergieList = new ArrayList<>();
        allergieList.add(new Allergie()); // Aanpassing hier

        when(allergieRepository.findAll()).thenReturn(allergieList);

        List<Allergie> result = allergieService.getAllAllergieen();

        assertEquals(1, result.size());
    }

    @Test
    public void testGetAllergieById() {
        Allergie allergie = new Allergie(); // Aanpassing hier
        allergie.setId(1L);

        when(allergieRepository.findById(1L)).thenReturn(Optional.of(allergie));

        Allergie result = allergieService.getAllergieById(1L);

        assertEquals(allergie, result);
    }

    @Test
    public void testCreateAllergie() {
        Allergie allergie = new Allergie(); // Aanpassing hier

        when(allergieRepository.save(allergie)).thenReturn(allergie);

        Allergie result = allergieService.createAllergie(allergie);

        assertEquals(allergie, result);
        verify(allergieRepository).save(allergie); // Verifieer of save is aangeroepen
    }

    @Test
    public void testUpdateAllergie() {
        Allergie existingAllergie = new Allergie(); // Aanpassing hier
        existingAllergie.setId(1L);
        Allergie updatedAllergie = new Allergie(); // Aanpassing hier

        when(allergieRepository.findById(1L)).thenReturn(Optional.of(existingAllergie));
        when(allergieRepository.save(updatedAllergie)).thenReturn(updatedAllergie);

        Allergie result = allergieService.updateAllergie(1L, updatedAllergie);

        assertEquals(updatedAllergie, result);
        verify(allergieRepository).findById(1L); // Verifieer of findById is aangeroepen
        verify(allergieRepository).save(updatedAllergie); // Verifieer of save is aangeroepen
    }

    @Test
    public void testGetByNameWhenAllergenExists() {
        List<Allergie> allergenList = new ArrayList<>();
        allergenList.add(new Allergie()); // Aanpassing hier

        when(allergieRepository.findByNaam("Test Allergie")).thenReturn(allergenList);

        Allergie result = allergieService.getByName("Test Allergie");

        assertNotNull(result);
        assertEquals(allergenList.get(0), result);
    }

    @Test
    public void testGetByNameWhenAllergenDoesNotExist() {
        when(allergieRepository.findByNaam("Nonexistent Allergie")).thenReturn(new ArrayList<>());

        Allergie result = allergieService.getByName("Nonexistent Allergie");

        assertNull(result);
    }

    @Test
    public void testGetAllAllergieenWhenNoneExist() {
        when(allergieRepository.findAll()).thenReturn(new ArrayList<>());

        List<Allergie> result = allergieService.getAllAllergieen();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllergieByIdWhenNotExist() {
        when(allergieRepository.findById(1L)).thenReturn(Optional.empty());

        Allergie result = allergieService.getAllergieById(1L);

        assertNull(result);
    }

    @Test
    public void testUpdateAllergieWhenNotExist() {
        // Maak een nieuwe allergie aan die niet bestaat in de repository
        Allergie updatedAllergie = new Allergie();
        updatedAllergie.setId(1L);

        // Wanneer findById wordt aangeroepen, geef een lege Optional terug (wat betekent dat de allergie niet bestaat)
        when(allergieRepository.findById(1L)).thenReturn(Optional.empty());

        // Roep de updateAllergie-methode aan
        Allergie result = allergieService.updateAllergie(1L, updatedAllergie);

        // Controleer of het resultaat null is, wat betekent dat de allergie niet kon worden bijgewerkt
        assertNull(result);

        // Verifieer dat de findById-methode is aangeroepen
        verify(allergieRepository).findById(1L);

        // Verifieer dat de save-methode nooit is aangeroepen omdat de allergie niet bestaat
        verify(allergieRepository, never()).save(any(Allergie.class));
    }




}
