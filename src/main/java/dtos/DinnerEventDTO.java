package dtos;

import entities.DinnerEvent;

import java.util.ArrayList;
import java.util.List;

public class DinnerEventDTO {
    private Long id;
    private String time;
    private String location;
    private String dish;
    private Integer pricePrPerson;
    private String assignment;

    public DinnerEventDTO() {
    }

    public DinnerEventDTO(String time, String location, String dish, Integer pricePrPerson) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.pricePrPerson = pricePrPerson;
    }

    public DinnerEventDTO(DinnerEvent dinnerEvent) {
        this.id = dinnerEvent.getId();
        this.time = dinnerEvent.getTime();
        this.location = dinnerEvent.getLocation();
        this.dish = dinnerEvent.getDish();
        this.pricePrPerson = dinnerEvent.getPricePrPerson();
        if (dinnerEvent.getAssignment() != null) {
            this.assignment = dinnerEvent.getAssignment().getFamilyName();
        }
    }

    public DinnerEventDTO(String time, String location, String dish, Integer pricePrPerson, String assignment) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.pricePrPerson = pricePrPerson;
        this.assignment = assignment;
    }

    public static List<DinnerEventDTO> getDtos(List<DinnerEvent> dinnerEvents) {
        List<DinnerEventDTO> dinnerEventDTOS = new ArrayList<>();
        for (DinnerEvent dinnerEvent : dinnerEvents) {
            dinnerEventDTOS.add(new DinnerEventDTO(dinnerEvent));
        }
        return dinnerEventDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public Integer getPricePrPerson() {
        return pricePrPerson;
    }

    public void setPricePrPerson(Integer pricePrPerson) {
        this.pricePrPerson = pricePrPerson;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    @Override
    public String toString() {
        return "DinnerEventDTO{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", dish='" + dish + '\'' +
                ", pricePrPerson='" + pricePrPerson + '\'' +
                ", assignment='" + assignment + '\'' +
                '}';
    }
}
