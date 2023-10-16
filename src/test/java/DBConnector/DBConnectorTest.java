package DBConnector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectorTest {

    @Test
    void getConnection() {
        try (Connection connection = DBConnector.getConnection()) {
            Assertions.assertNotNull(connection);
            Assertions.assertFalse(connection.isClosed());
        } catch (SQLException e) {
            Assertions.fail("An exception occurred while getting a connection: " + e.getMessage());
        }
    }
}