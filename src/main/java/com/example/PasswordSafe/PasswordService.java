package com.example.PasswordSafe;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Service
public class PasswordService {

    private Key secretKeySpec = new SecretKeySpec( "pizza67890123456".getBytes(), "AES" );;

    public String encryptString(String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(content.getBytes());

        String base64String = Base64.getEncoder().encodeToString(encrypted);

        return base64String;
    }

    public String decryptString(String data)  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte [] base64Bytes = Base64.getDecoder().decode(data);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decrypted = cipher.doFinal(base64Bytes);
        return new String(decrypted);
    }
}
