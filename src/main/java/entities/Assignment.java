package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assignment")
@NamedQuery(name = "assignment.deleteAllRows", query = "DELETE from Assignment")
public class Assignment {
    @Id
    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "contact_info")
    private String contactInfo;

    @OneToMany(mappedBy = "assignment", orphanRemoval = true)
    private List<DinnerEvent> dinnerEvents = new ArrayList<>();

    @ManyToMany(mappedBy = "assignments")
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<DinnerEvent> getDinnerEvents() {
        return dinnerEvents;
    }

    public Assignment() {
    }

    public Assignment(String familyName, String createDate, String contactInfo) {
        this.familyName = familyName;
        this.createDate = createDate;
        this.contactInfo = contactInfo;
    }

    public void setDinnerEvents(List<DinnerEvent> dinnerEvents) {
        this.dinnerEvents = dinnerEvents;
    }

    public void addDinnerEvent(DinnerEvent dinnerEvent) {
        dinnerEvents.add(dinnerEvent);
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "familyName='" + familyName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", dinnerEvents=" + dinnerEvents +
                ", members=" + members +
                '}';
    }
}