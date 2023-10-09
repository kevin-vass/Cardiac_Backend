package DBHelpers;
import Objects.Management;
import java.util.List;
import java.util.Map;

public class ManagementDBHelper {

    public boolean logIn(String email, String password) {
        if (email == null || password == null) {
            return false;
        }

        String tableName = "user";
        String[] columnNames = {"email", "password"};

        List<Map<String, Object>> results = MainDBHelper.getRecords(tableName, columnNames);
        if (results != null) {
            try {
                for (Map<String, Object> row : results) {
                    String userEmail = (String) row.get("email");
                    String storedHashedPassword = (String) row.get("password");

                    if (userEmail.equals(email) && EncAndDec.verifyPassword(password, storedHashedPassword)) {
                        System.out.println("Customer login successful!");
                        return true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error fetching records: " + e.getMessage());
            }
        }

        System.out.println("Error logging in customer.");
        return false;
    }


    public Management getUserByEmail(String email) {
        if (email == null) {
            return null;
        }

        String tableName = "user";
        String[] columnNames = {"email", "password", "name"};

        List<Map<String, Object>> results = MainDBHelper.getRecords(tableName, columnNames);
        if (results != null) {
            try {
                for (Map<String, Object> row : results) {
                    String userEmail  = (String) row.get("email");
                    String password = (String) row.get("password");
                    String name = (String) row.get("name");

                    if (userEmail.equals(email)) {
                        Management management = new Management(name, userEmail, password);
                        return management;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error fetching records: " + e.getMessage());
            }
        }

        System.out.println("Admin not found.");
        return null;
    }

}
