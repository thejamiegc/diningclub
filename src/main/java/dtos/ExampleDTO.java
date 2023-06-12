
package dtos;

import entities.EntityExample;

import java.util.ArrayList;
import java.util.List;


public class ExampleDTO {
    private long id;
    private String str1;
    private String str2;

    // Constructor
    public ExampleDTO(String String1, String String2) {
        this.str1 = String1;
        this.str2 = String2;
    }


    // Method that returns a list of DTOs
    public static List<ExampleDTO> getDtos(List<EntityExample> entityExamples){
        List<ExampleDTO> exampleDTOList = new ArrayList();
        entityExamples.forEach(entityExample->exampleDTOList.add(new ExampleDTO(entityExample)));
        return exampleDTOList;
    }

    // Constructor to check if ID from entity is null.
    public ExampleDTO(EntityExample entityExample) {
        if(entityExample.getId() != null)
            this.id = entityExample.getId();
        this.str1 = entityExample.getString1();
        this.str2 = entityExample.getString2();
    }

    // Getters & Setters
    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

        // To string
    @Override
    public String toString() {
        return "ExampleDTO{" + "id=" + id + ", str1=" + str1 + ", str2=" + str2 + '}';
    }

}
