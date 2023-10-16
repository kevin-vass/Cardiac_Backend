package DBHelpers;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainDBHelperTest {

    @Test
    public void testInsertRecords() {
        String tableName = "user";
        String[] columnNames = {"email", "password","user_type","name"};
        Object[] values = {"test88@test","test88", "user","test88"};

        assertTrue(MainDBHelper.insertRecords(tableName, columnNames, values));
    }

    @Test
    public void testUpdateRecord() {
        String tableName = "user";
        String[] columnNames = {"email", "password","user_type","name"};
        Object[] values = {"test888@test","","",""};
        String condition = "id = 6";

        assertTrue(MainDBHelper.updateRecord(tableName, columnNames, values, condition));
    }

    @Test
    public void testGetRecords() {
        String tableName = "user";
        String[] columnNames = {"email", "password","user_type","name"};
        List<Map<String, Object>> records = MainDBHelper.getRecords(tableName, columnNames);

        assertNotNull(records);
        assertFalse(records.isEmpty());
    }

    @Test
    public void testDeleteRecord() {
        String tableName = "user";
        String condition = "id = '6'";

        assertTrue(MainDBHelper.deleteRecord(tableName, condition));
    }

    @Test
    public void testGetRecordsByCondition() {
        String tableName = "user";
        String[] columnNames = {"email", "password","user_type","name"};
        String condition = "email = 'test@test'";
        List<Map<String, Object>> records = MainDBHelper.getRecordsByCondition(tableName, columnNames, condition);

        assertNotNull(records);
        assertFalse(records.isEmpty());
    }
}