package utils;


import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    
    // TODO IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // TODO Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("Jamie", "password");
    User admin = new User("admin", "pw#ad");
    Assignment assignment1 = new Assignment("Callan", "2020-12-24", "CallanEmail");
    Assignment assignment2 = new Assignment("Madsen", "2020-12-24", "MadsEmail");
    DinnerEvent dinnerEvent = new DinnerEvent("16:00", "parents hosue", "Pasta", 20);
    DinnerEvent dinnerEvent2 = new DinnerEvent("18:00", "Jamies drom", "Tacos", 45);
    Member member = new Member("userEmail","vejnavn 40", 12345678,1998,1000);
    Member member2 = new Member("adminEmail","vejgade 2", 98765432,1994,1000);




    //  Statmenet retrieves user passwords and checks to make sure they DON'T match the above passwords.
    if(admin.getUserPass().equals("pw#1")||user.getUserPass().equals("pw#ad"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
//    admin.addRole(adminRole);
//    em.persist(userRole);
//    em.persist(adminRole);
    em.persist(user);
//    em.persist(admin);
//    em.persist(assignment1);
//    em.persist(assignment2);
//    em.persist(dinnerEvent);
//    em.persist(dinnerEvent2);
//    em.persist(member);
//    em.persist(member2);

    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("pw#1"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
