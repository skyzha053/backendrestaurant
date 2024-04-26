
# Installatiehandleiding voor Spring Boot-applicatie
## 1. Inleiding

Deze handleiding biedt stapsgewijze instructies voor het installeren en configureren van een Spring Boot-applicatie.


## 2. Benodigdheden

Zorg ervoor dat de volgende benodigdheden zijn geïnstalleerd op de doel-pc:

- JDK (Java Development Kit)
- Maven 3.2.4
- Een teksteditor (bijv. IntelliJ IDEA, Eclipse, Visual Studio Code)
- Een database naar keuze (bijvoorbeeld MySQL, PostgreSQL, H2)
- Postman, Insomnia (optioneel, voor het testen van REST-endpoints)

## 3. Project klonen

1. Clone het project vanuit de versiebeheerrepository naar de doel-pc.

## 4. Projectstructuur opzetten

1. Open de gekloonde map van het project op de doel-pc.
2. Controleer of de voorgestelde mappenstructuur intact is.

## 5. Maven POM-bestand aanpassen

1. Controleer of het pom.xml-bestand aanwezig is in de hoofdmap van het project.
2. Als het pom.xml-bestand ontbreekt, maak er dan een aan en voeg de benodigde Maven-configuratie toe zoals beschreven in de originele handleiding.

## 6. Database-configuratie aanpassen

1. Open het application.properties-bestand onder src/main/resources/.
2. Pas de databaseconfiguratie aan volgens de specificaties van de doel-pc. Zorg ervoor dat de URL, gebruikersnaam en wachtwoord overeenkomen met de database-instellingen op de doel-pc.

## 7. Importeren in de IDE

1. Open je favoriete IDE op de doel-pc.
2. Importeer het project door de gekloonde map te selecteren.

## 8. Testgebruikers

| Gebruikersnaam | Wachtwoord  | Rol                |
|----------------|-------------|--------------------|
| Remon          | AylaRemon05!| baas               |
| Yente          | Novi123     | restaurantmanager  |
| Emma           | Novi123     | serveerster        |
| Chikit         | Novi123     | Chefkok            |


## 9. Postman collecties

## 10. REST-endpoints
    Swagger UI:
        URL: /swagger-ui.html
        Beschrijving: Swagger UI voor het bekijken en testen van de API-documentatie en -functionaliteit.

    Alle menu-items ophalen:
        Endpoint: /menuItems/all
        Methode: GET
        Beschrijving: Haalt alle menu-items op.

    Alle menu-items controleren op allergieën:
        Endpoint: /menuItems/all/checkAllergies
        Methode: GET
        Beschrijving: Controleert alle menu-items op allergieën.

    Nieuw menu-item toevoegen:
        Endpoint: /menuItems/all/create
        Methode: POST
        Beschrijving: Maakt een nieuw menu-item aan.

    Specifiek menu-item ophalen:
        Endpoint: /menuItems/all/{id}
        Methode: GET
        Beschrijving: Haalt een specifiek menu-item op aan de hand van het ID.

    Menu-item bijwerken:
        Endpoint: /menuItems/all/{id}
        Methode: PUT
        Beschrijving: Werkt een specifiek menu-item bij aan de hand van het ID.

    Menu-item verwijderen:
        Endpoint: /menuItems/all/{id}
        Methode: DELETE
        Beschrijving: Verwijdert een specifiek menu-item aan de hand van het ID.

    Menu-item blokkeren:
        Endpoint: /menuItems/all/{id}/block
        Methode: POST
        Beschrijving: Blokkeert een specifiek menu-item aan de hand van het ID.

    Menu-item deblokkeren:
        Endpoint: /menuItems/all/{id}/unblock
        Methode: POST
        Beschrijving: Deblokkeert een specifiek menu-item aan de hand van het ID.

    Alle allergieën ophalen:
        Endpoint: /allergie/allergieen
        Methode: GET
        Beschrijving: Haalt alle beschikbare allergieën op.

    Dranken ophalen:
        Endpoint: /drinks/
        Methode: GET
        Beschrijving: Haalt alle beschikbare dranken op.

    Nieuwe drank plaatsen:
        Endpoint: /drinks/plaatsen
        Methode: POST
        Beschrijving: Plaatst een nieuwe drank.

    Specifieke drank ophalen:
        Endpoint: /drinks/{id}
        Methode: GET
        Beschrijving: Haalt een specifieke drank op aan de hand van het ID.

    Drank bijwerken:
        Endpoint: /drinks/{id}
        Methode: PUT
        Beschrijving: Werkt een specifieke drank bij aan de hand van het ID.

    Drank verwijderen:
        Endpoint: /drinks/{id}
        Methode: DELETE
        Beschrijving: Verwijdert een specifieke drank aan de hand van het ID.

    Bestelling plaatsen:
        Endpoint: /bestelling/plaatsen
        Methode: POST
        Beschrijving: Plaatst een nieuwe bestelling.

    Tafel verplaatsen in bestelling:
        Endpoint: /bestelling/tafel-verplaatsen
        Methode: POST
        Beschrijving: Verplaatst een tafel in een bestelling.

    Bestelling bijwerken:
        Endpoint: /bestelling/update-bestelling
        Methode: PUT
        Beschrijving: Werkt een bestelling bij.

    Alle reserveringen ophalen:
        Endpoint: /reservering/reservations
        Methode: GET
        Beschrijving: Haalt alle reserveringen op.

    Reservering bijwerken:
        Endpoint: /reservering/reservations/updateReservationByName/{name}
        Methode: PUT
        Beschrijving: Werkt een reservering bij op naam.

    Reservering verwijderen op naam:
        Endpoint: /reservering/reservations/deleteByName/{name}
        Methode: DELETE
        Beschrijving: Verwijdert een reservering op naam.

    Bon genereren:
        Endpoint: /bon
        Methode: GET
        Beschrijving: Genereert een bon.

    Bon voor specifieke tafel genereren:
        Endpoint: /bon/{tafelId}
        Methode: GET
        Beschrijving: Genereert een bon voor een specifieke tafel.

    Bon als betaald markeren voor specifieke tafel:
        Endpoint: /bon/{tafelId}/betaald
        Methode: POST
        Beschrijving: Markeert een bon als betaald voor een specifieke tafel.

    Alle gebruikers ophalen:
        Endpoint: /users
        Methode: GET
        Beschrijving: Haalt alle gebruikers op.

    Specifieke gebruiker ophalen:
        Endpoint: /users/{id}
        Methode: GET
        Beschrijving: Haalt een specifieke gebruiker op aan de hand van het ID.

    Inloggen:
        Endpoint: /login
        Methode: POST
        Beschrijving: Verifieert de inloggegevens en geeft een token terug.

    Nieuwsbrief downloaden (laatste versie):
        Endpoint: /newsletter/download/latest
        Methode: GET
        Beschrijving: Download de laatste versie van de nieuwsbrief.

    Nieuwsbrief uploaden:
        Endpoint: /newsletter/upload
        Methode: POST
        Beschrijving: Uploadt een nieuwsbrief.

## 11. Overige commando’s
   
1. **Maven clean:**
   - Commando: `mvn clean`
   - Beschrijving: Verwijdert alle tijdelijke bestanden en gegenereerde artefacten, zoals de `target`-map.

2. **Maven install:**
   - Commando: `mvn install`
   - Beschrijving: Installeert het gebouwde artefact in het lokale Maven-repository, zodat het beschikbaar is voor andere projecten.

3. **Maven compile:**
   - Commando: `mvn compile`
   - Beschrijving: Compileert de broncode van het project.

4. **Maven test:**
   - Commando: `mvn test`
   - Beschrijving: Voert alle testcases in het project uit.

5. **Maven package:**
   - Commando: `mvn package`
   - Beschrijving: Verpakt de applicatie in een JAR- of WAR-bestand zonder het in het lokale Maven-repository te installeren.

6. **Starten van de Spring Boot-applicatie:**
   - Commando: `java -jar target/<project-naam>-<versie>.jar`
   - Beschrijving: Start de Spring Boot-applicatie met het gegenereerde JAR-bestand.



### 12. Bouwen en uitvoeren van de applicatie

1. Open een terminal of opdrachtprompt op de doel-pc.
2. Navigeer naar de hoofdmap van het project.
3. Voer het Maven-commando uit om de applicatie te bouwen: `mvn clean package`.
4. Nadat de build succesvol is voltooid, voer je de Spring Boot-applicatie uit met het gegenereerde JAR-bestand: `java -jar target/<project-naam>-versie>.jar`.

Nu zou de Spring Boot-applicatie moeten draaien op de doel-pc zoals geconfigureerd en opgezet volgens de originele installatiehandleiding. Je kunt de API testen met behulp van de beschikbare REST-endpoints en de Postman-collectie. Vergeet niet om eventuele specifieke configuraties aan te passen aan de omgeving van de doel-pc, zoals poorten en database-instellingen.
