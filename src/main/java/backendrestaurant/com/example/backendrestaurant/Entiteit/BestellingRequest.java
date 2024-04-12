package backendrestaurant.com.example.backendrestaurant.Entiteit;

import java.util.List;

public class BestellingRequest {
    private String tafelNaam;
    private List<BesteldItem> besteldeMenuItems;
    private List<BesteldItem> besteldeDranken;



    public BestellingRequest() {
    }

    public BestellingRequest(String tafelNaam, List<BesteldItem> besteldeMenuItems, List<BesteldItem> besteldeDranken) {
        this.tafelNaam = tafelNaam;
        this.besteldeMenuItems = besteldeMenuItems;
        this.besteldeDranken = besteldeDranken;
    }

    public String getTafelNaam() {
        return tafelNaam;
    }

    public void setTafelNaam(String tafelNaam) {
        this.tafelNaam = tafelNaam;
    }

    public List<BesteldItem> getBesteldeMenuItems() {
        return besteldeMenuItems;
    }

    public void setBesteldeMenuItems(List<BesteldItem> besteldeMenuItems) {
        this.besteldeMenuItems = besteldeMenuItems;
    }

    public List<BesteldItem> getBesteldeDranken() {
        return besteldeDranken;
    }

    public void setBesteldeDranken(List<BesteldItem> besteldeDranken) {
        this.besteldeDranken = besteldeDranken;
    }
}
