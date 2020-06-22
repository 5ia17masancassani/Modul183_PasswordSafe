package com.example.PasswordSafe;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Service
public class PasswordService {

    private final byte[] key = "SymmetricPw47111".getBytes();

    public String encryptPassword(String password) {

        System.out.println("1: " + password);

        byte[] dataToSend = password.getBytes();

        System.out.println("2: " + dataToSend);
        Cipher c;
        try {
            c = Cipher.getInstance("AES");
            System.out.println(key);
            SecretKeySpec k = new SecretKeySpec(key, "AES");
            c.init(Cipher.ENCRYPT_MODE, k);
            byte[] encryptedData = c.doFinal(dataToSend);

            System.out.println("3: " + encryptedData);

            String encodedString = Base64.getEncoder().encodeToString(encryptedData);

            System.out.println("4: " + encodedString);

            return encodedString;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decryptPassword(String password){

        System.out.println("5: " + password);
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        System.out.println("6: " + decodedBytes);

        Cipher c;
        try {
            c = Cipher.getInstance("AES");
            System.out.println(key);
            SecretKeySpec k =
                    new SecretKeySpec(key, "AES");
            c.init(Cipher.DECRYPT_MODE, k);
            byte[] data = c.doFinal(decodedBytes);
            System.out.println("8: " + data);
            return data.toString();
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        } catch (NoSuchPaddingException e) {

            e.printStackTrace();
        } catch (InvalidKeyException e) {

            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {

            e.printStackTrace();
        } catch (BadPaddingException e) {

            e.printStackTrace();
        }
        return "test";
    }
}
