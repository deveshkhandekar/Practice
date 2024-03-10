package com.test.streamApi.helper;

public class Employee {
    private String department;
    private Integer empID;
    private String name;
    private String address;
    private Double salary;


    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Integer getEmpID() {
        return empID;
    }
    public void setEmpID(Integer empID) {
        this.empID = empID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Employee(String department, Integer empID, String name, String address, Double salary) {
        super();
        this.department = department;
        this.empID = empID;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }



}

