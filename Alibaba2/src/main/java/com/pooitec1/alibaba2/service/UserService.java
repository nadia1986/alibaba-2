/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.User;
import com.pooitec1.alibaba2.entity.repository.Conexion;
import com.pooitec1.alibaba2.entity.repository.UserRepository;
import java.util.List;

/**
 *
 * @author nadia
 */
public class UserService {
     UserRepository repository;

    public UserService() {
        this.repository = new UserRepository(Conexion.getEmf());
    }

    public List<User> getUsers(){
        return repository.findUserEntities();
    }
    
}
