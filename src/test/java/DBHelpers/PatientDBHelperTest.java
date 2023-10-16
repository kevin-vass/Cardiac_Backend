package DBHelpers;

import Objects.Patient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatientDBHelperTest {

    @Test
    public void testAddPatient() {
        PatientDBHelper dbHelper = new PatientDBHelper();

        // Create a test Patient object
        Patient newPatient = new Patient("John Doe", "123 Main St", 1234567890, "1990-01-01", "Male", "Admin", "2023-10-16 12:00:00");

        assertTrue(dbHelper.addPatient(newPatient));

    }

    @Test
    public void testGetPatients() {
        PatientDBHelper dbHelper = new PatientDBHelper();

        // Fetch all patients
        List<Patient> patients = dbHelper.getPatients();

        assertNotNull(patients);
        assertFalse(patients.isEmpty());
    }

    @Test
    public void testDeletePatient() {
        PatientDBHelper dbHelper = new PatientDBHelper();


        int patientIdToDelete = 3;

        assertTrue(dbHelper.deletePatient(patientIdToDelete));
    }

    @Test
    public void testUpdatePatient() {
        PatientDBHelper dbHelper = new PatientDBHelper();

        int patientIdToUpdate = 2;
        String newName = "Updated Name";
        String newAddress = "Updated Address";
        int newTelephone = 987654;

        Patient updatedPatient = new Patient();
        updatedPatient.setPatient_id(patientIdToUpdate);
        updatedPatient.setName(newName);
        updatedPatient.setAddress(newAddress);
        updatedPatient.setTelephone(newTelephone);

        assertTrue(dbHelper.updatePatient(updatedPatient));
    }
}