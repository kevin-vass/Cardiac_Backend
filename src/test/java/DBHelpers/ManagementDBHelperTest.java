package DBHelpers;

import Objects.Management;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagementDBHelperTest {

    @Test
    public void testLogIn() {
        ManagementDBHelper dbHelper = new ManagementDBHelper();

        String userEmail = "test@test";
        String password = "test";

        assertTrue(dbHelper.logIn(userEmail, password));
    }

    @Test
    public void testGetUserByEmail() {
        ManagementDBHelper dbHelper = new ManagementDBHelper();

        String userEmail = "test@test";
        Management user = dbHelper.getUserByEmail(userEmail);

        assertNotNull(user);
        assertEquals(userEmail, user.getEmail());
    }

    @Test
    public void testAddUser() {
        ManagementDBHelper dbHelper = new ManagementDBHelper();

        // Create a test Management object
        Management newUser = new Management("unittest", "test@unit", "test");

        assertTrue(dbHelper.addUser(newUser));

    }

    @Test
    public void testGetUsers() {
        ManagementDBHelper dbHelper = new ManagementDBHelper();

        // Fetch all users
        List<Management> users = dbHelper.getUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void testDeleteUser() {
        ManagementDBHelper dbHelper = new ManagementDBHelper();

        int userIdToDelete = 7;

        assertTrue(dbHelper.deleteUser(userIdToDelete));
    }

    @Test
    public void testUpdateUser() {
        ManagementDBHelper dbHelper = new ManagementDBHelper();

        int userIdToUpdate = 7;
        String newName = "Updated Name";
        String newEmail = "updated@example.com";

        Management updatedUser = new Management();
        updatedUser.setId(userIdToUpdate);
        updatedUser.setName(newName);
        updatedUser.setEmail(newEmail);

        assertTrue(dbHelper.updateUser(updatedUser));
    }
}