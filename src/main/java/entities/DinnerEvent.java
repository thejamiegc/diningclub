package entities;

import javax.persistence.*;

@Entity
@Table(name = "dinner_event")
@NamedQuery(name = "dinner_event.deleteAllRows", query = "DELETE from DinnerEvent")
public class DinnerEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time")
    private String time;

    @Column(name = "location")
    private String location;

    @Column(name = "dish")
    private String dish;

    @Column(name = "price_pr_person")
    private Integer pricePrPerson;

    @ManyToOne
    @JoinColumn(name = "assignment_family_name")
    private Assignment assignment;

    public DinnerEvent() {
    }

    public DinnerEvent(String time, String location, String dish, Integer pricePrPerson) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.pricePrPerson = pricePrPerson;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Integer getPricePrPerson() {
        return pricePrPerson;
    }

    public void setPricePrPerson(Integer pricePrPerson) {
        this.pricePrPerson = pricePrPerson;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}