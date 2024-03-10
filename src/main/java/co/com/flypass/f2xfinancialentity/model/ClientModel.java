package co.com.flypass.f2xfinancialentity.model;

import co.com.flypass.f2xfinancialentity.enums.IdentificationType;
import co.com.flypass.f2xfinancialentity.exception.InvalidValueException;
import co.com.flypass.f2xfinancialentity.exception.MandatoryValueException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 5:50 PM
 **/
@Getter
public class ClientModel {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private String id;
    private IdentificationType identificationType;


    private String identificationNumber;
    private String name;
    private String lastname;
    private String email;
    private LocalDate birthday;
    @Setter
    private LocalDateTime creationDate;
    @Setter
    private LocalDateTime modificationDate;

    public ClientModel(String id, IdentificationType identificationType, String identificationNumber,
                       String name, String lastname, String email, LocalDate birthday, LocalDateTime creationDate){
        validateBirthday(birthday);
        validateEmail(email);
        validateNameLastName(name);
        validateNameLastName(lastname);
        this.id = generateId(id);
        this.birthday = birthday;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.creationDate = creationDate;
        this.modificationDate = LocalDateTime.now();
    }

    private String generateId(String id) {
        if(null == id){
            return UUID.randomUUID().toString();
        }
        return id;
    }

    public void validateBirthday(LocalDate localDate){
        if(null == localDate){
            throw new InvalidValueException("Se debe enviar el campo fecha nacimiento");
        }
        if(localDate.plusYears(18).isAfter(LocalDate.now())){
            throw new InvalidValueException("El cliente debe ser mayor de 18 años");
        }
    }

    public void validateEmail(String email){
        if(null == email){
            throw new MandatoryValueException("El correo es requerido");
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if(!matcher.matches()){
            throw new InvalidValueException("Debe ingresar un correo valido");
        }
    }

    public void validateNameLastName(String value){
        if(null == value){
            throw new MandatoryValueException("El nombre y apellido es obligatorio");
        }
        if(value.trim().length() < 2){
            throw new InvalidValueException("El nombre y apellido debe tener mas de 2 letras");
        }
    }
}
