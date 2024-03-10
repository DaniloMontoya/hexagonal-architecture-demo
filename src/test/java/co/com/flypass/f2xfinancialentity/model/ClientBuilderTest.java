package co.com.flypass.f2xfinancialentity.model;

import co.com.flypass.f2xfinancialentity.enums.IdentificationType;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 3:40 PM
 **/
public class ClientBuilderTest {
    private String id = "1";
    private IdentificationType identificationType = IdentificationType.CC;
    private String identificationNumber = "1";
    private String name = "Nombre Valido";
    private String lastname = "Apellido Valido";
    private String email = "test@test.com";
    private LocalDate birthday = LocalDate.of(2000, 1, 1);
    private LocalDateTime creationDate = LocalDateTime.now();

    private LocalDateTime modificationDate = LocalDateTime.now();

    public ClientModel build(){
        return new ClientModel(
                id,
                identificationType,
                identificationNumber,
                name,
                lastname,
                email,
                birthday,
                creationDate,
                modificationDate
        );
    }

    public ClientBuilderTest withId(String id){
        this.id = id;
        return this;
    }

    public ClientBuilderTest withBirthday(LocalDate birthday){
        this.birthday = birthday;
        return this;
    }

    public ClientBuilderTest withEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilderTest withName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilderTest withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
}
