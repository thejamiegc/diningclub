package facades;

import dtos.ChuckJokeDTO;
import dtos.DadJokeDTO;
import dtos.UltimateJokeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

public class JokeFacadeTest {

    private static JokeFacade facade;
    private static EntityManagerFactory entityManagerFactory;

public JokeFacadeTest(){}

    @BeforeAll
    public static void setUpClass(){
    entityManagerFactory = EMF_Creator.createEntityManagerFactoryForTest();
    facade = JokeFacade.getJokeFacade(entityManagerFactory);
    }

    @BeforeEach
    public void setUp() {

    }


    @Test
    void getHttpResponse() throws Exception{
            String joke = facade.getHttpResponse("https://icanhazdadjoke.com/");
        System.out.println("HTTP RESPONSE: "+joke);
        Assertions.assertNotNull(joke);

    }

    @Test
    void createDadJokeDTO() throws Exception{
        DadJokeDTO dadJokeDTO = facade.createDadJokeDTO(facade.getHttpResponse("https://icanhazdadjoke.com/"));
        System.out.println("DadJokeDTO: "+dadJokeDTO);
        Assertions.assertNotNull(dadJokeDTO.getJoke());
    }

    @Test
    void createChuckJokeDTO()throws Exception {
        ChuckJokeDTO chuckJokeDTO = facade.createChuckJokeDTO(facade.getHttpResponse("https://api.chucknorris.io/jokes/random"));
        System.out.println("ChuckJokeDTO: "+chuckJokeDTO);
        Assertions.assertNotNull(chuckJokeDTO.getValue());
    }
}