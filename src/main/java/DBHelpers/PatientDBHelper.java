package DBHelpers;

import Objects.Management;
import Objects.Patient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PatientDBHelper {

    static List<Patient> patients = new ArrayList<>();

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

    public static List<Patient> getPatients() {
        String tableName = "patient";
        String[] columnNames = {"patient_id", "name", "address", "telephone","birthdate","gender"};


        List<Map<String, Object>> results = MainDBHelper.getRecords(tableName, columnNames);
        System.out.println("Result set is null: " + (results == null));


        if (results != null) {
            try {
                patients.clear();
                for (Map<String, Object> row : results) {
                    int id = (int) row.get("patient_id");
                    String name = (String) row.get("name");
                    String address = (String) row.get("address");
                    int telephone = (int) row.get("telephone");
                    String birthdatestored = (String) row.get("birthdate");
                    String gender = (String) row.get("gender");

                    LocalDate birthdate = LocalDate.parse(birthdatestored);
                    LocalDate currentDate = LocalDate.now();
                    int ageBetween = Period.between(birthdate, currentDate).getYears();

                    String age = Integer.toString(ageBetween);

                    Patient patient = new Patient(id, name, address, telephone, age, gender);
                    patients.add(patient);
//                    System.out.println(customer.getId() + ", " + customer.getFirstname() + ", " + customer.getLastname());

                }
            } catch (Exception e) {
                System.out.println("Error fetching records: " + e.getMessage());
            }
        } else {
            System.out.println("Error fetching records.");
        }
        return patients;
    }

    public static boolean deletePatient(int id) {
        String tableName = "patient";
        String condition = "patient_id = " + id;

        boolean success = MainDBHelper.deleteRecord(tableName, condition);
        if (success) {
            System.out.println("Patient deleted successfully!");
            return true;
        } else {
            System.out.println("Error deleting patient.");
            return false;
        }
    }

    public static boolean updatePatient(Patient patient) {
        String tableName = "patient";
        String[] columnNames = {"name", "address","telephone"};
        Object[] values = {patient.getName(), patient.getAddress(), patient.getTelephone()};
        String condition = "patient_id = " + patient.getPatient_id();

        return MainDBHelper.updateRecord(tableName, columnNames, values, condition);
    }

}
