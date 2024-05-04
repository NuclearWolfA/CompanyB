package com.example.CompanyB.GeneralManagementModule.Service;

import com.example.CompanyB.GeneralManagementModule.Model.Employee;
import com.example.CompanyB.GeneralManagementModule.Model.User;
import com.example.CompanyB.GeneralManagementModule.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Finding by username
    public User findByUsername(String username) {
        return employeeRepository.findByUserName(username);
    }

    //Manual authentication system
    public boolean authenticate(String username, String password) {
        Employee employee = (Employee) findByUsername(username);
        if (employee != null && employee.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    //Save the new response
    public Employee createEmployee(String id, String firstname, String lastname, String email,
                                   String address, Integer mobileno, String username, String password,
                                   String role, double basicSalary, String joiningDate,
                                   String bankAccountNumber) {

        // Check if the username is already taken
        if (employeeRepository.existsByUserName(username)) {
            throw new RuntimeException("Username already exists. Please choose a different username.");
        } else if (employeeRepository.existsById(id)) {
            throw new RuntimeException("ID already exists. Please choose a different ID.");
        } else {


            // Create a new Employee entity
            Employee newEmployee = new Employee();
            newEmployee.setId(id);
            newEmployee.setFirstName(firstname);
            newEmployee.setLastName(lastname);
            newEmployee.setEmail(email);
            newEmployee.setAddress(address);
            newEmployee.setMobileNo(mobileno);
            newEmployee.setUserName(username);
            newEmployee.setPassword(password);
            newEmployee.setRole(role);
            newEmployee.setBasicSalary(basicSalary);
            newEmployee.setJoiningDate(joiningDate);
            newEmployee.setBankAccountNumber(bankAccountNumber);

            // Save the new employee in the database
            return employeeRepository.save(newEmployee);
        }
    }

}