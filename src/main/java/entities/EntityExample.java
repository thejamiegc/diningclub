package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "EntityExample.deleteAllRows", query = "DELETE from EntityExample")
public class EntityExample implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        // Null args constructor
    public EntityExample() {
    }  
    
    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo
    private String String1;
    private String String2;

    public EntityExample(String String1, String String2) {
        this.String1 = String1;
        this.String2 = String2;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getString1() {
        return String1;
    }

    public void setString1(String string1) {
        this.String1 = string1;
    }

    public String getString2() {
        return String2;
    }

    public void setString2(String string2) {
        this.String2 = string2;
    }


    // Delete and regenerate toString method as changes are made.
    @Override
    public String toString() {
        return "EntityExample{" +
                "id=" + id +
                ", String1='" + String1 + '\'' +
                ", String2='" + String2 + '\'' +
                '}';
    }
}
