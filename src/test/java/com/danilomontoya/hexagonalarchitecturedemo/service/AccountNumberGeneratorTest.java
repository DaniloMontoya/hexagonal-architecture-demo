package com.danilomontoya.hexagonalarchitecturedemo.service;

import com.danilomontoya.hexagonalarchitecturedemo.application.service.AccountNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 8:17 AM
 **/
class AccountNumberGeneratorTest {
    AccountNumberGenerator accountNumberGenerator = new AccountNumberGenerator();
    @Test
    void generateSavingNumber() {
        String accountNumber = accountNumberGenerator.generateSavingNumber();
        Assertions.assertNotNull(accountNumber);
        Assertions.assertEquals(10, accountNumber.length());
        Assertions.assertTrue(accountNumber.startsWith("53"));
    }

    @Test
    void generateCheckingNumber() {
        String accountNumber = accountNumberGenerator.generateCheckingNumber();
        Assertions.assertNotNull(accountNumber);
        Assertions.assertEquals(10, accountNumber.length());
        Assertions.assertTrue(accountNumber.startsWith("33"));
    }

    @Test
    void generateRandomNumber() {
         int random = accountNumberGenerator.generateRandomNumber();
         Assertions.assertTrue(random >= 0);
         Assertions.assertTrue(random <= 9);
    }
}
