/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conexiones.ConexionBdMysql;
import Modelo.ModeloTurnos;
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
public class ControladorTurnos 
{
    public boolean Insert(ModeloTurnos modelo)
    {
        boolean resulInser = false;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL;
            SQL = con.prepareStatement("INSERT INTO turnotiempos(codigo, descripcion,"
                    + "tipo_turno,hora_inicio,hora_fin,teorico,tolerancia_despues_entrada,"
                    + "tolerancia_antes_salir,tiempo_breack,limite_turno,gener_extras_entrada,"
                    + "tiempo_minimo_entrada,tiempo_maximo_entrada,genera_extras_salida,"
                    + "tiempo_minimo_salida,tiempo_maximo_salida,redondeo_entrada,sentido_entrada,"
                    + "redondeo_salida,sentido_salida,descanso,sentido_descanso,conceptos,sentido_concepto,"
                    + "hora_inicio_diurno, hora_inicio_nocturno)"
                    + " VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            SQL.setString(1, modelo.getCodigo());
            SQL.setString(2, modelo.getDescripcion());            
            SQL.setString(3, modelo.getTipo_turno());            
            SQL.setString(4, modelo.getHora_inicio());            
            SQL.setString(5, modelo.getHora_fin());            
            SQL.setString(6, modelo.getTeorico());            
            SQL.setString(7, modelo.getTolerancia_despues_entrada());            
            SQL.setString(8, modelo.getTolerancia_antes_salir());            
            SQL.setString(9, modelo.getTiempo_breack());            
            SQL.setString(10, modelo.getLimite_turno());            
            SQL.setString(11, modelo.getGener_extras_entrada());            
            SQL.setString(12, modelo.getTiempo_minimo_entrada());            
            SQL.setString(13, modelo.getTiempo_maximo_entrada());
            SQL.setString(14, modelo.getGenera_extras_salida());
            SQL.setString(15, modelo.getTiempo_minimo_salida());
            SQL.setString(16, modelo.getTiempo_maximo_salida());
            SQL.setString(17, modelo.getRedondeo_entrada());
            SQL.setString(18, modelo.getSentido_entrada());
            SQL.setString(19, modelo.getRedondeo_salida());
            SQL.setString(20, modelo.getSentido_salida());                    
            SQL.setString(21, modelo.getDescanso());        
            SQL.setString(22, modelo.getSentido_descanso());                    
            SQL.setString(23, modelo.getConceptos());        
            SQL.setString(24, modelo.getSentido_concepto());    
            SQL.setString(25, modelo.getHora_inicio_diurno());    
            SQL.setString(26, modelo.getHora_inicio_nocturno());    
            SQL.executeUpdate();
            resulInser = true;
            SQL.close();
            con.close();
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al guardar el turno " + e);
        }
        return resulInser;        
    }
    
    public boolean Update(ModeloTurnos modelo)
    {
        boolean resulInser = false;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL;
            SQL = con.prepareStatement("UPDATE turnotiempos SET codigo = ?, descripcion = ?,"
                    + "tipo_turno = ?,hora_inicio = ?,hora_fin = ?,teorico = ?,tolerancia_despues_entrada = ?,"
                    + "tolerancia_antes_salir = ?,tiempo_breack = ?,limite_turno = ?,gener_extras_entrada = ?,"
                    + "tiempo_minimo_entrada = ?,tiempo_maximo_entrada = ?,genera_extras_salida = ?,"
                    + "tiempo_minimo_salida = ?,tiempo_maximo_salida = ?,redondeo_entrada = ?,sentido_entrada = ?,"
                    + "redondeo_salida = ?,sentido_salida = ?,descanso = ?,sentido_descanso = ?,conceptos = ?,sentido_concepto = ?, "
                    + "hora_inicio_diurno = ?, hora_inicio_nocturno = ? WHERE id = ?");
            SQL.setString(1, modelo.getCodigo());
            SQL.setString(2, modelo.getDescripcion());            
            SQL.setString(3, modelo.getTipo_turno());            
            SQL.setString(4, modelo.getHora_inicio());            
            SQL.setString(5, modelo.getHora_fin());            
            SQL.setString(6, modelo.getTeorico());            
            SQL.setString(7, modelo.getTolerancia_despues_entrada());            
            SQL.setString(8, modelo.getTolerancia_antes_salir());            
            SQL.setString(9, modelo.getTiempo_breack());            
            SQL.setString(10, modelo.getLimite_turno());            
            SQL.setString(11, modelo.getGener_extras_entrada());            
            SQL.setString(12, modelo.getTiempo_minimo_entrada());            
            SQL.setString(13, modelo.getTiempo_maximo_entrada());
            SQL.setString(14, modelo.getGenera_extras_salida());
            SQL.setString(15, modelo.getTiempo_minimo_salida());
            SQL.setString(16, modelo.getTiempo_maximo_salida());
            SQL.setString(17, modelo.getRedondeo_entrada());
            SQL.setString(18, modelo.getSentido_entrada());
            SQL.setString(19, modelo.getRedondeo_salida());
            SQL.setString(20, modelo.getSentido_salida());                    
            SQL.setString(21, modelo.getDescanso());        
            SQL.setString(22, modelo.getSentido_descanso());                    
            SQL.setString(23, modelo.getConceptos());        
            SQL.setString(24, modelo.getSentido_concepto());         
            SQL.setString(25, modelo.getHora_inicio_diurno());    
            SQL.setString(26, modelo.getHora_inicio_nocturno());                
            SQL.setInt(27, modelo.getId());   
            SQL.executeUpdate();
            resulInser = true;
            SQL.close();
            con.close();
        } 
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al actualizar el turno " + e);
        }
        return resulInser;        
    }
    
    public ModeloTurnos Search(String codigo)
    {
        ModeloTurnos modelo = new ModeloTurnos();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL = con.prepareStatement("SELECT id, codigo, descripcion,"
                    + "tipo_turno,hora_inicio,hora_fin,teorico,tolerancia_despues_entrada,"
                    + "tolerancia_antes_salir,tiempo_breack,limite_turno,gener_extras_entrada,"
                    + "tiempo_minimo_entrada,tiempo_maximo_entrada,genera_extras_salida,"
                    + "tiempo_minimo_salida,tiempo_maximo_salida,redondeo_entrada,sentido_entrada,"
                    + "redondeo_salida,sentido_salida,descanso,sentido_descanso,conceptos,sentido_concepto,"
                    + " hora_inicio_diurno, hora_inicio_nocturno FROM turnotiempos WHERE codigo = ?");
            SQL.setString(1, codigo);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("descripcion"));            
                modelo.setTipo_turno(res.getString("tipo_turno"));            
                modelo.setHora_inicio(res.getString("hora_inicio"));            
                modelo.setHora_fin(res.getString("hora_fin"));            
                modelo.setTeorico(res.getString("teorico"));            
                modelo.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));            
                modelo.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));            
                modelo.setTiempo_breack(res.getString("tiempo_breack"));            
                modelo.setLimite_turno(res.getString("limite_turno"));            
                modelo.setGener_extras_entrada(res.getString("gener_extras_entrada"));            
                modelo.setTiempo_minimo_entrada(res.getString("tiempo_minimo_entrada"));            
                modelo.setTiempo_maximo_entrada(res.getString("tiempo_maximo_entrada"));
                modelo.setGenera_extras_salida(res.getString("genera_extras_salida"));
                modelo.setTiempo_minimo_salida(res.getString("tiempo_minimo_salida"));
                modelo.setTiempo_maximo_salida(res.getString("tiempo_maximo_salida"));
                modelo.setRedondeo_entrada(res.getString("redondeo_entrada"));
                modelo.setSentido_entrada(res.getString("sentido_entrada"));
                modelo.setRedondeo_salida(res.getString("redondeo_salida"));
                modelo.setSentido_salida(res.getString("sentido_salida"));                    
                modelo.setDescanso(res.getString("descanso"));        
                modelo.setSentido_descanso(res.getString("sentido_descanso"));                    
                modelo.setConceptos(res.getString("conceptos"));        
                modelo.setSentido_concepto(res.getString("sentido_concepto"));    
                modelo.setHora_inicio_diurno(res.getString("hora_inicio_diurno"));    
                modelo.setHora_inicio_nocturno(res.getString("hora_inicio_nocturno"));    
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
    
    public ModeloTurnos SearchId(int codigo)
    {
        ModeloTurnos modelo = new ModeloTurnos();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL = con.prepareStatement("SELECT id, codigo, descripcion,"
                    + "tipo_turno,hora_inicio,hora_fin,teorico,tolerancia_despues_entrada,"
                    + "tolerancia_antes_salir,tiempo_breack,limite_turno,gener_extras_entrada,"
                    + "tiempo_minimo_entrada,tiempo_maximo_entrada,genera_extras_salida,"
                    + "tiempo_minimo_salida,tiempo_maximo_salida,redondeo_entrada,sentido_entrada,"
                    + "redondeo_salida,sentido_salida,descanso,sentido_descanso,conceptos,sentido_concepto,"
                    + " hora_inicio_diurno, hora_inicio_nocturno, turno_noche FROM turnotiempos WHERE id = ?");
            SQL.setInt(1, codigo);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("descripcion"));            
                modelo.setTipo_turno(res.getString("tipo_turno"));            
                modelo.setHora_inicio(res.getString("hora_inicio"));            
                modelo.setHora_fin(res.getString("hora_fin"));            
                modelo.setTeorico(res.getString("teorico"));            
                modelo.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));            
                modelo.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));            
                modelo.setTiempo_breack(res.getString("tiempo_breack"));            
                modelo.setLimite_turno(res.getString("limite_turno"));            
                modelo.setGener_extras_entrada(res.getString("gener_extras_entrada"));            
                modelo.setTiempo_minimo_entrada(res.getString("tiempo_minimo_entrada"));            
                modelo.setTiempo_maximo_entrada(res.getString("tiempo_maximo_entrada"));
                modelo.setGenera_extras_salida(res.getString("genera_extras_salida"));
                modelo.setTiempo_minimo_salida(res.getString("tiempo_minimo_salida"));
                modelo.setTiempo_maximo_salida(res.getString("tiempo_maximo_salida"));
                modelo.setRedondeo_entrada(res.getString("redondeo_entrada"));
                modelo.setSentido_entrada(res.getString("sentido_entrada"));
                modelo.setRedondeo_salida(res.getString("redondeo_salida"));
                modelo.setSentido_salida(res.getString("sentido_salida"));                    
                modelo.setDescanso(res.getString("descanso"));        
                modelo.setSentido_descanso(res.getString("sentido_descanso"));                    
                modelo.setConceptos(res.getString("conceptos"));        
                modelo.setSentido_concepto(res.getString("sentido_concepto"));    
                modelo.setHora_inicio_diurno(res.getString("hora_inicio_diurno"));    
                modelo.setHora_inicio_nocturno(res.getString("hora_inicio_nocturno"));
                modelo.setTurno_noche(res.getString("turno_noche"));
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
    
    
    public List <ModeloTurnos> Read(String Clave)
    {
        String forSql = "%" + Clave + "%";
        PreparedStatement SQL = null;
        List <ModeloTurnos> modelo = new ArrayList<ModeloTurnos>();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            if (Clave != null)
            {
                SQL = con.prepareStatement("SELECT id, codigo, descripcion,"
                    + "tipo_turno,hora_inicio,hora_fin,teorico,tolerancia_despues_entrada,"
                    + "tolerancia_antes_salir,tiempo_breack,limite_turno,gener_extras_entrada,"
                    + "tiempo_minimo_entrada,tiempo_maximo_entrada,genera_extras_salida,"
                    + "tiempo_minimo_salida,tiempo_maximo_salida,redondeo_entrada,sentido_entrada,"
                    + "redondeo_salida,sentido_salida,descanso,sentido_descanso,conceptos,sentido_concepto,"
                        + " hora_inicio_diurno, hora_inicio_nocturno FROM turnotiempos "
                        + "WHERE codigo LIKE ? OR descripcion LIKE ? ORDER BY descripcion ");
                SQL.setString(1,forSql);
                SQL.setString(2,forSql);
            }
            else
            {
                SQL = con.prepareStatement("SELECT id, codigo, descripcion,"
                    + "tipo_turno,hora_inicio,hora_fin,teorico,tolerancia_despues_entrada,"
                    + "tolerancia_antes_salir,tiempo_breack,limite_turno,gener_extras_entrada,"
                    + "tiempo_minimo_entrada,tiempo_maximo_entrada,genera_extras_salida,"
                    + "tiempo_minimo_salida,tiempo_maximo_salida,redondeo_entrada,sentido_entrada,"
                    + "redondeo_salida,sentido_salida,descanso,sentido_descanso,conceptos,sentido_concepto, "
                        + "hora_inicio_diurno, hora_inicio_nocturno FROM turnotiempos ORDER BY descripcion");
            }
            ResultSet res = SQL.executeQuery();
            while (res.next())
            {
                ModeloTurnos modeloTurnos= new ModeloTurnos();
                modeloTurnos.setId(res.getInt("id"));
                modeloTurnos.setCodigo(res.getString("codigo"));
                modeloTurnos.setDescripcion(res.getString("descripcion"));            
                modeloTurnos.setTipo_turno(res.getString("tipo_turno"));            
                modeloTurnos.setHora_inicio(res.getString("hora_inicio"));            
                modeloTurnos.setHora_fin(res.getString("hora_fin"));            
                modeloTurnos.setTeorico(res.getString("teorico"));            
                modeloTurnos.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));            
                modeloTurnos.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));            
                modeloTurnos.setTiempo_breack(res.getString("tiempo_breack"));            
                modeloTurnos.setLimite_turno(res.getString("limite_turno"));            
                modeloTurnos.setGener_extras_entrada(res.getString("gener_extras_entrada"));            
                modeloTurnos.setTiempo_minimo_entrada(res.getString("tiempo_minimo_entrada"));            
                modeloTurnos.setTiempo_maximo_entrada(res.getString("tiempo_maximo_entrada"));
                modeloTurnos.setGenera_extras_salida(res.getString("genera_extras_salida"));
                modeloTurnos.setTiempo_minimo_salida(res.getString("tiempo_minimo_salida"));
                modeloTurnos.setTiempo_maximo_salida(res.getString("tiempo_maximo_salida"));
                modeloTurnos.setRedondeo_entrada(res.getString("redondeo_entrada"));
                modeloTurnos.setSentido_entrada(res.getString("sentido_entrada"));
                modeloTurnos.setRedondeo_salida(res.getString("redondeo_salida"));
                modeloTurnos.setSentido_salida(res.getString("sentido_salida"));                    
                modeloTurnos.setDescanso(res.getString("descanso"));        
                modeloTurnos.setSentido_descanso(res.getString("sentido_descanso"));                    
                modeloTurnos.setConceptos(res.getString("conceptos"));        
                modeloTurnos.setSentido_concepto(res.getString("sentido_concepto"));    
                modeloTurnos.setHora_inicio_diurno(res.getString("hora_inicio_diurno"));    
                modeloTurnos.setHora_inicio_nocturno(res.getString("hora_inicio_nocturno"));            
                modelo.add(modeloTurnos);
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
    
    public boolean Delete(ModeloTurnos modelo)
    {
        boolean resulDelete  = false;
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL = con.prepareStatement("DELETE FROM turnotiempos WHERE id = ?;");
            SQL.setInt(1, modelo.getId());
            SQL.executeUpdate();            
            resulDelete = true;            
            SQL.close();
            con.close();
        }
        catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al borrar el turno " + e);
        }
        return resulDelete;
    }    

    public ModeloTurnos SearchName(String codigo)
    {
        ModeloTurnos modelo = new ModeloTurnos();
        Connection con;
        ConexionBdMysql conexionBdMysql = new ConexionBdMysql();
        con = conexionBdMysql.abrirConexion();
        try 
        {
            PreparedStatement SQL = con.prepareStatement("SELECT id, codigo, descripcion,"
                    + "tipo_turno,hora_inicio,hora_fin,teorico,tolerancia_despues_entrada,"
                    + "tolerancia_antes_salir,tiempo_breack,limite_turno,gener_extras_entrada,"
                    + "tiempo_minimo_entrada,tiempo_maximo_entrada,genera_extras_salida,"
                    + "tiempo_minimo_salida,tiempo_maximo_salida,redondeo_entrada,sentido_entrada,"
                    + "redondeo_salida,sentido_salida,descanso,sentido_descanso,conceptos,sentido_concepto,"
                    + " hora_inicio_diurno, hora_inicio_nocturno FROM turnotiempos WHERE descripcion = ?");
            SQL.setString(1, codigo);
            ResultSet res = SQL.executeQuery();
            if (res.next())
            {
                modelo.setId(res.getInt("id"));
                modelo.setCodigo(res.getString("codigo"));
                modelo.setDescripcion(res.getString("descripcion"));            
                modelo.setTipo_turno(res.getString("tipo_turno"));            
                modelo.setHora_inicio(res.getString("hora_inicio"));            
                modelo.setHora_fin(res.getString("hora_fin"));            
                modelo.setTeorico(res.getString("teorico"));            
                modelo.setTolerancia_despues_entrada(res.getString("tolerancia_despues_entrada"));            
                modelo.setTolerancia_antes_salir(res.getString("tolerancia_antes_salir"));            
                modelo.setTiempo_breack(res.getString("tiempo_breack"));            
                modelo.setLimite_turno(res.getString("limite_turno"));            
                modelo.setGener_extras_entrada(res.getString("gener_extras_entrada"));            
                modelo.setTiempo_minimo_entrada(res.getString("tiempo_minimo_entrada"));            
                modelo.setTiempo_maximo_entrada(res.getString("tiempo_maximo_entrada"));
                modelo.setGenera_extras_salida(res.getString("genera_extras_salida"));
                modelo.setTiempo_minimo_salida(res.getString("tiempo_minimo_salida"));
                modelo.setTiempo_maximo_salida(res.getString("tiempo_maximo_salida"));
                modelo.setRedondeo_entrada(res.getString("redondeo_entrada"));
                modelo.setSentido_entrada(res.getString("sentido_entrada"));
                modelo.setRedondeo_salida(res.getString("redondeo_salida"));
                modelo.setSentido_salida(res.getString("sentido_salida"));                    
                modelo.setDescanso(res.getString("descanso"));        
                modelo.setSentido_descanso(res.getString("sentido_descanso"));                    
                modelo.setConceptos(res.getString("conceptos"));        
                modelo.setSentido_concepto(res.getString("sentido_concepto"));    
                modelo.setHora_inicio_diurno(res.getString("hora_inicio_diurno"));    
                modelo.setHora_inicio_nocturno(res.getString("hora_inicio_nocturno"));    
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
}
