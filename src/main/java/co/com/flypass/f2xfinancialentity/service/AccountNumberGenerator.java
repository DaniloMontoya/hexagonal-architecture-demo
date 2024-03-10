package co.com.flypass.f2xfinancialentity.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 7:43 AM
 **/
@Service
public class AccountNumberGenerator {
    public static final String INITIAL_SAVING_NUMBER = "53";
    public static final String INITIAL_CHECKING_NUMBER = "33";
    private final Random random = new Random();

    public static final int MAX_NUMBER = 9;

    public String generateSavingNumber(){
        StringBuilder builder = new StringBuilder(INITIAL_SAVING_NUMBER);
        for (int i = 0; i < 8; i++) {
            builder.append(generateRandomNumber());
        }
        return builder.toString();
    }
    public String generateCheckingNumber(){
        StringBuilder builder = new StringBuilder(INITIAL_CHECKING_NUMBER);
        for (int i = 0; i < 8; i++) {
            builder.append(generateRandomNumber());
        }
        return builder.toString();
    }

    public int generateRandomNumber(){
        return random.nextInt(MAX_NUMBER);
    }
}
