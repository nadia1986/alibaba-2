/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.Employee;
import com.pooitec1.alibaba2.entity.User;
import com.pooitec1.alibaba2.entity.repository.Conexion;
import com.pooitec1.alibaba2.entity.repository.EmployeeRepository;
import com.pooitec1.alibaba2.entity.repository.UserRepository;
import java.util.List;

/**
 *
 * @author nadia
 */
public class UserService {

    UserRepository repository;
    EmployeeRepository employeeRepo;

    public UserService() {
        this.repository = new UserRepository(Conexion.getEmf());
        this.employeeRepo = new EmployeeRepository(Conexion.getEmf());
    }

    public List<User> getUsers() {
        return repository.findUserEntities();
    }

    public Employee findEmployeeByUser(User user) {
        Employee employeeEncontrado = null;
        for (User ur : this.repository.findUserEntities()) {
            // System.out.println(ur.getNickname());
            if (user.getNickname().equals(ur.getNickname())) {
                employeeEncontrado = employeeRepo.findEmployee(ur.getEmployee().getId());
            }

        }

        return employeeEncontrado;

    }
    
    public Employee buscarEmployeetByUser(User user) {
               Employee employee=null; 
        for (Employee erec : employeeRepo.findEmployeeEntities()) {
            if (erec.getId().equals(user.getId())) {
                employee= user.getEmployee();
            }
        }    
        return employee;        
    }

}
