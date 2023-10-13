package Resources;

import DBHelpers.ManagementDBHelper;
import DBHelpers.PatientDBHelper;
import Objects.Management;
import Objects.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/patient")
public class PatientResource {

        public static void main(String[] args) {
        Patient patient = new Patient();
        patient.setPatient_id(2);
        patient.setName("test256");
        patient.setAddress("test");
        patient.setTelephone(585458);


       PatientResource patientResource = new PatientResource();
            patientResource.updatePatient(2, patient);
    }

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

    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPatients() {
        List<Patient> patients = PatientDBHelper.getPatients();
        if (patients != null && !patients.isEmpty()) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(patients);
        } else {
            return "No users found";
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePatient(@PathParam("id") int id) {
        boolean success = PatientDBHelper.deletePatient(id);
        if (success) {
            return Response.status(Response.Status.OK).entity("Patient deleted successfully!").build();
        } else {
            return Response.status(500)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Error deleting patient.")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") int id, Patient patient) {
        // Update the category with the specified ID
        boolean success = PatientDBHelper.updatePatient(patient);

        if (success) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
