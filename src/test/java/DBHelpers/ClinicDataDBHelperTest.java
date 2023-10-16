package DBHelpers;

import Objects.ClinicData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicDataDBHelperTest {

    @Test
    void addClinicData() {
        ClinicDataDBHelper dbHelper = new ClinicDataDBHelper();
        ClinicData clinicData = new ClinicData(
                6,     // patient_id
                "50",  // age
                "Male",  // sex
                "ASY",  // chest_pain
                180.0,  // resting_bp
                256.0,  // cholesterol
                "1",    // fasting_bs
                "LVH",  // resting_ecg
                120.0,  // max_hr
                "N",    // exercise_angina
                2.0,    // old_peak
                "Flat"  // st_slope
        );


        boolean result = dbHelper.addClinicData(clinicData);

        assertTrue(result);
    }

    @Test
    void getClinicData() {
        ClinicDataDBHelper dbHelper = new ClinicDataDBHelper();
        // Assuming you have some test data already in the database

        List<ClinicData> clinicDataList = dbHelper.getClinicData();

        assertNotNull(clinicDataList);
        assertTrue(clinicDataList.size() > 0);
    }

    @Test
    void deleteClinicData() {
        ClinicDataDBHelper dbHelper = new ClinicDataDBHelper();
        // Assuming you have a valid clinic ID to delete

        int clinicIdToDelete = 5;
        boolean result = dbHelper.deleteClinicData(clinicIdToDelete);

        assertTrue(result);
    }

    @Test
    void updateClinicRecord() {
        ClinicDataDBHelper dbHelper = new ClinicDataDBHelper();
        // Assuming you have a valid `ClinicData` object with updated data

        ClinicData updatedClinicData = new ClinicData(
                3,
                5,     // patient_id
                "50",  // age
                "Male",  // sex
                "ASY",  // chest_pain
                182.0,  // resting_bp
                256.0,  // cholesterol
                "1",    // fasting_bs
                "LVH",  // resting_ecg
                120.0,  // max_hr
                "N",    // exercise_angina
                2.0,    // old_peak
                "Flat"  // st_slope
        );
        boolean result = dbHelper.updateClinicRecord(updatedClinicData);

        assertTrue(result);
    }
}