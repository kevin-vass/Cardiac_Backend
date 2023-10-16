package DBHelpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncAndDecTest {

    @Test
    public void testVerifyPasswordWithCorrectPassword() {

        String inputPassword = "myPassword";
        String storedHashedPassword = EncAndDec.generateSHA256Hash(inputPassword);

        assertTrue(EncAndDec.verifyPassword(inputPassword, storedHashedPassword));
    }

    @Test
    public void testVerifyPasswordWithIncorrectPassword() {
        String inputPassword = "myPassword";
        String storedHashedPassword = EncAndDec.generateSHA256Hash("anotherPassword");

        assertFalse(EncAndDec.verifyPassword(inputPassword, storedHashedPassword));
    }

    @Test
    public void testGenerateSHA256Hash() {
        String input = "admin";
        String expectedHash = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";

        assertEquals(expectedHash, EncAndDec.generateSHA256Hash(input));
    }
}