package co.com.flypass.f2xfinancialentity.exception.handler;

import lombok.Data;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 11:42 PM
 **/
@Data
public class Error {
    private String exceptionName;
    private String message;

    public Error(String exceptionName, String message) {
        this.exceptionName = exceptionName;
        this.message = message;
    }
}
