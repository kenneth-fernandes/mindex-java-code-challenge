package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.CompensationNotFoundException;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.exception.InvalidInputParameters;
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
    private CompensationRepository compensationRepository;
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
        LOG.debug("Retrieving employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new EmployeeNotFoundException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    @Override
    public ReportingStructure getReportingStructure(String employeeId) {
        LOG.debug("Retrieveing employee reporting structure with employeeId [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if(employee == null) {
            throw new EmployeeNotFoundException("Invalid employeeId: " + employeeId);
        }
        int numberOfReports = employeeUtils.calculateNumberOfReports(employee);

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(numberOfReports);

        return reportingStructure;
    }

    @Override
    public Compensation createCompensation(Compensation compensation) {
        if(compensation.getEmployeeId() == null 
            || compensation.getEmployeeId().equals("") 
            || compensation.getSalary() < 0.0 
            || compensation.getEffectiveDate() == null) {
                throw new InvalidInputParameters("Invalid input parameters entered " +  compensation.getEmployeeId() + " " + compensation.getSalary() + " " + compensation.getEffectiveDate() );
        }
        String employeeId = compensation.getEmployeeId();
        LOG.debug("Creating employee compensation structure with id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if(employee == null) {
            throw new EmployeeNotFoundException("Invalid employeeId: " + employeeId);
        }
        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation getCompensation(String employeeId) {
        LOG.debug("Retrieveing employee compensation with employeeId [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if(employee == null) {
            throw new EmployeeNotFoundException("Invalid employeeId: " + employeeId);
        }

        Compensation compensation = compensationRepository.findByEmployeeId(employeeId);
        if(compensation == null) {
            throw new CompensationNotFoundException("Could not find compensation for employeeId " + employeeId);
        }

        return compensation;
    }
}
