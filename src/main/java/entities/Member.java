package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NamedQuery(name = "member.deleteAllRows", query = "DELETE from Member")
public class Member {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private Integer phone;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "account")
    private Integer account;

    @ManyToMany
    @JoinTable(name = "member_assignments",
            joinColumns = @JoinColumn(name = "member_email"),
            inverseJoinColumns = @JoinColumn(name = "assignments_family_name"))
    private List<Assignment> assignments = new ArrayList<>();

    @OneToOne(mappedBy = "member", orphanRemoval = true)
    private User user;

    public Member() {
    }

    public Member(String email, String address, Integer phone, Integer birthYear, Integer account) {
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.birthYear = birthYear;
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String id) {
        this.email = id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", birthYear=" + birthYear +
                ", account=" + account +
                ", assignments=" + assignments +
                ", user=" + user +
                '}';
    }
}