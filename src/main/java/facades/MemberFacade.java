package facades;

import dtos.MemberDTO;
import entities.Assignment;
import entities.Member;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MemberFacade {
    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MemberFacade() {}

    // Method returns an instance of the FacadeExample class
    public static MemberFacade getFacade(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            emf = entityManagerFactory;
            instance = new MemberFacade();
        }
        return instance;
    }

    // Method returns EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MemberDTO addAssignmentToMember(String familyName, String email) {
        EntityManager entityManager = emf.createEntityManager();
        Member member = entityManager.find(Member.class, email);
        Assignment assignment = entityManager.find(Assignment.class, familyName);
        if(member == null || assignment == null) {
            return null;
        }
        try {
            entityManager.getTransaction().begin();
            member.addAssignment(assignment);
            entityManager.persist(member);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

        return new MemberDTO(member);
    }

    public MemberDTO getMemberByUser(String username) {
        EntityManager entityManager = emf.createEntityManager();
        User user = entityManager.find(User.class, username);

        if(user.getMember() == null) {
            return null;
        }
        return new MemberDTO(user.getMember());
    }


}
