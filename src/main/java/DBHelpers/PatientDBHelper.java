package DBHelpers;

import Objects.Patient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PatientDBHelper {

    public boolean addPatient(Patient patient) {


        String tableName = "patient";
        String[] columnNames = {"name", "address","telephone","birthdate","gender","created_by","created_on"};
        String[] columnNames1 = {"patient_id"};

        List<Map<String, Object>> results = MainDBHelper.getRecords(tableName, columnNames1);
        if (results != null) {
            try {
                for (Map<String, Object> row : results) {
                    String patient_id = (String) row.get("patient_id");

                    if (patient_id.equals(patient.getPatient_id())) {
                        System.out.println("Error Inserting Patient: Patient already exists.");
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
        patient.setCrated_on(formattedDate);
        Object[] values = {patient.getName(), patient.getAddress(), patient.getTelephone(), patient.getBirthdate(), patient.getGender(), patient.getCrated_by(), patient.getCrated_on()};

        boolean success = MainDBHelper.insertRecords(tableName, columnNames, values);

        if (success) {
            System.out.println("Patient registration successful!");
            return true;
        } else {
            System.out.println("Error registering Patient.");
            return false;
        }
    }

}
