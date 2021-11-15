package dataProviders;

import  org.apache.commons.codec.binary.Base64;
public class PasswordEncryption {

    /**
     * Following method is used to Decode the generated string to password
     */
    public static String Decode(String value)
    {
        byte[] decoded = Base64.decodeBase64(value);
        return new String(decoded);
    }
}