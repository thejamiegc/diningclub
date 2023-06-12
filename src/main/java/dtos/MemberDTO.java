package dtos;

import entities.Assignment;
import entities.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDTO {
    private String email;
    private String address;
    private Integer phone;
    private Integer birthYear;
    private Integer account;
    private List<String> assignments;

    public MemberDTO() {
    }

    public MemberDTO(String email, String address, Integer phone, Integer birthYear, Integer account) {
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.birthYear = birthYear;
        this.account = account;
    }

    public MemberDTO(String email, String address, Integer phone, Integer birthYear, Integer account, List<String> assignments) {
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.birthYear = birthYear;
        this.account = account;
        if(assignments != null) {
            this.assignments = assignments;
        }
    }

    public MemberDTO(Member member){
        this.email = member.getEmail();
        this.address = member.getAddress();
        this.phone = member.getPhone();
        this.birthYear = member.getBirthYear();
        this.account = member.getAccount();
        if(member.getAssignments().size() != 0) {
            ArrayList<String> temp = new ArrayList<>();
            for (Assignment assignment : member.getAssignments()) {
                temp.add(assignment.getFamilyName());
            }
            this.assignments = temp;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public List<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<String> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", birthYear=" + birthYear +
                ", account=" + account +
                ", assignments=" + assignments +
                '}';
    }
}
