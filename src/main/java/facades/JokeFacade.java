package facades;

import com.google.gson.Gson;
import dtos.ChuckJokeDTO;
import dtos.DadJokeDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory entityManagerFactory;

    private JokeFacade(){}

    // Method returns an instance of the FacadeExample class
    public static JokeFacade getJokeFacade(EntityManagerFactory entityManagerFactory1){
        if( instance == null) {
            entityManagerFactory = entityManagerFactory1;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();

    public String getHttpResponse(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public DadJokeDTO createDadJokeDTO(String input){
        return GSON.fromJson(input,DadJokeDTO.class);
    }
    public ChuckJokeDTO createChuckJokeDTO(String input){
        return GSON.fromJson(input,ChuckJokeDTO.class);
    }

}
