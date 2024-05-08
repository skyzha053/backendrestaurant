package backendrestaurant.com.example.backendrestaurant.Service;

import backendrestaurant.com.example.backendrestaurant.Entiteit.Reservation;
import backendrestaurant.com.example.backendrestaurant.Repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testGetAllReservations() {
        // Arrange
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Act
        List<Reservation> result = reservationService.getAllReservations();

        // Assert
        assertEquals(reservations.size(), result.size());
    }

    @Test
    public void testGetReservationById_ExistingId() {
        // Arrange
        Long id = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        // Act
        Optional<Reservation> result = reservationService.getReservationById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(reservation, result.get());
    }

    @Test
    public void testGetReservationById_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(reservationRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Reservation> result = reservationService.getReservationById(id);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testCreateReservation_Integration() {
        // Arrange
        Reservation reservation = new Reservation();

        // Mock the behavior of the repository to return the saved reservation
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> {
            Reservation savedReservation = invocation.getArgument(0);
            savedReservation.setId(1L); // Assuming an ID is set after saving
            return savedReservation;
        });

        // Act
        Reservation result = reservationService.createReservation(reservation);

        // Assert
        assertNotNull(result);
    }


    @Test
    public void testUpdateReservationByName_Integration_ExistingName() {
        // Arrange
        String name = "Reservation1";
        Reservation existingReservation = new Reservation();
        existingReservation.setName(name);
        existingReservation.setNumberOfPersons(3); // Set initial number of persons

        Reservation updatedReservation = new Reservation();
        updatedReservation.setNumberOfPersons(5);

        // Mock the behavior of the repository to return the existing reservation
        when(reservationRepository.findByName(name)).thenReturn(Optional.of(existingReservation));

        // Mock the behavior of the repository to return the updated reservation after saving
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> {
            Reservation savedReservation = invocation.getArgument(0);
            return savedReservation; // Assuming the same instance is returned after saving
        });

        // Act
        Reservation result = reservationService.updateReservationByName(name, updatedReservation);

        // Assert
        assertNotNull(result);
        assertEquals(updatedReservation.getNumberOfPersons(), result.getNumberOfPersons());
    }


    @Test
    public void testCreateReservation() {
        // Arrange
        Reservation reservation = new Reservation();

        // Act
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation result = reservationService.createReservation(reservation);

        // Assert
        assertNotNull(result);
        assertEquals(reservation, result);
    }

    @Test
    public void testUpdateReservationByName_ExistingName() {
        // Arrange
        String name = "Reservation1";
        Reservation existingReservation = new Reservation();
        existingReservation.setName(name);

        Reservation updatedReservation = new Reservation();
        updatedReservation.setNumberOfPersons(5);

        when(reservationRepository.findByName(name)).thenReturn(Optional.of(existingReservation));
        when(reservationRepository.save(existingReservation)).thenReturn(existingReservation);

        // Act
        Reservation result = reservationService.updateReservationByName(name, updatedReservation);

        // Assert
        assertNotNull(result);
        assertEquals(updatedReservation.getNumberOfPersons(), result.getNumberOfPersons());
    }

    @Test
    public void testUpdateReservationByName_NonExistingName() {
        // Arrange
        String name = "NonExistingReservation";
        Reservation updatedReservation = new Reservation();
        updatedReservation.setNumberOfPersons(5);

        when(reservationRepository.findByName(name)).thenReturn(Optional.empty());

        // Act
        Reservation result = reservationService.updateReservationByName(name, updatedReservation);

        // Assert
        assertNull(result);
    }

    @Test
    public void testDeleteReservation_ExistingId() {
        // Arrange
        Long id = 1L;
        when(reservationRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = reservationService.deleteReservation(id);

        // Assert
        assertTrue(result);
        verify(reservationRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteReservation_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(reservationRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = reservationService.deleteReservation(id);

        // Assert
        assertFalse(result);
        verify(reservationRepository, never()).deleteById(id);
    }

    @Test
    public void testDeleteReservationByName_ExistingName() {
        // Arrange
        String name = "Reservation1";
        Reservation existingReservation = new Reservation();
        existingReservation.setName(name);

        when(reservationRepository.findByName(name)).thenReturn(Optional.of(existingReservation));

        // Act
        boolean result = reservationService.deleteReservationByName(name);

        // Assert
        assertTrue(result);
        verify(reservationRepository, times(1)).delete(existingReservation);
    }

    @Test
    public void testDeleteReservationByName_NonExistingName() {
        // Arrange
        String name = "NonExistingReservation";
        when(reservationRepository.findByName(name)).thenReturn(Optional.empty());

        // Act
        boolean result = reservationService.deleteReservationByName(name);

        // Assert
        assertFalse(result);
        verify(reservationRepository, never()).delete(any());
    }
}
