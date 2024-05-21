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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AllergieServiceTest {

    @Mock
    private AllergieRepository allergieRepository;

    @InjectMocks
    private AllergieService allergieService;

    @Test
    public void testGetAllAllergieen() {
        List<Allergie> allergieList = new ArrayList<>();
        allergieList.add(new Allergie("Gluten"));
        allergieList.add(new Allergie("Lactose"));

        when(allergieRepository.findAll()).thenReturn(allergieList);

        List<Allergie> result = allergieService.getAllAllergieen();

        assertEquals(allergieList.size(), result.size());
        verify(allergieRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllAllergieen_EmptyList() {
        when(allergieRepository.findAll()).thenReturn(new ArrayList<>());

        List<Allergie> result = allergieService.getAllAllergieen();

        assertTrue(result.isEmpty());
        verify(allergieRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllergieById() {
        Allergie allergie = new Allergie("Gluten");

        when(allergieRepository.findById(1L)).thenReturn(Optional.of(allergie));

        Allergie result = allergieService.getAllergieById(1L);

        assertEquals(allergie, result);
        verify(allergieRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllergieById_NotFound() {
        when(allergieRepository.findById(any())).thenReturn(Optional.empty());

        Allergie result = allergieService.getAllergieById(1L);

        assertNull(result);
        verify(allergieRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateAllergie() {
        Allergie allergie = new Allergie("Gluten");

        when(allergieRepository.save(allergie)).thenReturn(allergie);

        Allergie result = allergieService.createAllergie(allergie);

        assertEquals(allergie, result);
        verify(allergieRepository, times(1)).save(allergie);
    }


    @Test
    public void testUpdateAllergie() {
        Allergie existingAllergie = new Allergie("Gluten");
        Allergie updatedAllergie = new Allergie("Lactose");

        when(allergieRepository.findById(1L)).thenReturn(Optional.of(existingAllergie));
        when(allergieRepository.save(updatedAllergie)).thenReturn(updatedAllergie);

        Allergie result = allergieService.updateAllergie(1L, updatedAllergie);

        assertEquals(updatedAllergie, result);
        verify(allergieRepository, times(1)).findById(1L);
        verify(allergieRepository, times(1)).save(updatedAllergie);
    }

    @Test
    public void testUpdateAllergie_NotFound() {
        when(allergieRepository.findById(any())).thenReturn(Optional.empty());

        Allergie result = allergieService.updateAllergie(1L, new Allergie(""));

        assertNull(result);
        verify(allergieRepository, never()).save(any());
    }

    @Test
    public void testGetByName() {
        Allergie allergie = new Allergie("Gluten");
        List<Allergie> allergenList = new ArrayList<>();
        allergenList.add(allergie);

        when(allergieRepository.findByNaam("Gluten")).thenReturn(allergenList);

        Allergie result = allergieService.getByName("Gluten");

        assertEquals(allergie, result);
        verify(allergieRepository, times(1)).findByNaam("Gluten");
    }

    @Test
    public void testGetByName_NotFound() {
        when(allergieRepository.findByNaam(any())).thenReturn(new ArrayList<>());

        Allergie result = allergieService.getByName("Nonexistent");

        assertNull(result);
        verify(allergieRepository, times(1)).findByNaam("Nonexistent");
    }
}
