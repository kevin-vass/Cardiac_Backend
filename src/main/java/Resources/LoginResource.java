package Resources;

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
           return Response.status(400).type(MediaType.TEXT_PLAIN).entity("Email and password cannot be empty").build();
           // return "empty";
        }

        ManagementDBHelper managementDBHelper = new ManagementDBHelper();
        Management storedUser = managementDBHelper.getUserByEmail(management.getEmail());

        if (storedUser == null) {
            return Response.status(401).type(MediaType.TEXT_PLAIN).entity("Invalid email or password").build();
            // return "invalid";
        }

        if (!storedUser.getPassword().equals(management.getPassword())) {
            return Response.status(401).type(MediaType.TEXT_PLAIN).entity("Invalid email or password").build();
           // return "invalid";
        }

        boolean success = managementDBHelper.logIn(management.getEmail(), management.getPassword());

        if (success) {
            // Generate a unique session token for the logged-in user
//            String sessionToken = UUID.randomUUID().toString();

            // Set session token cookie with a 30-minute expiration time
//            LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30);
//            NewCookie sessionCookie = new NewCookie("session_token", sessionToken, "/", "", "", 1800, false);

            // Insert session token into database
//            SessionDBHelper sessionDBHelper = new SessionDBHelper();
//            boolean tokenSuccess = sessionDBHelper.createSessionToken(admin.getEmail(), sessionToken, expiresAt);

//            if (tokenSuccess) {
//                System.out.println("Admin login successful!");
//                Response.ResponseBuilder builder = Response.ok("Customer successfully logged in")
//                        .cookie(sessionCookie)
//                        .header("session_token", sessionToken);
//                return builder.build();
//                // return "success";
//            } else {
//                System.out.println("Error inserting session token.");
//                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error inserting session token.").build();
//                // return "token error";
//            }
        } else {
            return Response.status(401).type(MediaType.TEXT_PLAIN).entity("Invalid email or password").build();
           //   return "invalid";
        }

        return null;
    }

}
