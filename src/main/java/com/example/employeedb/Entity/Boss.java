package com.example.employeedb.Entity;

public class Boss {
    private int id;
    private String firstName;
    private String department;
    private boolean isActive;

    public Boss() {
    }

    public Boss(String firstName) {
        this.firstName = firstName;
    }

    public Boss(String firstName, String department, boolean isActive) {
        this.firstName = firstName;
        this.department = department;
        this.isActive = isActive;
    }

    public Boss(int id, String firstName, String department, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.department = department;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", department='" + department + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
