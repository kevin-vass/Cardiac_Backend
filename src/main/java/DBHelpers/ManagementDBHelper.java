package DBHelpers;

import Objects.Management;
import Objects.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ManagementDBHelper {

    static List<Management> users = new ArrayList<>();

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
                    String userEmail = (String) row.get("email");
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

    public boolean addUser(Management management) {

        String hashPassword = null;
        String tableName = "user";
        String[] columnNames = {"email", "password", "user_type", "name"};
        String[] columnNames1 = {"email"};

        List<Map<String, Object>> results = MainDBHelper.getRecords(tableName, columnNames1);
        if (results != null) {
            try {
                for (Map<String, Object> row : results) {
                    String email = (String) row.get("email");

                    if (email.equals(management.getEmail())) {
                        System.out.println("Error Inserting User: User already exists.");
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error fetching records: " + e.getMessage());
            }
        }
        try {
            hashPassword = EncAndDec.generateSHA256Hash(management.getPassword());
        } catch (Exception e) {
            System.out.println("Error while hashing the password " + e.getMessage());
        }

        Object[] values = {management.getEmail(), hashPassword, "user", management.getName()};

        boolean success = MainDBHelper.insertRecords(tableName, columnNames, values);

        if (success) {
            System.out.println("Patient registration successful!");
            return true;
        } else {
            System.out.println("Error registering Patient.");
            return false;
        }
    }

    public static List<Management> getUsers() {
        String tableName = "user";
        String[] columnNames = {"id", "email", "password", "name"};
        String condition = "user_type = 'user'";


        List<Map<String, Object>> results = MainDBHelper.getRecordsByCondition(tableName, columnNames, condition);
        System.out.println("Result set is null: " + (results == null));


        if (results != null) {
            try {
                users.clear();
                for (Map<String, Object> row : results) {
                    int id = (int) row.get("id");
                    String email = (String) row.get("email");
                    String password = (String) row.get("password");
                    String name = (String) row.get("name");

                    Management management = new Management(id, email, password, name);
                    users.add(management);
//                    System.out.println(customer.getId() + ", " + customer.getFirstname() + ", " + customer.getLastname());

                }
            } catch (Exception e) {
                System.out.println("Error fetching records: " + e.getMessage());
            }
        } else {
            System.out.println("Error fetching records.");
        }
        return users;
    }

    public static boolean deleteUser(int id) {
        String tableName = "user";
        String condition = "id = " + id;

        boolean success = MainDBHelper.deleteRecord(tableName, condition);
        if (success) {
            System.out.println("User deleted successfully!");
            return true;
        } else {
            System.out.println("Error deleting user.");
            return false;
        }
    }

    public static boolean updateUser(Management management) {
        String tableName = "user";
        String[] columnNames = {"email", "name"};
        Object[] values = {management.getEmail(), management.getName()};
        String condition = "id = " + management.getId();

        return MainDBHelper.updateRecord(tableName, columnNames, values, condition);
    }

}
