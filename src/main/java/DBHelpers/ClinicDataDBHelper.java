package DBHelpers;

import Objects.ClinicData;
import Objects.Patient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ClinicDataDBHelper {

    static List<ClinicData> clinicDataList = new ArrayList<>();
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

    public static List<ClinicData> getClinicData() {
        String tableName = "clinic_data";
        String[] columnNames = {"clinic_id","patient_id", "age", "sex", "chest_pain", "resting_bp", "cholesterol", "fasting_bs", "resting_ecg", "max_hr", "exercise_angina", "old_peak", "st_slope", "created_on", "created_by"};


        List<Map<String, Object>> results = MainDBHelper.getRecords(tableName, columnNames);
        System.out.println("Result set is null: " + (results == null));


        if (results != null) {
            try {
                clinicDataList.clear();
                for (Map<String, Object> row : results) {
                    int clinic_id = (int) row.get("clinic_id");
                    int patient_id = (int) row.get("patient_id");
                    String age = (String) row.get("age");
                    String sex = (String) row.get("sex");
                    String chest_pain = (String) row.get("chest_pain");
                    Double resting_bp = (Double) row.get("resting_bp");
                    Double cholesterol = (Double) row.get("cholesterol");
                    String fasting_bs = (String) row.get("fasting_bs");
                    String resting_ecg = (String) row.get("resting_ecg");
                    Double max_hr = (Double) row.get("max_hr");
                    String exercise_angina = (String) row.get("exercise_angina");
                    Double old_peak = (Double) row.get("old_peak");
                    String st_slope = (String) row.get("st_slope");

                    ClinicData clinicData = new ClinicData(clinic_id,patient_id,age,sex,chest_pain,resting_bp,cholesterol,fasting_bs,resting_ecg,max_hr,exercise_angina,old_peak,st_slope);
                    clinicDataList.add(clinicData);
//                    System.out.println(customer.getId() + ", " + customer.getFirstname() + ", " + customer.getLastname());

                }
            } catch (Exception e) {
                System.out.println("Error fetching records: " + e.getMessage());
            }
        } else {
            System.out.println("Error fetching records.");
        }
        return clinicDataList;
    }

    public static boolean deleteClinicData(int id) {
        String tableName = "clinic_data";
        String condition = "clinic_id = " + id;

        boolean success = MainDBHelper.deleteRecord(tableName, condition);
        if (success) {
            System.out.println("Clinic record deleted successfully!");
            return true;
        } else {
            System.out.println("Error deleting clinic record.");
            return false;
        }
    }

    public static boolean updateClinicRecord(ClinicData clinicData) {
        String tableName = "clinic_data";
        String[] columnNames = {"chest_pain", "resting_bp", "cholesterol", "fasting_bs", "resting_ecg", "max_hr", "exercise_angina", "old_peak", "st_slope"};
        Object[] values = {clinicData.getChest_pain(), clinicData.getResting_bp(), clinicData.getCholesterol(), clinicData.getFasting_bs(), clinicData.getResting_ecg(), clinicData.getMax_hr(), clinicData.getExercise_angina(),clinicData.getOld_peak(),clinicData.getSt_slope()};
        String condition = "clinic_id = " + clinicData.getClinic_id();

        return MainDBHelper.updateRecord(tableName, columnNames, values, condition);
    }

}
