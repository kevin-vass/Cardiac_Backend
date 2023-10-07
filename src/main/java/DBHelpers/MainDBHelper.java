package DBHelpers;

import DBConnector.DBConnector;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainDBHelper {

    public static boolean insertRecords(String tableName, String[] columnNames, Object[] values){

        String sql = "INSERT INTO " + tableName + "(";
        for (int i = 0; i < columnNames.length; i++) {
            sql += columnNames[i];
            if (i < columnNames.length - 1) {
                sql += ",";
            }
        }
        sql += ") VALUES (";
        for (int i = 0; i < values.length; i++) {
            sql += "?";
            if (i < values.length - 1) {
                sql += ",";
            }
        }
        sql += ")";

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting record: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateRecord(String tableName, String[] columnNames, Object[] values, String condition) {
        String sql = "UPDATE " + tableName + " SET ";
        for (int i = 0; i < columnNames.length; i++) {
            sql += columnNames[i] + "=?";
            if (i < columnNames.length - 1) {
                sql += ",";
            }
        }
        sql += " WHERE " + condition;

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);

            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
            return false;
        }
    }

    public static List<Map<String, Object>> getRecords(String tableName, String @NotNull [] columnNames) {
        String sql = "SELECT ";
        for (int i = 0; i < columnNames.length; i++) {
            sql += columnNames[i];
            if (i < columnNames.length - 1) {
                sql += ",";
            }
        }
        sql += " FROM " + tableName;
//        if (condition != null) {
//            sql += " WHERE " + condition;
//        }

        try (Connection conn = DBConnector.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Map<String, Object>> results = new ArrayList<>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    row.put(columnName, columnValue);
                }
                results.add(row);
            }


            return results;
        } catch (SQLException e) {
            System.out.println("Error fetching records: " + e.getMessage());
            return null;
        }
    }

    public static boolean deleteRecord(String tableName, String condition) {
        String sql = "DELETE FROM " + tableName + " WHERE " + condition;

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
            return false;
        }
    }

    public static List<Map<String, Object>> getRecordsByCondition(String tableName, String @NotNull [] columnNames, String condition) {
        String sql = "SELECT ";
        for (int i = 0; i < columnNames.length; i++) {
            sql += columnNames[i];
            if (i < columnNames.length - 1) {
                sql += ",";
            }
        }
        sql += " FROM " + tableName;
        if (condition != null) {
            sql += " WHERE " + condition;
        }

        try (Connection conn = DBConnector.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Map<String, Object>> results = new ArrayList<>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numColumns = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    row.put(columnName, columnValue);
                }
                results.add(row);
            }


            return results;
        } catch (SQLException e) {
            System.out.println("Error fetching records: " + e.getMessage());
            return null;
        }
    }

}
