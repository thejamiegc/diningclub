package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.DinnerEventFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo CHANGE  @PATH to match API endpoint
@Path("dinnerevent")
public class DinnerEventResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final DinnerEventFacade FACADE =  DinnerEventFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllDinnerEvent() {
        return GSON.toJson(FACADE.getAllDinnerEventDTOs());
    }


}
