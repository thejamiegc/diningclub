package rest;

import com.google.gson.Gson;
import dtos.ChuckJokeDTO;
import dtos.DadJokeDTO;
import dtos.UltimateJokeDTO;
import facades.JokeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("joke")
public class JokeResource {

    private EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();
    private JokeFacade FACADE = JokeFacade.getJokeFacade(EMF);

@GET
@Produces({MediaType.APPLICATION_JSON})
@Path("dad")
public Response getDadJoke() throws Exception {
    DadJokeDTO dadJokeDTO = FACADE.createDadJokeDTO(FACADE.getHttpResponse("https://icanhazdadjoke.com/"));
    return Response.ok().entity(dadJokeDTO).build();
}

@GET
@Produces({MediaType.APPLICATION_JSON})
@Path("chuck")
public Response getChuckJoke() throws Exception {
    ChuckJokeDTO chuckJokeDTO = FACADE.createChuckJokeDTO(FACADE.getHttpResponse("https://api.chucknorris.io/jokes/random"));
    return Response.ok().entity(chuckJokeDTO).build();
}

@GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("2jokes")
    public Response getUltimateJoke() throws Exception{
    DadJokeDTO dadJokeDTO = FACADE.createDadJokeDTO(FACADE.getHttpResponse("https://icanhazdadjoke.com/"));
    ChuckJokeDTO chuckJokeDTO = FACADE.createChuckJokeDTO(FACADE.getHttpResponse("https://api.chucknorris.io/jokes/random"));
    UltimateJokeDTO ultimateJokeDTO = new UltimateJokeDTO(dadJokeDTO,chuckJokeDTO);
    return Response.ok().entity(ultimateJokeDTO).build();
}

}
