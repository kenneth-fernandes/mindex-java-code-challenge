package com.mindex.challenge.utils.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.utils.EmployeeServiceUtils;

@Component
public class EmployeeServiceUtilsImpl implements EmployeeServiceUtils {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public int calculateNumberOfReports(final Employee employee) {
        List<Employee> directReports = employee.getDirectReports();
        
        if(directReports == null) {
            return 0;
        }
        int numberOfReports = calculateNumberOfReportsByBFS(employee.getEmployeeId());

        return numberOfReports;
    }

    private int calculateNumberOfReportsByBFS(final String employeeId) {
        int count = -1;

        Queue<String> queue = new LinkedList<>();
        queue.add(employeeId);

        while(!queue.isEmpty()) {
            int currentSize = queue.size();
            for(int i =0; i < currentSize; i++) {
                Employee currentEmployee = employeeRepository.findByEmployeeId(queue.poll());
                List<Employee> currentDirectReports = currentEmployee.getDirectReports();
                if(currentDirectReports != null) {
                    queue.addAll(
                        currentDirectReports
                        .stream()
                        .map(Employee::getEmployeeId)
                        .collect(Collectors.toList()));
                }
                count++;
            }
        }
        return count;
    }
}
