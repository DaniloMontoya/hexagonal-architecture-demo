package co.com.flypass.f2xfinancialentity.model;

import co.com.flypass.f2xfinancialentity.infrastructure.exception.InvalidValueException;
import co.com.flypass.f2xfinancialentity.infrastructure.exception.MandatoryValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 3:22 PM
 **/
class ClientModelTest {

    @Test
    void isValidateBirthday() {
        var builder = new ClientBuilderTest()
                .withBirthday(LocalDate.of(2000, 1, 1));
        Assertions.assertNotNull(builder.build());
    }

    @Test
    void notValidateBirthday() {
        var builder = new ClientBuilderTest()
                .withBirthday(LocalDate.now().minusYears(10));
        Assertions.assertThrows(InvalidValueException.class, builder::build);
    }

    @Test
    void nullEmail() {
        var builder = new ClientBuilderTest()
                .withEmail(null);
        Assertions.assertThrows(MandatoryValueException.class, builder::build);
    }

    @Test
    void invalidEmail() {
        var builder = new ClientBuilderTest()
                .withEmail("invalid.email");
        Assertions.assertThrows(InvalidValueException.class, builder::build);
    }

    @Test
    void validateEmail() {
        var builder = new ClientBuilderTest()
                .withEmail("valid.email@test.com");
        Assertions.assertNotNull(builder.build());
        Assertions.assertEquals("valid.email@test.com", builder.build().getEmail());
    }

    @Test
    void nullName() {
        var builder = new ClientBuilderTest()
                .withName(null);
        Assertions.assertThrows(MandatoryValueException.class, builder::build);
    }

    @Test
    void nullLastname() {
        var builder = new ClientBuilderTest()
                .withLastname(null);
        Assertions.assertThrows(MandatoryValueException.class, builder::build);
    }

    @Test
    void invalidName() {
        var builder = new ClientBuilderTest()
                .withName("Na");
        Assertions.assertThrows(InvalidValueException.class, builder::build);
    }

    @Test
    void invalidLastname() {
        var builder = new ClientBuilderTest()
                .withLastname("Na");
        Assertions.assertThrows(InvalidValueException.class, builder::build);
    }

    @Test
    void validateName() {
        var builder = new ClientBuilderTest()
                .withName("Andres");
        Assertions.assertNotNull(builder.build());
        Assertions.assertEquals("Andres", builder.build().getName());
    }

    @Test
    void validateLastName() {
        var builder = new ClientBuilderTest()
                .withLastname("Cortes");
        Assertions.assertNotNull(builder.build());
        Assertions.assertEquals("Cortes", builder.build().getLastname());

    }
}
