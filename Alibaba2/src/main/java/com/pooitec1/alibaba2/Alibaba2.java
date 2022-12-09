/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.pooitec1.alibaba2;

import com.pooitec1.alibaba2.entity.repository.Conexion;
import com.pooitec1.alibaba2.view.JFrameLogin;

/**
 *
 * @author nadia
 */
public class Alibaba2 {

    public static void main(String[] args) {

        new Conexion();
       JFrameLogin app = new JFrameLogin();
        System.out.println("Hello World!");
    }
}
