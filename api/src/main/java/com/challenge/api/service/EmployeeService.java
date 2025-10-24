package com.challenge.api.service;

import com.challenge.api.exception.EmployeeAlreadyExistsException;
import com.challenge.api.exception.EmployeeNotFoundException;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    // HashMap acts as an in-memory "database" to store employees
    private final Map<UUID, Employee> employees = new HashMap<>();

    // Populate database with mock data
    public EmployeeService() {
        Employee e1 = new EmployeeImpl(
                UUID.randomUUID(), "John", "Doe", 75000, 25, "Developer", "doe@company.com", Instant.now(), null);
        Employee e2 = new EmployeeImpl(
                UUID.randomUUID(), "Jane", "Smith", 60000, 23, "Designer", "smith@company.com", Instant.now(), null);

        employees.put(e1.getUuid(), e1);
        employees.put(e2.getUuid(), e2);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employees.get(uuid);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found with UUID: " + uuid);
        }
        return employee;
    }

    public Employee createEmployee(EmployeeImpl newEmployee) {
        // Generate a new UUID if not provided
        if (newEmployee.getUuid() == null) {
            newEmployee.setUuid(UUID.randomUUID());
        } else if (employees.containsKey(newEmployee.getUuid())) {
            throw new EmployeeAlreadyExistsException("Employee UUID already exists: " + newEmployee.getUuid());
        }

        employees.put(newEmployee.getUuid(), newEmployee);
        return newEmployee;
    }
}
