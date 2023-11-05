package com.example.employeedb.Entity;

public class Department {
    private int id;
    private String location;
    private Employee employee;
    private Boss boss;

    public Department() {
    }

    public Department(String location, Employee employee, Boss boss) {
        this.location = location;
        this.employee = employee;
        this.boss = boss;
    }

    public Department(int id, String location, Employee employee, Boss boss) {
        this.id = id;
        this.location = location;
        this.employee = employee;
        this.boss = boss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", employee=" + employee.getFirstName() +
                ", boss=" + boss.getFirstName() +
                '}';
    }
}
