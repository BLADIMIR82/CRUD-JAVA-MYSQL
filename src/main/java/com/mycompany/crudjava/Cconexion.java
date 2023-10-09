/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudjava;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Cconexion {
//variables de conexion a MYSQL//
    Connection conectar = null;
    String usuario = "soporte";
    String contrasenia = "Santy0422+";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection estableceConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            
              //JOptionPane.showMessageDialog(null, "Conexion exitosa a la BD");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al conectarse a la BD, error:" + e.toString());
        }
        return conectar;
    }
;

}
