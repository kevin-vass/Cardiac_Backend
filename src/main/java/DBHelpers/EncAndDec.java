package DBHelpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncAndDec {

//    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
//        // Combine the password and salt
//        String combined = password + salt;
//
//        // Create a SHA-256 message digest
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//
//        // Update the digest with the combined bytes
//        messageDigest.update(combined.getBytes());
//
//        // Generate the hashed password as a byte array
//        byte[] hashedPasswordBytes = messageDigest.digest();
//
//        // Convert the byte array to a base64-encoded string
//        String hashedPassword = Base64.getEncoder().encodeToString(hashedPasswordBytes);
//
//        return hashedPassword;
//    }

//    public static String generateSalt() {
//        SecureRandom random = new SecureRandom();
//        byte[] saltBytes = new byte[16];
//        random.nextBytes(saltBytes);
//        return Base64.getEncoder().encodeToString(saltBytes);
//    }

    public static boolean verifyPassword(String inputPassword, String storedHashedPassword) {
        String hashedInputPassword = generateSHA256Hash(inputPassword);
        return hashedInputPassword.equals(storedHashedPassword);
    }

    public static String generateSHA256Hash(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convert the input String to bytes
            byte[] inputData = input.getBytes();

            // Calculate the hash value
            byte[] hashBytes = md.digest(inputData);

            // Convert the byte array to a hexadecimal String representation
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Return the hexadecimal String, which is the SHA-256 hash of the input
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

