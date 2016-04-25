package org.javarush.bigtask;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Alexey on 17.03.2016.
 */
public class Helper {
    public static String generateRandomString(){
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    public static void printMessage(String message){
        System.out.println(message);
    }
}
