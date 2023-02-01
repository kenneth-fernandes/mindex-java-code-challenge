package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeService {

    /**
     * Create and Insert Employee data
     * @param employee - Employee data from API request
     * @return - Created and inserted Employee data
     */
    Employee create(Employee employee);

    /**
     * Retrive Employee by Employee ID
     * @param id - Employee ID from API request
     * @return - Employee data
     */
    Employee read(String id);

    /**
     * Update Employee data
     * @param employee - Employee data from API request
     * @return - Employee data
     */
    Employee update(Employee employee);

    /**
     * Retrive Reporting struture for Employee by Employee ID
     * @param id - Employee ID from API request
     * @return - Reporting struture for the Employee
     */
    ReportingStructure getReportingStructure(String id);

    /**
     * Create and Insert Employee Compensation
     * @param compensation - Employee Compensation from API request
     */
    Compensation createCompensation(Compensation compensation);

    /**
     * Retrieve Employee Compensation
     * @param id - Employee ID
     * @return - Employee Compensation
     */
    Compensation getCompensation(String id);
}
