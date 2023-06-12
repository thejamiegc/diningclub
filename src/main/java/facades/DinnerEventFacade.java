package facades;

import dtos.DinnerEventDTO;
import entities.Assignment;
import entities.DinnerEvent;
import entities.Member;

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

    public DinnerEventDTO createDinnerEvent(DinnerEventDTO dinnerEventDTO) {
        EntityManager entityManager = emf.createEntityManager();
        DinnerEvent dinnerEvent = new DinnerEvent(dinnerEventDTO.getTime(), dinnerEventDTO.getLocation(), dinnerEventDTO.getDish(),dinnerEventDTO.getPricePrPerson());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dinnerEvent);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return new DinnerEventDTO(dinnerEvent);
    }

    public void deleteDinnerEvent(long id) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            DinnerEvent dinnerEvent = entityManager.find(DinnerEvent.class, id);
            entityManager.remove(dinnerEvent);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public DinnerEventDTO updateDinnerEvent(DinnerEventDTO dinnerEventDTO) {
        EntityManager entityManager = emf.createEntityManager();
        DinnerEvent dinnerEvent = new DinnerEvent(dinnerEventDTO.getTime(), dinnerEventDTO.getLocation(), dinnerEventDTO.getDish(),dinnerEventDTO.getPricePrPerson());
        dinnerEvent.setId(dinnerEventDTO.getId());
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(dinnerEvent);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return new DinnerEventDTO(dinnerEvent);
    }

    public DinnerEventDTO addAssignmentToDinnerEvent(long id, String familyName) {
        EntityManager entityManager = emf.createEntityManager();
        DinnerEvent dinnerEvent = entityManager.find(DinnerEvent.class, id);
        Assignment assignment = entityManager.find(Assignment.class, familyName);
        dinnerEvent.setAssignment(assignment);
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(dinnerEvent);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return new DinnerEventDTO(dinnerEvent);
    }

    public List<DinnerEventDTO> getDinnerEventByMember(String email) {
        EntityManager entityManager = emf.createEntityManager();
        Member member = entityManager.find(Member.class, email);
        TypedQuery<DinnerEvent> query = entityManager.createQuery("SELECT d FROM DinnerEvent d JOIN d.assignment a where a.members = :member", DinnerEvent.class);
        query.setParameter("member", member);
        List<DinnerEvent> dinnerEvents = query.getResultList();
        return DinnerEventDTO.getDtos(dinnerEvents);
    }
}
