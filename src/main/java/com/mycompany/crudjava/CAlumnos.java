/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudjava;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author USUARIO
 */
public class CAlumnos {

    int codigo;
    String nombreAlumnos;
    String apellidoAlumnos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidoAlumnos() {
        return apellidoAlumnos;
    }

    public void setApellidoAlumnos(String apellidoAlumnos) {
        this.apellidoAlumnos = apellidoAlumnos;
    }

    public void InsertarAlumno(JTextField paraNombres, JTextField paraApellidos) {
        setNombreAlumnos(paraNombres.getText());
        setApellidoAlumnos(paraApellidos.getText());

        Cconexion objetoConexion = new Cconexion();
        String consulta = "insert into Alumnos (nombres, apellidos)values(?,?);";
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidoAlumnos());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO se inserto correctamente el alumno, error: " + e.toString());

        }
    }

    public void MostrarAlumnos(JTable paraTablaTotalAlumnos) {
        Cconexion objetoConexion = new Cconexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paraTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        String sql = "";

        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");

        paraTablaTotalAlumnos.setModel(modelo);

        sql = "select * from alumnos;";
        String[] datos = new String[3];
        Statement st;

        try {
            st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);
            }
            paraTablaTotalAlumnos.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "NO se pudo mostrar los resgistro, error:" + e.toString());
        }
    }

    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos) {

        try {
            int fila = paramTablaAlumnos.getSelectedRow();
            if (fila >= 0) {
                paramId.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
                paramNombres.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no se leccionada");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Fila no se leccionada error: " + e.toString());
        }
    }

    public void ModificarAlumno(JTextField paramCodigo, JTextField paraNombres, JTextField paramApellidos) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos(paraNombres.getText());
        setApellidoAlumnos(paramApellidos.getText());
        Cconexion objetoConexion = new Cconexion();
        String consulta = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos =? WHERE alumnos.id=?";

        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidoAlumnos());
            cs.setInt(3, getCodigo());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modifico la seleccion : " + e.toString());
        }
    }

    public void EliminarAlumnos(JTextField paramCodigo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        Cconexion objetoConexion = new Cconexion();
        String consulta = "DELETE FROM Alumnos where alumnos.id=?;";
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "se ha eliminado exitosamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el alumno : " + e.toString());
        }

    }

}
