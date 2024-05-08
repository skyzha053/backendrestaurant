package backendrestaurant.com.example.backendrestaurant.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class CreateReservationDTO {
    @NotBlank(message = "Naam mag niet leeg zijn")
    private String name;

    @Min(value = 1, message = "Aantal personen moet minimaal 1 zijn")
    private int numberOfPersons;

    @NotNull(message = "Reserveringsdatum mag niet leeg zijn")
    private LocalDate reservationDate;

    private String allergies;
    private String comments;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Ongeldig telefoonnummer")
    private String phoneNumber;

    @Email(message = "Ongeldig e-mailadres")
    private String emailAddress;

    private String bedrijfsnaam;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }
}
