package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {
    private String employeeId;
    private Double salary;
    private Date effectiveDate;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effeciveDate) {
        this.effectiveDate = effeciveDate;
    }
}
