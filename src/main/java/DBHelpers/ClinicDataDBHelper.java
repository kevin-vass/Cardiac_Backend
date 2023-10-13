package DBHelpers;

import Objects.ClinicData;
import Objects.Patient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ClinicDataDBHelper {

    public boolean addClinicData(ClinicData clinicData) {


        String tableName = "clinic_data";
        String[] columnNames = {"patient_id", "age", "sex", "chest_pain", "resting_bp", "cholesterol", "fasting_bs", "resting_ecg", "max_hr", "exercise_angina", "old_peak", "st_slope", "created_on", "created_by"};
        String[] columnNames1 = {"patient_id"};

        List<Map<String, Object>> results = MainDBHelper.getRecords(tableName, columnNames1);
        if (results != null) {
            try {
                for (Map<String, Object> row : results) {
                    Integer patient_id = (Integer) row.get("patient_id");

                    if (patient_id.equals(clinicData.getPatient_id())) {
                        System.out.println("Error Inserting Data: Data already exists.");
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error fetching records: " + e.getMessage());
            }
        }
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        clinicData.setCreated_on(formattedDate);
        Object[] values = {clinicData.getPatient_id(), clinicData.getAge(), clinicData.getSex(), clinicData.getChest_pain(), clinicData.getResting_bp(), clinicData.getCholesterol(), clinicData.getFasting_bs(), clinicData.getResting_ecg(), clinicData.getMax_hr(), clinicData.getExercise_angina(), clinicData.getOld_peak(), clinicData.getSt_slope(), clinicData.getCreated_on(), clinicData.getCreated_by()};

        boolean success = MainDBHelper.insertRecords(tableName, columnNames, values);

        if (success) {
            System.out.println("Clinic Record registration successful!");
            return true;
        } else {
            System.out.println("Error registering clinic record.");
            return false;
        }
    }



}
