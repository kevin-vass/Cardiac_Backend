package Resources;

import DBHelpers.PatientDBHelper;
import Objects.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/patient")
public class PatientResource {

//        public static void main(String[] args) {
//        Patient patient = new Patient();
//        patient.setName("test");
//        patient.setAddress("test");
//        patient.setTelephone("0555");
//        patient.setBirthdate("2151");
//        patient.setGender("dd");
//
//       PatientResource patientResource = new PatientResource();
//            patientResource.insertPatient(patient);
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPatient(Patient patient) {
        Gson gson = new GsonBuilder().create();
        patient = gson.fromJson(gson.toJson(patient), Patient.class);
        if (patient.getName() == null) {
 //                   return "Invalid data";
            return Response.status(401)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Invalid patient data")
                    .build();

        }

        PatientDBHelper patientDBHelper = new PatientDBHelper();
        boolean success = patientDBHelper.addPatient(patient);
        if (success) {
 //                         return " registration successful!";
            return Response.status(200)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Patient registration successful!")
                    .build();

        } else {
//                       return "Error registering.";
            return Response.status(500)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Error registering patient.")
                    .build();
        }
    }

}
