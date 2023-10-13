package Resources;


import DBHelpers.MainDBHelper;
import DBHelpers.ManagementDBHelper;
import DBHelpers.PatientDBHelper;
import Objects.Management;
import Objects.Patient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.List;

@Path("/management")
public class ManagementResource {

//        public static void main(String[] args) {
//            Management management = new Management();
//            management.setId(4);
//            management.setEmail("test@test");
//            management.setName("test256");
//
//       ManagementResource managementResource = new ManagementResource();
//       managementResource.updateUser(4,management);
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(Management management) {
        Gson gson = new GsonBuilder().create();
        management = gson.fromJson(gson.toJson(management), Management.class);
        if (management.getEmail() == null) {
            //                   return "Invalid data";
            return Response.status(401)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Invalid user data")
                    .build();

        }

        ManagementDBHelper managementDBHelper = new ManagementDBHelper();
        boolean success = managementDBHelper.addUser(management);
        if (success) {
            //                         return " registration successful!";
            return Response.status(200)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("User registration successful!")
                    .build();

        } else {
//                       return "Error registering.";
            return Response.status(500)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Error registering user.")
                    .build();
        }
    }

    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        List<Management> users = ManagementDBHelper.getUsers();
        if (users != null && !users.isEmpty()) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(users);
        } else {
            return "No users found";
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        boolean success = ManagementDBHelper.deleteUser(id);
        if (success) {
            return Response.status(Response.Status.OK).entity("User deleted successfully!").build();
        } else {
            return Response.status(500)
                    .type(MediaType.TEXT_PLAIN)
                    .entity("Error deleting User.")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, Management management) {
        // Update the category with the specified ID
        boolean success = ManagementDBHelper.updateUser(management);

        if (success) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
