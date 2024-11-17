package tech.reliab.course.bank.service;

import tech.reliab.course.bank.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee get(long employeeId);

    void insert(Employee employee);

    void update(Employee employee);

    void delete(long employeeId);

    void deleteAll();

    List<Employee> getAll();
}
