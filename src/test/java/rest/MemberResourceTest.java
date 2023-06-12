package rest;

import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;
import dtos.DinnerEventDTO;
import entities.*;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class MemberResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private Assignment assignment1;
    private Member member1;
    private User user1;
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        assignment1 = new Assignment("fam","cratedate","email");
        member1 = new Member("email","address",57,24,100);
        user1 = new User("username","password");
        user1.setMember(member1);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("dinner_event.deleteAllRows").executeUpdate();
            em.createNamedQuery("assignment.deleteAllRows").executeUpdate();
            em.createNamedQuery("roles.deleteAllRows").executeUpdate();
            em.createNamedQuery("users.deleteAllRows").executeUpdate();
            em.createNamedQuery("member.deleteAllRows").executeUpdate();
            em.persist(assignment1);
            em.persist(member1);
            em.persist(user1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static String securityToken;


    @Test
    public void testServerIsUp() {
        given().when().get("/member").then().statusCode(200);
    }

    //This test assumes the database contains two rows

//    @Test
//    public void testAddAssignmentToMember(){
//        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(assignment1);
//
//        given()
//                .contentType("application/json")
//                .accept("application/json")
//                .body(json)
//                .when()
//                .post("/member/assignment/"+member1.getEmail())
//                .then()
//                .statusCode(200)
//                .body("assignments[0]", equalTo("fam"));
//    }

    @Test
    public void testGetMemberByUser(){
        given()
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("/member/"+user1.getUserName())
                .then()
                .statusCode(200)
                .body("email", equalTo("email"));
    }





}
