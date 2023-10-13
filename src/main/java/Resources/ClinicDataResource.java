package Resources;


import DBHelpers.ClinicDataDBHelper;
import DBHelpers.PatientDBHelper;
import Objects.ClinicData;
import Objects.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clinic")
public class ClinicDataResource {

    public static void main(String[] args) {
        ClinicData clinicData = new ClinicData();
        clinicData.setPatient_id(2);
        clinicData.setAge("5");
        clinicData.setSex("test");
        clinicData.setChest_pain("test");
        clinicData.setResting_bp(Double.valueOf(180));
        clinicData.setCholesterol(Double.valueOf(120));
        clinicData.setFasting_bs("test");
        clinicData.setResting_ecg("test");
        clinicData.setMax_hr(Double.valueOf(585458));
        clinicData.setExercise_angina("test");
        clinicData.setOld_peak(Double.valueOf(585458));
        clinicData.setSt_slope("test");
        clinicData.setCreated_on("test");
        clinicData.setCreated_by("test");

        ClinicDataResource clinicDataResource = new ClinicDataResource();
        clinicDataResource.insertClinicData(clinicData);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertClinicData(ClinicData clinicData) {
        Gson gson = new GsonBuilder().create();
        clinicData = gson.fromJson(gson.toJson(clinicData), ClinicData.class);
        if (clinicData.getPatient_id() == null) {
            //                   return "Invalid data";
            return Response.status(401)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Invalid patient data")
                    .build();

        }

        ClinicDataDBHelper clinicDataDBHelper = new ClinicDataDBHelper();
        boolean success = clinicDataDBHelper.addClinicData(clinicData);
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
