package backendrestaurant.com.example.backendrestaurant.Entiteit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int numberOfPersons;
    private LocalDate reservationDate;
    private String allergies;
    private String comments;
    private String phoneNumber;
    private String emailAddress;

    private String bedrijfsnaam; // New field for company name

    // Constructors
    public Reservation() {
        // default constructor
    }

    public Reservation(String name, int numberOfPersons, LocalDate reservationDate, String allergies, String comments, String phoneNumber, String emailAddress, String bedrijfsnaam) {
        this.name = name;
        this.numberOfPersons = numberOfPersons;
        this.reservationDate = reservationDate;
        this.allergies = allergies;
        this.comments = comments;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.bedrijfsnaam = bedrijfsnaam;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
