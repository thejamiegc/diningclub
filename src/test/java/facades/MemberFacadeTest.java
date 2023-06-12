package facades;

import dtos.DinnerEventDTO;
import dtos.MemberDTO;
import entities.Assignment;
import entities.DinnerEvent;
import entities.Member;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MemberFacadeTest {

    private static EntityManagerFactory emf;
    private static MemberFacade facade;
    private Assignment assignment1;
    private Member member1;

    public MemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MemberFacade.getFacade(emf);
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
        assignment1 = new Assignment("assignment1", "description1", "test1");
        member1 = new Member("email1","address1" , 123,1998,100);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("member.deleteAllRows").executeUpdate();
            em.createNamedQuery("assignment.deleteAllRows").executeUpdate();
            em.persist(member1);
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
    public void testAddAssignmentToMember(){
        System.out.println("testAddAssignmentToMember");

        MemberDTO memberDTO = facade.addAssignmentToMember(assignment1.getFamilyName(), member1.getEmail());
        assertEquals(assignment1.getFamilyName(), memberDTO.getAssignments().get(0));
    }

}
