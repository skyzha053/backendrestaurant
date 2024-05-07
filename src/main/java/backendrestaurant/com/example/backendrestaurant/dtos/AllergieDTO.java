package backendrestaurant.com.example.backendrestaurant.dtos;

public class AllergieDTO {
    private Long id;
    private String naam;



    public AllergieDTO() {
    }

    public AllergieDTO(Long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
