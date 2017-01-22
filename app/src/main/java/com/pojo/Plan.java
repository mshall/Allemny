package com.pojo;

/**
 * Created by elsaidel on 1/22/2017.
 */

public class Plan {
    int planNumber;
    String email;

    public Plan() {
    }

    public Plan(int planNumber, String email) {
        this.planNumber = planNumber;
        this.email = email;
    }

    public int getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(int planNumber) {
        this.planNumber = planNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
