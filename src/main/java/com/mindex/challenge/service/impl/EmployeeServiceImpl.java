package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.utils.EmployeeServiceUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeServiceUtils employeeUtils;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     * Retrive Reporting struture for Employee by Employee ID
     * @param - id - Employee ID
     * @return - Reporting struture for the Employee
     */
    @Override
    public ReportingStructure getReportingStructure(String id) {
        LOG.debug("Retrieveing employee reporting structure with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        
        if(employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        int numberOfReports = employeeUtils.calculateNumberOfReports(employee);

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(numberOfReports);

        return reportingStructure;
    }
}
