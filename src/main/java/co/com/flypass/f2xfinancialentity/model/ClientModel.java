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
    public static final String BIRTHDAY_REQUIRED_MESSAGE = "Se debe enviar el campo fecha nacimiento";
    public static final String CLIENT_MAJOR_18_YEARS_OLD_MESSAGE = "El cliente debe ser mayor de 18 años";
    public static final String REQUIRED_EMAIL_MESSAGE = "El correo es requerido";
    public static final String INVALID_EMAIL_MESSAGE = "Debe ingresar un correo valido";
    public static final String MANDATORY_NAME_MESSAGE = "El nombre y apellido es obligatorio";
    public static final String INVALID_NAME_MESSAGE = "El nombre y apellido debe tener más de 2 letras";

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
                       String name, String lastname, String email, LocalDate birthday, LocalDateTime creationDate,
                       LocalDateTime modificationDate){
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
        this.creationDate = null == creationDate ? LocalDateTime.now() : creationDate;
        this.modificationDate = null == modificationDate ? LocalDateTime.now() : modificationDate;
    }

    private String generateId(String id) {
        if(null == id){
            return UUID.randomUUID().toString();
        }
        return id;
    }

    public void validateBirthday(LocalDate localDate){
        if(null == localDate){
            throw new InvalidValueException(BIRTHDAY_REQUIRED_MESSAGE);
        }
        if(localDate.plusYears(18).isAfter(LocalDate.now())){
            throw new InvalidValueException(CLIENT_MAJOR_18_YEARS_OLD_MESSAGE);
        }
    }

    public void validateEmail(String email){
        if(null == email){
            throw new MandatoryValueException(REQUIRED_EMAIL_MESSAGE);
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if(!matcher.matches()){
            throw new InvalidValueException(INVALID_EMAIL_MESSAGE);
        }
    }

    public void validateNameLastName(String value){
        if(null == value){
            throw new MandatoryValueException(MANDATORY_NAME_MESSAGE);
        }
        if(value.trim().length() < 3){
            throw new InvalidValueException(INVALID_NAME_MESSAGE);
        }
    }
}
