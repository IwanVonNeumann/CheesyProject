package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Iwan on 27.04.2014
 */
public class PasswordManager {

    public static byte[] calculateHash(String password) {
        byte[] hash;
        try {
            byte[] passwordBytes = password.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(passwordBytes);
            return hash;
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("No algorithm ex...");
            return null;
        }
    }

    public static boolean passwordIsCorrect(String password, byte[] hash) {
        byte[] pass = calculateHash(password);
        int k = pass.length;
        for (int i = 0; i < k; i++) {
            if (hash[i] != pass[i]) return false;
        }
        return true;
    }
}