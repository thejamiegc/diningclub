package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dtos.DinnerEventDTO;
import dtos.MemberDTO;
import facades.DinnerEventFacade;
import facades.MemberFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

//Todo CHANGE  @PATH to match API endpoint
@Path("member")
public class MemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final MemberFacade FACADE =  MemberFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllDinnerEvent() {
        return GSON.toJson("Hello from member");
    }

    @PATCH
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("assignment/{email}")
    public String addAssignmentToMember(@PathParam("email") String email, String jsonString){
        String assignment = "";
        MemberDTO memberDTO = null;
        try {
            JsonPrimitive json = new Gson().fromJson(jsonString, JsonPrimitive.class);
            assignment = json.getAsString();
            memberDTO = FACADE.addAssignmentToMember(assignment, email);
        }catch (Exception e){
            e.printStackTrace();
        }
        return GSON.toJson(memberDTO);
    }

}
