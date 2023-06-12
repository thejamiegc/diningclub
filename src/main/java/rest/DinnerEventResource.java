package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.DinnerEventDTO;
import facades.DinnerEventFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

//Todo CHANGE  @PATH to match API endpoint
@Path("dinnerevent")
public class DinnerEventResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final DinnerEventFacade FACADE =  DinnerEventFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllDinnerEvent() {
        return GSON.toJson(FACADE.getAllDinnerEventDTOs());
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("create")
    @RolesAllowed("admin")
    public String createNewDinnerEvent(String jsonString) {
        String thisuser = securityContext.getUserPrincipal().getName();
        String time = "";
        String location = "";
        String dish = "";
        Integer pricePrPerson = 0;
        DinnerEventDTO dinnerEventDTO = null;
        try {
            JsonObject json = new Gson().fromJson(jsonString, JsonObject.class);
            time = json.get("time").getAsString();
            location = json.get("location").getAsString();
            dish = json.get("dish").getAsString();
            pricePrPerson = json.get("pricePrPerson").getAsInt();
            dinnerEventDTO = new DinnerEventDTO(time, location, dish, pricePrPerson);
        }catch (Exception e){
            e.printStackTrace();
        }
        DinnerEventDTO returnDTO = FACADE.createDinnerEvent(dinnerEventDTO);
        return GSON.toJson(returnDTO);
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("delete/{id}")
    @RolesAllowed("admin")
    public void deleteDinnerEvent(@PathParam("id") int id) {
        System.out.println("id: " + id);
        FACADE.deleteDinnerEvent(id);
        return ;
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("update/{id}")
    @RolesAllowed("admin")
    public String updateDinnerEvent( @PathParam("id") int id, String jsonString){
        String thisuser = securityContext.getUserPrincipal().getName();
        String time = "";
        String location = "";
        String dish = "";
        Integer pricePrPerson = 0;
        DinnerEventDTO dinnerEventDTO = null;
        try {
            JsonObject json = new Gson().fromJson(jsonString, JsonObject.class);
            time = json.get("time").getAsString();
            location = json.get("location").getAsString();
            dish = json.get("dish").getAsString();
            pricePrPerson = json.get("pricePrPerson").getAsInt();
            dinnerEventDTO = new DinnerEventDTO(time, location, dish, pricePrPerson);
        }catch (Exception e){
            e.printStackTrace();
        }
        dinnerEventDTO.setId((long) id);
        DinnerEventDTO returnDTO = FACADE.updateDinnerEvent(dinnerEventDTO);
        return GSON.toJson(returnDTO);
    }

}
