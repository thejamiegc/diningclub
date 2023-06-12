package facades;

import dtos.ExampleDTO;
import entities.EntityExample;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import utils.EMF_Creator;

/* Rename Class to a relevant name Add add relevant facade methods */
public class FacadeExample {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeExample() {}

        // Method returns an instance of the FacadeExample class
    public static FacadeExample getFacadeExample(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            emf = entityManagerFactory;
            instance = new FacadeExample();
        }
        return instance;
    }

        // Method returns EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

        // Method creates an Entity together with a DTO as a base.
    public ExampleDTO create(ExampleDTO exampleDTO){
        EntityExample entityExample = new EntityExample(exampleDTO.getStr1(), exampleDTO.getStr2());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entityExample);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new ExampleDTO(entityExample);
    }
    //  Method returns a DTO based on entities ID
    public ExampleDTO getById(long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityExample entityExample = entityManager.find(EntityExample.class, id);

        return new ExampleDTO(entityExample);
    }

    // this method is used for testing
    public long getEntityCount(){
        EntityManager em = getEntityManager();
        try{
            long entityCount = (long)em.createQuery("SELECT COUNT(r) FROM EntityExample r").getSingleResult();
            return entityCount;
        }finally{  
            em.close();
        }
    }

        // Method pulls a list of entities from database and returns it as a List of DTOs
    public List<ExampleDTO> getAll(){
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<EntityExample> query = entityManager.createQuery("SELECT r FROM EntityExample r", EntityExample.class);
        List<EntityExample> entityExampleList = query.getResultList();
        return ExampleDTO.getDtos(entityExampleList);
    }
        // Main
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample facadeExample = getFacadeExample(emf);
        facadeExample.getAll().forEach(dto->System.out.println(dto));
    }

}
