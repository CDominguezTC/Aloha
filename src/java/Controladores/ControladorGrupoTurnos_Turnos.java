/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloGrupoTurnos_Turnos;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ControladorGrupoTurnos_Turnos 
{
    public boolean Insert(ModeloGrupoTurnos_Turnos modelo)
    {
         boolean resulInser = false;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL;
            SQL = con.prepareStatement("INSERT INTO grupohorario_horario(IdGrupoHorario,IdHorario,diaSeman) VALUE (?,?,?)");
            SQL.setInt(1, modelo.getIdGrupoHorario());
            SQL.setInt(2, modelo.getIdHorario());            
            SQL.setString(3, modelo.getDiaSeman());                        
            SQL.executeUpdate();
            resulInser = true;
            SQL.close();
            con.close();
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al guardar la relacion " + e);
        }
        return resulInser;        
    }
    
    public boolean Update(ModeloGrupoTurnos_Turnos modelo)
    {
        boolean resulInser = false;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL;
            SQL = con.prepareStatement("UPDATE grupohorario_horario SET IdGrupoHorario = ?, IdHorario = ?,diaSeman = ? WHERE id = ?;");
            SQL.setInt(1, modelo.getIdGrupoHorario());
            SQL.setInt(2, modelo.getIdHorario());            
            SQL.setString(3, modelo.getDiaSeman());             
            SQL.setInt(4, modelo.getId());
            SQL.executeUpdate();
            resulInser = true;
            SQL.close();
            con.close();
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar la relacion " + e);
        }
        return resulInser;        
    }
    
    public ModeloGrupoTurnos_Turnos Search(String codigo)
    {
        ModeloGrupoTurnos_Turnos modelo = new ModeloGrupoTurnos_Turnos();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL = con.prepareStatement("SELECT id,IdGrupoHorario,IdHorario,diaSeman FROM grupohorario_horario WHERE id = ?");
            SQL.setString(1, codigo);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modelo.setId(res.getInt("id"));
                modelo.setIdGrupoHorario(res.getInt("IdGrupoHorario"));
                modelo.setIdHorario(res.getInt("IdHorario"));
                modelo.setDiaSeman(res.getString("diaSeman"));                             
            }
            res.close();
            SQL.close();
            con.close();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error buscado el dato solicitado " + e);
        }
        return modelo;
    }
    
    public List <ModeloGrupoTurnos_Turnos> Read(String Clave)
    {
        String forSql = "%" + Clave + "%";
        PreparedStatement SQL = null;
        List <ModeloGrupoTurnos_Turnos> modelo = new ArrayList<ModeloGrupoTurnos_Turnos>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            if (Clave != null)
            {
                SQL = con.prepareStatement("SELECT id,IdGrupoHorario,IdHorario,diaSeman FROM grupohorario_horario WHERE IdGrupoHorario LIKE ?");
                SQL.setString(1,forSql);                
            }
            else
            {
                SQL = con.prepareStatement("SELECT id,IdGrupoHorario,IdHorario,diaSeman FROM grupohorario_horario ");
            }
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloGrupoTurnos_Turnos modeloGrupoTurnos= new ModeloGrupoTurnos_Turnos();
                modeloGrupoTurnos.setId(res.getInt("id"));
                modeloGrupoTurnos.setIdGrupoHorario(res.getInt("IdGrupoHorario"));
                modeloGrupoTurnos.setIdHorario(res.getInt("IdHorario"));
                modeloGrupoTurnos.setDiaSeman(res.getString("diaSeman"));                    
                modelo.add(modeloGrupoTurnos);
            }
            res.close();
            SQL.close();
            con.close();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
        }
        return modelo;
    }            
    
    public boolean Delete(ModeloGrupoTurnos_Turnos modelo)
    {
        boolean resulDelete  = false;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL = con.prepareStatement("DELETE FROM grupohorario_horario WHERE IdGrupoHorario = ?;");
            SQL.setInt(1, modelo.getIdGrupoHorario());
            SQL.executeUpdate();            
            resulDelete = true;            
            SQL.close();
            con.close();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al borrar la Dependencia " + e);
        }
        return resulDelete;
    }    

    List<ModeloGrupoTurnos_Turnos> SearchIdGrupoHorario(int idGrupoTurno, String diaMarcacion) 
    {        
        PreparedStatement SQL = null;
        List <ModeloGrupoTurnos_Turnos> modelo = new ArrayList<ModeloGrupoTurnos_Turnos>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            SQL = con.prepareStatement("SELECT id,IdGrupoHorario,IdHorario,diaSeman FROM grupohorario_horario WHERE IdGrupoHorario = ? AND diaSeman = ? ");
            SQL.setInt(1, idGrupoTurno);
            SQL.setString(2, diaMarcacion);
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloGrupoTurnos_Turnos modeloGrupoTurnos= new ModeloGrupoTurnos_Turnos();
                modeloGrupoTurnos.setId(res.getInt("id"));
                modeloGrupoTurnos.setIdGrupoHorario(res.getInt("IdGrupoHorario"));
                modeloGrupoTurnos.setIdHorario(res.getInt("IdHorario"));
                modeloGrupoTurnos.setDiaSeman(res.getString("diaSeman"));                    
                modelo.add(modeloGrupoTurnos);
            }
            res.close();
            SQL.close();
            con.close();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error buscandp el dato solicitado " + e);
        }
        return modelo;
        
    }
}   

