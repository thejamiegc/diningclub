package rest;

import com.google.gson.Gson;
import dtos.DinnerEventDTO;
import entities.Assignment;
import entities.DinnerEvent;
import entities.Role;
import entities.User;
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

public class DinnerEventResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private DinnerEvent dinnerEvent1;
    private Assignment assignment1;
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
        dinnerEvent1 = new DinnerEvent("time1","location","dish1",10);
        DinnerEvent dinnerEvent2 = new DinnerEvent("time2","location","dish2",20);
        DinnerEvent dinnerEvent3 = new DinnerEvent("time3","location","dish3",30);
        assignment1 = new Assignment("fam","cratedate","email");
        Role adminRole = new Role("admin");
        User admin = new User("admin", "test");
        admin.addRole(adminRole);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("dinner_event.deleteAllRows").executeUpdate();
            em.createNamedQuery("assignment.deleteAllRows").executeUpdate();
            em.createNamedQuery("roles.deleteAllRows").executeUpdate();
            em.createNamedQuery("users.deleteAllRows").executeUpdate();
            em.persist(dinnerEvent1);
            em.persist(dinnerEvent2);
            em.persist(dinnerEvent3);
            em.persist(assignment1);
            em.persist(adminRole);
            em.persist(admin);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static String securityToken;

    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                //.when().post("/api/login")
                .when().post("/login")
                .then()
                .extract().path("token");
        //System.out.println("TOKEN ---> " + securityToken);
    }

    private void logOut() {
        securityToken = null;
    }
    @Test
    public void testServerIsUp() {
        given().when().get("/dinnerevent").then().statusCode(200);
    }

    //This test assumes the database contains two rows
    @Test
    public void testGetAll() throws Exception {
        given()
                .contentType("application/json")
                .get("/dinnerevent").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("size()", equalTo(3));
    }

    @Test
    public void testCreateNewDinnerEvent() throws Exception{
        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
        DinnerEventDTO dinnerEventDTO = new DinnerEventDTO("time4","location","dish4",40);
        String json = gson.toJson(dinnerEventDTO);
        login("admin", "test");
        given()
                .contentType("application/json")
                .body(json)
                .header("x-access-token", securityToken)
                .post("/dinnerevent/create").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("time", equalTo("time4"))
                .body("location", equalTo("location"))
                .body("dish", equalTo("dish4"))
                .body("pricePrPerson", equalTo(40));

    }

    @Test
    public void testDeleteDinnerEvent(){
        System.out.println("DinnerEvent1 id: " + dinnerEvent1.getId());
        login("admin", "test");
        given()
                .contentType("application/json")
                .header("x-access-token", securityToken)
                .delete("/dinnerevent/delete/" + dinnerEvent1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT_204.getStatusCode());

    }

    @Test
    public void testupdateDinnerEvent(){
        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
        DinnerEventDTO dinnerEventDTO = new DinnerEventDTO("time4","location","dish4",40);
        String json = gson.toJson(dinnerEventDTO);
        login("admin", "test");
        given()
                .contentType("application/json")
                .body(json)
                .header("x-access-token", securityToken)
                .put("/dinnerevent/update/" + dinnerEvent1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("time", equalTo("time4"))
                .body("location", equalTo("location"))
                .body("dish", equalTo("dish4"))
                .body("pricePrPerson", equalTo(40));
    }

    @Test
    public void testAddAssignmentToDinnerEvent(){
        Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
        String json = gson.toJson(assignment1.getFamilyName());
        given()
                .contentType("application/json")
                .body(json)
                .patch("/dinnerevent/assignment/" + dinnerEvent1.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("time", equalTo("time1"))
                .body("location", equalTo("location"))
                .body("dish", equalTo("dish1"))
                .body("pricePrPerson", equalTo(10))
                .body("assignment", equalTo(assignment1.getFamilyName()));
    }
}
