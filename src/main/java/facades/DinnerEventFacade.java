package facades;

import dtos.DinnerEventDTO;
import entities.DinnerEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class DinnerEventFacade {

    private static DinnerEventFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private DinnerEventFacade() {}

    // Method returns an instance of the FacadeExample class
    public static DinnerEventFacade getFacade(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            emf = entityManagerFactory;
            instance = new DinnerEventFacade();
        }
        return instance;
    }

    // Method returns EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<DinnerEventDTO> getAllDinnerEventDTOs() {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<DinnerEvent> query = entityManager.createQuery("SELECT d FROM DinnerEvent d", DinnerEvent.class);
        List<DinnerEvent> dinnerEvents = query.getResultList();
        return DinnerEventDTO.getDtos(dinnerEvents);
    }
}
