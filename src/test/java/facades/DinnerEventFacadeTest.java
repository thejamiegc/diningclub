package facades;

import dtos.DinnerEventDTO;
import entities.Assignment;
import entities.DinnerEvent;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class DinnerEventFacadeTest {

    private static EntityManagerFactory emf;
    private static DinnerEventFacade facade;
    private DinnerEvent dinnerEvent1;
    private Assignment assignment1;

    public DinnerEventFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = DinnerEventFacade.getFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        dinnerEvent1 = new DinnerEvent("time1","location","dish1",10);
        DinnerEvent dinnerEvent2 = new DinnerEvent("time2","location","dish2",20);
        DinnerEvent dinnerEvent3 = new DinnerEvent("time3","location","dish3",30);
        assignment1 = new Assignment("assignment1", "description1", "test1");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("dinner_event.deleteAllRows").executeUpdate();
            em.createNamedQuery("assignment.deleteAllRows").executeUpdate();

            em.persist(dinnerEvent1);
            em.persist(dinnerEvent2);
            em.persist(dinnerEvent3);
            em.persist(assignment1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

   @Test
    public void testGetAllDinnerEventDTOs(){
       System.out.println("getAllDinnerEventDTOs");
        assertEquals(3, facade.getAllDinnerEventDTOs().size());
   }

   @Test
    public void testCreateDinnerEvent() {
       System.out.println("createDinnerEvent");
       DinnerEvent dinnerEvent = new DinnerEvent("time4", "location", "dish4", 40);
       DinnerEventDTO expected = new DinnerEventDTO(dinnerEvent);
       DinnerEventDTO actual = facade.createDinnerEvent(expected);
       assertEquals(expected.getDish(), actual.getDish());
   }

   @Test
    public void testDeleteDinnerEvent(){
       System.out.println("deleteDinnerEvent");
       int beforeDelete = facade.getAllDinnerEventDTOs().size();
       facade.deleteDinnerEvent(dinnerEvent1.getId());
       int afterDelete = facade.getAllDinnerEventDTOs().size();
       assertEquals(beforeDelete-1, afterDelete);
   }

   @Test
    public void testUpdateDinnerEvent(){
         System.out.println("updateDinnerEvent");
         DinnerEventDTO expected = new DinnerEventDTO(dinnerEvent1);
         expected.setDish("newDish");
         DinnerEventDTO actual = facade.updateDinnerEvent(expected);
         assertEquals(expected.getDish(), actual.getDish());
   }

   @Test
    public void testAddAssignmentToDinnerEvent(){
         System.out.println("addAssignmentToDinnerEvent");
         DinnerEventDTO expected = new DinnerEventDTO(dinnerEvent1);
         DinnerEventDTO actual = facade.addAssignmentToDinnerEvent(expected.getId(), assignment1.getFamilyName());
         assertEquals(assignment1.getFamilyName(), actual.getAssignment());
   }
}
