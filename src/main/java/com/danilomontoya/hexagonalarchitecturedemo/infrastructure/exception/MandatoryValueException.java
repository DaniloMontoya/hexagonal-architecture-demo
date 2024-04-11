package com.danilomontoya.hexagonalarchitecturedemo.infrastructure.exception;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 6:19 PM
 **/
public class MandatoryValueException extends RuntimeException{
    public MandatoryValueException(String message) {
        super(message);
    }
}
