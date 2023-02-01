package com.mindex.challenge.data;

import java.time.LocalDate;

public class Compensation {
    private String employeeId;
    private Double salary;
    private LocalDate effectiveDate;

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

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effeciveDate) {
        this.effectiveDate = effeciveDate;
    }
}
