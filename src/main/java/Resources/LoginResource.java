package Resources;

import DBHelpers.EncAndDec;
import DBHelpers.ManagementDBHelper;
import Objects.Management;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.security.NoSuchAlgorithmException;


@Path("/login")
public class LoginResource {

//    public static void main(String[] args) {
//        Management management = new Management();
//        management.setEmail("admin@admin");
//        management.setPassword("admin");
//
//       LoginResource loginResource = new LoginResource();
//       loginResource.loginAdmin(management);
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAdmin(Management management) {
        Gson gson = new GsonBuilder().create();
        management = gson.fromJson(gson.toJson(management), Management.class);

        if (management.getEmail() == null || management.getPassword() == null) {
            return Response.status(400).type(MediaType.APPLICATION_JSON)
                    .entity("{\"message\": \"Email and password cannot be empty\"}")
                    .build();
        }

        ManagementDBHelper managementDBHelper = new ManagementDBHelper();
        Management storedUser = managementDBHelper.getUserByEmail(management.getEmail());

//        try {
//            if (storedUser == null || !storedUser.getPassword().equals(EncAndDec.hashPassword(management.getPassword(), storedUser.getSalt()))) {
//                return Response.status(401).type(MediaType.APPLICATION_JSON)
//                        .entity("{\"message\": \"Invalid email or password\"}")
//                        .build();
//            }
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }

        boolean success = managementDBHelper.logIn(management.getEmail(), management.getPassword());

        if (success) {
            return Response.status(200).type(MediaType.APPLICATION_JSON)
                    .entity("{\"message\": \"Login successful\"}")
                    .build();
        } else {
            return Response.status(401).type(MediaType.APPLICATION_JSON)
                    .entity("{\"message\": \"Invalid email or password\"}")
                    .build();
        }
    }


}
