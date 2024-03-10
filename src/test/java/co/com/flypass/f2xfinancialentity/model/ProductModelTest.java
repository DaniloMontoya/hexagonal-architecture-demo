package co.com.flypass.f2xfinancialentity.model;

import co.com.flypass.f2xfinancialentity.enums.AccountType;
import co.com.flypass.f2xfinancialentity.exception.InvalidValueException;
import co.com.flypass.f2xfinancialentity.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.exception.NotAllowedOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 3:59 PM
 **/
class ProductModelTest {

    @Test
    void negativeSavingBalance() {
        var builder = new ProductBuilderTest()
                .withType(AccountType.SAVING)
                .withBalance(-10);
        Assertions.assertThrows(NotAllowedOperationException.class, builder::build);
    }

    @Test
    void negativeCheckingBalance() {
        var builder = new ProductBuilderTest()
                .withBalance(-10)
                .withType(AccountType.CHECKING);
        Assertions.assertNotNull(builder.build());
        Assertions.assertEquals(-10, builder.build().getBalance());
    }

    @Test
    void validateSavingBalance() {
        var builder = new ProductBuilderTest()
                .withType(AccountType.SAVING)
                .withBalance(1000);
        Assertions.assertNotNull(builder.build());
        Assertions.assertEquals(1000, builder.build().getBalance());
    }


    @Test
    void validateAccountNumber() {
        var builder = new ProductBuilderTest()
                .withType(AccountType.SAVING)
                .withAccountNumber("5348218446");
        Assertions.assertNotNull(builder.build());
        Assertions.assertEquals("5348218446", builder.build().getAccountNumber());
    }

    @Test
    void invalidAccountNumber() {
        var builder = new ProductBuilderTest()
                .withAccountNumber("534821844");
        Assertions.assertThrows(InvalidValueException.class, builder::build);

    }

    @Test
    void nullAccountNumber() {
        var builder = new ProductBuilderTest()
                .withAccountNumber(null);
        Assertions.assertThrows(MandatoryValueException.class, builder::build);

    }
}
