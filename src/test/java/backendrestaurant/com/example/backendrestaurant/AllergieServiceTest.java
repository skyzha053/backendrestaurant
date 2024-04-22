package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Allergie;
import backendrestaurant.com.example.backendrestaurant.Repository.AllergieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
        allergieList.add(new Allergie());

        when(allergieRepository.findAll()).thenReturn(allergieList);

        List<Allergie> result = allergieService.getAllAllergieen();

        assertEquals(allergieList.size(), result.size());
        assertEquals(allergieList.get(0), result.get(0));
    }

    @Test
    public void testGetAllergieById() {
        Allergie allergie = new Allergie();
        allergie.setId(1L);

        when(allergieRepository.findById(1L)).thenReturn(Optional.of(allergie));

        Allergie result = allergieService.getAllergieById(1L);

        assertNotNull(result);
        assertEquals(allergie.getId(), result.getId());
    }

    @Test
    public void testCreateAllergie() {
        Allergie allergie = new Allergie();

        when(allergieRepository.save(allergie)).thenReturn(allergie);

        Allergie result = allergieService.createAllergie(allergie);

        assertNotNull(result);
        assertEquals(allergie, result);
        verify(allergieRepository).save(allergie);
    }

    @Test
    public void testUpdateAllergie() {
        Allergie existingAllergie = new Allergie();
        existingAllergie.setId(1L);
        Allergie updatedAllergie = new Allergie();

        when(allergieRepository.findById(1L)).thenReturn(Optional.of(existingAllergie));
        when(allergieRepository.save(updatedAllergie)).thenReturn(updatedAllergie);

        Allergie result = allergieService.updateAllergie(1L, updatedAllergie);

        assertNotNull(result);
        assertEquals(updatedAllergie, result);
        verify(allergieRepository).findById(1L);
        verify(allergieRepository).save(updatedAllergie);
    }

    @Test
    public void testGetByNameWhenAllergenExists() {
        List<Allergie> allergenList = new ArrayList<>();
        Allergie allergie = new Allergie();
        allergenList.add(allergie);

        when(allergieRepository.findByNaam("Test Allergie")).thenReturn(allergenList);

        Allergie result = allergieService.getByName("Test Allergie");

        assertNotNull(result);
        assertEquals(allergie, result);
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
        Allergie updatedAllergie = new Allergie();
        updatedAllergie.setId(1L);

        when(allergieRepository.findById(1L)).thenReturn(Optional.empty());

        Allergie result = allergieService.updateAllergie(1L, updatedAllergie);

        assertNull(result);
        verify(allergieRepository).findById(1L);
        verify(allergieRepository, never()).save(updatedAllergie);
    }

}
