package tech.reliab.course.bank.service.impl;

import tech.reliab.course.bank.database.dao.EmployeeDAO;
import tech.reliab.course.bank.entity.Employee;
import tech.reliab.course.bank.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    public Employee get(long employeeId) {
        return employeeDAO.get(employeeId).orElse(null);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public void insert(Employee employee) {
        employeeDAO.insert(employee);
    }

    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    public void delete(long employeeId) {
        employeeDAO.delete(employeeId);
    }

    @Override
    public void deleteAll() {
        employeeDAO.deleteAll();
    }
}
