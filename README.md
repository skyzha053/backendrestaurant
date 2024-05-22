### Installatiehandleiding voor mijn Spring Boot Applicatie

Welkom bij de installatiehandleiding voor mijn Spring Boot applicatie! Volg deze eenvoudige stappen om de applicatie te installeren en te gebruiken.

#### Vereisten

Voordat je begint, zorg ervoor dat je de volgende vereisten hebt geïnstalleerd:

1. Java Development Kit (JDK) 8 of hoger.
2. Apache Maven.
3. Een SQL-database zoals MySQL of PostgreSQL.

#### Stappen voor Installatie

1. **Clone de Repository:**
   Clone de repository van mijn Spring Boot applicatie vanuit de bron waar deze is gehost, bijvoorbeeld GitHub.

   ```bash
   git clone https://github.com/skyzha053/backendrestaurant.git
   ```

2. **Build de Applicatie:**
   Navigeer naar de hoofdmap van het gekloonde project en bouw de applicatie met Maven.

   ```bash
   cd <project_map>
   mvn clean package
   ```

3. **Configure de Database:**
   Maak een nieuwe database aan in jouw SQL-databaseomgeving en update de databaseconfiguratie in het `application.properties` bestand met de juiste database-URL, gebruikersnaam en wachtwoord.

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/mijn_database
   spring.datasource.username=jouw_gebruikersnaam
   spring.datasource.password=jouw_wachtwoord
   ```

4. **Voer de Applicatie uit:**
   Voer de Spring Boot applicatie uit met behulp van het volgende Maven-commando.

   ```bash
   java -jar target/<applicatie_naam>.jar
   ```

5. **Controleer de Toepassing:**
   Open een webbrowser en ga naar `http://localhost:8080` om te controleren of de applicatie correct is gestart.

6. **Gebruik de Applicatie:**
   Gefeliciteerd! De applicatie is nu geïnstalleerd en klaar voor gebruik. Volg de instructies op het scherm om de gewenste functionaliteit te gebruiken.





7. **overige commando's**

**Maven clean:**
   - Commando: `mvn clean`
   - Beschrijving: Verwijdert alle tijdelijke bestanden en gegenereerde artefacten, zoals de `target`-map.

**Maven install:**
   - Commando: `mvn install`
   - Beschrijving: Installeert het gebouwde artefact in het lokale Maven-repository, zodat het beschikbaar is voor andere projecten.

**Maven compile:**
   - Commando: `mvn compile`
   - Beschrijving: Compileert de broncode van het project.

**Maven test:**
   - Commando: `mvn test`
   - Beschrijving: Voert alle testcases in het project uit.

**Maven package:**
   - Commando: `mvn package`
   - Beschrijving: Verpakt de applicatie in een JAR- of WAR-bestand zonder het in het lokale Maven-repository te installeren.

**Starten van de Spring Boot-applicatie:**
   - Commando: `java -jar target/<project-naam>-<versie>.jar`
   - Beschrijving: Start de Spring Boot-applicatie met het gegenereerde JAR-bestand.


### Testgebruikers:

| Gebruikersnaam | Wachtwoord  | Rol                |
|----------------|-------------|--------------------|
| Remon          | AylaRemon05!| baas               |
| Yente          | Novi123     | restaurantmanager  |
| Emma           | Novi123     | serveerster        |
| Chikit         | Novi123     | Chefkok            |


### REST-endpoints:


| Endpoint                    | Methode | Beschrijving                                | Body                                                                                          |
|-----------------------------|---------|---------------------------------------------|-----------------------------------------------------------------------------------------------|
| /swagger-ui.html            | -       | Swagger UI voor API-documentatie en -testen | -                                                                                             |
| /Login                      | POST    | Inloggen van gebruiker                      | {"userName": "string", "password": "string"}                                                  |
| /users                      | POST    | Aanmaken van gebruiker                      | {"userName": "string", "password": "string", "roles": ["ROLE_SERVEERSTERS"]}                  |
| /newsletter/upload          | POST    | Uploaden van nieuwsbrief                    | form-data                                                                                     |
| /newsletter/download/latest| POST    | Downloaden van nieuwsbrief                  | -                                                                                             |
| /allergies/{{id}}           | PUT     | Updaten van allergie                        | {"id": "1", "naam": "Nieuwe Allergie Naam"}                                                  |
| /allergies                  | GET     | Opvragen van alle allergieën                | -                                                                                             |
| /allergies/{{id}}           | GET     | Opvragen van allergie per id                | -                                                                                             |
| /bestelling/{{id}}          | GET     | Opvragen van bestelling                     | -                                                                                             |
| /bestelling/plaatsen        | POST    | Bestelling plaatsen                         | {"tafelNaam": "pieter", "besteldeMenuItems": [{"itemNaam": "Mediterraans Landbrood", "hoeveelheid": 3}], "besteldeDranken": [{"itemNaam": "Cola", "hoeveelheid": 1}, {"itemNaam": "Fanta", "hoeveelheid": 2}]} |
| /bestelling/tafel-verplaatsen| PUT    | Tafel verplaatsen                           | {"tafelNaam": "string", "nieuweNaam": "string"}                                               |
| /bestelling/update-bestelling| PUT    | Bestelling veranderen                       | {"tafelNaam": "string", "itemName": "Mediterraans Landbrood", "hoeveelheid": 100}             |
| /bonnen/{{id}}              | GET     | Vraag bon per tafelId                      | -                                                                                             |
| /bonnen                     | GET     | Vraag alle bonnen op                        | -                                                                                             |
| /bonnen/{{id}}/betaald      | GET     | Verander bon na betaald                     | -                                                                                             |
| /drinks                     | GET     | Vraag alle dranken op                       | -                                                                                             |
| /drinks                     | POST    | Voeg drank toe aan database                 | {"naam": "string", "type": "string", "prijs": 0}                                              |
| /drinks/{{id}}              | DEL     | Verwijder drank uit database                | -                                                                                             |
| /drinks/{{id}}              | PUT     | Bewerk drank op basis van id                | {"id": 1, "naam": "fanta", "prijs": 0}                                                       |
| /drinks/{{id}}              | GET     | Vraag drank aan op id                       | -                                                                                             |
| /menuItems/all/checkAllergies| GET    | Vraag allergie op in menuitem                | {"menuItemId": 1, "allergens": ["gluten"]}                                                   |
| /menuItems/all              | GET     | Vraag alle menu's op                        | -                                                                                             |
| /menuItems/all/{{id}}/block| PUT     | Blokkeer menu item per id                   | -                                                                                             |
| /menuItems/all/{{id}}/unblock| PUT   | Deblokkeer menu item per id                 | -                                                                                             |
| /menuItems/all/{{id}}       | DEL     | Delete menu item op id                      | -                                                                                             |
| /menuItems/all/{{id}}       | GET     | Vraag menu op op basis van id               | -                                                                                             |
| /reservations/deleteByName/{{name}}| DEL| Verwijder reservering                       | -                                                                                             |
| /reservations               | POST    | Plaats reservering                          | {"name": "piet", "numberOfPersons": 0, "reservationDate": "2024-03-27", "allergies": "string", "comments": "string", "phoneNumber": "string", "emailAddress": "string", "bedrijfsnaam": "string"} |
| /reservations               | GET     | Ophalen van alle reserveringen              | -                                                                                             |
| /reservations/updateReservationByName/{{name}}| PUT| Updaten van een bestaande reservering      | {"id": 0, "name": "string", "numberOfPersons": 0, "reservationDate": "2024-03-27", "allergies": "string", "comments": "string", "phoneNumber": "string", "emailAddress": "string", "bedrijfsnaam": "string"} |



#### Opmerkingen:

- Zorg ervoor dat de poort 8080 op jouw systeem beschikbaar is voor de Spring Boot applicatie.
- Voor productie-omgevingen wordt aangeraden om de applicatie te implementeren op een veilige en schaalbare infrastructuur, zoals een cloudplatform.

Gebruik onderstaande link voor het downloaden van de postman collectie
https://www.mediafire.com/file/bq3h15gt73usof3/Backend.postman_collectionv3/file


